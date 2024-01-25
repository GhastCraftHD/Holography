package de.leghast.holography.ui;

import de.leghast.holography.Holography;
import de.leghast.holography.ui.page.AttributesPage;
import de.leghast.holography.ui.page.PositionPage;
import de.leghast.holography.ui.page.RotationPage;
import de.leghast.holography.ui.page.SizePage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UserInterface {

    public UserInterface(Holography main, Player player, Page page){
        Inventory inv = Bukkit.createInventory(null, 45, page.getTitle());
        switch (page){
            case POSITION -> inv.setContents(PositionPage.getPositionPage(main, player));
            case SIZE -> inv.setContents(SizePage.getSizePage(main, player));
            case ROTATION -> inv.setContents(RotationPage.getRotationPage(main, player));
            case ATTRIBUTES -> inv.setContents(AttributesPage.getAttributesPage(main, player));
        }
        player.openInventory(inv);
    }

}
