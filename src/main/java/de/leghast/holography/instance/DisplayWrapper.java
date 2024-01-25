package de.leghast.holography.instance;

import de.leghast.holography.util.Util;
import org.bukkit.Axis;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;

public class DisplayWrapper {

    private TextDisplay display;

    public DisplayWrapper(TextDisplay display){
        this.display = display;
    }

    public TextDisplay getDisplay(){
        return display;
    }

    public void move(Axis axis, double factor){
        switch(axis){
            case X -> display.teleport(display.getLocation().add(factor, 0, 0));
            case Y -> display.teleport(display.getLocation().add(0, factor, 0));
            case Z -> display.teleport(display.getLocation().add(0, 0, factor));
        }
    }

    public void rotate(Axis axis, double factor){
        float radiant = (float) Math.toRadians(factor);
        Transformation transformation = display.getTransformation();
        switch (axis){
            case X -> transformation.getLeftRotation().rotateX(radiant);
            case Y -> transformation.getLeftRotation().rotateY(radiant);
            case Z -> transformation.getLeftRotation().rotateZ(radiant);
        }
        display.setTransformation(transformation);
    }

    public void scaleUp(double factor){
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(transformation.getScale().x * factor);
        display.setTransformation(transformation);
    }

    public void scaleDown(double factor){
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(transformation.getScale().x / factor);
        display.setTransformation(transformation);
    }

    public void setTextOpacity(String opacity, Player player){
        try{
            int newOpacity = Integer.parseInt(opacity);
            if(newOpacity < 25 || newOpacity > 255){
                throw new NumberFormatException();
            }else if(newOpacity > 127){
                newOpacity -= 256;
            }
            display.setTextOpacity((byte) newOpacity);
            player.sendMessage(Util.PREFIX + "§aSet text opacity to §e" + opacity + "§a");
        }catch (NumberFormatException e){
            player.sendMessage(Util.PREFIX + "§cPlease enter a valid opacity (25 - 255)");
        }
    }

    public void remove(){
        display.remove();
    }

}
