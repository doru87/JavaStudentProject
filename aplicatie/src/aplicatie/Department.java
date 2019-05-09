
package aplicatie;

import java.util.List;

public class Department {
    String name; 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private List<Student> students; 
    
    Department(String name, List<Student> students)  
    { 
          
        this.name = name; 
        this.students = students; 
          
    } 
      
    public List<Student> getStudents()  
    { 
        return students; 
    } 
}
