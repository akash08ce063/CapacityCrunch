/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.util.Random;

/**
 *
 * @author Ricardo KAKA
 */


// Traffic generation 
public class Distribution {
    
    public Distribution(){
    
    }
    
    public double exponentialDistribution(double mean){
        Random rand = new Random();  
        
        //return rand.nextDouble();
        return Math.log(1.0 - rand.nextDouble()) * -mean ;
    
    }
    
    public int possionDistribution(double mean){
        
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
    
    public int uniformdistibution(int range){
        Random r = new Random();
        int i = r.nextInt(range);
        return i;
    }
    
    
    
    
}
