@file:Suppress("DialogTitleCapitalization")

package ch.sam41z.trueblack.notifications

import ch.sam41z.trueblack.PluginMetadata
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import org.intellij.lang.annotations.Language

object PluginNotification {
    @Language("HTML")
    private val WELCOME_MESSAGE = "<div>" +
            "<p>Thank you for choosing <span>True Black Theme</span>.</p>" +
            "<br>" +
            "<p><a href=\"https://github.com/ahmed3elshaer/Intelij-True-Black/blob/main/CHANGELOG.md\">Changelog</a> | <a href=\"https://github.com/ahmed3elshaer/Intelij-True-Black\">Repository</a></p>" +
            "</div>"
    private const val NOTIFICATION_GROUP_ID = "Truely Black Theme"
    private val icon = IconLoader.getIcon("/icons/logo.svg", PluginNotification::class.java)
    private val NOTIFICATION_GROUP = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID)

    fun notifyWelcome(project: Project?) {
        val notification = NOTIFICATION_GROUP.createNotification(
            "Truely Black Theme is installed $WELCOME_MESSAGE",
            NotificationType.INFORMATION,
        )
        notification.icon = icon
        notification.notify(project)
    }
}