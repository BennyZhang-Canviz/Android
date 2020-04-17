演示如何使用ViewModel和SharedPreferences来保存数据。

自定义ViewModel可以继承自AndroidViewModel，这里面自带了Context.

app bundle文件设置如下：
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.3"


   dataBinding {
        enabled true
    }

   ....其他设置


}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.core:core-ktx:1.2.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    要添加下面的这个引用
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
}
