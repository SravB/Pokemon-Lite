/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemontester;

import java.io.FileNotFoundException;

/**
 *
 * @author bisws1991
 */
public class GrassType extends Pokemon{
    
    //specialized field
    int grassPower;
    
    //specialized constructor
    public GrassType(String n,int l) throws FileNotFoundException {
        super(n,l);
        grassPower = (int) Math.random() * 10 + 50;
        for (int i = 0; i < level; i++) {
            grassPower += Math.round(Math.random()) + 2;
        }
    }
    
    //specialized train method
    public void train() {
        super.train();
        grassPower++;
        defensePower += (int) (0.1 * grassPower);
    }
    
    //specialized levelUp method
    public void levelUp() { 
        grassPower += Math.round(Math.random()) + 2;
        super.levelUp();
    }
    
    //specialized evolve method
    public void evolve() {
        grassPower += Math.round(Math.random()) + 10;
        super.evolve();
    }
    
    //specialized display method 
    public void display(){
        super.display();
        System.out.println("Grass Power:\t" + grassPower);
    }
}
