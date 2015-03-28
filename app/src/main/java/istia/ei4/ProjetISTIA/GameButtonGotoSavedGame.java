package istia.ei4.ProjetISTIA;
import android.app.Activity;

/**
 * Created by Alain on 28/03/2015.
 */
public class GameButtonGotoSavedGame extends GameButtonGoto {

    private String mapPath = null;

    public GameButtonGotoSavedGame(int x, int y, int w, int h, int imageUp, int imageDown, int target, String filePath) {
        super(x, y, w, h, imageUp, imageDown, target);
        mapPath = filePath;
    }



    @Override
    public void onClick(GameManager gameManager) {
        super.onClick(gameManager);

        System.out.println("Click on GameButtonGotoSavedGame");

        ((GridGameScreen) (gameManager.getScreens().get(4))).setGame(mapPath);
        System.out.println("Click on GameButtonGotoSavedGame 2");


    }
}