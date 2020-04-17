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
    
    要添加下面的这个引用
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
}
