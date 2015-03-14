package a1;
import java.util.Scanner;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class instantiates a GameWorld object and processes user input.
 * This class manages the flow of control in the game (controller).
 * This class also display information about the state of the game.
 */
public class Game {
    public Game(){
        GameWorld gw = new GameWorld();
        gw.initLayout();
        play(gw);

    }

    /**
     * This method accepts and executes user commands that operate
     * on the GameWorld object.
     */
    public static void play(GameWorld gw){
        Scanner keyboard = new Scanner(System.in);
        int clock = 0;
        String invalidMessage = "I'm sorry command was invalid.\n Please enter in either a, b, l, r, o, c, f, g, e, x, n, t, d, m, q, or p(followed by a pylon number).\n\n";
        int lives = 3;
        String lifeLost = "Oh no! You took on too much damage or ran out of gas! You lost 1 life. Lives remaining: ";
        do{
            System.out.println("Welcome to the Racing Game!\nPlease enter in a command:\n");
            String input = keyboard.nextLine();
            //Checks the validity of the user's input
            if(input.length() <= 3){
                if(input.length() == 1){
                    input = input.toLowerCase();
                    //Checks user's input and executes the corresponding method
                    //on the GameWorld object
                    switch (input.charAt(0)){
                        case 'a': //the user chooses to accelerate the car
                            gw.accelerateCar();
                            break;
                        case 'b': //the user chooses to brake the car
                            gw.brakeCar();
                            break;
                        case 'l'://the user chooses to steer the car left
                            gw.steerCarLeft();
                            break;
                        case 'r'://the user chooses to steer the car right
                            gw.steerCarRight();
                            break;
                        case 'o'://the user chooses to create a new oil slick in the GameWorld
                            gw.createOilSlick();
                            break;
                        case 'c': //the user chooses to collide the car with another car
                            //if the car collision results in the car being unable to move then the player loses a life. the damaged car is removed and a new car is created.
                            if(!gw.carCollision()){
                                lives = lives -1;
                                gw.loseLife();
                                System.out.println("\n" + lifeLost + lives);
                            }
                            break;
                        case 'f': //the user chooses to collide the car with a fuel can
                            gw.fuelCanCollision();
                            break;
                        case 'g': //the user chooses to collide the car with a bird
                            //if the bird collision results in the car being unable to move then the player loses a life. the damaged car is removed and a new car is created.
                            if(!gw.birdCollision()){
                                lives = lives -1;
                                gw.loseLife();
                                System.out.println("\n" + lifeLost + lives);
                            }
                            break;
                        case 'e': //the user chooses to have the car enter an oil slick
                            gw.carEnteredOilSlick();
                            break;
                        case 'x': //the user chooses to have the car exit an oil slick
                            gw.carExitedOilSlick();
                            break;
                        case 'n': //the user chooses to have all colorable objects change their color
                            gw.changeObjectsColors();
                            break;
                        case 't': //the user chooses to advance the game clock by 1 tick
                            clock = clock + 1;
                            gw.updateAllMoveables();
                            //if the move results in the car being unable to move then the player loses a life. the damaged car is removed and a new car is created.
                            if(!gw.carCanMove()){
                                lives = lives -1;
                                gw.loseLife();
                                System.out.println("\n" + lifeLost + lives);
                            }
                            break;
                        case 'd': //the user chooses to diplay the current game state values
                            System.out.println(display(lives, clock, gw));
                            break;
                        case 'm': //the user chooses to diplay the current game world
                            System.out.println(gw);
                            break;
                        case 'q': //the user chooses to quit the game
                            boolean notValid = true;
                            //checks to ensure that user input is valid
                            while(notValid) {
                                System.out.println("Are you sure you want to quit the game? Y/N");
                                input = keyboard.nextLine();
                                input = input.toLowerCase();
                                //if the user input is valid and the user chooses to quit the game the program is exited, else the game continues
                                if(input.length() == 1){
                                    if(input.charAt(0) == 'y'){
                                        System.exit(0);
                                    } else if(input.charAt(0) == 'n'){
                                        notValid = false;
                                    }
                                } else{
                                    System.out.println("Invalid response.");
                                }
                            }
                            break;
                    }

                } else if(input.charAt(0) == 'p'){ //if user chooses collision with a pylon
                    //checks to see if user input resembles the format [p0#]
                    if(input.charAt(1) == '0'){
                        input = input.substring(2,input.length());
                    } else{
                        input = input.substring(1,input.length());
                    }
                    //checks that the input is numeric
                    if(isNumeric(input)){
                        if(gw.pylonExists(Integer.parseInt(input))){
                            gw.pylonCollision(Integer.parseInt(input));
                            if(gw.checkWin()){
                                break;
                            }
                        } else{
                            //if the pylon does not exist in the game world an error message is displayed
                            System.out.println("Pylon " + input + " does not exist.");
                        }
                    } else{
                        System.out.println(invalidMessage);
                    }
                } else{
                    System.out.println(invalidMessage);
                }
            } else{
                System.out.println(invalidMessage);
            }

        } while(lives !=0);
        if(lives == 0){
            System.out.println("Game over! No lives remaining! :(");
        } else {
            System.out.println("Congratulations! You won! :D\n");
            System.out.println("\nStats:\n");
            System.out.println(display(lives, clock, gw));
        }

        //the game continues while the player still has lives
    }

    //Returns true if the string passed in is numeric and can be converted to type int
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static String display(int lives, int clock, GameWorld gw){
        String output = "Lives : " + lives + "\nClock: " + clock;
        if(clock == 1){
            output = output + " tick";
        } else{
            output = output + " ticks";
        }
        output = output + "\nHighest Pylon Reached: " + gw.getHighestPylon() + "\nFuel Level: " + gw.getCarFuelLevel() + "\nDamage Level: " + gw.getCarDamageLevel();
        return output;
    }
}

