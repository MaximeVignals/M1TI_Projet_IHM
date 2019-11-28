/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mvignals
 */
public class Chrono implements Runnable{
    
   int temps;

   public Chrono(){
       temps = 0;
   }
   @Override
   public void run(){
    while(true){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Chrono.class.getName()).log(Level.SEVERE, null, ex);
        }
        temps++;
    }    
   }
   public int getTemps(){
       return temps;
   }
}
