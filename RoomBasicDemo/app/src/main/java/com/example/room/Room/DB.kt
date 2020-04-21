package com.example.room.Room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Entity
data class User(
    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "age")
    var age: Int,

    @ColumnInfo(name = "age_available")
    var showAge: Boolean
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

@Dao
interface UserDao{
    @Query("select * from User")
    fun getAll(): LiveData<List<User>>

    @Query("select * from User where id in (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("select * from user where first_name like :name or last_name like :name ")
    fun findByName(name: String ): LiveData<List<User>>

    @Delete()
    fun delete(user: User)

    @Query("delete  from user")
    fun deleteAll()

    @Insert
    fun insert(user: Array<out User>)

    @Update
    fun update(user:Array<out User>)
}

@Database(entities = [User::class],version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    //Singleton
    companion object {

        fun getInstance(context: Context): AppDatabase {
            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                            "PRIMARY KEY(`id`))")
                }
            }
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "appDB")
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }

    abstract fun userDao(): UserDao


}