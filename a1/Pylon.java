package a1;

import java.awt.*;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is a Pylon object which is a fixed and non-colorable game object.
 */
public class Pylon extends Fixed {
    private int radius;
    private int sequenceNumber;

    public Pylon(float locationX, float locationY, int sequenceNumber) {
            this.setColor(Color.GREEN);
            this.setSequenceNumber(sequenceNumber);
            this.setRadius(11);
            this.setLocationY(locationY);
            this.setLocationX(locationX);
    }

    public void setRadius(int radius){
        this.radius= radius;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getSequenceNumber(){
        return sequenceNumber;
    }

    public int getRadius(){
        return radius;
    }

    void setColor(Color color){
        this.color = color;
    }

    Color getColor() {
        return color;
    }



    public String toString(){
        String string = "";
        string = "Pylon: " + super.toString()+ " radius=" + this.getRadius() + " seqNum=" + this.getSequenceNumber();
        return string;
    }
}
