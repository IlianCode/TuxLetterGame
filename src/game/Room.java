/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

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
    
    public Room(){
        this.depth =100;
        this.height=100;
        this.width =60;
        this.textureBottom="/home/b/benaisil/Bureau/TuxLetterGame_template/textures/floor/grass1.png";
        this.textureNorth="/home/b/benaisil/Bureau/TuxLetterGame_template/textures/skybox/snow/north.png";
        this.textureEast = "/home/b/benaisil/Bureau/TuxLetterGame_template/textures/skybox/snow/east.png";
        this.textureWest = "/home/b/benaisil/Bureau/TuxLetterGame_template/textures/skybox/snow/west.png";
        this.textureTop = null;
        this.textureSouth = null;
        
    }

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
    
    
}
