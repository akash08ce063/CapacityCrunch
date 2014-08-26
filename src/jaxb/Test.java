/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo KAKA
 */

@XmlRootElement
public class Test {
    int salary ;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    String name ;
    
    
    
}
