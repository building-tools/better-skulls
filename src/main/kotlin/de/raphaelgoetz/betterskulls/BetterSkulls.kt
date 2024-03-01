package de.raphaelgoetz.betterskulls

import de.raphaelgoetz.betterskulls.command.OpenSkullMenu
import de.raphaelgoetz.betterskulls.manager.SkullManager
import org.bukkit.plugin.java.JavaPlugin

class BetterSkulls : JavaPlugin() {

    override fun onEnable() {
        val currentTime = System.currentTimeMillis()
        println("Initializing BetterSkulls")

        val manager = SkullManager()
        println("BetterSkulls took " + System.currentTimeMillis().minus(currentTime))

        getCommand("skulls")?.setExecutor(OpenSkullMenu(manager))
    }

    override fun onDisable() {}

}