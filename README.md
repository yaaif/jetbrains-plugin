# YAAIF for IntelliJ IDEA

Install the YAAIF Platform plugin from JetBrains Marketplace. Run **YAAIF:
Configure MCP Bridge** and add the copied user-level configuration to JetBrains
AI Assistant. The Admin UI **Open in IntelliJ IDEA** action opens the plugin
handoff and copies the selected catalog prompt for AI Assistant.

## Tenant governance

`yaaif-governance` gathers authorized tenant business context, interviews for missing requirements, diagnoses failures, and prepares guardrail/harness policy changes. It starts with effective permissions and uses a preview → explicit confirmation → apply workflow. It never grants permissions or treats tenant documents as authorization. Retention is inspection only; infrastructure, data purges and run controls are excluded.

OpenCode: `/yaaif-governance`. Cursor/Claude Code: the `yaaif-governance` command or skill. Codex: `$yaaif-governance`. VS Code/JetBrains: **YAAIF: Governance Agent** (JetBrains copies the bundled instructions for AI Assistant).

Requires a bridge and backend containing the governance tools, validation routes and conditional-write middleware. Prepare and test the packages together before publishing; do not point a live install at an unpublished version. A saved configuration does not by itself prove runtime enforcement.
