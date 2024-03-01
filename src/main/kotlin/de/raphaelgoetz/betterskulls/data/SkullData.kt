package de.raphaelgoetz.betterskulls.data

import org.bukkit.inventory.ItemStack

data class SkullData(
        val category: String,
        val id: Int,
        val name: String,
        val head: ItemStack
)