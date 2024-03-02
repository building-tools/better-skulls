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
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
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
        val inputStream = javaClass.classLoader.getResourceAsStream("skulls.txt")
            ?: throw NullPointerException("RessourceSteam is empty!")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val map = mutableMapOf<String, MutableList<SkullData>>()

        var line: String? = reader.readLine()
        while (line != null) {
            println(line)
            val skullData = convertFromString(line)
            map.putIfAbsent(skullData.category, mutableListOf())
            map[skullData.category]?.add(skullData)
            line = reader.readLine()
        }

        println(map.size)
        return map;
    }

    private fun createCategories(): Map<String, CategoryData> {
        val map = mutableMapOf<String, CategoryData>()
        this.skullData.forEach { entry: Map.Entry<String, List<SkullData>> ->

            val skullMeta = entry.value[0].head.itemMeta as SkullMeta
            val item = itemStack(Material.PLAYER_HEAD) {
                meta<SkullMeta> {
                    playerProfile = skullMeta.playerProfile
                    name = literalText(entry.key) {
                        color = KColors.GOLD
                        italic = false
                        bold = true
                    }
                }
            }

            map[entry.key] = CategoryData(entry.key, item)
        }

        return map
    }

    private fun convertFromString(string: String): SkullData {
        val data = string.split(";")

        val category = data[0]
        val id = data[1].toInt()
        val name = data[2]
        val url = data[3]
        val query = data[5]

        return SkullData(category, id, name, convertToItem(url, name), query)
    }

    private fun convertToItem(url: String, display: String): ItemStack {
        val fakePlayerProfile = Bukkit.createProfile(UUID.randomUUID())
        val playerTextures = fakePlayerProfile.textures

        playerTextures.skin = URL("https://textures.minecraft.net/texture/$url")
        fakePlayerProfile.setTextures(playerTextures)

        return itemStack(Material.PLAYER_HEAD) {
            meta<SkullMeta> {
                playerProfile = fakePlayerProfile
                name = literalText(display) {
                    color = KColors.LIMEGREEN
                    italic = false
                }
            }
        }
    }

    fun search(string: String): List<SkullData> {
        val list = mutableListOf<SkullData>()

        this.skullData.forEach {entry ->
            entry.value.forEach {skullData ->
                if (skullData.query.lowercase().contains(string.lowercase())) list.add(skullData)
            }
        }

        return list
    }

}