package de.leghast.holography.instance.settings;

import de.leghast.holography.ui.Page;
import org.bukkit.entity.Player;

public class AdjusterSettings {

    private Player player;

    private Page page;

    private DimensionSettings positionSettings;
    private FactorSettings sizeSettings;
    private DimensionSettings rotationSettings;

    private AttributeSettings attributeSettings;

    public AdjusterSettings(Player player){
        this.player = player;
        this.page = Page.POSITION;

        positionSettings = new DimensionSettings(this, 1);
        sizeSettings = new FactorSettings(this, 1);
        rotationSettings = new DimensionSettings(this, 22.5);
        attributeSettings = new AttributeSettings(this);
    }

    public Player getPlayer(){
        return player;
    }

    public DimensionSettings getPositionSettings(){
        return positionSettings;
    }

    public FactorSettings getSizeSettings(){
        return sizeSettings;
    }

    public DimensionSettings getRotationSettings(){
        return rotationSettings;
    }

    public AttributeSettings getAttributeSettings(){
        return attributeSettings;
    }

    public Page getPage(){
        return page;
    }

    public void setPage(Page page){
        this.page = page;
    }

}
