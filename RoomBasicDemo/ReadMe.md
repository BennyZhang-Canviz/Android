演示如何使用Room.
注意点：
1. build.gradle中要引用Kotlin相关的：
    def room_version = "2.2.3"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:2.2.3"

2. 如何定义autoGenerate主键：
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