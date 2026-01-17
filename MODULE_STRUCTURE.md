# Module Structure Visualization

## Project Directory Structure

```
MyApplicationClaude/
│
├── app/                                          [Android Application Module]
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   └── java/com/example/myapplicationclaude/
│   │       └── MainActivity.kt                   # Entry point
│   └── build.gradle.kts
│
├── buildSrc/                                     [Gradle Plugin Module]
│   ├── src/main/kotlin/
│   │   └── Dependencies.kt                       # Centralized dependencies
│   └── build.gradle.kts
│
├── core/                                         [Core Modules]
│   │
│   ├── core-ui/                                  [UI Module]
│   │   ├── src/main/
│   │   │   ├── AndroidManifest.xml
│   │   │   └── java/com/example/myapplicationclaude/core/ui/
│   │   │       └── theme/
│   │   │           ├── Color.kt                  # Theme colors
│   │   │           ├── Theme.kt                  # App theme
│   │   │           └── Type.kt                   # Typography
│   │   └── build.gradle.kts
│   │
│   ├── core-domain/                              [Domain Module]
│   │   ├── src/main/
│   │   │   ├── AndroidManifest.xml
│   │   │   └── java/com/example/myapplicationclaude/core/domain/
│   │   │       # Future: Use cases, models, repository interfaces
│   │   └── build.gradle.kts
│   │
│   └── core-data/                                [Data Module]
│       ├── src/main/
│       │   ├── AndroidManifest.xml
│       │   └── java/com/example/myapplicationclaude/core/data/
│       │       # Future: Repository implementations, data sources
│       └── build.gradle.kts
│
├── feature/                                      [Feature Modules]
│   │
│   └── feature-greeting/                         [Greeting Feature]
│       ├── src/main/
│       │   ├── AndroidManifest.xml
│       │   └── java/com/example/myapplicationclaude/feature/greeting/
│       │       └── presentation/                 # MVI Pattern
│       │           ├── GreetingContract.kt       # Intent, State, Effect
│       │           ├── GreetingViewModel.kt      # Business logic
│       │           └── GreetingScreen.kt         # UI Composables
│       └── build.gradle.kts
│
├── build.gradle.kts                              # Root build file
├── settings.gradle.kts                           # Module configuration
├── ARCHITECTURE.md                               # Architecture docs
└── REFACTORING_SUMMARY.md                        # Refactoring summary
```

## Module Dependency Graph

```
┌─────────────────────────────────────────────────────────────┐
│                           app                               │
│                    (Android Application)                    │
│                      - MainActivity                         │
└────────────┬────────────────────────────┬───────────────────┘
             │                            │
             │                            │
             ▼                            ▼
┌────────────────────────┐    ┌──────────────────────────────┐
│      core-ui           │    │   feature-greeting           │
│  (Android Library)     │◄───┤   (Android Library)          │
│   - Theme              │    │    - GreetingContract        │
│   - Color              │    │    - GreetingViewModel       │
│   - Typography         │    │    - GreetingScreen          │
└────────────────────────┘    └──────────────────────────────┘
                                           │
                                           │
                                           ▼
                              ┌────────────────────────┐
                              │    core-domain         │
                              │  (Android Library)     │
                              │   - Domain models      │
                              │   - Use cases          │
                              └────────────────────────┘
                                           ▲
                                           │
                                           │
                              ┌────────────────────────┐
                              │    core-data           │
                              │  (Android Library)     │
                              │   - Repositories       │
                              │   - Data sources       │
                              └────────────────────────┘
```

## MVI Pattern Flow (feature-greeting)

```
┌──────────────────────────────────────────────────────────────────┐
│                        GreetingScreen.kt                         │
│                          (View Layer)                            │
│                                                                  │
│  @Composable                                                     │
│  fun GreetingScreen(viewModel: GreetingViewModel)                │
│                                                                  │
└───────────┬────────────────────────────────────┬─────────────────┘
            │                                    │
            │ Observes State                     │ Sends Intent
            │ (StateFlow)                        │ (User Actions)
            │                                    │
            ▼                                    │
┌──────────────────────────────────────────────────┐◄──────────────┘
│              GreetingViewModel.kt                │
│               (ViewModel Layer)                  │
│                                                  │
│  - handleIntent(intent: GreetingIntent)          │
│  - state: StateFlow<GreetingState>               │
│  - Business Logic                                │
└──────────────────┬───────────────────────────────┘
                   │
                   │ Updates
                   │
                   ▼
┌──────────────────────────────────────────────────┐
│           GreetingContract.kt                    │
│              (Contract Layer)                    │
│                                                  │
│  sealed interface GreetingIntent {               │
│      data class UpdateName(name: String)         │
│  }                                               │
│                                                  │
│  data class GreetingState(                       │
│      val name: String,                           │
│      val greetingMessage: String                 │
│  )                                               │
│                                                  │
│  sealed interface GreetingEffect {               │
│      data class ShowToast(message: String)       │
│  }                                               │
└──────────────────────────────────────────────────┘
```

## Data Flow

```
User Interaction
      │
      ▼
[GreetingScreen]
      │
      │ sends
      ▼
[GreetingIntent] ──────► [GreetingViewModel]
                               │
                               │ processes
                               ▼
                         [Business Logic]
                               │
                               │ updates
                               ▼
                         [GreetingState]
                               │
                               │ emits
                               ▼
                         [StateFlow]
                               │
                               │ collects
                               ▼
                      [GreetingScreen]
                               │
                               │ renders
                               ▼
                         [UI Updates]
```

## Scalability Pattern

To add a new feature (e.g., "Profile"):

```
1. Create module:
   feature/feature-profile/

2. Implement MVI:
   - ProfileContract.kt    (Intent, State, Effect)
   - ProfileViewModel.kt   (Business logic)
   - ProfileScreen.kt      (UI)

3. Update settings.gradle.kts:
   include(":feature:feature-profile")

4. Add to app/build.gradle.kts:
   implementation(project(":feature:feature-profile"))

5. Use in MainActivity:
   ProfileScreen()
```

## Build Performance

Modules can be built in parallel:
```
┌─────────┐  ┌─────────┐  ┌─────────┐
│core-ui  │  │core-    │  │core-    │  ← Can build simultaneously
│         │  │domain   │  │data     │
└────┬────┘  └────┬────┘  └────┬────┘
     │            │            │
     └────────┬───┴────────────┘
              ▼
      ┌───────────────┐
      │feature-       │  ← Builds after core modules
      │greeting       │
      └───────┬───────┘
              │
              ▼
         ┌────────┐
         │  app   │  ← Builds last
         └────────┘
```
