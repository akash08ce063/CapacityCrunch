/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Ricardo KAKA
 */

@XmlRootElement
public class Customer {
    String name ;
    int id;
    int age ;
    List<Test> item ;
    
    
    Customer(){
        item = new ArrayList<Test>();
    }

    public List getItem() {
        return item;
    }
    
    public void setTest(){
    
    }
    

    public void setItem(List item) {
        this.item = item;
    }
    public void addItem(){
        Test t = new Test();
        t.setName("Bittu");
        t.setSalary(33);
        
        this.item.add(t);
        System.out.println("size After adding" + this.item.size());
    
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
    
}
