package a1;

import java.awt.*;
import java.util.Random;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This abstract class holds the qualities, setters, and getters shared by all game world objects.
 */
public abstract class GameWorldObjects {
    Color color;
    Random randomGenerator = new Random();
    float locationX;
    float locationY;

    abstract void setColor(Color color);

    abstract Color getColor();

    public void setLocationX(float x){
        locationX = x;
    }

    public void setLocationY(float y){
        locationY = y;
    }

    public float getLocationX(){
        return locationX;
    }

    public float getLocationY(){
        return locationY;
    }


    public String toString(){
        String string = "loc=" + this.getLocationX() + "," + this.getLocationY() + " color=[" + this.getColor().getRed() + "," + this.getColor().getGreen() + "," + this.getColor().getBlue() + "] ";
        return string;
    }


}
