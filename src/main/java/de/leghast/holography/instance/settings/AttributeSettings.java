package de.leghast.holography.instance.settings;

import de.leghast.holography.ui.Attribute;

public class AttributeSettings {

    private AdjusterSettings parent;

    private Attribute attribute = Attribute.TEXT;

    public AttributeSettings(AdjusterSettings parent){
        this.parent = parent;
    }

    public Attribute getAttribute(){
        return attribute;
    }

    public void setAttribute(Attribute attribute){
        this.attribute = attribute;
    }

}
