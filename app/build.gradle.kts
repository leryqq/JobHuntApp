import org.jetbrains.kotlin.resolve.sam.SamConstructorDescriptorKindExclude.excludes

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.jobhuntapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jobhuntapp"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += listOf(
                "META-INF/LICENSE-notice.md",
                "META-INF/LICENSE.md"
            )
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:19.0.0")
    implementation("androidx.databinding:databinding-runtime:8.5.2")
    implementation("org.junit.jupiter:junit-jupiter:5.10.1")
    implementation("androidx.test.ext:junit-ktx:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    //navigation/fragments
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //map yandex
    implementation ("com.yandex.android:maps.mobile:4.7.0-lite")
    //viewmodel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    //livedata
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
    //adapterdelegates
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl:4.3.2")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //mockwebserver
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")
    //junit
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    //espresso
    testImplementation ("androidx.test.espresso:espresso-core:3.6.1")
    //dagger2
    implementation ("com.google.dagger:dagger:2.28.3")
}