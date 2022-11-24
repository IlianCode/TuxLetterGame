/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author benaisil
 */
import game.Dico;
import java.io.IOException;
import org.xml.sax.SAXException;
public class TestDico {

    public static void main(String[] args) throws SAXException, IOException {
        Dico dictionnaire = new Dico("testdico.xml");
        
        
        
        dictionnaire.lireDictionnaireDOM("src/test/dico.xml", "dico");
        
        System.out.println("niveau 1 : " + dictionnaire.getMotDepuisListeNiveau(1));
        System.out.println("niveau 2 : " +dictionnaire.getMotDepuisListeNiveau(2));
        System.out.println("niveau 3 : " +dictionnaire.getMotDepuisListeNiveau(3));
        System.out.println("niveau 4 : " +dictionnaire.getMotDepuisListeNiveau(4));
        System.out.println("niveau 5 : " +dictionnaire.getMotDepuisListeNiveau(5));
        
    }

   
}
