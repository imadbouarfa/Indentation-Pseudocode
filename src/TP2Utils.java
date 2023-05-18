import java.io.*;

/**
 * Classe contenant une methode de lecture de fichier texte, a utiliser pour le TP2.
 * INF1120 A22
 * @author Melanie Lord
 * @version Automne 2022
 */
public class TP2Utils {
   
   /**
    * Lit le fichier texte donne par cheminFic, et retourne son contenu sous 
    * la forme d'une chaine de caracteres. Si le fichier donne par cheminFic
    * n'existe pas ou ne peut pas etre lu, la methode retourne null.
    * 
    * @param cheminFic le chemin du fichier texte a lire.
    * @return le contenu du fichier texte donne par cheminFic ou null si ce 
    *         fichier ne peut pas etre lu.
    */
   public static String lireFichierTexte (String cheminFic) {
      File fic = new File(cheminFic);
      String texte = null;
      BufferedReader in;
      
      if (fic.exists() && fic.isFile() && fic.canRead()) {
         try {
            texte = "";
            in = new BufferedReader(new FileReader(fic));
         
            while (in.ready()) {
               texte += in.readLine() + "\n";
            }
         } catch (IOException e) {
            System.err.println("ERREUR INATTENDUE, NE DEVRAIT PAS SE PRODUIRE");
         }         
      }

      return texte;
   }
     
}
