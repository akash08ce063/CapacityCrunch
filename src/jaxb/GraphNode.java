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
public class GraphNode {
     coordinates Coordinates;
     String name;
     ArrayList<GraphNode> neighbour ;
     HashMap nodeLink;
     GraphNode(){
         Coordinates = new coordinates();
         neighbour = new ArrayList<GraphNode>();
         nodeLink = new HashMap();
     }
     
     
     public String toString(){
         return name;
     }
     
}
