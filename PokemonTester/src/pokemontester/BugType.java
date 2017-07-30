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
public class BugType extends Pokemon{
    
    //specialized field
    int bugPower;
    
    //specialized constructor
    public BugType(String n,int l) throws FileNotFoundException {
        super(n,l);
        bugPower = (int) Math.random() * 10 + 50;
        for (int i = 0; i < level; i++) {
            bugPower += Math.round(Math.random()) + 2;
        }
    }
    
    //specialized train method
    public void train() {
        super.train();
        bugPower++;
        attackPower += (int) (0.05 * bugPower);
        speed += (int) (0.05 * bugPower);
    }
    
    //specialized levelUp method
    public void levelUp() { 
        bugPower += Math.round(Math.random()) + 2;
        super.levelUp();
    }
    
    //specialized evolve method
    public void evolve() {
        bugPower += Math.round(Math.random()) + 10;
        super.evolve();
    }
    
    //specialized display method 
    public void display(){
        super.display();
        System.out.println("Bug Power:\t" + bugPower);
    }
}
