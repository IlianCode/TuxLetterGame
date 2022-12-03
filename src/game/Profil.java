/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author benaisil
 */
public class Profil {

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
        this.avatar = avatar;
        this.parties = new ArrayList<Partie>();

    }

    public boolean charge(String nomJoueur) {
        return this.existe;
    }
    
    public String getNom(){
        return this.nom;
    }
}
