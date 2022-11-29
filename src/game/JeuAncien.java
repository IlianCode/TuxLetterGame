/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import static com.jme3.math.FastMath.rand;
import env3d.Env;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benaisil
 */
public abstract class JeuAncien {

    Env env;
    private Room room;
    private Profil profil;
    private Tux tux;
    private Letter letter;
    protected ArrayList<Letter> lettres;
    private Dico dico;

    public JeuAncien() {

        // Crée un nouvel environnement
        env = new Env();

        // Instancie une Room
        room = new Room();
        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
      //  profil = new Profil();

        lettres = new ArrayList<Letter>();
        //instanciation dictionnaire 
        //dico = new Dico("/mondictionnaire.xml");

    }

    public void execute() {
        // pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
        // et nous créons une partie vide, juste pour que cela fonctionne
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        joue(new Partie("28-11-2022", "oui" , 2 ));

        // Détruit l'environnement et provoque la sortie du programme 
        env.exit();
    }

    public void joue(Partie partie) {
        // TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
        env.setRoom(room);

        // Instancie un Tux
        tux = new Tux(env, room);
        env.addObject(tux);
        //liste de lettre

        dico.ajouteMotADico(1, "cheval");
        //String motAdd = dico.getMotDepuisListeNiveau(1);
        String motAdd = "t";
        for (int i = 0; i < motAdd.length(); i++) {
            lettres.add(new Letter(motAdd.charAt(i), rand.nextInt(60), rand.nextInt(100)));
        }
        //lettres.add(new Letter('Y', 20,90)); 
        //lettres.add(new Letter('E', 10,70)); 
        //lettres.add(new Letter(' ', 30,40)); 
        //lettres.add(new Letter('X', 50,20)); 

        for (Letter l : lettres) {
            env.addObject(l);
        }
        // env.addObject(letter);

        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        démarrePartie(partie);

        // Boucle de jeu
        Boolean finished;
        finished = false;
        while (!finished) {
            Boolean col = false;

            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }
            // Contrôles des déplacements de Tux (gauche, droite, ...)
            for (Letter l : lettres) {
                if (coli(l)) {
                    col = true;
                    System.out.println("in");
                } else {
                  //  System.out.println("out");
                }

            }
            try {
                tux.deplace();
            } catch (InterruptedException ex) {
                Logger.getLogger(JeuAncien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Ici, on applique les regles
            appliqueRegles(partie);
             // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }

        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);

    }

    protected abstract void démarrePartie(Partie partie);

    protected abstract void appliqueRegles(Partie partie);

    protected abstract void terminePartie(Partie partie);
    //===========

    protected double distance(Letter letter) {
        double dist;
        dist = Math.sqrt(Math.pow(letter.getX() - tux.getX(), 2) + Math.pow(letter.getY() - tux.getY(), 2) + Math.pow(letter.getZ() - tux.getZ(), 2));
        return dist;

    }

    public boolean coli(Letter lettre) {
        boolean res = false;
        if (distance(lettre) < tux.getScale() + lettre.getScale()) {
            res = true;
        }
        return res;
    }

}

