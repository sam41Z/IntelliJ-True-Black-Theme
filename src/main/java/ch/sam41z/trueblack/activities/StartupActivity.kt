package ch.sam41z.trueblack.activities

import ch.sam41z.trueblack.PluginMetadata
import ch.sam41z.trueblack.notifications.PluginNotification
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class StartupActivity : ProjectActivity, DumbAware {

    override suspend fun execute(project: Project) {
        val currentVersion = PluginMetadata.currentVersion() ?: "Version Error !"
        // get plugin current version
        val version = PluginManagerCore.getPlugin(PluginId.getId("ch.sam41z.trueblack"))?.version ?: ""

        if (currentVersion == version) {
            PluginNotification.notifyWelcome(project)
            return
        }
    }
}