plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.notepad"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.notepad"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Room (for Databases)
    //noinspection UseTomlInstead
    implementation ("androidx.room:room-runtime:2.6.1")
    //noinspection UseTomlInstead
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    //Recyclerview
    //noinspection UseTomlInstead
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    //noinspection UseTomlInstead
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    //noinspection UseTomlInstead
    implementation("com.intuit.ssp:ssp-android:1.0.6")
    //noinspection UseTomlInstead
    implementation("com.google.android.material:material:1.12.0")
    //noinspection UseTomlInstead
    implementation("com.makeramen:roundedimageview:2.3.0")

}