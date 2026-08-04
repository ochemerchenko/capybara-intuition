import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

val keystoreProperties = Properties()
val keystorePropertiesFile = rootProject.file("key.properties")

if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(keystorePropertiesFile.inputStream())
}

android {
    namespace = "com.capybara.intuition"
    compileSdk = 35

    signingConfigs {
        create("release") {
            if (keystorePropertiesFile.exists()) {
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            }
        }
    }

    defaultConfig {
        applicationId = "com.capybara.intuition"
        minSdk = 24
        targetSdk = 35

        versionCode = 4
        versionName = "0.19"
    }

    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")

            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        isCoreLibraryDesugaringEnabled = true
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}

dependencies {

    // --- Core ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // --- Lifecycle ---
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)

    // --- Navigation ---
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // --- Room ---
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // --- Compose ---
    implementation(platform(libs.compose.bom))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")


    // --- Markdown ---
    implementation(libs.richtext.commonmark)
    implementation(libs.richtext.material3)

    // --- Desugare ---
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // --- Vico --- It's a magic shit
    // implementation("com.patrykandpatrick.vico:core:1.13.1")
    // implementation("com.patrykandpatrick.vico:compose:1.13.1")

    //implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.compose.material:material-icons-extended")

    implementation("androidx.navigation:navigation-compose:2.8.0")

    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("junit:junit:4.13.2")

    // --- Datastore ---
    implementation(libs.androidx.datastore.preferences)

    // --- Google Billing System ---
    implementation("com.android.billingclient:billing-ktx:7.1.1")


    // --- Google Firebase ---
    implementation(platform(libs.firebase.bom))

    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    implementation(libs.androidx.activity.compose)
}