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

extra["appVersionCode"] = 27
extra["appVersionName"] = "0.19"
val appVersionCode: Int by extra
val appVersionName: String by extra

android {
    namespace = "com.capybara.intuition"
    compileSdk = 37

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
        targetSdk = 37

        versionCode = appVersionCode
        versionName = appVersionName


    }

    buildFeatures {
        compose = true
        viewBinding = false
        dataBinding = false
        buildConfig = true
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")

            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
        disable += "LintVitalAnalyze"
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
    implementation(libs.androidx.lifecycle.process)

    // --- Navigation ---
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.compose)

    // --- Room ---
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // --- Compose ---
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.foundation)
    implementation(libs.material3)
    implementation(libs.ui.tooling.preview)
    implementation(libs.icons.extended)

    // --- Markdown ---
    implementation(libs.richtext.commonmark)
    implementation(libs.richtext.material3)

    // --- Desugaring ---
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // --- Serialization ---
    implementation(libs.gson)

    // --- Testing ---
    testImplementation(libs.junit)

    // --- Datastore ---
    implementation(libs.androidx.datastore.preferences)

    // --- Google Billing System ---
    implementation(libs.billing.ktx)


    // --- Google Firebase ---
    implementation(platform(libs.firebase.bom))

    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage)

    implementation(libs.androidx.activity.compose)


    // --- Application updates ---
    implementation(libs.play.app.update)
    implementation(libs.play.app.update.ktx)

    // --- Coroutines ---
    implementation(libs.kotlinx.coroutines.play.services)
}

configurations.all {
    exclude(
        group = "com.google.android.gms",
        module = "play-services-ads-identifier"
    )
}

apply(
    from = "gradle/generate-build-info.gradle.kts"
)