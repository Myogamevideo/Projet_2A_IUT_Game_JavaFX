package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * The type Load.
 */
public class LoadJSON {

    /**
     * Read json classement.
     *
     * @param nomfic the nomfic
     * @return the classement
     */
    public static Classement load(String nomfic) {
        Classement classement = new Classement();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(nomfic)) {
            Object obj = jsonParser.parse(reader);
            JSONArray joueurList = (JSONArray) obj;
            joueurList.forEach(joueur -> parseClassementObject((JSONObject) joueur, classement));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return classement;
    }

    private static void parseClassementObject(JSONObject joueurObject, Classement classement) {
        JSONObject joueur = (JSONObject) joueurObject.get("Joueur");
        String pseudo = (String) joueur.get("pseudo");
        long vague1 = (long) joueur.get("vague");
        int vague = (int) vague1;
        long nbtour1 = (long) joueur.get("nbtour");
        int nbtour = (int) nbtour1;
        long tempsJ1 = (long) joueur.get("tempsJ");
        int tempsJ = (int) tempsJ1;
        Joueur j = new Joueur(pseudo, vague, nbtour,tempsJ);
        classement.addJoueur(j);
    }
}
