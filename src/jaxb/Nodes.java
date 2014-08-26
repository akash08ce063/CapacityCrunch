/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Ricardo KAKA
 */
public class Nodes {
    public HashMap<String,GraphNode> node;
    
    public String getCoordinatesType() {
        return coordinatesType;
    }

    public void setCoordinatesType(String coordinatesType) {
        this.coordinatesType = coordinatesType;
    }
    String coordinatesType ;
    
    Nodes(){
        node = new HashMap<String,GraphNode>();
    }
    
}
