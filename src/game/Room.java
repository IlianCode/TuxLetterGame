/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author benaisil
 */
public class Room {

    private int depth;
    private int height;
    private int width;
    private String textureBottom;
    private String textureNorth;
    private String textureEast;
    private String textureWest;
    private String textureTop;
    private String textureSouth;

    Document doc;
//constructeur de la room par défaut
    public Room() {
        this.depth = 100;
        this.height = 100;
        this.width = 60;
        this.textureBottom = "/textures/floor/grass1.png";
        this.textureNorth = "/textures/skybox/snow/north.png";
        this.textureEast = "/textures/skybox/snow/east.png";
        this.textureWest = "/textures/skybox/snow/west.png";
        this.textureTop = null;
        this.textureSouth = null;

    }

    //creation de la room en sax
    public Room(String filename) throws ParserConfigurationException, SAXException, IOException {
        doc = fromXML(filename);
        height = Integer.parseInt(doc.getElementsByTagName("height").item(0).getTextContent());
        depth = Integer.parseInt(doc.getElementsByTagName("depth").item(0).getTextContent());
        width = Integer.parseInt(doc.getElementsByTagName("width").item(0).getTextContent());
        textureBottom = doc.getElementsByTagName("textureBottom").item(0).getTextContent();
        textureNorth = doc.getElementsByTagName("textureNorth").item(0).getTextContent();
        textureEast = doc.getElementsByTagName("textureEast").item(0).getTextContent();
        textureWest = doc.getElementsByTagName("textureWest").item(0).getTextContent();
    }

    
    //getter et setter
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTextureBottom() {
        return textureBottom;
    }

    public void setTextureBottom(String textureBottom) {
        this.textureBottom = textureBottom;
    }

    public String getTextureNorth() {
        return textureNorth;
    }

    public void setTextureNorth(String textureNorth) {
        this.textureNorth = textureNorth;
    }

    public String getTextureEast() {
        return textureEast;
    }

    public void setTextureEast(String textureEast) {
        this.textureEast = textureEast;
    }

    public String getTextureWest() {
        return textureWest;
    }

    public void setTextureWest(String textureWest) {
        this.textureWest = textureWest;
    }

    public String getTextureTop() {
        return textureTop;
    }

    public void setTextureTop(String textureTop) {
        this.textureTop = textureTop;
    }

    public String getTextureSouth() {
        return textureSouth;
    }

    public void setTextureSouth(String textureSouth) {
        this.textureSouth = textureSouth;
    }
    
    public Document fromXML(String nomFichier) throws ParserConfigurationException, SAXException, IOException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(nomFichier));

    }

}
