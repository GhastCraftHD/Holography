package de.leghast.holography.settings;

import de.leghast.holography.constant.Message;
import de.leghast.holography.ui.AnvilInputHelper;

public class FactorSettings {

    private AdjusterSettings parent;

    private double factor;

    public FactorSettings(AdjusterSettings parent, double factor){
        this.parent = parent;
        this.factor = factor;
    }

    public double getFactor(){
        return factor;
    }

    public void setFactor(double factor){
        this.factor = factor;
    }

    public void setFactor(String factor){
        try{
            this.factor = Double.parseDouble(factor);
            parent.getPlayer().sendMessage(Message.changedFactor(this.factor));
        }catch(NumberFormatException e){
            parent.getPlayer().sendMessage(Message.INVALID_FACTOR);
        }
    }

}
