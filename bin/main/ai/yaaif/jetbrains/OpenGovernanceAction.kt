package ai.yaaif.jetbrains

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.notification.NotificationType
import java.awt.datatransfer.StringSelection

/** AI Assistant handoff uses the same clipboard pattern as MCP configuration. */
class OpenGovernanceAction : AnAction() {
  override fun actionPerformed(event: AnActionEvent) {
    val paths = listOf("SKILL.md", "references/requirements.md", "references/diagnosis.md")
    val instructions = paths.map { path ->
      javaClass.getResourceAsStream("/skills/yaaif-governance/$path")
        ?.bufferedReader()?.use { it.readText() }
        ?: error("Missing bundled governance instructions: $path")
    }.joinToString("\n\n")
    val prompt = "Use the bundled YAAIF governance workflow. Start with yaaif_governance_capabilities; obtain explicit confirmation of the exact proposal before apply.\n\n$instructions"
    CopyPasteManager.getInstance().setContents(StringSelection(prompt))
    showHandoffNotification("Governance instructions copied. Open AI Assistant and paste them to start the YAAIF governance agent.", NotificationType.INFORMATION)
  }
}
