/*
 * Unit 5 Team Project - Pokemon Lite
 * By: Sourav, Jessica and Jiwoong
 */

package pokemontester;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class PokemonTester {
    
    //checks to see if the selected pokemon is a valid choice
    //(is in the team and is a number)
    public static boolean isValid(String input,int numOptions) {
        
        for (int i = 0; i < numOptions; i++) {
            if (input.equals(String.valueOf(i + 1))) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        //initializing game settings and inputs
        WaterType Blas = new WaterType("Blastoise",50);
        Scanner s = new Scanner(System.in);
        int idCounter = 1;
        
        //max size of pokemon party is 6
        Pokemon[] team = new Pokemon[6]; 
        int numPotions = 15;
        int numPokeballs = 10;
        String stone;
        
        //obtaining random starter pokemon
        int starter = (int) (Math.random() * 2.4);
        Pokemon start;
        if (starter == 0) {
            start = new FireType("Charmander",5);
        } else if (starter == 1) {
            start = new WaterType("Squirtle",5);
        } else {
            start = new GrassType("Bulbasaur",5);
        }
        
        //obtaining a random evolution stone
        starter = (int) (Math.random() * 2.4);
        if (starter == 0) {
            stone = "Fire";
        } else if (starter == 1) {
            stone = "Leaf";
        } else {
            stone = "Water";
        }
        
        team[idCounter - 1] = start;
        idCounter++;
        
        //initial messages
        System.out.println("Welcome to the world of Pokemon");
        System.out.println("Your starting pokemon is " + start.name);
        System.out.println("You were given a " + stone + " Stone by Professor Oak");
        
        //main loop
        OUTER:        
        while (Blas.hp > 0) {
            
            //prompting user for initial inputs
            System.out.println("");
            System.out.println("What would you like to do?");
            System.out.println("1 to battle, 2 to train, 3 to heal, 4 to catch pokemon, "
                    + "5 to breed pokemon, 6 to use an evolution stone, 7 to check stats");
            String option = s.next();
            System.out.println("");
            
            //final battle
            switch (option) {
                //final battle
                case "1":
                    {
                        //choose the pokemon to battle
                        for (int i = 0; i < idCounter - 1; i++) {
                            System.out.println("Pokemon #" + (i + 1) + " is " + team[i].name);
                        }
                        
                        //the final battle
                        System.out.println("Which pokemon will you battle with?");
                        String answer = s.next();
                        while (PokemonTester.isValid(answer,idCounter - 1) == false) {
                            System.out.println("That is not a valid option, please try again");
                            answer = s.next();
                        }
                        team[Integer.parseInt(answer) - 1].battle(Blas);
                        break OUTER;
                    }
                
                //training
                case "2":
                    {
                        //choose the pokemon to train
                        for (int i = 0; i < idCounter - 1; i++) {
                            System.out.println("Pokemon #" + (i + 1) + " is " + team[i].name);
                        }
                        System.out.println("Which pokemon will you train?");
                        String answer = s.next();
                        while (PokemonTester.isValid(answer,idCounter - 1) == false) {
                            System.out.println("That is not a valid option, please try again");
                            answer = s.next();
                        }
                        team[Integer.parseInt(answer) - 1].train();
                        
                        break;
                    }
                    
                //healing    
                case "3":
                    
                    if (numPotions > 0) {
                        
                        //choose the pokemon to heal 
                        for (int i = 0; i < idCounter - 1; i++) {
                            System.out.println("Pokemon #" + (i + 1) + " is " + team[i].name);
                        }
                        System.out.println("Which pokemon will you heal?");
                        
                        String answer = s.next();
                        while (PokemonTester.isValid(answer,idCounter - 1) == false) {
                            System.out.println("That is not a valid option, please try again");
                            answer = s.next();
                        }
                        
                        //healing the pokemon
                        team[Integer.parseInt(answer) - 1].heal();
                        numPotions -= 1;
                        System.out.println("You have " + numPotions + " potions left");
                        
                    } else {
                        System.out.println("You don't have any potions left!");
                    }
                 
                    break;
                    
                //catching pokemon    
                case "4":
                    
                    //if there are enough pokeballs
                    if (numPokeballs > 0) {
                        
                        //if there is enough room in the party
                        if (idCounter < 7) {
                            
                            //generating a random pokemon encounter
                            int i = (int) (Math.random() * (Blas.numPokemon - 0.6));
                            int lvl = (int) (Math.random() * 15 + 5);
                            System.out.println("A wild level " + lvl + " " + Blas.pokemonNames[i][0] + " appeared!");
                            System.out.println("1 to use a pokeball, 2 to run");
                            
                            String answer = s.next();
                            while (PokemonTester.isValid(answer,2) == false) {
                                System.out.println("That is not a valid option, please try again");
                                answer = s.next();
                            }
                        
                            if (Integer.parseInt(answer) == 1) {
                                
                                //50% catch rate
                                if (Math.random() * 100 > 50) {
                                    
                                    //catching a pokemon
                                    System.out.println("The wild " + Blas.pokemonNames[i][0] + " was caught!");
                                    switch (Blas.pokemonNames[i][1]) {
                                        case "Fire":
                                            team[idCounter - 1] = new FireType(Blas.pokemonNames[i][0],lvl);
                                            break;
                                        case "Water":
                                            team[idCounter - 1] = new WaterType(Blas.pokemonNames[i][0],lvl);
                                            break;
                                        case "Grass":
                                            team[idCounter - 1] = new GrassType(Blas.pokemonNames[i][0],lvl);
                                            break;
                                        default:
                                            team[idCounter - 1] = new BugType(Blas.pokemonNames[i][0],lvl);
                                            break;
                                    }

                                    idCounter++;
                                    System.out.println(Blas.pokemonNames[i][0] + " has been added to your party");

                                } else {
                                    System.out.println("Oh no, the pokeball didn't work!");
                                    System.out.println("The wild " + Blas.pokemonNames[i][0] + " escaped!");
                                }
                                
                                numPokeballs--;
                                System.out.println("You have " + numPokeballs + " pokeballs left");

                            } else {
                                System.out.println("You got away safetly!");
                            }
                        } else {
                            System.out.println("You do not have enough space in your party to catch more pokemon!");
                        }
                        
                    } else {
                        System.out.println("You don't have any pokeballs!");
                    }
                    
                    break;
                    
                //breeding    
                case "5":
                    
                    if (idCounter > 2) {
                        
                        if (idCounter < 7) {
                            
                            System.out.println("Choose the pokemon you want to breed");
                            
                            //choose the pokemon to breed 
                            for (int i = 0; i < idCounter - 1; i++) {
                                System.out.println("Pokemon #" + (i + 1) + " is " + team[i].name);
                            }
                            
                            //checking valid first choice
                            String posA = s.next();
                            
                            while (PokemonTester.isValid(posA,idCounter - 1) == false) {
                                System.out.println("That is not a valid option, please try again");
                                posA = s.next();
                            }
                            
                            System.out.println("Pick your second pokemon you want to breed");
                            String posB = s.next();
                            
                            //checking valid second choice
                            while (isValid(posB,idCounter - 1) == false || posB.equals(posA)) {
                                
                                if (posB.equals(posA)) {
                                    System.out.println("You can't breed a pokemon with itself");
                                    System.out.println("Choose a second pokemon to breed");
                                } else {
                                    System.out.println("That is not a valid option, please try again");
                                }
                                posB = s.next();
                            }
                            
                            //creating new pokemon
                            team[idCounter - 1] = team[Integer.parseInt(posA) - 1].breed(team[Integer.parseInt(posB) - 1]);
                            idCounter++;

                        } else {
                            System.out.println("You do not have enough room in your party to breed");
                        }
                        
                    } else {
                        System.out.println("You need at least 2 pokemon to breed");
                    }
                    
                    break;
                    
                //evolution stone    
                case "6":
                    
                    if (stone.equals("")) {
                        System.out.println("You already used your evolution stone!");
                        
                    } else {
                        
                        System.out.println("Choose a pokemon to use your " + stone + " Stone");
                        
                        //choose the pokemon to breed 
                        for (int i = 0; i < idCounter - 1; i++) {
                            System.out.println("Pokemon #" + (i + 1) + " is " + team[i].name);
                        }
                        
                        String answer = s.next();
                        while (PokemonTester.isValid(answer,idCounter - 1) == false) {
                            System.out.println("That is not a valid option, please try again");
                            answer = s.next();
                        }
                        
                        //checks if pokemon is the right type
                        int pos = Integer.parseInt(answer);
                        if (team[pos - 1].type.equals("Fire") && stone.equals("Fire") || team[pos - 1].type.equals("Grass") && stone.equals("Leaf") || 
                                team[pos - 1].type.equals("Water") && stone.equals("Water")) {
                            
                            //exception if pokemon is last in the pokedex
                            if (team[pos - 1].pokedexNum == Blas.pokemonNames.length) {
                                System.out.println("That pokemon can't evolve any further!");
                            
                            //evolving pokemon if all conditions are met
                            } else if (team[pos - 1].evoStage == Integer.parseInt(Blas.pokemonNames[team[pos - 1].pokedexNum][2]) - 1) {
                                team[pos - 1].evolve();
                                stone = "";
                                
                            } else {
                                System.out.println("That pokemon can't evolve any further!");
                            }
                            
                        } else {
                            System.out.println("You can't use your " + stone + " Stone on that type of pokemon!");
                        }
                    }
                    break;
                
                //check pokemon team stats    
                case "7":
                    
                    //displays stats for all pokemon in the party
                    for (int i = 0; i < idCounter - 1; i++) {
                        team[i].display();
                        System.out.println("");
                    }
                break;
            }
        }
        
        //win or loss message
        System.out.println("");
        if (Blas.hp == 0) {
            System.out.println("Congratulations! You beat your rival Gary!");
        } else {
            System.out.println("You lost against Gary. Game Over");
        }
    }
}
