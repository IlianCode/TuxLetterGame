/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.advanced.EnvNode;

/**
 *
 * @author benaisil
 */
public class Letter extends EnvNode {
    private char letter; 
    
    public Letter(char l, double x, double z) {
        setScale(4.0);

        setX(x);
        setZ(z);
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        String texturePath ="";
        if(Character.isWhitespace(l)){
            texturePath += "models/letter/cube.png";
        }else{
            this.letter = Character.toLowerCase(l);
            texturePath += "models/letter/"+letter+".png";
            
        }
        
        setTexture(texturePath);
        setModel("models/letter/cube.obj");
    }

    public char getLetter() {
        return letter;
    }
    
    @Override
    public String toString(){
       return Character.toString(letter);
    }
}
