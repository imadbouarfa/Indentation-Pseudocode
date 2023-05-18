/**
 * Cette classe indente les fichiers pseudocodes
 *
 * @author Imad Bouarfa Shaker
 * Courriel: bouarfa.imad@courrier.uqam.ca
 * Cours: INF1120-20
 * @version 2022-11-19
 */
public class IndenteurPseudocode {


    public static final String INDENTATION = "   ";
    public static final String PRESENTATION = "Ce programme permet de corriger l'indentation d'un algorithme ecrit en pseudocode.";


    /**
     * Affiche un menu de 2 options pour l'indentation d'un algorithme ecrit en pseudocode..
     *    1. Indenter pseudocode
     *    2. Quitter
     */
    public static void afficherMenu() {
        System.out.println("\n\n----"
                + "\nMENU\n"
                + "----");
        System.out.println("1. Indenter pseudocode");
        System.out.println("2. Quitter");
        System.out.print("\nEntrez votre choix : ");

    }

    /**
     * Ceci demande de rentrer un choix de menu valide entre 1 et 2.
     * @param msgErr Le message d'erreur si choix entre n'est pas valide.
     * @return Retourne le caractere saisie et valide.
     */
    public static String validerChoix(String choix1, String choix2, String msgErr) {
        String car;
        car = Clavier.lireString();
        while(!car.equals(choix1) && !car.equals(choix2)){
            System.out.print(msgErr);
            car = Clavier.lireString();
        }
        System.out.println();
        return car;
    }

    /**
     * Cette methode affiche un message de fin du programme.
     */
    public static void afficherFinProg() {
        System.out.println("F I N   N O R M A L E   D U   P R O G R A M M E");
    }

    /**
     *Cette methode permet de lire les fichiers a indenter.
     * @return le fichier a indente dans contenu.
     */
    public static String lireFichier() {
        String cheminFichier;
        String contenu;

        System.out.print("Entrez le chemin du fichier de pseudocode : ");
        cheminFichier = Clavier.lireString();
        contenu = TP2Utils.lireFichierTexte(cheminFichier);
        while(contenu == null) {
            System.out.println("ERREUR ! Chemin de fichier invalide. Recommencez...");
            System.out.print("Entrez le chemin du fichier de pseudocode : ");
            cheminFichier = Clavier.lireString();
            contenu = TP2Utils.lireFichierTexte(cheminFichier);
        }
        return contenu;
    }

    /**
     * Cette methode contient les mots reserves en minuscules.
     * @param mot
     * @return les mots reserves en minuscules.
     */
    public static boolean estMotReserve (String mot) {
        mot = mot.toLowerCase();
        return (mot.equals("debut") || mot.equals("fin") || mot.equals("tant") || mot.equals("que")
                || mot.equals("si") || mot.equals("alors") || mot.equals("sinon") || mot.equals("faire")
                || mot.equals("afficher") || mot.equals("ecrire") || mot.equals("lire") || mot.equals("saisir"));
    }

    /**
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *

     Cette methode retourne une nouvelle chaine de caracteres qui represente le pseudocode donne en parametre, auquel on a enleve tous les caracteres blancs au debut de chaque ligne, et tous les caracteres blancs superflus a la fin de chaque ligne (en conservant cependant le caractere '\n' qui marque la fin d'une ligne).
     @param pseudocode le pseudocode a traiter.
     @return une nouvelle chaine representant le pseudocode donne en parametre,
     */

    public static String trimer (String pseudocode) {
        String sTrim = "";
        String sTmp;
        int debut = 0;
        int fin;

        pseudocode = pseudocode.trim() + "\n";
        fin = pseudocode.indexOf("\n", debut);
        while (fin != -1) {
            sTmp = pseudocode.substring(debut, fin).trim();
            sTrim = sTrim + sTmp + "\n";
            debut = fin + 1;
            fin = pseudocode.indexOf("\n", debut);
        }
        return sTrim;
    }

    /**
     * Cette methode contient le choix menu pour mettre les mots en minuscule ou majuscule et retourne le contenu au choix menu approprie
     * @param contenu
     * @return le choix de menu pour transformer les mots en minuscules ou en majuscules a algorithmeMinMaj
     */
    public static String appliquerMinMaj(String contenu) {
        String choixMinMaj;

        System.out.print("Mots reserves : en (m)inuscules ou en (M)ajuscules :");
        choixMinMaj = validerChoix("m", "M", "ERREUR ! Entrez m ou M. Recommencez...\n" +
                "Mots reserves : en (m)inuscules ou en (M)ajuscules :");

        contenu = algorithmeMinMaj(contenu, choixMinMaj);
        return contenu;
    }

    /**
     * Cette methode transforme les mots reserve en minuscule ou en majuscule.
     * @param contenu
     * @param choixMinMaj
     * @return le contenu apres avoir transformer les mots reserves en minuscules ou en majuscules
     */
    public static String algorithmeMinMaj(String contenu, String choixMinMaj) {
        String contenuApresMinMaj = "";
        String motTmp;
        int debut = 0;
        int fin;
        String caractereBlanc;
        boolean isString = false;
        boolean isCommentaire = false;

        //fin = 5 ;
        fin = Math.min(contenu.indexOf(" ", debut), contenu.indexOf("\n", debut)) ;
        while (fin != -1) {
            //motTmp = "Debut"
            motTmp = contenu.substring(debut, fin);

            if (motTmp.length()>0 && motTmp.substring(0,1).equals("\"")) isString = true; //debut d'une chaine de caractere
            if (motTmp.length()>0 && motTmp.substring(motTmp.length()-1).equals("\"")) isString = false; //fin d'une chaine de caractere
            if (motTmp.length()>1 && motTmp.substring(0,2).equals("//")) isCommentaire = true; //debut d'un commentaire

            if(estMotReserve(motTmp) && !isString && !isCommentaire) {
                if(choixMinMaj.equals("m")) {
                    motTmp = motTmp.toLowerCase();
                } else {
                    motTmp = motTmp.toUpperCase();
                }
            }
            if (motTmp.contains("Selection2"))
                System.out.println("here");
            caractereBlanc = contenu.substring(fin,fin+1);
            if (caractereBlanc.equals("\n")) isCommentaire = false; //fin d'un commentaire

            contenuApresMinMaj += motTmp + caractereBlanc;
            debut = fin + 1; // passer au prochain mot
            fin = trouverFinMot(contenu, debut);
        }
        return contenuApresMinMaj;
    }

    /**
     * Cette methode trouve les fins de mots ayant les caracteres " ", "\t", "\n"
     * @param contenu
     * @param debut
     * @return les mots finissant par les caracteres blancs suivants " ", "\t", "\n".
     */
    public static int trouverFinMot(String contenu, int debut) {

        int finMot = Math.min(contenu.indexOf(" ", debut), Math.min(contenu.indexOf("\t", debut),contenu.indexOf("\n", debut)));

        if (finMot < 0){
            if(contenu.indexOf(" ", debut)<0 && contenu.indexOf("\t", debut)>0){
                finMot = Math.min(contenu.indexOf("\t", debut),contenu.indexOf("\n", debut));
            } else if (contenu.indexOf(" ", debut)>0 && contenu.indexOf("\t", debut)<0){
                finMot = Math.min(contenu.indexOf(" ", debut),contenu.indexOf("\n", debut));
            } else {
                finMot = contenu.indexOf("\n", debut);
            }
        }

        return finMot;
    }

    /**
     * Cette methode indente le pseudocode passe comme contenu
     * @param contenu
     * @return le pseudocode indenter "pseudocodeIndenter"
     */
    public static String indenterPseudocode(String contenu) {
        int niveauIndentation = 0;
        String pseudocodeIndenter = "";
        String debutStructureControle = "";
        String ligneTmp;
        String motTmp;
        int debut = 0;
        int finLigne;
        int finMot;

        finLigne = contenu.indexOf("\n", debut);
        while (finLigne != -1) {
            ligneTmp = contenu.substring(debut, finLigne);

            if(estCommentaire(ligneTmp)){
                pseudocodeIndenter += indentation(niveauIndentation) + ligneTmp + "\n"; //On ignore les commentaires
            } else{
                finMot = trouverFinMot(contenu, debut);

                motTmp = contenu.substring(debut, finMot); //premier mot de la ligne

                if(estDebutStructureControle(motTmp, debutStructureControle)){
                    debutStructureControle = motTmp;
                    pseudocodeIndenter += indentation(niveauIndentation) + ligneTmp + "\n";
                    niveauIndentation++;
                } else if(estMilieuStructureControle(motTmp, debutStructureControle)){
                    pseudocodeIndenter += indentation(niveauIndentation-1) + ligneTmp + "\n";
                } else if(estFinStructureControle(motTmp, debutStructureControle)){
                    niveauIndentation--;
                    pseudocodeIndenter += indentation(niveauIndentation) + ligneTmp + "\n";
                } else {
                    pseudocodeIndenter += indentation(niveauIndentation) + ligneTmp + "\n";
                }
            }

            debut = finLigne + 1;
            finLigne = contenu.indexOf("\n", debut);
        }

        return pseudocodeIndenter;

    }

    /**
     * Cette methode permet de verifier si une ligne est un commentaire
     * @param ligneTmp
     * @return les lignes qui sont des commentaires.
     */
    public static boolean estCommentaire(String ligneTmp) {
        return ligneTmp.length() > 2 && ligneTmp.substring(0, 2).equals("//");
    }


    /**
     * Cette permet de voir les niveau d'indentation
     * @param niveauIndentation
     * @return les espaces équivalents au niveau d’indentation en paramètre
     */
    public static String indentation(int niveauIndentation) {
        String espaces = "";
        for (int i=0; i<niveauIndentation; i++) espaces += INDENTATION;
        return espaces;
    }

    /**
     * Cette methode transforme les mots du debut de la structure de controle en minuscule.
     * @param motTmp
     * @param debutStructureControle
     * @return les mots du debut de la structure de controle en minuscule.
     */
    public static boolean estDebutStructureControle(String motTmp, String debutStructureControle) {
        motTmp = motTmp.toLowerCase().trim();
        debutStructureControle = debutStructureControle.toLowerCase().trim();
        return (motTmp.equals("debut") || (motTmp.equals("tant") && !debutStructureControle.equals("faire")) || motTmp.equals("si") || motTmp.equals("faire"));

    }

    /**
     * Cette methode transforme les mots du milieu de la structure de controle en minuscule.
     * @param motTmp
     * @param debutStructureControle
     * @return les mots du milieu de la structure de controle en minuscule.
     */
    public static boolean estMilieuStructureControle(String motTmp, String debutStructureControle) {
        motTmp = motTmp.toLowerCase().trim();
        debutStructureControle = debutStructureControle.toLowerCase().trim();
        return (motTmp.equals("sinon") && (debutStructureControle.equals("si") || debutStructureControle.equals("sinon")));
    }

    /**
     * Cette methode transforme les mots de la fin de la structure de controle en minuscule.
     * @param motTmp
     * @param debutStructureControle
     * @return les mots de la fin de la structure de controle en minuscule.
     */
    public static boolean estFinStructureControle(String motTmp, String debutStructureControle) {
        motTmp = motTmp.toLowerCase().trim();
        debutStructureControle = debutStructureControle.toLowerCase().trim();
        return (motTmp.equals("fin") || (motTmp.equals("tant") && debutStructureControle.equals("faire")));
    }

    public static void main(String[] args) {
        String choix;
        String contenu;

        System.out.println(PRESENTATION);

        do {
            afficherMenu();
            choix = validerChoix("1" ,"2", "ERREUR ! Choix invalide. Recommencez..."); //Demande a l'utilisateur de valider un choix entre 1 et 2.

            if (choix.equals("1")) {
                contenu = lireFichier(); //Recupere le fichier a indenter.
                contenu = trimer(contenu); //Retourne une nouvelle chaine de caracteres en enlevant tous les caracteres blancs.
                contenu = appliquerMinMaj(contenu); //Transforme les mots en minuscules ou en majuscules.
                contenu = indenterPseudocode(contenu);
                System.out.println("-------------");
                System.out.println(contenu);
                System.out.println("-------------");
            }

        } while (!choix.equals("2"));
        afficherFinProg();
    }
}
