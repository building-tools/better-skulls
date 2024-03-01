package de.raphaelgoetz.betterskulls.menu

import de.raphaelgoetz.betterskulls.data.SkullData
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.PageChangeEffect
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class SkullSubMenu(private val skulls: List<SkullData>, category: String) {

    val skullSubMenu = kSpigotGUI(GUIType.SIX_BY_NINE) {
        title = literalText("SkullMenu$category (${skulls.size})") {
            color = KColors.GRAY
        }

        page(1) {
            val skullSubCategory = createRectCompound<SkullData>(
                Slots.RowOneSlotOne, Slots.RowFiveSlotNine,

                iconGenerator = {
                    it.head
                },

                onClick = { clickEvent, element ->
                    clickEvent.player.inventory.addItem(element.head)
                }
            )

            skullSubCategory.addContent(skulls)

            transitionFrom = PageChangeEffect.SWIPE_VERTICALLY
            transitionTo = PageChangeEffect.SWIPE_VERTICALLY

            compoundScroll(
                Slots.RowSixSlotOne,
                ItemStack(Material.ARROW), skullSubCategory, scrollTimes = 6
            )
            compoundScroll(
                Slots.RowSixSlotNine,
                ItemStack(Material.ARROW), skullSubCategory, scrollTimes = 6, reverse = true
            )
        }
    }
}