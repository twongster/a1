package a1;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This is an abstract class that contains constructors and ____
 * to manipulate and create objects of type Moveable. A moveable object
 * implies that the object's location will change based on a speed and heading.
 */
public abstract class Moveable extends GameWorldObjects {
    private int heading;
    private int speed;


    public void move(){
        double angle = 90.0 - (double)this.getHeading();
        double deltaX = Math.cos(angle) * this.getSpeed();
        double deltaY = Math.sin(angle) * this.getSpeed();

        float newX = this.getLocationX() + (float)deltaX;
        float newY = this.getLocationX() + (float)deltaY;

        this.setLocationY(newY);
        this.setLocationX(newX);
    }

    public void setLocation(float locationX, float locationY){
        this.setLocationX(locationX);
        this.setLocationY(locationY);
    }



    public void setHeading(int h){
        heading = h;
    }

    public void setSpeed(int s){
        speed = s;
    }



    public int getHeading() {
        return heading;
    }

    public int getSpeed() {
        return speed;
    }

    public String toString(){
        String string = "";
        string = super.toString() +"heading=" + this.getHeading() + " speed=" + this.getSpeed();
        return string;
    }
}
