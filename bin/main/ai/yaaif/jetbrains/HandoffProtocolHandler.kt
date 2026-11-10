package ai.yaaif.jetbrains

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType

fun showHandoffNotification(message: String, type: NotificationType) {
  NotificationGroupManager.getInstance()
    .getNotificationGroup("YAAIF Platform")
    .createNotification(message, type)
    .notify(null)
}
