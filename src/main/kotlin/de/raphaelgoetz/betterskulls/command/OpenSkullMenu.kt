package de.raphaelgoetz.betterskulls.command

import de.raphaelgoetz.betterskulls.BetterSkulls
import de.raphaelgoetz.betterskulls.menu.SkullMenu
import net.axay.kspigot.gui.openGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OpenSkullMenu(val betterSkulls: BetterSkulls) : CommandExecutor {

    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (p0 !is Player) return true

        val menu = SkullMenu(betterSkulls).skullMenu
        p0.openGUI(menu)

        return false
    }
}