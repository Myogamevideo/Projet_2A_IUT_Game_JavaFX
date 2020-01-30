package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The type Save.
 */
public class SaveJSON implements ISave{

    /**
     * Write json classement.
     *
     * @param nomfic     the nomfic
     * @param classement the classement
     */
    @Override
    public void save(String nomfic, Classement classement) {
        JSONArray joueurList = new JSONArray();
        for (Joueur j : classement.getJoueurs()) {
            JSONObject joueurDetails = new JSONObject();
            joueurDetails.put("pseudo", j.getPseudo());
            joueurDetails.put("vague", j.getVague());
            joueurDetails.put("nbtour", j.getNbtour());
            joueurDetails.put("tempsJ", j.getTempsJ());
            JSONObject joueurObject = new JSONObject();
            joueurObject.put("Joueur", joueurDetails);
            joueurList.add(joueurObject);
        }
        try (FileWriter file = new FileWriter(nomfic)) {

            file.write(joueurList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
