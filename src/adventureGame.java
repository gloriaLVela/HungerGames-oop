
import java.util.Random;
import java.util.Scanner;

public class adventureGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        boolean gameOver = false;

        int presidentSnowHealth = 100;
        int katnissHealth = 100;

        int shield;
        int choosenWeapon;
        int userResponse;

        int potion = random.nextInt(10) + 1;

        // Weapons
        String[] weapons = new String [] {"bow and arrow", "spear", "awl", "axe", "bar mace", "baton", "blowgun", "blow torch"};
        int[] weaponsPower = new int [] {10, 15, 5, 8, 5, 3, 6, 20};

        int currentLevel = 1;
        int currentIteration = 0;

        String message;


        /**
         *  Ready to start?
         */

        System.out.println("Are you ready to start yes or no?");
        String readyToPlay = sc.nextLine();
        if (!readyToPlay.equalsIgnoreCase("yes")) {
            System.out.println("See you next time");
            gameOver = true;
        }



        /**
         *  Game on!!!!!
         */
        while (!gameOver) {

            /**
             *  Interaction with Katniss
             */
            System.out.format("\nKatniss, you have %s potions left \n", potion);
            System.out.println("\nDo you want to attack,  drink a potion or run? Attack = 1, Potion = 2, Run = 3");

            /**
             * Get Katniss response
             */
            userResponse = getInteger(1,3);

            /**
             * Running!!!
             */
            if (userResponse == 3) {
                System.out.println("I'm running!");
                break;

            }

            /**
             * Attack
             */
            if (userResponse == 1) {
                message = "Katniss, choose the weapon bow and arrow (0), spear (1)";
                for (var index = 2; (index < weapons.length && index < currentLevel + 3); index++) {
                    message += ", " + weapons[index] + "(" + (index ) + ")";
                }
                message = message + "\n";
                System.out.println(message);
                choosenWeapon = getInteger(0,currentLevel + 3);
                // Did they raise their shields?
                shield = random.nextInt(10);
                if (shield % 2 == 1) {
                    presidentSnowHealth -= weaponsPower[choosenWeapon];
                    message = "\n Katniss, the attack was successful, ";
                } else {
                    message = "\n Ketniss, even though you use the " + weapons [choosenWeapon] + " they raised the shield and the attack was not successful, ";
                }
                System.out.format("%s President Snows health is %d \n", message,presidentSnowHealth );
            } else {
                /**
                 * Drink the potion
                 */
                katnissHealth += 10;
                potion -= 1;
            }

            /**
             *  President snow attacks
             */
            shield = random.nextInt(10);
            if (shield % 2 == 1) {
                choosenWeapon = random.nextInt(currentLevel + 3);
                katnissHealth -= weaponsPower[choosenWeapon];;
                message = "\n THis is Katniss, their attack was successful and they use and the " +  weapons [choosenWeapon]  + ", ";
            } else {
                message = "\n THis is Katniss, their attack was not successful, ";
            }
            System.out.format("%s my health is %d \n", message, katnissHealth );
            if (katnissHealth == 0 || presidentSnowHealth == 0) {
                gameOver = true;
            }
            // Increase the level
            currentIteration++;
            if (currentIteration > 5 && currentLevel < 6) {
                currentLevel++;
            }
        }
        System.out.format("President Snow health: %d, Katniss health: %d, potion: %d \n", presidentSnowHealth, katnissHealth, potion);
        ;
    }

    /**
     *  Get an integer
     */

    public static int getInteger(int min, int max) {
       // int userInput;
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt()) input.next();
        int userInput = input.nextInt();

        //* If the input is invalid, prompt the user again.
        if (userInput >= min && userInput <= max) {
            return userInput;
        } else {
            System.out.format("The number %d is not between %d and %d \n", userInput, min, max);
            return getInteger(min, max);
        }
    }

}
