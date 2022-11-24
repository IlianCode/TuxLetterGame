/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import static com.jme3.math.FastMath.rand;
import env3d.Env;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author benaisil
 */
public abstract class Jeu {

    private Env env;
    private Room room;
    private Profil profil;
    private Tux tux;
    private Letter letter;
    private ArrayList<Letter> lettres;
    private Dico dico;

    public Jeu() {

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
        profil = new Profil();

        lettres = new ArrayList<Letter>();
        //instanciation dictionnaire 
        dico = new Dico("/mondictionnaire.xml");

    }

    public void execute() {
        // pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
        // et nous créons une partie vide, juste pour que cela fonctionne
        joue(new Partie());

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
        String motAdd ="t";
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
            Boolean collision = false;

            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }
            // Contrôles des déplacements de Tux (gauche, droite, ...)
            for (Letter l : lettres) {
                if (collision(l)) {
                    collision = true;
                    System.out.println(distance(l));
                }
            }
            
            tux.déplace(collision);
            // Ici, on applique les regles
            appliqueRegles(partie);

            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }

        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);

    }

    protected void démarrePartie(Partie partie) {

    }

    protected void appliqueRegles(Partie partie) {

    }

    protected void terminePartie(Partie partie) {

    }

    protected double distance(Letter letter) {
        Double posTux ;
        posTux = tux.getX() - letter.getX();
        return posTux;
    }

    protected Boolean collision(Letter letter) {
        Boolean res = false;
        //valeur par défaut 
        if (env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP)) { // Fleche 'haut' ou Z
            if (tux.getZ() - 4 == letter.getZ() + 4 && (letter.getX() - 4 <= tux.getX() + 4 && letter.getX() + 4 >= tux.getX() - 4)) {
                res = true;

            }
        }
        if (env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT)) { // Fleche 'gauche' ou Q
            // Gauche
            if (tux.getX() - 4 == letter.getX() + 4 && (letter.getZ() - 4 <= tux.getZ() + 4 && letter.getZ() + 4 >= tux.getZ() - 4)) {
                res = true;
            }

        }
        if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT)) { // Fleche 'droite' ou D
            // Droite
            if (tux.getX() + 4 == letter.getX() - 4 && (letter.getZ() - 4 <= tux.getZ() + 4 && letter.getZ() + 4 >= tux.getZ() - 4)) {
                res = true;
                //&& l.getZ() == tux.getZ()

            }

        }
        if (env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN)) { // Fleche 'bas' ou S
            // Bas
            if (tux.getZ() + 4 == letter.getZ() - 4 && (letter.getX() - 4 <= tux.getX() + 4 && letter.getX() + 4 >= tux.getX() - 4)) {
                res = true;

            }

        }

        // System.out.println(tux.getZ());
        //x = 14
        //for(Letter l : lettres){
        //System.out.println(tux.getX());
        //System.out.println(l.getX());
        /* if (tux.getX()+3 == letter.getX()-3 && (letter.getZ()-3 <= tux.getZ()+3 && letter.getZ()+3 >= tux.getZ()-3 )) {
            //(letter.getX() + 3 == tux.getX()-3 || letter.getX() - 3 == tux.getX()+3) && ( letter.getZ() + 3 == tux.getZ()-3 || letter.getZ() - 3 == tux.getZ()+3)
            res = true;
            //&& l.getZ() == tux.getZ()

        } else if (tux.getX()-3 == letter.getX()+3 && (letter.getZ()-3 <= tux.getZ()+3 && letter.getZ()+3 >= tux.getZ()-3 )){
            res = true;  
        }else if (tux.getZ()+3 == letter.getZ()-3 && (letter.getX()-3 <= tux.getX()+3 && letter.getX()+3 >= tux.getX()-3 ))  {
             res = true;  
       
        }else if (tux.getZ()-3 == letter.getZ()+3 && (letter.getX()-3 <= tux.getX()+3 && letter.getX()+3 >= tux.getX()-3 ))  {
             res = true;  
       
        }*/
        // }
        return res;
    }
    //if(!testeRoomCollision(this.getX() , this.getZ()-1)&& !collision){
    //this.setRotateY(180);
    //this.setZ(this.getZ() - 1.0);
    //}
}

//test