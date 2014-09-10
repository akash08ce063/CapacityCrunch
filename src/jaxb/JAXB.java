/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Ricardo KAKA
 */
public class JAXB extends TimerTask {

   
    
    static Queue waitingRequestQueue ;
    static ParseTopology parsing;
    public static void main(String[] args)  {
        try {
            process();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JAXB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JAXB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JAXB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(JAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void process() throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        parsing  = new ParseTopology();
        ExponentialDistribution expDis = new ExponentialDistribution(5);
        parsing.parse();
        //System.out.println(parsing.Links.Links.toString());
        System.out.println();
        
      TimerTask tasknew = new JAXB();
      Timer timer = new Timer();
      
      // scheduling the task at interval
      timer.schedule(tasknew,0, 1000);   
        
        
        ///////////// initialize the link information
        
        Iterator iteratorLinks =parsing.Links.Links.keySet().iterator();
        while(iteratorLinks.hasNext()){
          String key =  (String) iteratorLinks.next();
        //  System.out.println("Key "+ key);
          link links =   parsing.Links.Links.get(key);
         // System.out.println("Source"+links.AdditionalModules.addMoudle.size());
//          System.out.println("The size of additional modules" + links.additionalModules.size());
            Random rand = new Random();
            int index = rand.nextInt(3) ;
            //System.out.println("Index" + index);
            // put index down
            addModule additionalModule = (addModule) links.AdditionalModules.addMoudle.get(0);
            links.capacity = additionalModule.getCapacity() ;
            links.availableCapacity = links.capacity ;
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
        for(int i = 0 ; i < 2 ; i ++){
           int node =   distribution.uniformdistibution(37);
           while(listofNode.contains(node)){
               node = distribution.uniformdistibution(37);
           }
           
           System.out.println("List of node" + node);
           listofNode.add(node);
           // if(listofNode.contains()){
            //}
        }
        
        
        
        
        
        ShortestPath spath = new ShortestPath();
        waitingRequestQueue = new LinkedList();
        
        
        
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
         
         /*
            long startingTime = System.currentTimeMillis() ;
           
          
          */
         
         
         double nextTime = (double) expDis.sample();
           
         
        
        long startingTime = System.currentTimeMillis() ;
        
        System.out.println("The size of Queue" + parsing.eventQueue.size());
        
        
        
           
           Request request = new Request();
           //listofNode.get(0)
           
           
           request.source = JAXB.returnHashElement(parsing.nodes.node, 10) ;
           request.target = JAXB.returnHashElement(parsing.nodes.node,11) ;
             
            System.out.println("Source " + request.source + "Target "+ request.target);
          
           request.allocatedBandwidth = distribution.possionDistribution(30000);
           request.holdingTime = 50;
          // request.holdingTime = distribution.uniformdistibution(50);
           request.maxBandwidth = request.allocatedBandwidth + 15;
           request.minBandwidth = request.allocatedBandwidth - 15;
           request.crunchingRatio = distribution.possionDistribution(50);
         //  System.out.println("allocatedBandwidth"+distribution.possionDistribution(6000));
           HashMap shortestPathMap = spath.shortestPathFindingHandler(parsing, request.source,request.target , request.allocatedBandwidth);
           //HashMap shortestPathMap = spath.costdijkstra(parsing, request.source, request.source,request.allocatedBandwidth);
           if(shortestPathMap == null){
               // Block the perticular request
               
               System.out.println("Block the thing");
               
           }else{
               if(spath.crunchingNeeded){
                   // crunching needed
                   
                   System.out.println("Crunching needed");
                   
               }else{
                   // Assing the request
                   
                   System.out.println("Assigned the request");
                  // System.out.println(shortestPathMap);
                   
                   
               }
               
           }
          
           
           String previous =  (String) shortestPathMap.get(request.target);
           request.path.add(request.target);
           request.path.add(previous);
           
           
           
           while(previous != request.source){
              previous =  (String) shortestPathMap.get(previous);
              request.path.add(previous);
           }  
           
            Collections.reverse(request.path);
           
            for(int k = 0 ; k < request.path.size() ; k++){
                System.out.println(request.path.get(k));
            }
            
            
            
           
           
            provisionRequest(request,parsing);
           
           /*
           for(int i=0 ; i < request[index].path.size()-1 ; i ++){
               System.out.println("Path : "+index  +request[index].path.get(i));
               GraphNode sourceNode = parsing.nodes.node.get(request[index].path.get(i));
               System.out.println("Source -"+ sourceNode);
               System.out.println(sourceNode.nodeLink.toString());
              // GraphNode targetNode = parsing.nodes.node.get(request[index].path.get(i+1));
              // System.out.println("Target -" + targetNode);
               link Link = (link) sourceNode.nodeLink.get(parsing.nodes.node.get(request[index].path.get(i+1)));
               System.out.println("Capacity before" + Link.capacity);
               if(Link.capacity - request[index].allocatedBandwidth <= 0 ){
                   System.out.println("Capacity crunch has been happened");
                   waitingRequestQueue.add(request[index]);
                   // Do here crucnhing  (Crunching logic)
                   
                }else{
                   // Allocated the request 
                   
                   Link.capacity = Link.capacity - request[index].allocatedBandwidth;
                   Link.passingRequest.add(request[index]);
                   parsing.eventQueue.add(request[index]);
                }
           }*/
           
           
           
        
        
        // checking and allocation according to it 
        // if it is not to be allocated then we put in the waitign queue.
        
       //  System.out.println("ok"+  (nextTime*1000));
       //  System.out.println("h" +((startingTime+ (nextTime*1000) ) - (  System.currentTimeMillis())));
        Thread.sleep((long) (startingTime+ (nextTime*1000) ) - (  System.currentTimeMillis()));
        
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
    
    
    public static void crunchRequests(){
        // Crunching the request according to cplex code.
        
    
    }
    
    public static void provisionRequest(Request request,ParseTopology parsing){
        
     /*   
     boolean crunching = false;   
        
            for(int i=0 ; i < request.path.size()-1 ; i ++){
                      GraphNode sourceNode = parsing.nodes.node.get(request.path.get(i));
                     // GraphNode targetNode = parsing.nodes.node.get(request[index].path.get(i+1));
                     // System.out.println("Target -" + targetNode);
                      link Link = (link) sourceNode.nodeLink.get(parsing.nodes.node.get(request.path.get(i+1)));
                      System.out.println("Source: "+sourceNode.name + " Target: "+ parsing.nodes.node.get(request.path.get(i+1)) + " Link Capacity:"+Link.capacity);
                      
                      if(Link.availableCapacity - request.allocatedBandwidth <= 0 ){
                          crunching = true; 
                      }
                      
                      
            
            }
           
           // if cannot provide crunching
            
           
                if(crunching){
                     /*
                    System.out.println("------------------------------- Crunching is needed ");
                    
                    for(int i = 0 ; i < request.path.size()-1 ; i++){
                        boolean allocatedRequest = false;
                        GraphNode sourceNode = parsing.nodes.node.get(request.path.get(i));
                        link Link = (link) sourceNode.nodeLink.get(parsing.nodes.node.get(request.path.get(i+1)));
                        Link.capacity = Link.capacity - request.allocatedBandwidth;
                        
                        if(Link.capacity - request.allocatedBandwidth <= 0 ){
                            
                            System.out.println("Crunching needed in link - " + Link.source + "d" + Link.target);
                            
                            int requireBandwidth = (int) (request.allocatedBandwidth - Link.capacity);
                            for(int j = 0 ; j <Link.passingRequest.size(); j ++){
                              // Optimze the problem here .. We crunch the request according to the 
                                
                                
                              Request crunchingRequest =  Link.passingRequest.get(j);
                              crunchingRequest.crunchCount ++;
                              int canAllocateBandwidth = (crunchingRequest.allocatedBandwidth * crunchingRequest.crunchingRatio) /100 ;
                              if(requireBandwidth > canAllocateBandwidth){
                                  crunchingRequest.allocatedBandwidth = crunchingRequest.allocatedBandwidth - canAllocateBandwidth;
                                  Link.capacity = Link.capacity + canAllocateBandwidth;
                              }else{
                                  int remainingBandwidth = canAllocateBandwidth - requireBandwidth;
                                  crunchingRequest.allocatedBandwidth = crunchingRequest.allocatedBandwidth - remainingBandwidth;
                                  Link.capacity = Link.capacity + remainingBandwidth;
                                  allocatedRequest = true ;
                                  break ;
                              }  
                           
                              
                            }

                          if(!waitingRequestQueue.contains(request) && !allocatedRequest)
                             waitingRequestQueue.add(request);

                       }
                    }*/
               // }else{
               
                    for(int i = 0 ; i < request.path.size()-1 ; i++){
                        GraphNode sourceNode = parsing.nodes.node.get(request.path.get(i));
                        link Link = (link) sourceNode.nodeLink.get(parsing.nodes.node.get(request.path.get(i+1)));
                        Link.availableCapacity = Link.availableCapacity - request.allocatedBandwidth;
                        Link.passingRequest.add(request);
                       
                        System.out.println(" Actual cut Source: "+sourceNode.name + " Target: "+ parsing.nodes.node.get(request.path.get(i+1)) + " Link Capacity:"+Link.availableCapacity);
                     
                    }
                    
                     parsing.eventQueue.add(request);
                    System.out.println("Request is provisioned");
                    
               // }
               
     
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
    
    public void run(){
        
        //System.out.println("Runinnnnggg");
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
                   System.out.println("Req available badwidth" + Link.availableCapacity);
                   Link.availableCapacity = Link.availableCapacity +  req.allocatedBandwidth ;
                   System.out.println("Req Allocated Band" + req.allocatedBandwidth + "And avaialble" + Link.availableCapacity);
                   
                   System.out.println(sourceNode);
                   Link.passingRequest.remove(req);
                
                }
                 
                 Iterator waitinIterator = waitingRequestQueue.iterator();
                   while(waitinIterator.hasNext()){
                       Request waitingRequest = (Request) waitinIterator.next();
                       provisionRequest(waitingRequest,parsing);
                   }
                
                 parsing.eventQueue.remove(loop);
            }
            
        }
    
    }
    
    
}
