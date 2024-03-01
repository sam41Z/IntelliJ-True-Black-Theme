package ch.sam41z.trueblack

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId

object PluginMetadata {
    private const val PLUGIN_ID = "ch.sam41z.true-black"
    fun currentVersion(): String? {
        return PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID))?.version
    }
}