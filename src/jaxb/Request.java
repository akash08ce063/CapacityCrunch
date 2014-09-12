/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Ricardo KAKA
 */
public class Request {
    String source;
    String target;
    ArrayList path;
    double capacity ;
    // holdingTime should be in the seconds
    int holdingTime;
    double allocatedBandwidth;
    double maxBandwidth;
    double minBandwidth;
    int crunchCount;
    int crunchingRatio;
    long startingTime ;
    double xPast;
    HashMap crunchTime;
    
    
    Request(){
        source ="";
        target = "";
        path = new ArrayList();
        crunchTime= new HashMap();
    }
}
