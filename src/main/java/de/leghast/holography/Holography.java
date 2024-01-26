package de.leghast.holography;

import de.leghast.holography.command.HologramCommand;
import de.leghast.holography.listener.InventoryClickListener;
import de.leghast.holography.listener.PlayerInteractListener;
import de.leghast.holography.listener.PlayerJoinListener;
import de.leghast.holography.manager.ClipboardManager;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.manager.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

public final class Holography extends JavaPlugin {

    private ClipboardManager clipboardManager;
    private SettingsManager settingsManager;

    private String owner = "LeGhast";
    private String repo = "Holography";

    private boolean updateAvailable;

    @Override
    public void onEnable() {
        clipboardManager = new ClipboardManager(this);
        settingsManager = new SettingsManager();
        ConfigManager.setupConfig(this);
        getCommand("hologram").setExecutor(new HologramCommand(this));
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        updateAvailable = isUpdateAvailable("v" + getDescription().getVersion());
    }

    @Override
    public void onDisable(){
        this.saveConfig();
    }

    public ClipboardManager getClipboardManager(){
        return clipboardManager;
    }

    public SettingsManager getSettingsManager(){
        return settingsManager;
    }

    public String getLatestReleaseVersion(){
        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/releases/latest";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    return response.toString().contains("tag_name")
                            ? response.toString().split("\"tag_name\":\"")[1].split("\",")[0]
                            : null;
                } else {
                    getLogger().log(Level.CONFIG,"Error: " + connection.getResponseCode() + " " + connection.getResponseMessage());
                    return null;
                }
            } finally {
                connection.disconnect();
            }
        }catch (Exception e){
            return null;
        }
    }

    private boolean isUpdateAvailable(String currentVersion){
        String latestVersion = getLatestReleaseVersion();
        return latestVersion != null && !latestVersion.equals(currentVersion);
    }

    public boolean isUpdateAvailable(){
        return updateAvailable;
    }
}
