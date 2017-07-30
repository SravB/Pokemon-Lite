/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemontester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author USER
 */

public class Pokemon {
    
    //fields
    String name;
    int level;
    int maxHP;
    int hp;
    int attackPower;
    int defensePower;
    int speed;
    String type;
    int evoStage;
    int evoLvl;
    int pokedexNum;
    int numPokemon = 0;
    String[][] pokemonNames;
    
    //constructor
    public Pokemon(String n,int l) throws FileNotFoundException {
        name = n;
        level = l;
        
        //setting pokemon stats
        maxHP = (int) Math.random() * 10 + 50;
        attackPower = (int) Math.random() * 10 + 50;
        defensePower = (int) Math.random() * 10 + 50;
        speed = (int) Math.random() * 10 + 50;
        
        //calculates randomized stats based on current level
        for (int i = 0; i < level; i++) {
            maxHP += Math.round(Math.random()) + 2;
            attackPower += Math.round(Math.random()) + 2;
            defensePower += Math.round(Math.random()) + 2;
            speed += Math.round(Math.random()) + 2;
        }
        hp = maxHP;
        
        //creating a pokedex (database) available to each pokemon
        File inputFile = new File("InputFile.txt");
        Scanner s1 = new Scanner(inputFile);
        while (s1.hasNext()) {
            numPokemon++;
            s1.nextLine();
        }
        pokemonNames = new String[numPokemon][4];
        
        Scanner s2 = new Scanner(inputFile);
        for (int i = 0; i < numPokemon; i++) {
            pokemonNames[i][0] = s2.next();
            pokemonNames[i][1] = s2.next();
            pokemonNames[i][2] = s2.next();
            pokemonNames[i][3] = s2.next();
            if (pokemonNames[i][0].equals(name)) {
                type = pokemonNames[i][1];
                evoStage = Integer.parseInt(pokemonNames[i][2]);
                evoLvl = Integer.parseInt(pokemonNames[i][3]);
                pokedexNum = i + 1;
            }
        }
    }
    
    //sleep method used for custom printing
    public static void sleep(int duration) {    
        try {
            Thread.sleep(duration);
        } catch (Exception e) {}
    }
    
    //classic pokemon printing
    public static void print(String s) {
        
        String[] strings = s.split(" ");
        for (int i = 0; i < strings.length; i++) {
            
            System.out.print(strings[i] + " ");
            
            if (i == strings.length - 1) {
                System.out.println("");
            }
            
            sleep(500);
        }
    }
    
    //battle method - simulates battle between the two pokemon
    //returns true/false if the main pokemon won
    public void battle(Pokemon other) {
        
        //determining first move
        int counter;
        if (speed > other.speed) {
            counter = 1;
        } else {
            counter = 2;
        }
        
        //main battle loop
        while (hp > 0 && other.hp > 0) {
            
            print("");
            int missChance = (int) (Math.random() * 300);

            //miss chance is based on pokemon speeds
            if (counter % 2 == 1) {
                
                if (missChance >= 300 - (other.speed - speed)) {
                    print(name + " missed!");
                    
                } else {
                    
                    int dmg = (int) (Math.abs((Math.random() * 20 + 90) / 100 * attackPower + 0.3 * speed - 0.5 * other.defensePower)) + 1;
                    
                    switch (type) {
                        
                        //if super effective
                        case "Grass":
                            dmg += (int) (dmg * 0.5);
                            print(name + "'s move was super effective!");
                            break;
                            
                        //if not very effective    
                        case "Fire":
                            dmg -= (int) (dmg * 0.5);
                            print(name + "'s move was not very effective...");
                            break;
                    }

                    //summary messages
                    print(name + " dealt " + dmg + " damage to " + other.name + "!");
                    other.hp -= dmg;
                    
                    if (other.hp < 0) {
                        other.hp = 0;
                    }
                    print(other.name + " has " + other.hp + " hp left!");
                }
                
            } else {
                
                if (missChance >= 300 - (speed - other.speed)) {
                    print(other.name + " missed!");
                    
                } else {
                    
                    int dmg = (int) (Math.abs((Math.random() * 20 + 90) / 100 * other.attackPower + 0.3 * other.speed - 0.5 * defensePower)) + 1;
                    
                    switch (type) {
                        
                        //if super effective
                        case "Fire":
                            dmg += (int) (dmg * 0.5);
                            print(other.name + "'s move was super effective!");
                            break;
                            
                        //if not very effective
                        case "Grass":
                            dmg -= (int) (dmg * 0.5);
                            print(other.name + "'s move was not very effective...");
                            break;
                    }

                    //summary messages
                    print(other.name + " dealt " + dmg + " damage to " + name + "!");
                    hp -= dmg;
                    if (hp < 0) {
                        hp = 0;
                    }
                    print(name + " has " + hp + " hp left!");
                }
            }
            counter++;
        }
        
        //declares winner
        if (hp > 0) {
            print(name + " beat " + other.name + "!");
            
        } else {
            print(other.name + " beat " + name + "!");
        }
    }
    
    //training method
    public void train(){
        
        Random r = new Random();
        
        //pokemon must have enough hp to train
        if (hp >= 0.6 * maxHP) {
            
            System.out.println(name + " has been trained");
            hp -= (int) (maxHP * 0.5);
            
            //40% chance to level up while training
            if (r.nextInt(10) < 4){
                levelUp();
            }
            
        } else {
            System.out.println("Your pokemon doesn't have enough health to train");
            System.out.println("Try healing your pokemon first");
        }
    }
    
    //display method 
    public void display(){
        System.out.println(name + "\t" + "Level " + level);
        System.out.println("************************");
        System.out.println("Health Points:\t" + maxHP);
        System.out.println("Attack Power:\t" + attackPower);
        System.out.println("Defense Power:\t" + defensePower);
        System.out.println("Speed:\t\t" + speed);
    }


    //level up method
    public void levelUp() { 
        
        level++;
        maxHP += Math.round(Math.random()) + 2;
        attackPower += Math.round(Math.random()) + 2;
        defensePower += Math.round(Math.random()) + 2;
        speed += Math.round(Math.random()) + 2;
        
        System.out.println(name + " leveled up!");
        display();
        
        //if the pokemon has reached a certain level it evolves
        if (level == evoLvl) {
            evolve();
        }
    }
    
    //evolve method
    public void evolve() {
        
        System.out.println("Oh? " + name + " is evolving!");
        String a = name;

        //stat boost from evolution
        maxHP += Math.round(Math.random()) + 10;
        attackPower += Math.round(Math.random()) + 10;
        defensePower += Math.round(Math.random()) + 10;
        speed += Math.round(Math.random()) + 10;

        //setting new fields based on evolved state
        pokedexNum++;
        name = pokemonNames[pokedexNum - 1][0];
        evoStage = Integer.parseInt(pokemonNames[pokedexNum - 1][2]);
        evoLvl = Integer.parseInt(pokemonNames[pokedexNum - 1][3]);

        System.out.println("Congratulations! Your " + a + " evolved into " + name + "!");
    }
    
    //heal method 
    public void heal(){
        hp = maxHP;
        System.out.println(name + " has been healed to full health");
    }

    //pokemon breeder method
    public Pokemon breed(Pokemon other) throws FileNotFoundException{
        
        Random r = new Random ();
        String newN;
        // 1/2 chance of being pokemon of first type or second type
        if (r.nextInt(2) == 0) {
            newN = name;      
            
        } else {
            newN = other.name;
        }

        for (int i = 0; i < numPokemon; i ++){
            
            // find the right pokemon in the 2d pokemon array
            if (newN.equals(pokemonNames[i][0])){
                
                //assign the proper type
                type = pokemonNames[i][1];
                
                //assign the new breeded pokemon to lowest evolution type using pokemonNames array
                switch (pokemonNames[i][2]) {
                    
                    case "1":
                        newN = pokemonNames[i - 1][0];
                        break;
                        
                    case "2":
                        newN = pokemonNames[i - 2][0];
                        break;
                }
            }
        }
        
        System.out.println(name + " and " + other.name + " bred to create " + newN);
        
        // returns new pokemon with new name to proper subclass
        switch (type) {
            case "Fire":
                return new FireType(newN, 1);
            case "Water":
                return new WaterType(newN, 1);
            case "Grass":
                return new GrassType(newN, 1);
            default:
                return new BugType(newN, 1);
        }
    }
}
