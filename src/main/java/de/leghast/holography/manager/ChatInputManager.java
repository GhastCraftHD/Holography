package de.leghast.holography.manager;

import java.util.*;

public class ChatInputManager {

    private final Set<UUID> registeredUUIDs = new HashSet<>();

    public void register(UUID uuid){
        registeredUUIDs.add(uuid);
    }

    public boolean isRegistered(UUID uuid){
        return registeredUUIDs.contains(uuid);
    }

    public void unregister(UUID uuid){
        registeredUUIDs.remove(uuid);
    }


}
