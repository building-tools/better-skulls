package de.raphaelgoetz.betterskulls.command

import de.raphaelgoetz.betterskulls.manager.SkullManager
import de.raphaelgoetz.betterskulls.menu.SearchMenu
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.gui.openGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OpenSearchMenu(private val skullManager: SkullManager) : CommandExecutor {

    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (p0 !is Player) return true
        if (p3 == null) return true
        if (p3[0].isEmpty()) return true

        val list = skullManager.search(p3[0])

        if (list.isEmpty()) {
            val text = literalText("No heads found") {
                color = KColors.RED
                bold = true
                italic = false
            }

            p0.sendMessage(text)
            return true
        }

        val menu = SearchMenu(skullManager, list).searchMenu
        p0.openGUI(menu)

        return false
    }
}