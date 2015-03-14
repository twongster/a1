package a1;

import java.awt.*;
import java.util.Random;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is an object of type car that is a moveable, colorable, and steerable game world object.
 * In this class there are methods that process the movement of the car as well as it's behavior
 * after collisions with fuel cans, birds, pylons, and other cars.
 */
public class Car extends Moveable implements ISteerable, IColorable {
    private int width;
    private int length;
    private int steeringDirection;
    private int maximumSpeed;
    private int maximumDamage;
    private int fuelLevel;
    private int damageLevel;
    private int checkPoint;
    private boolean stuck;

    public Car(float locationX, float locationY){
        this.setWidth(15);
        this.setLength(27);
        this.setLocation(locationX, locationY);
        this.setSteeringDirection(0);
        this.setMaximumSpeed(60);
        this.setMaximumDamage(100);
        this.setFuelLevel(100);
        this.setDamageLevel(0);
        this.setCheckPoint(1);
        this.changeColor();
        this.setSpeed(0);
        this.setStuck(false);
    }

    public Car(float locationX, float locationY, int checkPoint){
        this.setWidth(15);
        this.setLength(27);
        this.setLocation(locationX, locationY);
        this.setSteeringDirection(0);
        this.setMaximumSpeed(60);
        this.setMaximumDamage(100);
        this.setFuelLevel(100);
        this.setDamageLevel(0);
        this.setCheckPoint(checkPoint);
        this.changeColor();
        this.setSpeed(0);
        this.setStuck(false);
    }


    public Car(){
        this.setWidth(15);
        this.setLength(27);
        this.setLocation(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000);
        this.setSteeringDirection(0);
        this.setMaximumSpeed(60);
        this.setMaximumDamage(60);
        this.setFuelLevel(100);
        this.setDamageLevel(0);
        this.changeColor();
    }

    //Getter and setter Methods that allow manipulation of the Car's attributes
    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length){
        this.length = length;
    }

    public void setSteeringDirection(int steeringDirection){
        this.steeringDirection = steeringDirection;
    }

    public void setMaximumSpeed(int maximumSpeed){
        this.maximumSpeed = maximumSpeed;
    }

    public void setFuelLevel(int fuelLevel){
        this.fuelLevel = fuelLevel;
    }

    public void  setDamageLevel(int damageLevel){
        this.damageLevel = damageLevel;
    }
    public int getWidth(){
        return width;
    }

    public int getLength(){
        return length;
    }

    public int getSteeringDirection(){
        return steeringDirection;
    }

    public int getMaximumSpeed(){
        return maximumSpeed;
    }

    public int getFuelLevel(){
        return fuelLevel;
    }

    public int getDamageLevel(){
        return damageLevel;
    }

    public void setMaximumDamage(int maximumDamage){
        this.maximumDamage = maximumDamage;
    }

    //Increases the damage level and adjusts the level of the maximum speed
    // depending on the amount of damage acquired by the collision
    public void increaseDamage(int amount){
        this.damageLevel = this.damageLevel + amount;
        double proportion = (double)(this.damageLevel)/(double)(this.maximumDamage);
        this.setMaximumSpeed((int)(this.getMaximumSpeed() * (1-proportion)));
        if(this.getSpeed() > this.getMaximumSpeed()){
            this.setSpeed(this.getMaximumSpeed());
        }
    }

    public int getMaximumDamage(){
        return maximumDamage;
    }

    //Returns true if the car is in an oil slick or not
    public boolean isStuck(){
        return stuck;
    }

    public void setStuck(boolean stuck){
        this.stuck = stuck;
    }

    public boolean hasGas(){
        return (this.getFuelLevel() > 0);
    }

    void setColor(Color color){
        this.color = color;
    }

    Color getColor() {
        return color;
    }

    //Returns the last pylon hit by the car
    public int getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(int checkPoint) {
        this.checkPoint = checkPoint;
    }

    //Changes the color of the car to a random color
    public void changeColor() {
        Random randomGenerator = new Random();
        float r = randomGenerator.nextFloat();
        float g = randomGenerator.nextFloat();
        float b = randomGenerator.nextFloat();

        Color randomColor = new Color(r, g, b);

        this.setColor(randomColor);
    }

    public void steerLeft(){
        if(this.getSteeringDirection() > -40 ){
            this.setSteeringDirection(this.getSteeringDirection()-5);
        }
    }


    public void steerRight(){
        if(this.getSteeringDirection() < 40 ){
            this.setSteeringDirection(this.getSteeringDirection()+5);
        }
    }

    //Returns True if the car is at it's maximum speed
    public boolean atMaxSpeed(){
        return(this.getSpeed() >= this.getMaximumSpeed());
    }

    //Returns True if the car is at it's maximum damage level
    public boolean atMaxDamage(){
        return (this.getDamageLevel() >= this.getMaximumDamage());
    }

    //Returns true if the car can move
    public boolean canMove(){
        return (!this.atMaxDamage() && this.hasGas());
    }

    //updates the heading of the car
    public void updateHeading(){
        int newHeading = this.getHeading() + this.getSteeringDirection();
        if (newHeading >= 360){
            this.setHeading(newHeading-360);
        } else if(newHeading <= -360){
            this.setHeading(newHeading+360);
        } else{
            this.setHeading(newHeading);
        }
    }

    public void accelerate(){
        if(!this.atMaxSpeed() && this.hasGas() && !this.atMaxDamage() && !isStuck()){
            this.setSpeed(this.getSpeed() + 5);
        }
    }

    public void brake(){
        if(!this.atMaxSpeed() && this.hasGas() && !this.atMaxDamage() && !isStuck() && (this.getSpeed() != 0)){
            this.setSpeed(this.getSpeed() - 5);
        }
    }

    //Returns true if the pylon collided is the next sequential pylon
    //updates the last pylon the car has collided with
    public boolean pylonCollision(int pylon){
        if(pylon == (this.getCheckPoint() + 1)){
            this.setCheckPoint(pylon);
            return true;
        } else{
            return false;
        }
    }

    //Increases the damage level of the car based on what object it collides with
    public void damaged(Object x){
        if(x instanceof Bird){
            if((this.getDamageLevel() + 5) >= this.getMaximumDamage()){
                this.setDamageLevel(this.getMaximumDamage());
            } else{
                increaseDamage(5);
            }
        } else if(x instanceof Car){
            if((this.getDamageLevel() + 10) >= this.getMaximumDamage()){
                this.setDamageLevel(this.getMaximumDamage());
            } else{
                increaseDamage(10);
            }
        }
    }

    //Adds fuel to the car based on the amount passed in
    public void addFuel(int amount) {
        if ((this.getFuelLevel()+amount) >= 100){
            this.setFuelLevel(100);
        } else{
            this.setFuelLevel(this.getFuelLevel() + amount);
        }
    }

    public void decreaseFuel(){
        if((this.getFuelLevel()-5)<= 0){
            this.setFuelLevel(0);
        } else{
            this.setFuelLevel(this.getFuelLevel()-5);
        }
    }

    public String toString(){
            String string = "";
            string = "Car: " + super.toString() + " width=" + this.getWidth()+ " length=" + this.getLength() + " maxSpeed=" + this.getMaximumSpeed() + " steeringDirection=" + this.getSteeringDirection() + " fuelLevel=" + this.getFuelLevel() + " damage=" + this.getDamageLevel();
            string = string + " last Pylon = " + this.getCheckPoint();
            return string;

    }

    //Moves the car depending on it's value of being in a oil slick or not
    public void move(){
        if(!this.isStuck() && this.canMove()){
                this.updateHeading();
                super.move();
                this.decreaseFuel();
        }else if (this.isStuck() && this.canMove()){
            super.move();
            this.decreaseFuel();
        }
    }


}
