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
public class WaterType extends Pokemon{
    
    //specialized field
    int waterPower;
    
    //specialized constructor
    public WaterType(String n,int l) throws FileNotFoundException {
        super(n,l);
        waterPower = (int) Math.random() * 10 + 50;
        for (int i = 0; i < level; i++) {
            waterPower += Math.round(Math.random()) + 2;
        }
    }
    
    //specialized train method
    public void train() {
        super.train();
        waterPower++;
        speed += (int) (0.1 * waterPower);
    }
    
    //specialized levelUp method
    public void levelUp() { 
        waterPower += Math.round(Math.random()) + 2;
        super.levelUp();
    }
    
    //specialized evolve method
    public void evolve() {
        waterPower += Math.round(Math.random()) + 10;
        super.evolve();
    }
    
    //specialized display method 
    public void display(){
        super.display();
        System.out.println("Water Power:\t" + waterPower);
    }
}


