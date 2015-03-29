package istia.ei4.ProjetISTIA;
        import android.app.Activity;

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
        super.onClick(gameManager);

        ((GridGameScreen) (gameManager.getScreens().get(4))).setGame(mapPath);

    }
}