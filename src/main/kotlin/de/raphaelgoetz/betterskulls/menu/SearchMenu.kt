package de.raphaelgoetz.betterskulls.menu

import de.raphaelgoetz.betterskulls.data.SkullData
import de.raphaelgoetz.betterskulls.manager.SkullManager
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.gui.*
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class SearchMenu(
    private val skullManager: SkullManager,
    private val skulls: List<SkullData>
) {

    val searchMenu = kSpigotGUI(GUIType.SIX_BY_NINE) {

        title = literalText("Found ${skulls.size} skulls") {
            color = KColors.GRAY
        }

        page(1) {
            val searchedSkulls = createRectCompound<SkullData>(
                Slots.RowTwoSlotOne, Slots.RowSixSlotNine,

                iconGenerator = {
                    it.head
                },

                onClick = { clickEvent, element ->
                    val data = skullManager.skullData[element.name] ?: return@createRectCompound
                    val gui = SkullSubMenu(data, element.name).skullSubMenu
                    clickEvent.player.openGUI(gui)
                }
            )

            searchedSkulls.addContent(skulls)

            transitionFrom = PageChangeEffect.SWIPE_VERTICALLY
            transitionTo = PageChangeEffect.SWIPE_VERTICALLY

            compoundScroll(
                Slots.RowOneSlotNine,
                ItemStack(Material.ARROW), searchedSkulls, scrollTimes = 6
            )
            compoundScroll(
                Slots.RowOneSlotOne,
                ItemStack(Material.ARROW), searchedSkulls, scrollTimes = 6, reverse = true
            )

            onClose {
                searchedSkulls.removeContent(skulls)
            }
        }
    }
}