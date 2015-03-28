package istia.ei4.ProjetISTIA;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by Alain on 21/01/2015.
 */
public class FileReadWrite {
    public FileReadWrite(){

    }

    //private static String file = "mydata";


    public static String readAssets(Activity activity, String fileLocation)
    {
        String aBuffer = "";


        try {
            Resources resources;

            resources = activity.getResources();
            InputStream iS = resources.getAssets().open(fileLocation);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(iS));
            String aDataRow = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }

            //System.out.println(aBuffer);
            myReader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exception");
            return null;

        }
        return aBuffer;
    }

    public static String read(String fileLocation)
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
    public static void write(String fileLocation, String content)
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

    /*
 * Ecrit toute la chaine de caractère dans le fichier souhaité
 * Les données crées avec cette méthode sont privées à l'application
 * Elles ne sont donc pas accessible par l'utilisateur et supprimmées en cas de desinstallation de l'application
 * @param fileLocation : chemin d'accès au fichier
 * @return contenu du fichier
 */
    public static void writePrivateData(Activity activity, String fileLocation, String content)
    {
        try {

            FileOutputStream fOut = activity.openFileOutput(fileLocation, Context.MODE_APPEND);

            fOut.write(content.getBytes());
            fOut.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String readPrivateData(Activity activity, String fileLocation)
    {
        String txtData = null;
        String aBuffer = "";

        try {

            FileInputStream fin = activity.openFileInput(fileLocation);
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }

            aBuffer = temp;


        } catch (Exception e) {
            System.err.println(e.toString());
            return null;

        }
        return aBuffer;
    }


}
