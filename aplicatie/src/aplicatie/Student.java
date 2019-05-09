/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicatie;

import java.util.Comparator;

public class Student { 
    String name; 
    double medie ; 
    String location; 
    String sex;
      
    Student(String name, double medie, String location)  
    { 
        this.name = name; 
        this.medie = medie; 
        this.location = location; 
          
    } 
      Student(String name, double medie, String location,String sex)  
    { 
        this.name = name; 
        this.medie = medie; 
        this.location = location; 
        this.sex = sex;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMedie() {
        return medie;
    }

    public void setMedie(double medie) {
        this.medie = medie;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String toString() {
        return name+" "+location + " "+ medie;
    }

    public boolean studenti_admisi(){
        return this.getMedie() > 5;   
    }
    
    public boolean studenti_respinsi(){
        return this.getMedie() < 5;
    }
    
}
