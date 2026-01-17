# Version Catalog Migration Guide

This project now uses Gradle Version Catalogs (`libs.versions.toml`) instead of buildSrc for dependency management.

## What Changed

### Before (buildSrc)
```
buildSrc/
└── src/main/kotlin/
    └── Dependencies.kt
```

Dependencies accessed via:
```kotlin
implementation(Deps.AndroidX.coreKtx)
compileSdk = Versions.compileSdk
```

### After (Version Catalog)
```
gradle/
└── libs.versions.toml
```

Dependencies accessed via:
```kotlin
implementation(libs.androidx.core.ktx)
compileSdk = libs.versions.compileSdk.get().toInt()
```

## Version Catalog Structure

The [gradle/libs.versions.toml](gradle/libs.versions.toml) file has three main sections:

### 1. [versions]
Define all version numbers in one place:
```toml
[versions]
compileSdk = "36"
minSdk = "24"
agp = "8.13.2"
kotlin = "2.0.21"
```

### 2. [libraries]
Define library dependencies:
```toml
[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
androidx-compose-ui = { group = "androidx.compose.ui", name = "ui" }
```

### 3. [plugins]
Define Gradle plugins:
```toml
[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
android-library = { id = "com.android.library", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
```

## How to Use Version Catalog

### In build.gradle.kts Files

#### Plugins
```kotlin
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}
```

#### SDK Versions
```kotlin
android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
}
```

#### Dependencies
```kotlin
dependencies {
    // Implementation
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // API (for library modules exposing dependencies)
    api(libs.androidx.compose.ui)
    api(platform(libs.androidx.compose.bom))

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
```

## Benefits of Version Catalog

1. **Type Safety**: Auto-completion and compile-time checking in IDE
2. **Centralized Management**: All versions in one file
3. **Performance**: Faster than buildSrc (no recompilation of build logic)
4. **Standard**: Official Gradle feature (since Gradle 7.0)
5. **Sharing**: Can share catalogs across projects
6. **Easier Migration**: Simpler syntax than buildSrc

## Adding New Dependencies

### Step 1: Add to libs.versions.toml

```toml
[versions]
retrofit = "2.9.0"

[libraries]
retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
retrofit-gson = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "retrofit" }
```

### Step 2: Use in build.gradle.kts

```kotlin
dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
}
```

## Naming Conventions

### Version References
- Use camelCase: `coreKtx`, `lifecycleRuntimeKtx`
- Group related versions: `composeBom`, `junit`

### Library References
- Use kebab-case with dots: `androidx.core.ktx`
- Group by organization: `androidx.*`, `compose.*`
- Match artifact structure when possible

### Plugin References
- Use kebab-case: `android-application`, `kotlin-android`
- Descriptive names: `kotlin-compose`

## Migration Checklist

- [x] Created `gradle/libs.versions.toml`
- [x] Updated all `build.gradle.kts` files
- [x] Converted all `Deps.*` references to `libs.*`
- [x] Converted all `Versions.*` to `libs.versions.*`
- [x] Updated plugin declarations to use `alias()`
- [x] Removed `buildSrc` directory
- [x] Updated documentation

## Example: Complete build.gradle.kts

```kotlin
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.myapplicationclaude.feature.example"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:core-ui"))
    implementation(project(":core:core-domain"))

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
```

## Troubleshooting

### Issue: "Unresolved reference: libs"
**Solution**: Sync Gradle files. The version catalog is generated during sync.

### Issue: "Cannot access libs.versions"
**Solution**: Use `.get().toInt()` for SDK versions:
```kotlin
compileSdk = libs.versions.compileSdk.get().toInt()
```

### Issue: "Plugin not found"
**Solution**: Ensure plugin is declared in root `build.gradle.kts`:
```kotlin
plugins {
    alias(libs.plugins.android.library) apply false
}
```

## References

- [Gradle Version Catalogs](https://docs.gradle.org/current/userguide/platforms.html)
- [Android Gradle Plugin Version Catalogs](https://developer.android.com/build/migrate-to-catalogs)
- [libs.versions.toml file](gradle/libs.versions.toml)
