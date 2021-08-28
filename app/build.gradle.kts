plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions{
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }

    android.buildFeatures.viewBinding = true
}


dependencies {
    implementation(project(":core"))
    implementation(kotlin("stdlib-jdk7"))
    implementation(SupportLibs.ANDROIDX_APPCOMPAT)
    implementation(SupportLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    /* implementation(SupportLibs.CARD_VIEW)
     implementation(SupportLibs.RECYCLER_VIEW)*/
    implementation(SupportLibs.ANDROIDX_CORE_KTX)
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.android.material:material:1.4.0")

    testImplementation(TestingLib.JUNIT)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.ESPRESSO_CORE)

    /**
     * DI
     * */
    implementation(DAGGER.DAGGER)
    implementation(DAGGER.DAGGER_ANDROID_SUPPORT)
    implementation(DAGGER.DAGGER_ANDROID)
    kapt(DAGGER.DAGGER_ANNOTATION)
    kapt(DAGGER.DAGGER_KAPT)

    // OkHttp, Retrofit
    implementation(RETROFIT.RETROFIT)
    implementation("com.google.code.gson:gson:2.8.6")


    //RX
    implementation(RX.RXANDROID)
    implementation(RX.RXJAVA)
    implementation(RX.RXKOTLIN)


    //Paging
    implementation(PAGING.PAGING_RUNTIME)
    implementation(PAGING.PAGING_COMMON)
    implementation(PAGING.PAGING_RX)


    //NavComp
    implementation(NAVIGATION.NAVIGATION_RUNTIME)
    implementation(NAVIGATION.NAVIGATION_UI)
    implementation(NAVIGATION.NAVIGATION_FRAGMENT)

    //Coil (Image Loading)
    implementation(COIL.COIL)

}
