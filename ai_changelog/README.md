# AI Changelog

This folder contains detailed changelogs for significant changes made by AI assistants (like Claude Code).

## Purpose

When AI assistants make substantial changes to the project, they should document them here with:
- Date and time of changes
- Summary of what was changed
- Reasoning behind the changes
- Files affected
- Any breaking changes or migration notes

## File Naming Convention

Use the format: `YYYY-MM-DD_description.md`

Example: `2026-01-17_multi-module-refactoring.md`

## When to Add an Entry

Add an AI changelog entry when:
- Implementing new features or modules
- Making architectural changes
- Refactoring significant portions of code
- Adding/removing dependencies
- Changing build configuration

## Template

```markdown
# [Change Title]

**Date**: YYYY-MM-DD
**AI Assistant**: Claude Code / GPT / etc.
**Type**: Feature / Refactoring / Bug Fix / Architecture

## Summary
Brief description of what was changed and why.

## Changes Made
- Bullet list of specific changes
- File paths and modifications

## Impact
- Breaking changes (if any)
- Migration steps (if needed)
- Areas requiring attention

## Files Changed
- `path/to/file1.kt`
- `path/to/file2.kt`

## Next Steps
Optional: What should be done next
```

## Relation to CHANGELOG.md

- **CHANGELOG.md** (root): User-facing changes for releases
- **ai_changelog/** (this folder): Detailed AI work logs for development reference

The root CHANGELOG.md should still be updated with significant changes, but this folder provides more detailed technical documentation of AI-assisted development sessions.
