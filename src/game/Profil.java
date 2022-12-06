/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.XMLUtil;

/**
 *
 * @author benaisil
 */
public final class Profil {

    private String nom;
    private String dateNaissance;
    private boolean existe = false;
    private ArrayList<Partie> parties;
    private String avatar;

    private Element nomCharge;
    private Element profilEnDOM; //le profil en Dom (Element DOM)

    public final String fileProfilXML = "src/Data/xml/profil.xml";
    public Document doc;
    private Boolean docCharge = false;

    public Profil() {

    }

    public Profil(String nom, String dateNaissance) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.avatar = "";
    }

    public Profil(String nomJoueur) {
        init_doc();
//TODO: penser à l'interface du jeu pour charger une partie, afficher tous les profils
        if (charge(nomJoueur)) {
            if (nomJoueur.equals(nomCharge.getTextContent())) {
                profilEnDOM = (Element) nomCharge.getParentNode();
                Element dateNais = (Element) profilEnDOM.getElementsByTagName("anniversaire").item(0);
                Element avatar = (Element) profilEnDOM.getElementsByTagName("avatar").item(0);
                this.nom = nomCharge.getTextContent();
                this.dateNaissance = xmlDateToProfileDate(dateNais.getTextContent());
                this.avatar = avatar.getTextContent();
            }
        }
    }

    public void ajouterPartie(Partie p) {
        parties.add(p);
    }

    private void init_doc() {
        doc = fromXML(fileProfilXML);
        docCharge = true;
    }

    protected boolean charge(String nomJoueur) {
        if (!docCharge) {
            init_doc();
        }
        NodeList noms = doc.getElementsByTagName("nom");
        int i;
        for (i = 0; i < noms.getLength(); i++) {
            String nom = noms.item(i).getTextContent();

            if (nom.equals(nomJoueur)) {
                nomCharge = (Element) noms.item(i);
                this.nom = nomJoueur;
                return true;
            }
        }
        return false;
    }

    public void sauvegarder(Partie p) {
        //init_doc();
        p.setDate(profileDateToXmlDate(p.getDate()));
        Element partie = p.createPartieOnDOM(_doc);
        Element parties = (Element) profilEnDOM.getElementsByTagName("parties").item(0);
        parties.appendChild(partie);
        toXML(fileProfilXML);
    }

    public Document _doc;

    // Cree un DOM à partir d'un fichier XML
    // Cree un DOM à partir d'un fichier XML
    public Document fromXML(String nomFichier) {
        try {
            return XMLUtil.DocumentFactory.fromFile(nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Sauvegarde un DOM en XML
    public void toXML(String nomFichier) {
        try {
            XMLUtil.DocumentTransform.writeDoc(_doc, nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// Takes a date in XML format (i.e. ????-??-??) and returns a date
    /// in profile format: dd/mm/yyyy
    public static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }

    /// Takes a date in profile format: dd/mm/yyyy and returns a date
    /// in XML format (i.e. ????-??-??)
    public static String profileDateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }

    public String getNom() {
        return this.nom;
    }
}
