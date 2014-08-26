/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Ricardo KAKA
 */
public class JAXB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        ParseTopology parsing  = new ParseTopology();
        parsing.parse();
        //System.out.println(parsing.Links.Links.toString());
        System.out.println();
        
        
        
        ///////////// initialize the link information
        
        Iterator iteratorLinks =parsing.Links.Links.keySet().iterator();
        while(iteratorLinks.hasNext()){
          String key =  (String) iteratorLinks.next();
          System.out.println("Key "+ key);
          link links =   parsing.Links.Links.get(key);
         // System.out.println("Source"+links.AdditionalModules.addMoudle.size());
//          System.out.println("The size of additional modules" + links.additionalModules.size());
            Random rand = new Random();
            int index = rand.nextInt(3) ;
            //System.out.println("Index" + index);
            // put index down
            addModule additionalModule = (addModule) links.AdditionalModules.addMoudle.get(0);
            links.capacity = additionalModule.getCapacity() ;
            GraphNode graphSourceNode = parsing.nodes.node.get(links.getSource());
            graphSourceNode.nodeLink.put(parsing.nodes.node.get(links.getTarget()), links);
            graphSourceNode.neighbour.add(parsing.nodes.node.get(links.getTarget()));
            GraphNode graphTargetNode = parsing.nodes.node.get(links.getTarget());
            graphTargetNode.nodeLink.put(parsing.nodes.node.get(links.getSource()),links);
            graphTargetNode.neighbour.add(parsing.nodes.node.get(links.getSource()));
            
            
            System.out.println(links.capacity);
        }
        
        //spath.dijkstra(parsing,"Warsaw","");
        
        System.out.println("Nodes" + parsing.nodes.node.size());
        
        
        Distribution distribution = new Distribution();
        
        
        ArrayList<Integer> listofNode = new ArrayList<Integer>();
        for(int i = 0 ; i < 12 ; i ++){
           int node =   distribution.uniformdistibution(37);
           while(listofNode.contains(node)){
               node = distribution.uniformdistibution(37);
           }
           
           System.out.println("List of node" + node);
           listofNode.add(node);
           // if(listofNode.contains()){
            //}
        }
        
        
        
        
        
        Request request[] = new Request[6];
        ShortestPath spath = new ShortestPath();

        // while(true){ 
           
            
      /*      
        for(int index = 0 ; index <6 ; index++){
           request[index] = new Request();
           request[index].source = JAXB.returnHashElement(parsing.nodes.node, listofNode.get(index*2)) ;
           request[index].target = JAXB.returnHashElement(parsing.nodes.node, listofNode.get((index*2)+1)) ;
           request[index].capacity = distribution.possionDistribution(3000);
           System.out.println("capacity"+distribution.possionDistribution(6000));
           HashMap shortestPathMap = spath.dijkstra(parsing, request[index].source, request[index].target);
         //  System.out.println(shortestPathMap.toString());
           
           
           String previous =  (String) shortestPathMap.get(request[index].target);
           request[index].path.add(request[index].target);
           request[index].path.add(previous);
           
           while(previous != request[index].source){
              previous =  (String) shortestPathMap.get(previous);
              request[index].path.add(previous);
           }  
          
           System.out.println("Source" + request[index].source  );
           System.out.println("Target" + request[index].target  );
           for(int i=0 ; i < request[index].path.size() ; i ++){
               System.out.println("Path : "+index  +request[index].path.get(i));
           }
           
        }
       */
      // } // while loop end
        
        ///////////////////////////////////
        // generating the scenario of capacity crunch
     
        /*
        Request requesting = new Request();
        requesting.source = "London";
        requesting.target = "Barcelona";
        requesting.capacity = 400.0;
        requesting.holdingTime = 10;
        requesting.allocatedBandwidth = 300;
        HashMap shortestPathMap = spath.dijkstra(parsing, requesting.source, requesting.target);
        String previous =  (String) shortestPathMap.get(requesting.target);
        requesting.path.add(previous);
        while(previous != requesting.source){
              previous =  (String) shortestPathMap.get(previous);
              requesting.path.add(previous);
        }
        
        parsing.eventQueue.add(requesting);
        
        for(int i = 0 ; i < requesting.path.size() ; i++){
            System.out.println(requesting.path.get(i));
        }
        
        */
        
        
        
     while(true){
        
        long startingTime = System.currentTimeMillis() ;
        for(int loop = 0 ; loop < parsing.eventQueue.size() ; loop++){
            Request req =  (Request) parsing.eventQueue.get(loop);
            req.holdingTime = req.holdingTime - 1;
            System.out.println("Holding Time" + req.holdingTime);
            if(req.holdingTime <= 0){
                System.out.println("Removed the element");
               
                // loop through the path and increase the badwidth everwhere
                ArrayList takenPath =  req.path ;
                for(int index = 0 ; index < takenPath.size()-1 ; index++){
                   GraphNode sourceNode = parsing.nodes.node.get(takenPath.get(index));
                   link Link = (link) sourceNode.nodeLink.get(parsing.nodes.node.get(takenPath.get(index+1)));
                   //System.out.println(Link.toString());
                   Link.capacity = Link.capacity +  req.allocatedBandwidth ;
                   System.out.println(sourceNode);
                }
                
                 parsing.eventQueue.remove(loop);
            }
            
        }
        
        
        
        for(int index = 0 ; index < 6 ; index++){
           
           request[index] = new Request();
           request[index].source = JAXB.returnHashElement(parsing.nodes.node, 10) ;
           request[index].target = JAXB.returnHashElement(parsing.nodes.node,11) ;
           request[index].allocatedBandwidth = distribution.possionDistribution(30000);
           request[index].holdingTime = distribution.uniformdistibution(50);
           System.out.println("allocatedBandwidth"+distribution.possionDistribution(6000));
           HashMap shortestPathMap = spath.dijkstra(parsing, request[index].source, request[index].target);
         //  System.out.println(shortestPathMap.toString());
           String previous =  (String) shortestPathMap.get(request[index].target);
           request[index].path.add(request[index].target);
           request[index].path.add(previous);
           
           while(previous != request[index].source){
              previous =  (String) shortestPathMap.get(previous);
              request[index].path.add(previous);
           }  
          
           System.out.println("Source" + request[index].source  );
           System.out.println("Target" + request[index].target  );
           System.out.println("-----");
           for(int i=0 ; i < request[index].path.size()-1 ; i ++){
               System.out.println("Path : "+index  +request[index].path.get(i));
               GraphNode sourceNode = parsing.nodes.node.get(request[index].path.get(i));
               System.out.println("Source -"+ sourceNode);
               System.out.println(sourceNode.nodeLink.toString());
              // GraphNode targetNode = parsing.nodes.node.get(request[index].path.get(i+1));
              // System.out.println("Target -" + targetNode);
               link Link = (link) sourceNode.nodeLink.get(parsing.nodes.node.get(request[index].path.get(i+1)));
               System.out.println("Capacity before" + Link.capacity);
               Link.capacity = Link.capacity - request[index].allocatedBandwidth;
               System.out.println("Capacity after" + Link.capacity);
                if(Link.capacity <= 0 ){
                   System.out.println("Capacity crunch has been happened");
              }
              
               Link.passingRequest.add(request[index]);
               parsing.eventQueue.add(request[index]);
               
           }
        
        }
        
        Thread.sleep((startingTime+ 1000 )- System.currentTimeMillis());
        
        }
        
        /*
         // Printing module for nodes
         
        
        Iterator iteratorPrint =parsing.nodes.node.keySet().iterator();
        while(iteratorPrint.hasNext()){
          String key =  (String) iteratorPrint.next();
          System.out.println("Key "+ key);
          GraphNode graphNode =   parsing.nodes.node.get(key);
          
          System.out.println("Parent: "+ graphNode.toString());
          for(int i = 0  ; i < graphNode.neighbour.size() ; i++) {
                GraphNode neighNode =   graphNode.neighbour.get(i);
                System.out.println("     "+neighNode.toString());
          }
          
        }
        
        */
        
        
    }
    
    public static <T> T returnHashElement(HashMap map,int index){
        Iterator hashIterator = map.keySet().iterator();
        int pointer = 0  ;
        while(hashIterator.hasNext()){
               T key =   (T) hashIterator.next();
               
               if(pointer == index){
                  return key;
               }
               pointer++;
        }
        
       
        
        return null;
    
    }
    
    
}
