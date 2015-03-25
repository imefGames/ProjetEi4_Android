package istia.ei4.ProjetISTIA;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;

/**
 * Created by Alain on 21/01/2015.
 */
public class FileReadWrite {
    public FileReadWrite(){
    }

    /*
     * Retourne une chaine de caractère contenant tout le fichier.
     * @param chemin d'accès au fichier
     * @return contenu du fichier
     */
    public String read(String fileLocation)
    {
        String txtData = null;
        String aBuffer = "";

        try {
            File myFile = new File(fileLocation);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            String aDataRow = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }

            myReader.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;

        }
        return aBuffer;
    }

    /*
     * Ecrit toute la chaine de caractère dans le fichier souhaité
     * @param fileLocation : chemin d'accès au fichier
     * @return contenu du fichier
     */
    public void write(String fileLocation, String content)
    {
        try {
            File myFile = new File(fileLocation);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
            myOutWriter.append(content);
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
