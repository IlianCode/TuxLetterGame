/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    private Boolean end;
    
    public Partie(String date, String mot, int niveau){
       this.date = date; 
       this.mot = mot;
       this.niveau = niveau;
       this.temps = 0;
       this.trouvé = 0;
    }

    Partie(Element partieElt) {
        // élément DOM correspondant à une partie
        //recuperer les données d'une partie
        Element mot = (Element) partieElt.getElementsByTagName("mot").item(0);
        this.mot = mot.getTextContent();
        this.niveau = Integer.parseInt(mot.getAttribute("niveau"));
        this.date = partieElt.getAttribute("date");
        String trouvé = partieElt.getAttribute("trouvé");
        this.trouvé = Integer.parseInt(trouvé.replace("%", ""));
        
        //reinitialisation des données
        partieElt.setAttribute("trouvé", "" + this.trouvé);
        Element temps = (Element) partieElt.getElementsByTagName("temps").item(0);
        temps.setNodeValue("" + this.temps);
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    Element getPartie(Document doc){
         Element partieElem =  (Element) doc.createElement("partie");
        partieElem.setAttribute("date", this.date);
        partieElem.setAttribute("trouvé", this.trouvé + "%");
        
        //balise temps 
        Element tempsElem = (Element) doc.createElement("temps");
        tempsElem.appendChild(doc.createTextNode("" + this.temps));

        //balise mot avec attribut niveau
        Element motElem = (Element) doc.createElement("mot");
        motElem.setAttribute("niveau", ""+this.niveau);
        motElem.appendChild(doc.createTextNode(this.mot));

        //relie 
        partieElem.appendChild(tempsElem);
        partieElem.appendChild(motElem);

        return partieElem;
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

    public void setEnd(Boolean end) {
        this.end = end;
    }

    
    @Override
    public String toString(){
        return super.toString() + " ";
    }

    public String getDate() {
        return date;
    }

    public int getTrouvé() {
        return trouvé;
    }

    public int getTemps() {
        return temps;
    }
    
    
    
}
