package aplicatie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CitesteDateConsola {
    
    public static String stringInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String dateConsola = null;
            try {
              dateConsola = br.readLine();
            } catch (IOException e) {
            }
        return dateConsola;
    }
}
