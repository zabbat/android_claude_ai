# Android Project - AI Agent Instructions

## Project Type
Multi-module Android application using Kotlin.

## Architecture
- **Pattern**: MVI with Clean Architecture
- **DI**: Koin
- **UI**: XML layouts and Compose. Favor Compose.
- **Navigation**: Android Navigation Component

## Module Structure
```
app/                    # Main app module
features/               # Feature module
  ├── helloworld/      # Feature sub modules
  └── [other-features]/
core/                   # Shared utilities
```

## Coding Standards
- **Language**: Kotlin only for new code
- **Naming**: camelCase for variables, PascalCase for classes
- **Formatting**: Follow Kotlin style guide
- **Comments**: Use KDoc format for public APIs


## Dependencies (Key Libraries)
- AndroidX Core KTX
- Material Components
- ViewModel & Flow
- Navigation Component
- [Add others you use]


## Testing
- Unit tests: `src/test/`
- Instrumentation tests: `src/androidTest/`
- Use JUnit 5 and Mockk

## Git & Commits
- Branch naming: `feature/description` 
- Commit messages: Conventional commits format

## Android-Specific Notes
- Always include proper lifecycle handling
- Use ViewBinding for view access (or specify if using Compose)
- Follow Material Design 3 guidelines
- Handle configuration changes properly
- Add proper null safety checks

## Build Commands
```bash
# Clean build
./gradlew clean build

# Run tests
./gradlew test

# Install debug APK
./gradlew installDebug
```

## Notes for AI
- When creating new Activities, always register them in AndroidManifest.xml
- When adding dependencies, add to module-level build.gradle, and use the toml file for versioning
- Always create corresponding layout XML files for Activities/Fragments
- Follow Android resource naming conventions (activity_name.xml, fragment_name.xml)
```

