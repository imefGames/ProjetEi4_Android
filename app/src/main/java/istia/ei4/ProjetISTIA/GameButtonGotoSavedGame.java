package istia.ei4.ProjetISTIA;

import java.util.ArrayList;

/**
 * Created by Alain on 28/03/2015.
 */
public class GameButtonGotoSavedGame extends GameButtonGoto {

    private String mapPath = null;

    public GameButtonGotoSavedGame(float x, float y, float w, float h, int imageUp, int imageDown, int target, String filePath) {
        super((int)x, (int)y, (int)w, (int)h, imageUp, imageDown, target);
        mapPath = filePath;

    }



    @Override
    public void onClick(GameManager gameManager) {

        if(gameManager.getPreviousScreenKey() == 4)
        {
            ArrayList gridElements = ((GridGameScreen)gameManager.getScreens().get(gameManager.getPreviousScreenKey())).getGridElements();

            FileReadWrite.writePrivateData((gameManager.getActivity()), mapPath, MapObjects.createStringFromList(gridElements));

            addMapsSaved(gameManager);

        }
        else
        {
            SaveManager saver = new SaveManager(gameManager.getActivity());

            if(saver.getMapsStateSaved(mapPath, "mapsSaved.txt"))
            {
                super.onClick(gameManager);

                System.out.println("Click accepted");

                ((GridGameScreen)(gameManager.getScreens().get(4))).setSavedGame(mapPath);
            }
        }

    }

    public void addMapsSaved(GameManager gameManager)
    {
        if(mapPath.length() > 0)
        {
            SaveManager saver = new SaveManager(gameManager.getActivity());

            if(!saver.getMapsStateSaved(mapPath, "mapsSaved.txt"))
            {
                FileReadWrite.writePrivateData(gameManager.getActivity(), "mapsSaved.txt", mapPath+"\n");
            }
        }
    }

}