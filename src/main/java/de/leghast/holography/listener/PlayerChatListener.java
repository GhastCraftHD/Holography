package de.leghast.holography.listener;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Message;
import de.leghast.holography.display.DisplayWrapper;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class PlayerChatListener implements Listener {

    private final Holography main;

    public PlayerChatListener(Holography main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent e){

        if(!main.getChatInputManager().isRegistered(e.getPlayer().getUniqueId())) return;

        e.setCancelled(true);

        if(((TextComponent) e.message()).content().equals("cancel")){
            main.getChatInputManager().unregister(e.getPlayer().getUniqueId());
            e.getPlayer().sendMessage(Message.CANCELLED_INPUT);
            return;
        }

        DisplayWrapper wrapper = main.getClipboardManager().getWrapper(e.getPlayer().getUniqueId());

        wrapper.displayText(((TextComponent) e.message()).content());
        e.getPlayer().sendMessage(Message.setText(wrapper.display().text()));

    }

}
