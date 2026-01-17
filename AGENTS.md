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
- **Protected Branch Warning**:
  - If currently on `develop` or `main` branch and about to generate/modify code, STOP
  - Prompt user: "You are currently on [branch name]. I recommend creating a feature branch off develop first. Would you like me to create a feature branch, or continue on this branch?"
  - Wait for user confirmation before making any code changes
  - Exception: Documentation-only changes (*.md files) can be done on any branch
- **Push Confirmation**:
  - NEVER push to remote without explicit user confirmation
  - Before executing `git push`, ask: "Ready to push [branch name] to remote?"
  - Wait for user to confirm before proceeding with push
  - Local commits are fine without asking, but pushing requires confirmation
- **Changelog**: Add significant changes to CHANGELOG.md under `[Unreleased]` section
  - Use categories: Added, Changed, Deprecated, Removed, Fixed, Security
  - Examples of significant changes: new features, architecture changes, breaking changes
  - Small fixes and routine updates can be in git commits only
- **AI Changelog**: For larger AI-assisted changes, create detailed documentation in `ai_changelog/`
  - File naming: `YYYY-MM-DD_description.md`
  - Document: what changed, why, files affected, impact, and next steps
  - See `ai_changelog/README.md` for template and guidelines
  - Examples: major refactorings, new feature implementations, architecture changes

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
- For significant changes (new features, architecture changes), update CHANGELOG.md
- For larger AI development sessions, create a detailed log in `ai_changelog/YYYY-MM-DD_description.md`
- Keep git commits focused and atomic

## Documentation Structure
- **CHANGELOG.md** - User-facing changelog organized by version
- **ai_changelog/** - Detailed logs of AI development sessions
  - Create new file for each major AI session
  - Include technical details, reasoning, and context
  - Helps track AI work and decisions over time
- **documentation/** - Technical documentation for the project
  - ARCHITECTURE.md - Architecture overview
  - MODULE_STRUCTURE.md - Visual structure and diagrams
  - QUICK_START.md - Getting started guide

