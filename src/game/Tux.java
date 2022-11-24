/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;
import env3d.advanced.EnvNode;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author benaisil
 */
public class Tux extends EnvNode {

    private Env env;
    private Room room;

    public Tux(Env env, Room room) {
        this.env = env;

        this.room = room;

        setScale(4.0);
        setX(30);// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ(50); // positionnement au milieu de la profondeur de la room
        setTexture("models/tux/tux_happy.png");
        setModel("models/tux/tux.obj");
    }

    
    private Boolean testeRoomCollision(double x, double z){
        Boolean res= false; 
        
        if(x> 60 || x <0 ){
            res = true;
        }
        if(z> 100   || z < 0){
            res = true; 
        }
        //if(collision == true){
         //   res =true;
       // }
        return res;
    }
    public void déplace(Boolean collision) {
        if (env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP)) { // Fleche 'haut' ou Z
            // Haut
            if(!testeRoomCollision(this.getX() , this.getZ()-1)&& !collision){
                this.setRotateY(180);
                this.setZ(this.getZ() - 1.0);
            }
            
        }
        if (env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT)) { // Fleche 'gauche' ou Q
            // Gauche
            if(!testeRoomCollision(this.getX()-1 , this.getZ())&& !collision){
                this.setRotateY(270);
                this.setX(this.getX() - 1.0);
            }            
        }
        if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT)) { // Fleche 'droite' ou D
            // Droite
            if(!testeRoomCollision(this.getX()+1 , this.getZ()) && !collision){
                this.setRotateY(90);
                this.setX(this.getX() + 1.0);

            }
            
        }
        if (env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN)) { // Fleche 'bas' ou S
            // Bas
            if(!testeRoomCollision(this.getX() , this.getZ()+1) && !collision){            
                this.setRotateY(360);
                this.setZ(this.getZ() + 1.0);
            }
        }
        
    }
    
    
    
}
