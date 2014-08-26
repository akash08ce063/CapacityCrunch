/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Ricardo KAKA
 */
public class ShortestPath {
    
    
   public HashMap dijkstra(ParseTopology parse,String source,String destination){
      HashMap<String,Double> distance = new HashMap<String,Double>(); 
      HashMap precedence = new HashMap();
      HashMap previousNode = new HashMap();
      HashMap visited = new HashMap();
      Iterator nodeIterator =   parse.nodes.node.keySet().iterator();
      while(nodeIterator.hasNext()){
         String key = (String) nodeIterator.next();
         distance.put(key,123456.0 );
         precedence.put(key,"none" );
      }
      
      distance.put(source, 0.0);
      while(source.equals(destination)){
          double distanceCurrent = distance.get(source);
          double smallestDistance = 123456.0;
          String k = "-1";
      }
      
      PriorityQueue vertexQueue = new PriorityQueue();
      vertexQueue.add(source);
      
      while(!vertexQueue.isEmpty()){
          String currentVertex = (String) vertexQueue.peek();
            if(vertexQueue.size() != 1){
            
                Iterator vertexIte =  vertexQueue.iterator();
                while(vertexIte.hasNext()){
                    String vertex =   (String) vertexIte.next() ; 
                    if(distance.get(vertex) <distance.get(currentVertex)){
                        currentVertex = vertex ;
                    }
                   }
                vertexQueue.remove(currentVertex);
            }else{
                currentVertex = (String) vertexQueue.poll();
                
            }
            
            if(visited.get(currentVertex)!=null && visited.get(currentVertex) == true){
                continue;
            }
            
            GraphNode noding = parse.nodes.node.get(currentVertex);
            for(int i = 0 ; i < noding.neighbour.size() ; i++){
                GraphNode neighbourNode =  noding.neighbour.get(i);
                link Link = (link) noding.nodeLink.get(neighbourNode);
              //  System.out.println("routing cost" + Link.routingCost);
                double distanceThrough = distance.get(currentVertex) + Link.routingCost; 
                if(distanceThrough < distance.get(neighbourNode.name)){
                    
             //       System.out.println("I am going inside");
                    distance.put(neighbourNode.name, distanceThrough);
                    previousNode.put(neighbourNode.name, currentVertex );
                }
                vertexQueue.add(neighbourNode.name);
            }
            ////////// change the impelemenatiaon for th hashmap
            
            
            
            
            
            
            ////////
            
            visited.put(currentVertex, true);
      //      System.out.println("Current Vertex" + currentVertex);
      }
      
      
      //  System.out.println(" the HashMap is " + previousNode);
       return previousNode ;
   } 
   
}
