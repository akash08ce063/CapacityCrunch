/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Ricardo KAKA
 */
public class ShortestPath {
    
   public boolean crunchingNeeded = false;
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
            
            visited.put(currentVertex, true);
      //      System.out.println("Current Vertex" + currentVertex);
      }
      
       return previousNode ;
   } 

   
     
   public HashMap shortestPathFindingHandler(ParseTopology parse,String source,String destination,double requiredBandwidth){
       
       HashMap deadLink = linkCheckGraphGeneration(parse,source,requiredBandwidth,false);
       HashMap PreviousNode = costdijkstra(parse,source,destination,requiredBandwidth,deadLink);
       if(PreviousNode.get(destination) == null){
           // crunching needed
            HashMap deadLink1 = linkCheckGraphGeneration(parse,source,requiredBandwidth,true);
            HashMap previousNode1 = costdijkstra(parse,source,destination,requiredBandwidth,deadLink1);
            if(previousNode1.get(destination) != null){
                // Block the request
                
               // System.out.println("Crunching happed path" + previousNode1);
                this.crunchingNeeded = true;
                return previousNode1;
            }else{
                // Everything is going fine
                return null ;
            }
       }else{
           // Get the shortest path
           return PreviousNode;
       }
   
   }
   
   
   
   public HashMap costdijkstra(ParseTopology parse,String source,String destination,double requiredBandwidth,HashMap deadLink){
      HashMap<String,Double> distance = new HashMap<String,Double>(); 
      HashMap precedence = new HashMap();
      HashMap previousNode = new HashMap();
      HashMap visited = new HashMap();
      double assignedBandwidth = 0;
      
      //HashMap deadLink = linkCheckGraphGeneration(parse,source,requiredBandwidth);
      
      
      
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
      
      Queue vertexQueue = new LinkedList();
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
                // Required Bandwidth. 
               
               if(deadLink.get(Link) != null)
                   continue;
                
                double distanceThrough = distance.get(currentVertex) + 1; 
                if(distanceThrough < distance.get(neighbourNode.name)){
                
                    distance.put(neighbourNode.name, distanceThrough);
                    previousNode.put(neighbourNode.name, currentVertex );
                }
                vertexQueue.add(neighbourNode.name);
            }
            
            visited.put(currentVertex, true);
      
      }
      
      
      
      
      //  System.out.println(" the HashMap is " + previousNode);
       return previousNode ;
   } 
   
   
      
   public HashMap linkCheckGraphGeneration(ParseTopology parse,String source,double requiredBandwidth,boolean crunch){
   HashMap<String,Double> distance = new HashMap<String,Double>(); 
     // HashMap precedence = new HashMap();
      HashMap deadLink = new HashMap();
     // HashMap previousNode = new HashMap();
      HashMap visited = new HashMap();
      double totalCrunchableBandwidth = 0.0;
      double assignedBandwidth = 0;
      
      
      
      Queue vertexQueue = new LinkedList();
      vertexQueue.add(source);
     // visited.put(source,true);
      
      while(!vertexQueue.isEmpty()){
          
            String    currentVertex = (String) vertexQueue.poll();
            if(visited.get(currentVertex)!=null && visited.get(currentVertex) == true){
                continue;
            }
            
            GraphNode noding = parse.nodes.node.get(currentVertex);
            for(int i = 0 ; i < noding.neighbour.size() ; i++){
                totalCrunchableBandwidth = 0.0;
                assignedBandwidth = 0.0;
                GraphNode neighbourNode =  noding.neighbour.get(i);
                link Link = (link) noding.nodeLink.get(neighbourNode);
                // Required Bandwidth. 
                for(int j = 0 ; j < Link.passingRequest.size() ; j++){
                     Request passingRequest =   Link.passingRequest.get(j);
                     assignedBandwidth = assignedBandwidth + passingRequest.allocatedBandwidth ;
                     if(crunch)
                     totalCrunchableBandwidth = totalCrunchableBandwidth + ((passingRequest.maxBandwidth) * passingRequest.crunchingRatio)/100;
                }
                
                if(crunch){
                    if((Link.capacity-assignedBandwidth+totalCrunchableBandwidth) < requiredBandwidth){
                        System.out.println("------------------------------------------------------");
                        deadLink.put( Link, neighbourNode);
                        continue;
                    }
                }else{
                        if((Link.capacity-assignedBandwidth) < requiredBandwidth){
                        System.out.println("------------------------------------------------------");
                        System.out.println("Source :"+noding.name + "Target : "+neighbourNode.name + "Link Capacity: "+ Link.capacity +"Assigned: "+ assignedBandwidth );
                        System.out.println("Avaialble " +(Link.capacity-assignedBandwidth) + " Required " +requiredBandwidth   );
                        deadLink.put( Link, neighbourNode);
                        continue;
                    }
                
                }
                
                
                vertexQueue.add(neighbourNode.name);
            }
            
            visited.put(currentVertex, true);
      
      }
      
      
      //  System.out.println(" the HashMap is " + previousNode);
       return deadLink ;
   
   
   }
   
   // If topology become unreachable then 
   
   
   
   
   
   
   
   
   

}
