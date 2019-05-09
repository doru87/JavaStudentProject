package aplicatie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Aplicatie {
    Connection conexiune;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    
public Aplicatie(){
    conexiune = conexiune_baza_de_date.baza_de_date();
       try {
           stmt = conexiune.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(Aplicatie.class.getName()).log(Level.SEVERE, null, ex);
       }
}
     
public HashMap<String,Integer> getHashMap(){
       HashMap<String,Integer> hashmap = new HashMap<String, Integer>(); 
  
        String query = "SELECT * FROM informatii_studenti";
        try {
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                String nume = rs.getString("nume");
                Integer varsta = rs.getInt("varsta");
                hashmap.put(nume, varsta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Aplicatie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashmap;
}

 public void afisareVarstaStudenti(List<Student> list,Department depart){
     String name = "";
     Double medie;
     HashMap<String,Integer> hash = null;
     
     HashMap<String,Integer> hash_map = new HashMap<String, Integer>(); 
     for (Iterator<Student> it = list.iterator(); it.hasNext();) {
         Student s = it.next();
         name = s.getName();
         medie = s.getMedie();
        
         hash = getHashMap();
            
         if(hash.containsKey(name)){
             Integer age = hash.get(name);
              hash_map.put(name,age);
         }else{
             hash_map.put(name,0);
         }
     }  
         System.out.println(hash_map);
  
 }
 
 public static void studenti_admisi(List<Student> studenti){
     
    List<Student> studenti_admisi = studenti.stream().filter(Student::studenti_admisi).collect(Collectors.toList());
        for(Student s: studenti_admisi){
            System.out.println("Studentul " + s.getName() + " are media " + s.getMedie());
        }
   
 }
  public static void studenti_respinsi(List<Student> studenti){
 
    List<Student> studenti_respinsi = studenti.stream().filter(Student::studenti_respinsi).collect(Collectors.toList());
        for(Student s: studenti_respinsi){
            System.out.println("Studentul " + s.getName() + " are media " + s.getMedie());
        }
   
 }
 
 public static List<Student> getStudents(){
     
    List<Student> list = new ArrayList<Student>();
    
      list.add(new Student("Carstoiu Eugen",7.84,"Cluj-Napoca","masculin"));
      list.add(new Student("Radu Mariana",8.36,"Brasov","feminin"));
      list.add(new Student("Anghel Ramona",3.58,"Constanta","feminin"));
      list.add(new Student("Sorica Marius",2.36,"Iasi","masculin"));
      
    return list;
 }
 
 public static List<Student> sorteazaMedia(List<Student>list){
     
     list.sort((Student st1,Student st2)->(int) (st1.getMedie()-st2.getMedie()));
     
     return list;
     
 }
 public static void sortareOrdineAscendenta(List<Student> list){
    Collections.sort(list, new Comparator<Student>() {

        public int compare(Student s1, Student s2) {
           int c = s1.getName().compareTo(s2.getName());
         
            return c;
        }
           

});
     System.out.println(list);
 }
 
     public static void ordoneazaSex(List<Student> list,String sex){
         
         String sex_student = "";
         Student s = null;
            List<Student> listaSortata = new ArrayList<Student>();
                for (int i = 0; i < list.size(); i++) {
                    s = list.get(i);
                    sex_student = s.getSex();

                    if(sex_student == sex){
                         listaSortata.add(s);
                    }

                }
        System.out.println(listaSortata);
   
    }
     
     public void stergeStudent(String value){
          String nume = "";
        try {
      
            String query="delete from informatii_studenti where nume=?";
            pstmt=conexiune.prepareStatement(query);
            pstmt.setString(1,value);
            Integer update = pstmt.executeUpdate();
            if(update == 1){
                System.out.println("Studentul cu numele " + value + " a fost sters din baza de date");
            }else{
                System.out.println("Studentul cu numele " + value + " nu a fost gasit in baza de date");
            }
    
          
        } catch (SQLException ex) {
            Logger.getLogger(Aplicatie.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public void actualizeazaNumeStudent(String old_name,String new_name){
        try {
            String query = "update informatii_studenti set nume=? where nume=?";
            pstmt=conexiune.prepareStatement(query);
            pstmt.setString(1,new_name);
            pstmt.setString(2, old_name);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Aplicatie.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
    public static void main(String[] args) {

        List<Student> list = getStudents();
        
        Department dep = new Department("Informatica",list);


        Aplicatie apl = new Aplicatie();
        System.out.println("\n" +"-------------Afisarea varstei studentilor pe baza interogarii bazei de date-------------"+ "\n");
        
        apl.afisareVarstaStudenti(list,dep);
        
        System.out.println("\n" +"----------------------Lista studentilor admisi-----------------------"+ "\n");
       
        studenti_admisi(list);
       
        System.out.println("\n" +"----------------------Lista studentilor respinsi-----------------------"+ "\n");
        
        studenti_respinsi(list);
        
        System.out.println("\n" +"----------------Sortare in ordine ascendenta a mediilor----------------"+ "\n");
        
        List<Student> listastudenti = sorteazaMedia(list);
        
        for (Student s : listastudenti){
            System.out.println(s);
        }

        System.out.println("\n" +"----------------Sortare in ordine ascendenta a numelor----------------"+ "\n");
        sortareOrdineAscendenta(list);
        
        System.out.println("\n" +"----------------Sortare in functie de sexul feminin----------------"+ "\n");

        ordoneazaSex(list,"feminin");
        
        System.out.println("\n" +"----------------Sortare in functie de sexul masculin----------------"+ "\n");

        ordoneazaSex(list,"masculin");
   
        System.out.println("\n" +"-----------Introduce numele studentului care vrei sa il sterrgi din baza de date----------"+ "\n");
        String numeStudent = CitesteDateConsola.stringInput();
        
        apl.stergeStudent(numeStudent);
        
        System.out.println("\n" +"--------------------------Actualizeaza numele studentului-----------------------------"+ "\n");

        System.out.println("Introduce numele studentului care vrei sa il actualizezi:");
        String numeVechi = CitesteDateConsola.stringInput();
        
        System.out.println("Introduce nume nou:");
        String numeNou = CitesteDateConsola.stringInput();
        
        apl.actualizeazaNumeStudent(numeVechi,numeNou);
    }
    
}
