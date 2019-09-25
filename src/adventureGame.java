
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
        int snowAnswer;

        int potion = random.nextInt(10) + 1;

        // Weapons
        String[] weapons = new String[]{"bow and arrow", "spear", "awl", "axe", "bar mace", "baton", "blowgun", "blow torch"};
        int[] weaponsPower = new int[]{10, 15, 5, 8, 5, 3, 6, 20};

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
            userResponse = getInteger(1, 3);

            /**
             * Running!!!
             */
            if (userResponse == 3) {
                System.out.println("This is Katniss and I decided to run !");
                break;

            }

            /**
             * Katniss attacks
             */
            if (userResponse == 1) {
                /**
                 * Build the weapon list
                 */
                message = "Katniss, choose the weapon bow and arrow (0), spear (1)";
                for (var index = 2; (index < weapons.length && index < currentLevel + 3); index++) {
                    message += ", " + weapons[index] + "(" + (index) + ")";
                }
                message = message + "\n";
                System.out.println(message);
                choosenWeapon = getInteger(0, currentLevel + 3);
                /**
                 * Did they raise the shields?
                 */
                shield = random.nextInt(100);
                if (shield % 7 == 1) {
                    presidentSnowHealth -= weaponsPower[choosenWeapon];
                    message = "\nKatniss, the attack was successful, the damage was " + weaponsPower[choosenWeapon];
                } else {
                    message = "\nKetniss, even though you use the " + weapons[choosenWeapon] + " they raised the shield and the attack was not successful, ";
                }
                System.out.format(" %s President Snows health is %d \n", message, presidentSnowHealth);
            } else {
                /**
                 * Katniss drinks a potion
                 */
                katnissHealth += 10;
                potion -= 1;
            }

            /**
             *  President snow next action
             */

            snowAnswer = random.nextInt(100);
            /**
             * President Snow can decide to run away if he is almost dead.
             */
            if (presidentSnowHealth <= 30 && snowAnswer % 23 == 0) {
                System.out.println("\n====================================================================");
                System.out.println("President Snow runs away... and he may comeback");
                System.out.println("====================================================================");
                break;

            }

            /**
             * President Snow attacks
             */

            System.out.println("\n====================================================================");
            System.out.println("President Snow attacks");
            System.out.println("====================================================================");

            /**
             * Katniss may raise her shield on time
             */

            shield = random.nextInt(100);
            if (shield % 7 == 1) {
                choosenWeapon = random.nextInt(currentLevel + 3);
                katnissHealth -= weaponsPower[choosenWeapon];
                message = "\nThis is Katniss, their attack was successful, they use and the " + weapons[choosenWeapon] + ", and the damage was " + weaponsPower[choosenWeapon] + " ";
            } else {
                message = "\nThis is Katniss, their attack was not successful, ";
            }
            System.out.format("%s my health is %d \n", message, katnissHealth);

            /**
             * The game is over when either one's health is below 0
             */
            if (katnissHealth <= 0 || presidentSnowHealth <= 0) {
                gameOver = true;
            }
            /**
             * Increase the level
             */
            currentIteration++;
            if (currentIteration > 5 && currentLevel < 6) {
                currentLevel++;
            }

            System.out.println("\n====================================================================");
            System.out.println("Feedback to Katniss");
            System.out.println("====================================================================");
        }
        if (katnissHealth <= 0) {
            System.out.println("This is president Snow, Katniss is in danger, we may comeback again.");
        } else {
            System.out.println("Katniss saved the world again!");
        }
        System.out.format("President Snow health: %d, Katniss health: %d, potion: %d \n", presidentSnowHealth, katnissHealth, potion);
        ;
    }

    /**
     * Get an integer
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
            System.out.format("The option %d does not exists. Please choose between %d and %d \n", userInput, min, max);
            return getInteger(min, max);
        }
    }

}
