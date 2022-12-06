/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import static com.jme3.math.FastMath.rand;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author benaisil
 */
public class Dico {

    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;

    private String cheminFichierDico;
    private boolean boolMot;
    private int currentLevel;

    private StringBuffer buffer;

    public Dico(String cheminFichierDico) {
        super();
        this.cheminFichierDico = cheminFichierDico;
        listeNiveau1 = new ArrayList<String>();
        listeNiveau2 = new ArrayList<String>();
        listeNiveau3 = new ArrayList<String>();
        listeNiveau4 = new ArrayList<String>();
        listeNiveau5 = new ArrayList<String>();
        try {
            lireDictionnaireDOM(this.cheminFichierDico, "dico.xml");
        } catch (SAXException ex) {
            Logger.getLogger(Dico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int vérifieNiveau(int niveau) {
        int min = 1;
        int max = 5;

        int res = niveau;
        if (niveau < 1 || niveau > 5) {
            res = (int) Math.floor(Math.random() * (max - min + 1) + min);

        }
        return niveau;
    }

    private Boolean verifNiveau(int niveau) {
        Boolean res = true;
        if (niveau > 5 || niveau < 1) {
            res = false;
        }
        return res;
    }

    public String getMotDepuisListeNiveau(int niveau) {
        String res = "";
        int niveauVerif = vérifieNiveau(niveau);
        switch (niveauVerif) {
            case 1:
                res += getMotDepuisListe(listeNiveau1);
                break;
            case 2:
                res += getMotDepuisListe(listeNiveau2);
                break;
            case 3:
                res += getMotDepuisListe(listeNiveau3);
                break;
            case 4:
                res += getMotDepuisListe(listeNiveau4);
                break;
            case 5:
                res += getMotDepuisListe(listeNiveau5);
                break;

        }
        return res;
    }

    private String getMotDepuisListe(ArrayList<String> list) {
        String res = "";
        int max = list.size();
        int random_int = rand.nextInt(max);

        res += list.get(random_int);

        return res;

    }

    public void ajouteMotADico(int niveau, String mot) {
        if (verifNiveau(niveau)) {
            switch (niveau) {
                case 1:
                    listeNiveau1.add(mot);
                    break;
                case 2:
                    listeNiveau2.add(mot);
                    break;
                case 3:
                    listeNiveau3.add(mot);
                    break;
                case 4:
                    listeNiveau4.add(mot);
                    break;
                case 5:
                    listeNiveau5.add(mot);
                    break;
            }

        }
    }

    public void lireDictionnaireDOM(String path, String filename) throws SAXException, IOException {
        int j = 0;
        DOMParser parser;
        parser = new DOMParser();
        parser.parse(path);

        Document doc = parser.getDocument();
        NodeList listeMot = doc.getElementsByTagName("mot");

        ArrayList<String> motniv1 = new ArrayList<String>();
        ArrayList<String> motniv2 = new ArrayList<String>();
        ArrayList<String> motniv3 = new ArrayList<String>();
        ArrayList<String> motniv4 = new ArrayList<String>();
        ArrayList<String> motniv5 = new ArrayList<String>();

        for (j = 0; j < listeMot.getLength(); j++) {
            if (listeMot.item(j).getAttributes().getNamedItem("niveau").getTextContent().equals("1")) {
                motniv1.add(listeMot.item(j).getTextContent());
            }
        }
        for (j = 0; j < listeMot.getLength(); j++) {
            if (listeMot.item(j).getAttributes().getNamedItem("niveau").getTextContent().equals("2")) {
                motniv2.add(listeMot.item(j).getTextContent());
            }
        }
        for (j = 0; j < listeMot.getLength(); j++) {
            if (listeMot.item(j).getAttributes().getNamedItem("niveau").getTextContent().equals("3")) {
                motniv3.add(listeMot.item(j).getTextContent());
            }
        }
        for (j = 0; j < listeMot.getLength(); j++) {
            if (listeMot.item(j).getAttributes().getNamedItem("niveau").getTextContent().equals("4")) {
                motniv4.add(listeMot.item(j).getTextContent());
            }
        }
        for (j = 0; j < listeMot.getLength(); j++) {
            if (listeMot.item(j).getAttributes().getNamedItem("niveau").getTextContent().equals("5")) {
                motniv5.add(listeMot.item(j).getTextContent());
            }
        }
//        Element niveau1 = (Element) listeMot.getAttribute(path)
        for (String m : motniv1) {
            listeNiveau1.add(m);
        }
        for (String m : motniv2) {
            listeNiveau2.add(m);
        }
        for (String m : motniv3) {
            listeNiveau3.add(m);
        }
        for (String m : motniv4) {
            listeNiveau4.add(m);
        }
        for (String m : motniv5) {
            listeNiveau5.add(m);
        }

    }
}

   /* public void lireDictionnaire() throws SAXException, IOException {
        SAXParserFactory fabrique = SAXParserFactory.newInstance();
        SAXParser parseur = fabrique.newSAXParser();

        File fichier = new File(cheminFichierDico);
        DefaultHandler gestionnaire;
        gestionnaire = new DefaultHandler();
        parseur.parse(fichier, gestionnaire);

    }*/
/*
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        buffer = new StringBuffer();
        if (qName.equals("mot")) {
            boolMot = true;
            try {
                currentLevel = Integer.parseInt(attributes.getValue("niveau"));
                if (currentLevel < 1 || currentLevel > 5) {
                    currentLevel = 0;
                }
            } catch (NumberFormatException e) {
                currentLevel = 0;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("mot")) {
            int bufLen = buffer.toString().length();
            boolMot = false;
            switch (currentLevel) {
                case 0:
                    if (bufLen > 7) {
                        currentLevel = 5;
                    } else {
                        currentLevel = bufLen - 2;
                    }
                // On ne fait pas de break pour pouvoir prendre en compte le nouveau niveau.
                case 1:
                    listeNiveau1.add(buffer.toString());
                    break;
                case 2:
                    listeNiveau2.add(buffer.toString());
                    break;
                case 3:
                    listeNiveau3.add(buffer.toString());
                    break;
                case 4:
                    listeNiveau4.add(buffer.toString());
                    break;
                case 5:
                    listeNiveau5.add(buffer.toString());
                    break;
                default:
                    break;
            }
            buffer = null;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String lecture = new String(ch, start, length);
        if (buffer != null) {
            buffer.append(lecture);
        }
    }

    @Override
    public void startDocument() {
        listeNiveau1 = new ArrayList<String>();
        listeNiveau2 = new ArrayList<String>();
        listeNiveau3 = new ArrayList<String>();
        listeNiveau4 = new ArrayList<String>();
        listeNiveau5 = new ArrayList<String>();
    }

    @Override
    public void endDocument() {
    }
}*/
