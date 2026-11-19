package ai.yaaif.jetbrains

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class OpenGovernanceAction : AnAction() {
  override fun actionPerformed(event: AnActionEvent) {
    openYaaifWorkflow("yaaif-governance")
  }
}
