package ai.yaaif.jetbrains

import com.intellij.ide.CustomProtocolHandler
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection
import java.net.URI
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class HandoffProtocolHandler : CustomProtocolHandler() {
  override fun openLink(uri: URI) {
    if (uri.path != "/yaaif/handoff") return
    val prompt = uri.rawQuery
      ?.split("&")
      ?.firstOrNull { it.startsWith("prompt=") }
      ?.substringAfter("prompt=")
      ?.let { URLDecoder.decode(it, StandardCharsets.UTF_8) }
      ?.trim()
      .orEmpty()
    if (prompt.isEmpty()) {
      showHandoffNotification("The YAAIF handoff link did not contain a prompt.", NotificationType.WARNING)
      return
    }

    ApplicationManager.getApplication().invokeLater {
      CopyPasteManager.getInstance().setContents(StringSelection(prompt))
      showHandoffNotification(
        "YAAIF handoff copied to the clipboard. Open JetBrains AI Assistant and paste the prompt if it was not prefilled.",
        NotificationType.INFORMATION,
      )
    }
  }

}

fun showHandoffNotification(message: String, type: NotificationType) {
  NotificationGroupManager.getInstance()
    .getNotificationGroup("YAAIF Platform")
    .createNotification(message, type)
    .notify(null)
}
