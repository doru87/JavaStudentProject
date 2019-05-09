/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicatie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author John
 */
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
