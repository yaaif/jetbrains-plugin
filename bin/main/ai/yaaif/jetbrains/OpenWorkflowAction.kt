package ai.yaaif.jetbrains

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class OpenWorkflowAction : AnAction() {
  override fun actionPerformed(event: AnActionEvent) {
    val selected = Messages.showChooseDialog(
      event.project,
      "Choose the YAAIF workflow to start in AI Assistant",
      "YAAIF: Platform Workflow",
      null,
      YAAIF_WORKFLOWS.toTypedArray(),
      "yaaif-plan-usecase",
    )
    if (selected < 0) return
    openYaaifWorkflow(YAAIF_WORKFLOWS[selected])
  }
}
