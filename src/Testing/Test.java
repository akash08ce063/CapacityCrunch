/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import Testing.Outer.inner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import jaxb.*;
import org.apache.commons.math3.distribution.ExponentialDistribution;



public class Test extends TimerTask{

    static Timer timer;
    public static void main(String args[]){
       
      //  process();
      TimerTask tasknew = new Test();
      Timer timer = new Timer();
      
      // scheduling the task at interval
      timer.schedule(tasknew,100, 100);   
        
        Outer o = new Outer();
        o.a = 5;
        inner Inner = o.new inner();
    
    }
    
    public static void process(){
            Test t = new Test();
            timer = new Timer();
            timer.schedule(t, 1000);
           
    }

   
    public void run() {
        //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        System.out.println(""
                + "Hello this is run method"); 
        // timer.cancel();
    
    }
    
}
