package de.raphaelgoetz.betterskulls.menu

import de.raphaelgoetz.betterskulls.data.CategoryData
import de.raphaelgoetz.betterskulls.manager.SkullManager
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.gui.openGUI

class SkullMenu(private val skullManager: SkullManager) {

    val skullMenu = kSpigotGUI(GUIType.SIX_BY_NINE) {
        title = literalText("SkullMenu") {
            color = KColors.GRAY
        }

        page(1) {
            val categories = createRectCompound<CategoryData>(
                Slots.RowOneSlotOne, Slots.RowSixSlotEight,

                iconGenerator = {
                    it.item
                },

                onClick = { clickEvent, element ->
                    val data = skullManager.skullData[element.name] ?: return@createRectCompound
                    val gui = SkullSubMenu(data, element.name).skullSubMenu
                    clickEvent.player.openGUI(gui)
                }
            )

            categories.addContent(skullManager.categoryData.values)
        }
    }
}