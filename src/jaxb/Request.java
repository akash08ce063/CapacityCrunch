/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.util.ArrayList;
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
    int allocatedBandwidth;
    int maxBandwidth;
    int minBandwidth;
    Request(){
        source ="";
        target = "";
        path = new ArrayList();
    }
}
