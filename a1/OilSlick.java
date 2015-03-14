package a1;
import java.util.Random;
import java.awt.*;
/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is an Oil Slick object which is a fixed and colorable game object.
 * Oil slicks represent obstacles to the player that when collided with restrict steering.
 */
public class OilSlick extends Fixed implements IColorable{
    private int width;
    private int length;

    //allows the user to create an oilslick by passing in it's X and Y coordinates
    public OilSlick(float locationX, float locationY) {
        this.setWidth(30 + randomGenerator.nextInt(10));
        this.setLength(30 + randomGenerator.nextInt(10));
        this.setLocationX(locationX);
        this.setLocationY(locationY);
        this.changeColor();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    void setColor(Color color){
        this.color = color;
    }

    Color getColor() {
        return this.color;
    }

    public void changeColor() {
        Random randomGenerator = new Random();
        float r = randomGenerator.nextFloat();
        float g = randomGenerator.nextFloat();
        float b = randomGenerator.nextFloat();

        Color randomColor = new Color(r, g, b);

        this.setColor(randomColor);
    }


    public String toString(){
        String string = "";
        string = "OilSlick: " + super.toString() + " width=" + this.getWidth()+ " length=" + this.getLength();
        return string;
    }
}
