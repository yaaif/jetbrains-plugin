package ai.yaaif.jetbrains

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection

private const val MCP_ENTRY = """{
  \"command\": \"npx\",
  \"args\": [\"-y\", \"@yaaif/platform-mcp@1.3.4\", \"--client\", \"intellij\"]
}"""

class ConfigureMcpBridgeAction : AnAction() {
  override fun actionPerformed(event: AnActionEvent) {
    CopyPasteManager.getInstance().setContents(StringSelection(MCP_ENTRY))
    BrowserUtil.browse("https://www.jetbrains.com/help/idea/mcp-server.html")
    showHandoffNotification(
      "YAAIF MCP configuration was copied. Add it in JetBrains AI Assistant settings, then reopen the assistant.",
      com.intellij.notification.NotificationType.INFORMATION,
    )
  }
}
