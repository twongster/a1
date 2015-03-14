package a1;
import java.util.Random;
import java.awt.*;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is a Fuel Can object which is a fixed and colorable game object.
 * Fuel cans assist the player in providing fuel to the car to continue movement
 * throughout the game world.
 */
public class FuelCan extends Fixed implements IColorable {
    private int size;

    public FuelCan(float locationX, float locationY){
        this.setSize(3 + randomGenerator.nextInt(6));
        this.changeColor();
        this.setLocationY(locationY);
        this.setLocationX(locationX);
    }
    public void setSize(int size){
        this.size = size;
    }

    public int getSize(){
        return size;
    }


    public void changeColor() {
        Random randomGenerator = new Random();
        float r = randomGenerator.nextFloat();
        float g = randomGenerator.nextFloat();
        float b = randomGenerator.nextFloat();

        Color randomColor = new Color(r, g, b);

        this.setColor(randomColor);
    }


    void setColor(Color color){
        this.color = color;
    }

    Color getColor() {
        return color;
    }

    public String toString(){
        String string = "";
        string = "FuelCan: "+ super.toString() + " size=" + this.getSize();
        return string;
    }

}
