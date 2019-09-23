import jdk.dynalink.StandardNamespace;

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
        int potion = random.nextInt(10)+ 1;
        System.out.println("Are you ready to start yes or no?");
        String readyToPlay = sc.nextLine();
        if(!readyToPlay.equalsIgnoreCase("yes")){
            System.out.println("See you next time");
            gameOver = true;
        }
        while (!gameOver) {
            System.out.format("You have %s potions left", potion);
            System.out.println("\nDo you want to attack or drink a potion? Attack = 1, Potion = 2, Run = 3");
            userResponse = sc.nextInt();
            if(userResponse == 3){
                System.out.println("I'm running!");
                break;

            }
            if(userResponse == 1){
                shield = random.nextInt(10);
                if(shield % 2 == 1){
                    presidentSnowHealth -= 10;
                    System.out.format("\nAttack was successful, President Snows health %d", presidentSnowHealth);
                }else {
                    System.out.format("\nAttack was not successful, President Snows health %d", presidentSnowHealth);
                }
            }else {
                katnissHealth += 10;
                potion -=1;
            }

            // President Snow attack
            shield = random.nextInt(10);
            if(shield % 2 == 1){
                katnissHealth -=10;
                System.out.format("\nAttack was successful, Katniss health %d ", katnissHealth);
            }else {
                System.out.format("\nAttack was not successful, Katniss health %d ", katnissHealth);
            }
            if(katnissHealth == 0 || presidentSnowHealth == 0){
                gameOver = true;
            }
        }
    }
}
