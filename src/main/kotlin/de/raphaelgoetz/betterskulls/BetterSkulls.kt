package de.raphaelgoetz.betterskulls

import de.raphaelgoetz.betterskulls.command.OpenSearchMenu
import de.raphaelgoetz.betterskulls.command.OpenSkullMenu
import de.raphaelgoetz.betterskulls.manager.SkullManager
import net.axay.kspigot.main.KSpigot

class BetterSkulls : KSpigot() {

    override fun startup() {
        val currentTime = System.currentTimeMillis()
        println("Initializing BetterSkulls")

        val manager = SkullManager()
        println("BetterSkulls took " + System.currentTimeMillis().minus(currentTime) + " milliseconds")

        getCommand("skull")?.setExecutor(OpenSkullMenu(manager))
        getCommand("skullsearch")?.setExecutor(OpenSearchMenu(manager))
    }

}