package de.raphaelgoetz.betterskulls.menu

import de.raphaelgoetz.betterskulls.BetterSkulls
import de.raphaelgoetz.betterskulls.data.CategoryData
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.gui.openGUI

class SkullMenu(val betterSkulls: BetterSkulls) {

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
                    val data = betterSkulls.skullManager.skullData[element.name] ?: return@createRectCompound
                    val gui = SkullSubMenu(data, element.name).skullSubMenu
                    clickEvent.player.openGUI(gui)
                }
            )

            categories.addContent(betterSkulls.skullManager.categoryData.values)
        }
    }
}