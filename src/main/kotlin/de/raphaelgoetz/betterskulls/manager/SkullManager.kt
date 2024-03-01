package de.raphaelgoetz.betterskulls.manager

import de.raphaelgoetz.betterskulls.data.CategoryData
import de.raphaelgoetz.betterskulls.data.SkullData
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.io.File
import java.net.URL
import java.util.*

class SkullManager {

    val skullData: Map<String, List<SkullData>>
    val categoryData: Map<String, CategoryData>

    init {
        this.skullData = readSkullData()
        this.categoryData = createCategories()
    }

    private fun readSkullData(): Map<String, List<SkullData>> {
        val config = File("skulls.txt")
        val map = mutableMapOf<String, MutableList<SkullData>>()

        if (config.list() == null) return map

        for (line in config.list()) {
            val skullData = convertFromString(line)
            map.putIfAbsent(skullData.category, mutableListOf())
            map[skullData.category]?.add(skullData)
        }

        return map;
    }

    private fun createCategories(): Map<String, CategoryData> {
        val map = mutableMapOf<String, CategoryData>()
        this.skullData.forEach { entry: Map.Entry<String, List<SkullData>> ->
            map[entry.key] = CategoryData(entry.key, entry.value[0].head)
        }

        return map
    }

    private fun convertFromString(string: String): SkullData {
        val data = string.split(";")

        val category = data[0]
        val id = data[1].toInt()
        val name = data[2]
        val url = data[3]

        return SkullData(category, id, name, convertToItem(url, name))
    }

    private fun convertToItem(url: String, display: String): ItemStack {
        val profile = Bukkit.createProfile(UUID.randomUUID())
        profile.textures.skin = URL("https://textures.minecraft.net/texture/$url")

        return itemStack(Material.PLAYER_HEAD) {
            meta<SkullMeta> {
                playerProfile = profile
                name = literalText(display) {
                    color = KColors.OLIVE
                }
            }
        }
    }

}