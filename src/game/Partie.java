/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author benaisil
 */
public class Partie {
    private String date;
    private int niveau;
    private String mot;
    private int temps;
    private int trouvé;
    private boolean end;
    
    public Partie(String date, String mot, int niveau){
       this.date = date; 
       this.mot = mot;
       this.niveau = niveau;
       this.temps = 0;
       this.trouvé = 0;
       this.end = false; 
    }

    Partie() {
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public void setTrouvé(int trouvé) {
        this.trouvé = trouvé;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getMot() {
        return mot;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
    
    @Override
    public String toString(){
        return super.toString() + " ";
    }
    
}
