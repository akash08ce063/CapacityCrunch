/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Ricardo KAKA
 */
public class ParseTopology {
      Nodes nodes;
      links Links ;
      Demands demands;
      ArrayList eventQueue;
      
    ParseTopology(){
      nodes = new Nodes();
      Links = new links();
      demands = new Demands();
      eventQueue = new ArrayList();   
    }
    
    public int countNodes(){
        return nodes.node.size(); 
    }
    
    public void parse() throws ParserConfigurationException, SAXException, IOException{
           File xmlfile = new File("F:\\cost266--D-B-E-N-C-A-N-N-xml\\cost266--D-B-E-N-C-A-N-N\\cost266.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlfile);
        
        //doc.getDocumentElement().normalize(); 
                                                             
        System.out.println("Root Element " + doc.getDocumentElement().getNodeName());
        
        NodeList nList = doc.getElementsByTagName("networkStructure");
        
        for(int i = 0 ; i < nList.getLength() ; i++){
            Node nNode = nList.item(i);
            System.out.println("Cureent element" + nNode.getNodeName());
            
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                
                if(nNode.hasChildNodes()){
              //      System.out.println("Ya this node has the child nodes");
             
                    NodeList childNodes = nNode.getChildNodes();
                        for(int j = 0 ; j < childNodes.getLength() ; j++){
                    
                            Node node = childNodes.item(j);
                            if(node.getNodeType() == Node.ELEMENT_NODE){
                              //  System.out.println("Secondary" + node.getNodeName());
                                
                                if(node.hasChildNodes()){
                                    
                                    NodeList networkChild = node.getChildNodes();
                                    
                                    for(int k = 0 ; k < networkChild.getLength() ; k++){
                                        
                                        Node nodee = networkChild.item(k);
                                        if(nodee.getNodeType() == Node.ELEMENT_NODE){
                                            
                                            // This loop is used for the type is node 
                                            if(nodee.getNodeName().equals("node")){
                                             //   System.out.println("Third Elemtn" +nodee.getNodeName() );
                                             //   System.out.println("--"+nodee.getTextContent()+"--");
                                                 coordinates Coordinates = new coordinates();
                                                 getXY(nodee,Coordinates);
                                                 nodes.coordinatesType = nodee.getAttributes().item(0).getNodeValue();
                                                 GraphNode graphNode = new GraphNode();
                                                 graphNode.Coordinates = Coordinates ;
                                                 graphNode.name = nodee.getAttributes().item(0).getNodeValue();
                                                 nodes.node.put(nodee.getAttributes().item(0).getNodeValue(), graphNode);
                                                 // System.out.println("Attributes" + nodee.getAttributes().item(0).getNodeValue());
                                             //    System.out.println(nodes.node.toString());
                                            }
                                            // This loop is used for type is "links"
                                            else{
                                                
                                                // We put again a loop to loop through all 
                                                // the elemensts
                                               
                                                System.out.println(nodee.getNodeName()); 
                                                System.out.println();
                                                
                                                 additionaModule AdditionalModule = new additionaModule();
                                                 link Link  = new link();
                                                    NodeList childNodess = nodee.getChildNodes();
                                                    System.out.println("Length "  + childNodess.getLength());
                                                    for(int z=0 ; z< nodee.getChildNodes().getLength() ; z++){
                                                        Node temporaryNode = childNodess.item(z) ;
                                                        if(childNodess.item(z).getNodeType() == Node.ELEMENT_NODE){
                                                       
                                                           if(temporaryNode.getNodeName().equals("source")){
                                                             //  System.out.println("I am in the source");
                                                               Link.source = temporaryNode.getChildNodes().item(0).getTextContent() ;
                                                               System.out.println(Link.source);
                                                           }
                                                           if(temporaryNode.getNodeName().equals("target")){
                                                               Link.target = temporaryNode.getChildNodes().item(0).getTextContent() ;
                                                           }
                                                           if(temporaryNode.getNodeName().equals("routingCost")){
                                                               Link.routingCost = Double.valueOf(temporaryNode.getChildNodes().item(0).getTextContent()) ;
                                                           }
                                                           if(temporaryNode.getNodeName().equals("additionalModules")){

                                                               //addModule AddModule = new addModule();
                                                               getaddModule(temporaryNode,AdditionalModule);
                                                               System.out.println("Additional Modules" + AdditionalModule.addMoudle.size());
                                                               Link.AdditionalModules = AdditionalModule; 
                                                           }

                                                        }
                                                    }
                                                    Links.Links.put(nodee.getAttributes().item(0).getNodeValue(), Link);
                                                  
                                            }
                                            
                                        }
                                    }
                                }
                                
                            }
                            //e.getNodeName() );
                        }
                    
                }
                
               // System.out.println("Staff Id" + eElement.get);
            }
        }
        
        
        NodeList nodeList = doc.getElementsByTagName("demands");
        
        for(int i = 0 ; i < nodeList.getLength() ; i ++){
            Node nNode = nodeList.item(i);
            
            NodeList demandList = nNode.getChildNodes() ;
            for(int j = 0 ; j < demandList.getLength() ; j++){
                
                
                Demand demand = new Demand();
                Node DemandNode = demandList.item(j);
                if(DemandNode.getNodeType() == Node.ELEMENT_NODE){
                //System.out.println(DemandNode.getAttributes().item(0).getNodeValue());
                NodeList demandNode = DemandNode.getChildNodes() ; 
               // System.out.println("---");
                for(int k = 0 ; k < demandNode.getLength() ; k++){
                  Node tempNode =   demandNode.item(k); 
                  
                  if(tempNode.getNodeType() == Node.ELEMENT_NODE){
                    //System.out.println("Demand Temp node" + tempNode.getNodeName());
                    if(tempNode.getNodeName().equals("source")){
                        demand.source = tempNode.getChildNodes().item(0).getTextContent();
                    }
                    if(tempNode.getNodeName().equals("target")){
                        demand.target = tempNode.getChildNodes().item(0).getTextContent();
                    }
                    if(tempNode.getNodeName().equals("demandValue")){
                        demand.demandValue = Double.valueOf(tempNode.getChildNodes().item(0).getTextContent());
                    }
                    
                  }
                   demands.demands.put(DemandNode.getAttributes().item(0).getNodeValue(), demand);
                }
               }
            }
            
        }
     
    }
    
    
      public  void getaddModule(Node node,additionaModule AdditionalModule ){
         NodeList childNodes =node.getChildNodes();
         for(int i = 0 ; i < childNodes.getLength() ; i ++){
             
             Node tempNode = childNodes.item(i);
             if(childNodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                 addModule AddModule = new addModule();
                // System.out.println("XY = "+childNodes.item(i).getNodeName());
                 if(tempNode.getNodeName().equals("addModule")){
                      NodeList grandChild = tempNode.getChildNodes() ;
                        for(int j = 0 ; j < grandChild.getLength() ; j++){
                            Node tempNode1 = grandChild.item(j);
                           if(tempNode1.getNodeType() == Node.ELEMENT_NODE){
                               System.out.println("Grand == " + tempNode1.getNodeName());
                               if(tempNode1.getNodeName().equals("capacity")){
                                   AddModule.capacity = Double.valueOf(tempNode1.getChildNodes().item(0).getTextContent());
                               }else{
                                    AddModule.cost = Double.valueOf(tempNode1.getChildNodes().item(0).getTextContent());
                               }
                           }
                        }
                 }
                 
                 AdditionalModule.addMoudle.add(AddModule);
             }
         
         }
    
    }
    
     public  void getXY(Node node,coordinates Coordinates){
         NodeList childNodes =node.getChildNodes();
         for(int i = 0 ; i < childNodes.getLength() ; i ++){
             
             Node tempNode = childNodes.item(i);
             if(childNodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                // System.out.println("XY = "+childNodes.item(i).getNodeName());
                 if(childNodes.item(i).getNodeName().equals("x") || childNodes.item(i).getNodeName().equals("y")){
                     
                     System.out.println("Count in");
                     System.out.println("Node name = " + tempNode.getNodeName() + " NodeValue = " + tempNode.getChildNodes().item(0).getTextContent() );
                     if(tempNode.getNodeName() == "x"){
                         
                        Coordinates.setX(Double.valueOf(tempNode.getChildNodes().item(0).getTextContent()));
                     }else if(tempNode.getNodeName() == "y"){
                          Coordinates.setY(Double.valueOf(tempNode.getChildNodes().item(0).getTextContent()));
                    
                     }  
                     
                     
                 }else{
                     getXY(tempNode,Coordinates);
                 }
             
             }
         }
         
     }
    
    
    
    
}
