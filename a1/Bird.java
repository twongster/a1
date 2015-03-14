package a1;

import java.awt.*;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is a Bird object which is a moveable and non-colorable game object.
 * Bird objects serve as obstacles to the player that when collided with cause
 * damage to the vehicle.
 */
public class Bird extends Moveable {
    private int size;

    public Bird(float locationX, float locationY) {
        this.size = 5 + randomGenerator.nextInt(6);
        this.setLocationY(locationY);
        this.setLocationX(locationX);
        this.setColor(Color.DARK_GRAY);
        this.setHeading(randomGenerator.nextInt(360));
        this.setSpeed(3+ randomGenerator.nextInt(3));

    }

    public Bird() {
        new Bird(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000);
    }



    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    void setColor(Color color){
        this.color = color;
    }

    Color getColor() {
        return color;
    }

    public String toString(){
        String string = "";
        string = "Bird: " + super.toString() + " size=" + this.getSize();
        return string;
    }


}
