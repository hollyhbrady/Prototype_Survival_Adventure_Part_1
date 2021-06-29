import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        String[] enemies = {"Crawler", "Runner", "Bloater"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        int health = 100;
        int attackDamage = 50;
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 40;
        int enemiesDefeated = 0;
        int healthPotionsUsed = 0;
        int enemyFledCount = 0;

        boolean running = true;

        System.out.println("Welcome to the survival zone!");

        GAME:
        while(running) {
            System.out.println("------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            if (enemyHealth < 1) {
                enemyHealth = 1;
            }
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " has appeared! #\n");

            while(enemyHealth > 0) {
                System.out.println("Your HP: " + health);
                System.out.println(enemy + "'s HP: " + enemyHealth);
                System.out.println("What would you like to do?");
                System.out.println("1. Attack");
                System.out.println("2. Drink HP potion");
                System.out.println("3. Run!");

                String input = in.nextLine();

                if(input.equals("1")) {
                    int damageDealt = rand.nextInt(attackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    health -= damageTaken;

                    System.out.println("You receive " + damageTaken + " damage from the " + enemy + ".");
                    if (health < 1) {
                        System.out.println("You have taken too much damage to go on.");
                        break;
                    }
                    System.out.println("You strike the " + enemy + " for " + damageDealt + " damage.");

                }
                else if (input.equals("2")) {
                    if (numHealthPotions > 0) {
                        health += healthPotionHealAmount;
                        if (health > 100) {
                            health = 100;
                        }
                        numHealthPotions--;
                        healthPotionsUsed++;
                        System.out.println("You health has been restored by " + healthPotionHealAmount + ".");
                    }
                    else {
                        System.out.println("You have no health potions left!");
                    }
                }
                else if (input.equals("3")) {
                    System.out.println("You run away from the " + enemy + "!");
                    enemyFledCount++;
                    continue GAME;
                }
                else {
                    System.out.println("Invalid command.");
                }
            }
             if (health < 1) {
                 System.out.println("You limp back to your hut, will you be next to join the ranks of the infected?");
                 break;
             }

            System.out.println("------------------------------");
            System.out.println("The " + enemy + " was defeated!");
            enemiesDefeated++;
            if (rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions++;
                System.out.println("It dropped a health potion!");
                System.out.println("You now have " + numHealthPotions + " health potion(s).");
            }
            System.out.println("------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1. Continue on your path");
            System.out.println("2. Return to hut");
            
            String input = in.nextLine();

            while(!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command");
                input = in.nextLine();
            }
            if (input.equals("1")) {
                System.out.println("You continue further down the path.");
            }
            else if (input.equals("2")) {
                System.out.println("You return to your hut for a rest.");
                break;
            }
        }
        System.out.println("You defeated " + enemiesDefeated + " Zombies, fled from " + enemyFledCount + " and used " + healthPotionsUsed + " health potions.");
        System.out.println("Thanks for playing!");
        System.out.println("Let me know if you enjoyed the game, or if you have updates to suggest!");
    }

}
