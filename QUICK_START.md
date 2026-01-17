# Quick Start Guide

## Project Setup

### 1. Sync Gradle
After opening the project in Android Studio:
- Click "Sync Project with Gradle Files" button
- Or: File → Sync Project with Gradle Files
- Wait for all modules to sync successfully

### 2. Build the Project
```bash
# Windows
.\gradlew build

# macOS/Linux
./gradlew build
```

### 3. Run the App
```bash
# Install on connected device/emulator
.\gradlew installDebug

# Or simply run from Android Studio
```

## Project Structure Quick Reference

### Module Types

| Module | Type | Purpose |
|--------|------|---------|
| `app` | Application | Entry point, hosts features |
| `core-ui` | Library | Shared UI, theme, Compose components |
| `core-domain` | Library | Business logic, use cases |
| `core-data` | Library | Data layer, repositories |
| `feature-greeting` | Library | Greeting feature with MVI |

### Key Files

| File | Purpose |
|------|---------|
| `gradle/libs.versions.toml` | Central dependency management (Version Catalog) |
| `settings.gradle.kts` | Module configuration |
| `ARCHITECTURE.md` | Architecture documentation |
| `MODULE_STRUCTURE.md` | Visual structure guide |
| `VERSION_CATALOG_MIGRATION.md` | Version catalog usage guide |

## Adding a New Feature Module

### Step-by-Step

1. **Create Module Directory**
   ```bash
   mkdir -p feature/feature-myfeature/src/main/java/com/example/myapplicationclaude/feature/myfeature/presentation
   ```

2. **Create build.gradle.kts**
   ```kotlin
   plugins {
       alias(libs.plugins.android.library)
       alias(libs.plugins.kotlin.android)
       alias(libs.plugins.kotlin.compose)
   }

   android {
       namespace = "com.example.myapplicationclaude.feature.myfeature"
       compileSdk = libs.versions.compileSdk.get().toInt()

       defaultConfig {
           minSdk = libs.versions.minSdk.get().toInt()
       }

       buildFeatures {
           compose = true
       }
   }

   dependencies {
       implementation(project(":core:core-ui"))
       implementation(project(":core:core-domain"))
       implementation(libs.androidx.lifecycle.viewmodel.compose)
   }
   ```

3. **Create MVI Files**
   - `MyFeatureContract.kt` - Intent, State, Effect
   - `MyFeatureViewModel.kt` - State management
   - `MyFeatureScreen.kt` - UI Composables

4. **Add to settings.gradle.kts**
   ```kotlin
   include(":feature:feature-myfeature")
   ```

5. **Add to app/build.gradle.kts**
   ```kotlin
   dependencies {
       implementation(project(":feature:feature-myfeature"))
   }
   ```

6. **Use in MainActivity**
   ```kotlin
   import com.example.myapplicationclaude.feature.myfeature.presentation.MyFeatureScreen

   setContent {
       MyApplicationClaudeTheme {
           MyFeatureScreen()
       }
   }
   ```

## MVI Pattern Template

### Contract File
```kotlin
package com.example.myapplicationclaude.feature.myfeature.presentation

sealed interface MyFeatureIntent {
    data class OnAction(val data: String) : MyFeatureIntent
}

data class MyFeatureState(
    val isLoading: Boolean = false,
    val data: String = ""
)

sealed interface MyFeatureEffect {
    data class ShowError(val message: String) : MyFeatureEffect
}
```

### ViewModel File
```kotlin
package com.example.myapplicationclaude.feature.myfeature.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyFeatureViewModel : ViewModel() {

    private val _state = MutableStateFlow(MyFeatureState())
    val state: StateFlow<MyFeatureState> = _state.asStateFlow()

    fun handleIntent(intent: MyFeatureIntent) {
        when (intent) {
            is MyFeatureIntent.OnAction -> handleAction(intent.data)
        }
    }

    private fun handleAction(data: String) {
        _state.update { it.copy(data = data) }
    }
}
```

### Screen File
```kotlin
package com.example.myapplicationclaude.feature.myfeature.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyFeatureScreen(
    viewModel: MyFeatureViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    MyFeatureContent(
        state = state,
        onIntent = viewModel::handleIntent
    )
}

@Composable
internal fun MyFeatureContent(
    state: MyFeatureState,
    onIntent: (MyFeatureIntent) -> Unit
) {
    // UI implementation
}
```

## Common Commands

### Build Commands
```bash
# Clean build
.\gradlew clean build

# Build specific module
.\gradlew :app:build
.\gradlew :feature:feature-greeting:build

# Run tests
.\gradlew test

# Check dependencies
.\gradlew :app:dependencies
```

### Debug Commands
```bash
# Install debug APK
.\gradlew installDebug

# Uninstall app
.\gradlew uninstallDebug

# View build config
.\gradlew :app:dependencies --configuration debugRuntimeClasspath
```

## Troubleshooting

### Gradle Sync Issues
1. Click "Invalidate Caches / Restart" in Android Studio
2. Delete `.gradle` and `build` folders
3. Sync again

### Build Errors
1. Sync Gradle files to regenerate version catalog
2. Verify all modules are included in `settings.gradle.kts`
3. Check dependency versions in `gradle/libs.versions.toml`

### Module Not Found
1. Ensure module is in `settings.gradle.kts`
2. Verify module path is correct
3. Sync Gradle files

## Best Practices

1. **One Feature Per Module** - Keep features isolated
2. **Shared Code in Core** - Put reusable code in core modules
3. **MVI Pattern** - Follow the established pattern for consistency
4. **Naming Convention** - Use `feature-{name}` format
5. **Package Structure** - Keep presentation layer in feature modules
6. **Dependencies** - Update in `gradle/libs.versions.toml` only
7. **Version Catalog** - Use `libs.*` accessors for type-safe dependencies

## Resources

- [Android Multi-Module](https://developer.android.com/topic/modularization)
- [MVI Architecture](https://github.com/oldergod/android-architecture)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
