/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

/**
 *
 * @author Ricardo KAKA
 */
public class Demand {
    String source;

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

    public double getDemandValue() {
        return demandValue;
    }

    public void setDemandValue(double demandValue) {
        this.demandValue = demandValue;
    }
    String target;
    double demandValue;
}
