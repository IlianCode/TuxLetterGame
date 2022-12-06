/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author benaisil
 */
public class LanceurDeJeu {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
       
        Jeu jeu;
        jeu = new JeuDevineLeMotOrdre();
        jeu.execute();
    }
}
