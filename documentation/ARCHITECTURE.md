# Multi-Module MVI Architecture

This project follows a multi-module architecture with MVI (Model-View-Intent) pattern for feature modules.

## Module Structure

### App Module
- Entry point of the application
- Contains MainActivity which hosts feature screens
- Depends on feature and core modules
- Minimal code, acts as a container

### Core Modules

#### core-ui
- Shared UI components and theme
- Compose theme (Color, Type, Theme)
- Base UI utilities
- Material3 design system

#### core-domain
- Business logic and domain models
- Use cases
- Repository interfaces
- Pure Kotlin module (no Android dependencies)

#### core-data
- Data layer implementation
- Repository implementations
- Data sources (local/remote)
- Depends on core-domain

### Feature Modules

#### feature-greeting
- Greeting feature implementation
- Follows MVI architecture pattern
- Self-contained with its own presentation layer

## MVI Architecture Pattern

Each feature module follows the MVI pattern:

```
feature/
├── presentation/
│   ├── {Feature}Contract.kt    # Intent, State, Effect definitions
│   ├── {Feature}ViewModel.kt   # State management and intent handling
│   └── {Feature}Screen.kt      # Composable UI
```

### MVI Components

1. **Intent**: User actions/events
2. **State**: UI state representation
3. **Effect**: One-time events (navigation, toasts, etc.)
4. **ViewModel**: Processes intents and manages state

## Package Naming Conventions

- Core modules: `com.example.myapplicationclaude.core.{module}`
- Feature modules: `com.example.myapplicationclaude.feature.{feature}.presentation`
- Contract files: `{Feature}Contract.kt`
- ViewModels: `{Feature}ViewModel.kt`
- Screens: `{Feature}Screen.kt`

## Dependency Management

Dependencies are managed centrally using Gradle Version Catalog:
- `gradle/libs.versions.toml` - Central dependency versions and libraries
- Type-safe accessors in all modules via `libs.*`
- Ensures consistency across modules
- Easy version updates in one place

## Module Dependencies

```
app
├── core-ui
└── feature-greeting
    └── core-ui
    └── core-domain

core-data
└── core-domain
```

## Building the Project

1. Sync Gradle files
2. Build the project: `./gradlew build`
3. Run the app: `./gradlew installDebug`

## Adding a New Feature Module

1. Create module directory: `feature/feature-{name}`
2. Create `build.gradle.kts` with library plugin
3. Create MVI structure:
   - `{Feature}Contract.kt` (Intent, State, Effect)
   - `{Feature}ViewModel.kt`
   - `{Feature}Screen.kt`
4. Add module to `settings.gradle.kts`
5. Add dependency in app module

## Benefits

- **Scalability**: Easy to add new features
- **Separation of Concerns**: Clear boundaries between modules
- **Build Performance**: Parallel module compilation
- **Reusability**: Shared core modules
- **Testability**: Isolated module testing
- **Team Collaboration**: Different teams can work on different modules
