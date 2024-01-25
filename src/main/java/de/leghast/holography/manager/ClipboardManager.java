package de.leghast.holography.manager;

import de.leghast.holography.Holography;
import de.leghast.holography.instance.DisplayWrapper;
import org.bukkit.entity.TextDisplay;

import java.util.HashMap;
import java.util.UUID;

public class ClipboardManager {

    private Holography main;

    private HashMap<UUID, DisplayWrapper> clipboard;

    public ClipboardManager(Holography main){
        this.main = main;

        clipboard = new HashMap<>();
    }

    public void update(UUID uuid, TextDisplay display){
        if (hasClipboard(uuid)){
            remove(uuid);
        }
        clipboard.put(uuid, new DisplayWrapper(display));
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
