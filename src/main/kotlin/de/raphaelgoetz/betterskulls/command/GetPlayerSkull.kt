package de.raphaelgoetz.betterskulls.command

import de.raphaelgoetz.betterskulls.manager.SkullManager
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.SkullMeta

class GetPlayerSkull(private val skullManager: SkullManager) : CommandExecutor {

    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (p0 !is Player) return true
        if (p3 == null) return true
        if (p3[0].isEmpty()) return true

        val item = itemStack(Material.PLAYER_HEAD) {
            meta<SkullMeta> {
                owner = p3[0]
            }
        }

        p0.inventory.addItem(item)

        return false
    }
}