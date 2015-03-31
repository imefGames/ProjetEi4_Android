package istia.ei4.ProjetISTIA;

import android.app.Activity;

/**
 * Created by Alain on 28/03/2015.
 */
public class SaveManager {

    Activity act = null;
//    String saveFileManager = "mapsPlayed.txt";
    public SaveManager(Activity activity){
        act = activity;
    }

    public boolean getMapsStateLevel(String mapPath, String fileName)
    {
        return getMapsState(mapPath, fileName, "Maps/");
    }

    public boolean getMapsStateSaved(String mapPath, String fileName)
    {
        return getMapsState(mapPath, fileName, "");
    }

    public boolean getMapsState(String mapPath, String fileName, String folder)
    {
        String playedMaps = FileReadWrite.readPrivateData(act, fileName);

        if(playedMaps.equals(""))
            return false;

        String[] maps = playedMaps.split("[\\r\\n]+");

        for(String map : maps)
        {
            if(mapPath.equals(folder+map))
            {
                return true;
            }
        }
        return false;
    }

    public int getButtonLevels(String mapPath, Boolean up)
    {
        if (getMapsStateLevel(mapPath, "mapsPlayed.txt"))
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

    public int getButtonSaved(String mapPath, Boolean up)
    {
        if (getMapsStateSaved(mapPath, "mapsSaved.txt"))
        {
            if(up)
                return R.drawable.bt_start_up_saved_used;
            else
                return R.drawable.bt_start_down_saved_used;
        }
        else
        {
            if(up)
                return R.drawable.bt_start_up_saved;
            else
                return R.drawable.bt_start_down_saved;
        }
    }
}
