package de.leghast.holography.handler;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Message;
import de.leghast.holography.settings.AdjusterSettings;
import de.leghast.holography.ui.UserInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class AdjusterInteractionHandler {

    public AdjusterInteractionHandler(Holography main, Action action, Player player) {

        if(main.getClipboardManager().hasClipboard(player.getUniqueId())){
            if(action.isLeftClick()){
                AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                switch (settings.getPage()) {
                    case POSITION -> main.getClipboardManager().getWrapper(player.getUniqueId()).move(
                            settings.getPositionSettings().getAxis(),
                            settings.getPositionSettings().getFactor()
                    );
                    case ROTATION -> main.getClipboardManager().getWrapper(player.getUniqueId()).rotate(
                            settings.getRotationSettings().getAxis(),
                            settings.getRotationSettings().getFactor()
                    );
                    case SIZE ->
                            main.getClipboardManager().getWrapper(player.getUniqueId()).scaleUp(settings.getSizeSettings().getFactor());
                }
            }else if(action.isRightClick()){
                if(player.isSneaking()){
                    new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
                }else{
                    AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                    switch (settings.getPage()){
                        case POSITION -> main.getClipboardManager().getWrapper(player.getUniqueId()).move(
                                settings.getPositionSettings().getAxis(),
                                -settings.getPositionSettings().getFactor()
                        );
                        case ROTATION -> main.getClipboardManager().getWrapper(player.getUniqueId()).rotate(
                                settings.getRotationSettings().getAxis(),
                                -settings.getRotationSettings().getFactor()
                        );
                        case SIZE -> main.getClipboardManager().getWrapper(player.getUniqueId()).scaleDown(settings.getSizeSettings().getFactor());
                    }
                }
            }
        }else{
            player.sendMessage(Message.NO_HOLO_SELECTED);
        }

    }
}
