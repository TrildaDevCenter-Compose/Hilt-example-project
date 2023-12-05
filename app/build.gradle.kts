plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    //id("com.google.protobuf")
}

android {
    namespace = "com.tomasrepcik.hiltexample"
    compileSdk  = 34

    defaultConfig {
        applicationId = "com.tomasrepcik.hiltexample"
        minSdk  = 24
        targetSdk  = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type. Make sure to use a build
            // variant with `isDebuggable=false`.
            isMinifyEnabled = true

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true

            // Includes the default ProGuard rules files that are packaged with
            // the Android Gradle plugin. To learn more, go to the section about
            // R8 configuration files.

            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
        getByName("debug") {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type. Make sure to use a build
            // variant with `isDebuggable=false`.
            isMinifyEnabled = false

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = false

            // Includes the default ProGuard rules files that are packaged with
            // the Android Gradle plugin. To learn more, go to the section about
            // R8 configuration files.

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packaging.resources {
        // The Rome library JARs embed some internal utils libraries in nested JARs.
        // We don't need them so we exclude them in the final package.
        excludes += "/*.jar"

        // Multiple dependency bring these files in. Exclude them to enable
        // our test APK to build (has no effect on our AARs)
        excludes += "/META-INF/AL2.0"
        excludes += "/META-INF/LGPL2.1"
    }
}

dependencies {

    // work manager (workers)
    val workVersion = "2.8.1"

    // this solves on recenbt Android  tthe crash signaled in Logcat with
    // Targeting S+ (version 31 and above) requires that one of FLAG_IMMUTABLE or FLAG_MUTABLE be specified
    // when creating a PendingIntent. Strongly consider using FLAG_IMMUTABLE, only use FLAG_MUTABLE
    // if some functionality depends on the PendingIntent being mutable, e.g. if it needs to be used
    // with inline replies or bubbles.
    implementation("androidx.work:work-runtime-ktx:$workVersion")

    androidTestImplementation("androidx.work:work-testing:$workVersion")
    implementation ("androidx.work:work-multiprocess:$workVersion")

    val kotlin_version = "1.9.20"

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:${kotlin_version}")
    implementation ("androidx.core:core-ktx:1.10.1")

    // compose bom
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))

    // android core
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    //activity and compose
    implementation ("androidx.activity:activity-compose")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))

    implementation("androidx.compose.foundation:foundation")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-graphics-android")

    // tooling for compose previews in android studio
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Navigation and compose
    implementation("androidx.navigation:navigation-compose")

    // SPLASH SCREEN
    implementation( "androidx.core:core-splashscreen:1.0.1")

    // Material
    implementation ("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-core-android")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material:material-icons-extended-android")

    // Material 3
    implementation("androidx.compose.material3:material3:1.2.0-alpha09")
    implementation("androidx.compose.material3:material3:1.2.0-alpha09")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0-alpha09")

    // hilt
    ksp ("com.google.dagger:dagger-compiler:2.48.1")
    ksp ("com.google.dagger:hilt-compiler:2.48.1")
    implementation ("com.google.dagger:hilt-android:2.48.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation ("androidx.hilt:hilt-work:1.1.0")




    // Test
    testImplementation("junit:junit:4.13.2")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.00"))
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0-alpha07")
}