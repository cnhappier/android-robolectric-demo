# android-robolectric-demo

可测性是一个衡量软件质量的不错的标准。从Android Studio 1.3之后，对单元测试有了不错的支持。

这里示范一个带库library的app的例子，master分支现在同步到Robolectric 3.0版本。

##项目搭建

总的项目build.gradle
```
buildscript {
    repositories {
        maven{ url 'http://maven.oschina.net/content/groups/public/'}
//        jcenter()
    }
   
    dependencies {
        classpath "com.android.tools.build:gradle:1.2.3"
    }
}

allprojects {
    repositories {
        maven{ url 'http://maven.oschina.net/content/groups/public/'}
//        jcenter()
    }
}

ext.groups = [
        internal: 'com.example.library'
]

ext.versions = [
        internal_sdk: '0.1.0-SNAPSHOT',
        robolectric : '3.0'
]

ext.libraries = [
        //test
        junit      : "junit:junit:4.11",
        robolectric: "org.robolectric:robolectric:${ext.versions.robolectric}",
        mockito    : "org.mockito:mockito-core:1.9.5",
]
```

library, build.gradle
```
apply plugin: 'com.android.library'

android {
    compileSdkVersion 19
    buildToolsVersion "19.1.0"

    publishNonDefault true

    defaultConfig {
        minSdkVersion 8
        targetSdkVersion 19
    }

    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    testCompile libraries.junit
    testCompile libraries.robolectric
    testCompile libraries.mockito
    compile fileTree(dir: 'libs', include: ['*.jar'])
}
```

app, build.gradle
```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 19
    buildToolsVersion "19.1.0"

    defaultConfig {
        minSdkVersion 8
        targetSdkVersion 19
    }

    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    releaseCompile project(path: ':library', configuration: 'release')
    debugCompile project(path: ':library', configuration: 'debug')
}
```

配置完成后，在Android Studio的左边栏的下方，选择Build Variants，选择Unit Tests，就可以进行单元测试了。
![Build Variants](http://i.imgur.com/sStdOnI.png)

目录src\test\java是标准的maven项目样式。

##Trouble shooting

###在Android Stuido下，遇到这样的异常“java.io.FileNotFoundException: build\intermediates\bundles\debug\AndroidManifest.xml”

我在windows下使用AS 1.5 preview遇到了同样的问题，解决方法和官网上介绍Mac和Linux上的设置working directory一样。

This can be accomplished by editing the run configurations, Defaults -> JUnit and changing the working directory value to $MODULE_DIR$.
![Working Directory](http://robolectric.org/images/android-studio-configure-defaults-4bf48402.png)

##测试
github项目

···
gradle testDebug
···


##参考
http://robolectric.org/getting-started/

http://guides.codepath.com/android/Unit-Testing-with-Robolectric

https://www.bignerdranch.com/blog/all-in-together-android-studio-gradle-and-robolectric/

https://www.bignerdranch.com/blog/triumph-android-studio-1-2-sneaks-in-full-testing-support/
