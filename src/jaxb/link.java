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
public class link {
    String source;
    ArrayList<Request> passingRequest = new ArrayList<Request>();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getRoutingCost() {
        return routingCost;
    }

    public void setRoutingCost(double routingCost) {
        this.routingCost = routingCost;
    }
    double capacity ;
    String target;
    double routingCost;
    additionaModule AdditionalModules;
    
    
}
