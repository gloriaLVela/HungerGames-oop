
import java.util.Random;
import java.util.Scanner;

public class adventureGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int presidentSnowHealth = 100;
        int katnissHealth = 100;
        int shield = 0;
        boolean gameOver = false;


        int userResponse;

        int PresdentSnowsResponse;

        Random random = new Random();
        int potion = random.nextInt(10) + 1;
        System.out.println("Are you ready to start yes or no?");
        String readyToPlay = sc.nextLine();
        if (!readyToPlay.equalsIgnoreCase("yes")) {
            System.out.println("See you next time");
            gameOver = true;
        }

        String message;


        while (!gameOver) {
            System.out.format("\nKatniss, you have %s potions left \n", potion);
            System.out.println("\nDo you want to attack,  drink a potion or run? Attack = 1, Potion = 2, Run = 3");
            userResponse = getInteger(1,3); //sc.nextInt();
            if (userResponse == 3) {
                System.out.println("I'm running!");
                break;

            }
            if (userResponse == 1) {
                shield = random.nextInt(10);
                if (shield % 2 == 1) {
                    presidentSnowHealth -= 10;
                    message = "\n Katniss, the attack was successful, ";
                } else {
                    message = "\n Ketniss, the attack was not successful, ";
                }
                System.out.format("%s President Snows health is %d \n", message,presidentSnowHealth );
            } else {
                katnissHealth += 10;
                potion -= 1;
            }

            // President Snow attack
            shield = random.nextInt(10);
            if (shield % 2 == 1) {
                katnissHealth -= 10;
                message = "\n THis is Katniss, their attack was successful, ";
            } else {
                message = "\n THis is Katniss, their attack was not successful, ";
            }
            System.out.format("%s my health is %d \n", message, katnissHealth );
            if (katnissHealth == 0 || presidentSnowHealth == 0) {
                gameOver = true;
            }
        }
        System.out.format("President Snow health: %d, Katniss health: %d, potion: %d \n", presidentSnowHealth, katnissHealth, potion);
        ;
    }

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
