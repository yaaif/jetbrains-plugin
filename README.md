# YAAIF for IntelliJ IDEA

Build and install the plugin with the matching 1.7.0 npx installer (Java 21+):

```bash
npx -y @yaaif/platform-mcp@1.7.0 --install --client intellij
```

This downloads the 1.7.0 source, runs the bundled Gradle wrapper, and installs
`yaaif-jetbrains.jar` into the newest detected IntelliJ plugins directory after
backing up an existing jar. Use `--jetbrains-plugins-dir DIR` to select another
installation, `--gradle-path PATH` to override the wrapper, or `--build-only` to
leave `build/libs/yaaif-jetbrains-1.7.0-base.jar` without modifying the IDE.
Successful updates remove superseded YAAIF jars and backups from the active
plugins directory and move them to `~/.yaaif/intellij/plugin-backups/`; use
`--keep-old-versions` to retain them. Failed installation or verification restores the previous jar from its backup.
Source overrides and verification use `--plugin-src`, `--plugin-ref`,
`--plugin-sha256`, and `--plugin-source-base-url`.

Run **YAAIF: Configure MCP Bridge** and add the copied user-level configuration to JetBrains
AI Assistant. **YAAIF: Platform Workflow** copies a selected shared auth,
planning, Scenario, skill, MCP, ambient, file-workflow, Wiki, platform-update,
docs, or operations workflow for AI Assistant. The Admin UI **Open in IntelliJ IDEA** action opens
the plugin handoff and copies the selected catalog prompt. Auth and doctor
workflows use `yaaif_features` to distinguish missing permissions from backends
that need an upgrade.

## Tenant governance

`yaaif-governance` gathers authorized tenant business context, interviews for missing requirements, diagnoses failures, and prepares guardrail/harness policy changes. It starts with effective permissions and uses a preview → explicit confirmation → apply workflow. It never grants permissions or treats tenant documents as authorization. Retention is inspection only; infrastructure, data purges and run controls are excluded.

OpenCode: `/yaaif-governance`. Cursor/Claude Code: the `yaaif-governance` command or skill. Codex: `$yaaif-governance`. VS Code/JetBrains: **YAAIF: Governance Agent** (JetBrains copies the bundled instructions for AI Assistant).

Requires a bridge and backend containing the governance tools, validation routes and conditional-write middleware. Prepare and test the packages together before publishing; do not point a live install at an unpublished version. A saved configuration does not by itself prove runtime enforcement.
