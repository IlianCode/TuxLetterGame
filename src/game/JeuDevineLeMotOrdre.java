/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author benaisil
 */
public class JeuDevineLeMotOrdre extends Jeu{
    private int nbLettresRestantes;
    private Chronometre chrono; 
    int nbLetterLeft;
    public JeuDevineLeMotOrdre(){
        super();
    }
    
    @Override
    protected void démarrePartie(Partie partie){
        //initialisation du chrono 
        int limiteChrono = 20;
        chrono = new Chronometre(limiteChrono);
        chrono.start();
        chrono.stop();
        
        //initilisation du nombre de lettre restante dans la partie 
        nbLetterLeft = partie.getMot().length();
    }

    private Boolean tuxTrouveLettre(int i){
                //valeur par défaut 
        Boolean trouvee = coli(lettres.get(i));
        return trouvee;
        
    }

    @Override
    protected void appliqueRegles(Partie partie) {
        int lettreIndice;
        
        //s'il reste du temps dans la partie 
        if(chrono.remainsTime()){
            lettreIndice = partie.getMot().length() - nbLetterLeft;
            //si lettre trouver dans l'ordre le nombre de lettre restantre diminue 
            if(tuxTrouveLettre(lettreIndice)&& (lettreIndice < partie.getMot().length()) ){
                env.removeObject(lettres.get(lettreIndice));
                nbLettresRestantes -=1;
            }
            if(nbLettresRestantes == 0) {
                partie.setEnd(true);
            }
            System.out.println("test");
        }else {
            partie.setEnd(true);
        }
    }

    @Override
    protected void terminePartie(Partie partie) {
        if(!chrono.remainsTime()){
            partie.setTrouvé(nbLettresRestantes);
        }else{
            partie.setTemps(chrono.timeSpent());
            partie.setTrouvé(0);
        }
    }


}
