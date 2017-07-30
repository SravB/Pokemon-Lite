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
public class FireType extends Pokemon{
    
    //specialized field
    int firePower;
    
    //specialized constructor
    public FireType(String n,int l) throws FileNotFoundException {
        super(n,l);
        firePower = (int) Math.random() * 10 + 50;
        for (int i = 0; i < level; i++) {
            firePower += Math.round(Math.random()) + 2;
        }
    }
    
    //specialized train method
    public void train() {
        super.train();
        firePower++;
        attackPower += (int) (0.1 * firePower);
    }
    
    //specialized levelUp method
    public void levelUp() { 
        firePower += Math.round(Math.random()) + 2;
        super.levelUp();
    }
    
    //specialized evolve method
    public void evolve() {
        firePower += Math.round(Math.random()) + 10;
        super.evolve();
    }
    
    //specialized display method 
    public void display(){
        super.display();
        System.out.println("Fire Power:\t" + firePower);
    }
}
