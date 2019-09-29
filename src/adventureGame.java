
import java.util.Random;
import java.util.Scanner;

public class adventureGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        boolean gameOver = false;

        Villain badGuy = new Villain("President Snow");
        Hero hero = new Hero("Katniss", random.nextInt(10) + 1);


        int shield;
        int choosenWeapon;
        int heroResponse;
        int snowAnswer;

        int potion = random.nextInt(10) + 1;

        // Weapons
        String[] weapons = new String[]{"bow and arrow", "spear", "awl", "axe", "bar mace", "baton", "blowgun", "blow torch"};
        int[] weaponsPower = new int[]{10, 15, 5, 8, 5, 3, 6, 20};

//        int currentLevel = 1;
//        int currentIteration = 0;

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
            System.out.format("\n%s, you have %s potions left \n", hero.getName(), potion);
            if(hero.getPotionsLeft() >0) {
                System.out.println("\nDo you want to attack,  drink a potion or run? Attack = 1, Run = 2, Potion = 3");
                /**
                 * Get Katniss response
                 */
                heroResponse = getInteger(1, 3);
            } else {
                System.out.println("\nDo you want to attack,  drink a potion or run? Attack = 1, Run = 2");
                /**
                 * Get Katniss response
                 */
                heroResponse = getInteger(1, 2);
            }


            /**
             * Running!!!
             */
            if (heroResponse == 2) {
                System.out.format("This is %s and I decided to run !", hero.getName());
                break;

            }

            /**
             * The hero attacks
             */
            if (heroResponse == 1) {
                /**
                 * Build the weapon list
                 */
                message = String.format("%s, choose the weapon bow and arrow (0), spear (1)", hero.getName());
                for (var index = 2; (index < weapons.length && index < hero.getCurrentLevel() + 3); index++) {
                    message += ", " + weapons[index] + "(" + (index) + ")";
                }
                message = message + "\n";
                System.out.println(message);
                choosenWeapon = getInteger(0, hero.getCurrentLevel() + 3);
                /**
                 * Did they raise the shields?
                 */
                shield = random.nextInt(10);
                if (shield % 2 == 0) {
                    badGuy.decreaseLives(weaponsPower[choosenWeapon]);
                    //presidentSnowHealth -= weaponsPower[choosenWeapon];
                    message = "\nKatniss, the attack was successful, the damage was " + weaponsPower[choosenWeapon];
                    hero.increaseSucessfullAttackTotal();
                    hero.increaseLevel();

                } else {
                    message = "\nKetniss, even though you use the " + weapons[choosenWeapon] + " they raised the shield and the attack was not successful, ";
                }
                System.out.format(" %s President Snows health is %d \n", message, badGuy.getHealthLeft());
            } else {
                /**
                 * Katniss drinks a potion
                 */
                hero.drinkPotion();
            }

            /**
             *  President snow next action
             */

            snowAnswer = random.nextInt(100);
            /**
             * President Snow can decide to run away if he is almost dead.
             */
            if (badGuy.getHealthLeft() <= 30 && snowAnswer % 23 == 0) {
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

            shield = random.nextInt(10);
            if (shield % 2 == 0) {
                choosenWeapon = random.nextInt(hero.getCurrentLevel() + 3);
                hero.decreaseLives(weaponsPower[choosenWeapon]);
                message = String.format("\nThis is %s, their attack was successful, they use and the %s, and the damage was %d ", hero.getName(), weapons[choosenWeapon], weaponsPower[choosenWeapon]);
                badGuy.increaseSucessfullAttackTotal();
                badGuy.increaseLevel();
            } else {
                message = "\nThis is Katniss, their attack was not successful, ";
            }
            System.out.format("%s my health is %d \n", message, hero.getHealthLeft());

            /**
             * The game is over when either one's health is below 0
             */
            if (hero.getHealthLeft() <= 0 || badGuy.getHealthLeft() <= 0) {
                gameOver = true;
            }

            System.out.println("\n====================================================================");
            System.out.println("Feedback to Katniss");
            System.out.println("====================================================================");
        }
        if (hero.getHealthLeft() <= 0) {
            System.out.format("This is president Snow, %s is in danger, we may comeback again.", hero.getName());
        } else {
            System.out.println("Katniss saved the world again!");
        }
        System.out.format("President Snow health: %d, Katniss health: %d, potion: %d \n", badGuy.getHealthLeft(), hero.getHealthLeft(), potion);
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
