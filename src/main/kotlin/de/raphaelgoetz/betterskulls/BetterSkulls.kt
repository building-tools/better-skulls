package de.raphaelgoetz.betterskulls

import de.raphaelgoetz.betterskulls.command.OpenSkullMenu
import de.raphaelgoetz.betterskulls.manager.SkullManager
import net.axay.kspigot.runnables.firstAsync
import org.bukkit.plugin.java.JavaPlugin

class BetterSkulls : JavaPlugin() {

    lateinit var skullManager: SkullManager

    override fun onEnable() {
        val currentTime = System.currentTimeMillis()
        println("Initializing BetterSkulls")

        firstAsync {
            skullManager = SkullManager()
            println("BetterSkulls took " + System.currentTimeMillis().minus(currentTime))
        }

        getCommand("skulls")?.setExecutor(OpenSkullMenu(this))
    }

    override fun onDisable() {
    }

}