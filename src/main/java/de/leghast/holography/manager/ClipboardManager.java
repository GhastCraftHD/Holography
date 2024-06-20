package de.leghast.holography.manager;

import de.leghast.holography.Holography;
import de.leghast.holography.display.DisplayWrapper;
import org.bukkit.entity.TextDisplay;

import java.util.HashMap;
import java.util.UUID;

public class ClipboardManager {

    private final HashMap<UUID, DisplayWrapper> clipboard;

    public ClipboardManager(){
        clipboard = new HashMap<>();
    }

    public void update(UUID uuid, DisplayWrapper wrapper){
        if (hasClipboard(uuid)){
            remove(uuid);
        }
        clipboard.put(uuid, wrapper);
    }

    public void update(UUID uuid, TextDisplay display){
        update(uuid, new DisplayWrapper(display));
    }

    public DisplayWrapper getWrapper(UUID uuid){
        return clipboard.get(uuid);
    }

    public boolean hasClipboard(UUID uuid){
        return clipboard.containsKey(uuid);
    }

    public void remove(UUID uuid){
        if(hasClipboard(uuid)){
            clipboard.remove(uuid);
        }
    }

}
