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

    public Profil() {

    }

    public Profil(String nom, String dateNaissance) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.avatar = "";
    }

    
    
    
    
    
    public Profil(String filename) {

    }

    
    
    
    
    public void ajouterPartie(Partie p) {
        parties.add(p);
    }

    public boolean charge(String nomJoueur) {
        return this.existe;
    }

    public void sauvegarder(String filename) throws ParserConfigurationException, SAXException, IOException, TransformerException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder p = dbFactory.newDocumentBuilder();
        // récupération de la structure objet du document
        Document doc = p.newDocument();

        Element racine = doc.createElement("profil");
        doc.appendChild(racine);

        Element name = doc.createElement("nom");
        name.setTextContent(this.nom);
        racine.appendChild(name);

        Element Avatar = doc.createElement("avatar");
        Avatar.setTextContent(this.avatar);
        racine.appendChild(Avatar);

        Element birthday = doc.createElement("anniversaire");
        birthday.setTextContent(this.dateNaissance);
        racine.appendChild(birthday);

        Element games = doc.createElement("parties");
        racine.appendChild(games);

        for (int i = 0; i < this.parties.size(); i++) {

            Element game = doc.createElement("partie");
            game.setAttribute("date", this.parties.get(i).getDate());
            game.setAttribute("trouvé", Integer.toString(this.parties.get(i).getTrouvé()) + "%");
            racine.appendChild(game);

            Element word = doc.createElement("mot");
            word.setAttribute("niveau", Integer.toString(this.parties.get(i).getNiveau()));
            word.setTextContent(this.parties.get(i).getMot());
            game.appendChild(word);

            Element time = doc.createElement("temps");
            time.setTextContent(Integer.toString(this.parties.get(i).getTemps()));
            game.appendChild(time);
        }

        Source source = new DOMSource(doc);

        // le résultat de cette transformation sera un flux d'écriture dans
        // un fichier
        Result resultat = new StreamResult(new File(filename));

        // création du transformateur XML
        Transformer transfo = null;
        try {
            transfo = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            System.err.println("Impossible de créer un transformateur XML.");
            System.exit(1);
        }

        // configuration du transformateur
        // sortie en XML
        transfo.setOutputProperty(OutputKeys.METHOD, "xml");

        // inclut une déclaration XML (recommandé)
        transfo.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        // codage des caractères : UTF-8. Ce pourrait être également ISO-8859-1
        transfo.setOutputProperty(OutputKeys.ENCODING, "utf-8");

        // idente le fichier XML
        transfo.setOutputProperty(OutputKeys.INDENT, "yes");

        transfo.transform(source, resultat);
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
