package a1;

import java.util.Random;
import java.util.Vector;

/*
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is a GameWorld object that has a collection of GameWorldObjects stored as a Vector.
 * It contains a set of methods execute user commands.
 */
public class GameWorld {
    private Vector worldObjects;
    private float randomX;
    private float randomY;
    private Random randomGenerator;
    private int numberPylons;

    public GameWorld(){
        worldObjects = new Vector();
        randomGenerator = new Random();
        numberPylons = 5;
    }

    /*
     * This method creates the initial layout of the GameWorld
     * Each object created is passed a random locationX and locationY
     * The first pylon created is set to be at the same starting location as the car.
     */
    public void initLayout(){
        float startX = randomGenerator.nextFloat() * 1000;
        float startY = randomGenerator.nextFloat() * 1000;
        worldObjects.add(new Car(startX, startY));
        worldObjects.add(new Pylon(startX, startY, 1));
        for(int i = 2; i<=numberPylons; i++){
            worldObjects.add(new Pylon(randomGenerator.nextFloat() * 1000, randomGenerator.nextFloat() * 1000, i));
        }
        worldObjects.add(new OilSlick(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new OilSlick(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new OilSlick(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new Bird(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new Bird(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
    }
    /**
     * This method finds the car object in the vector and then calls the accelerate
     * method of that car to increase the speed.
     */
    public void accelerateCar(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                Car car = (Car)gwObject;
                car.accelerate();
            }
        }
    }
    /**
     * This method finds the car object in the vector and then calls the brake
     * method of that car to decrease the speed.
     */
    public void brakeCar(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                Car car = (Car)gwObject;
                car.brake();
            }
        }
    }
    /**
     * This method finds the car object in the vector and then calls the steerLeft
     * method of that car to adjust the steering direction of the car
     */
    public void steerCarLeft(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                Car car = (Car)gwObject;
                car.steerLeft();
            }
        }
    }
    /**
     * This method finds the car object in the vector and then calls the steerRight
     * method of that car to adjust the steering direction of the car
     */
    public void steerCarRight(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                Car car = (Car)gwObject;
                car.steerRight();
            }
        }
    }

    /**
     * This method creates a new randomly located oil slick in the game world
     */
    public void createOilSlick(){
        worldObjects.add(new OilSlick(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
    }

    public void createCar(int checkpt){
        worldObjects.add(new Car(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000, checkpt));
    }

    public void loseLife(){
        Car car = new Car();
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                car = (Car)gwObject;
                break;
            }
        }
        int checkpt = car.getCheckPoint();
        worldObjects.remove(car);
        createCar(checkpt);
    }

    public void createFuelCan(){
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
    }

    //returns True if the car can continue to play after collision
    public boolean carCollision(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                Car car = (Car)gwObject;
                car.damaged(new Car());
                return (car.canMove());
            }
        }
        return false;
    }

    public boolean birdCollision(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                Car car = (Car)gwObject;
                car.damaged(new Bird());
                return (car.canMove());
            }
        }
        return false;
    }
    public boolean pylonExists(int pylonNum){
        for(Object gwObject : worldObjects) {
            if (gwObject instanceof Pylon) {
                Pylon p = (Pylon) gwObject;
                if (p.getSequenceNumber() == pylonNum) {
                    return true;
                }
            }
        }
        return false;
    }

    public void pylonCollision(int pylonNum){
        Car car = new Car();
        for(Object gwObject : worldObjects) {
            if (gwObject instanceof Car) {
                car = (Car) gwObject;
            }
        }
            car.pylonCollision(pylonNum);
    }

    //Returns true if the car has collided with the last pylon and has won the race
    public boolean checkWin(){
        return(this.getHighestPylon() == numberPylons );
    }

    public void fuelCanCollision(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof FuelCan){
                Car car = (Car)worldObjects.get(0);
                car.addFuel(((FuelCan) gwObject).getSize());
                worldObjects.remove(gwObject);
                createFuelCan();
            }
        }
    }

    public void carEnteredOilSlick(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                Car car = (Car)gwObject;
                car.setStuck(true);
            }
        }
    }

    public void carExitedOilSlick(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Car){
                Car car = (Car)gwObject;
                car.setStuck(false);
            }
        }
    }

    //Changes the color of every object who is of type IColorable
    public void changeObjectsColors(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof IColorable){
                IColorable obj = (IColorable)gwObject;
                obj.changeColor();
            }
        }
    }

    public void updateAllMoveables(){
        for(Object gwObject : worldObjects){
            if(gwObject instanceof Moveable){
                Moveable obj = (Moveable)gwObject;
                obj.move();
            }
        }
    }

    public boolean carCanMove(){
        for(Object gwObject : worldObjects) {
            if (gwObject instanceof Car) {
                Car car = (Car) gwObject;
                return car.canMove();
            }
        }
        return false;
    }

    public int getHighestPylon(){
        for(Object gwObject : worldObjects) {
            if (gwObject instanceof Car) {
                Car car = (Car) gwObject;
                return car.getCheckPoint();
            }
        }
        return 0;
    }

    public int getCarFuelLevel(){
        for(Object gwObject : worldObjects) {
            if (gwObject instanceof Car) {
                Car car = (Car) gwObject;
                return car.getFuelLevel();
            }
        }
        return 0;
    }

    public int getCarDamageLevel(){
        for(Object gwObject : worldObjects) {
            if (gwObject instanceof Car) {
                Car car = (Car) gwObject;
                return car.getDamageLevel();
            }
        }
        return 0;
    }

    public String toString(){
        String string = "";
        int count = worldObjects.size();
        for(int i = 0; i<count; i++){
            string = string + worldObjects.get(i).toString() + "\n";
        }
        return string;
    }

}
