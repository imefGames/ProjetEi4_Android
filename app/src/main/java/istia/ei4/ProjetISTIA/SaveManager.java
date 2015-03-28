package istia.ei4.ProjetISTIA;

import android.app.Activity;

/**
 * Created by Alain on 28/03/2015.
 */
public class SaveManager {

    Activity act = null;
    String saveFileManager = "mapsPlayed.txt";
    public SaveManager(Activity activity){
        act = activity;
    }

    public boolean getMapsState(String mapPath)
    {

        //String playedMaps = FileReadWrite.readAssets(act, saveFileManager);
        String playedMaps = FileReadWrite.readPrivateData(act, saveFileManager);

        if(playedMaps == null)
            return false;


        String[] maps = playedMaps.split("[\\r\\n]+");

        for(String map : maps)
        {
            if(mapPath.equals("Maps/"+map))
            {
                return true;
            }
        }
        return false;
    }

    public int getButton(String mapPath, Boolean up)
    {
        if (getMapsState(mapPath))
        {
            if(up)
                return R.drawable.bt_start_up_played;
            else
                return R.drawable.bt_start_down_played;
        }
        else
        {
            if(up)
                return R.drawable.bt_start_up;
            else
                return R.drawable.bt_start_down;
        }


    }

}
