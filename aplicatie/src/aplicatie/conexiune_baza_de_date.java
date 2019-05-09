package aplicatie;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexiune_baza_de_date { 
    
    static Connection conexiune;
    public static Connection baza_de_date(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
		conexiune=DriverManager.getConnection("jdbc:mysql://localhost:3306/aplicatie","root","");

            return conexiune;
           
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
    }
}
