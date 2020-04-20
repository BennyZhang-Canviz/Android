package com.example.room.Room

import androidx.room.*


@Entity
data class User(
    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "age")
    val age: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

@Dao
interface UserDao{
    @Query("select * from User")
    fun getAll(): List<User>

    @Query("select * from User where id in (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("select * from user where first_name like :firstName and last_name like :lastName limit 1")
    fun findByName(firstName: String, lastName: String): List<User>

    @Delete()
    fun delete(user: User)

    @Query("delete  from user")
    fun deleteAll()

    @Insert()
    fun insert(user:User)

    @Update()
    fun update(user:User)
}

@Database(entities = [User::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}