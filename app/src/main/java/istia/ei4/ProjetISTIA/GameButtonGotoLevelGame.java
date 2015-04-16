package istia.ei4.ProjetISTIA;

/**
 * Created by Alain on 28/03/2015.
 */
public class GameButtonGotoLevelGame extends GameButtonGoto {

    private String mapPath = null;

    public GameButtonGotoLevelGame(float x, float y, float w, float h, int imageUp, int imageDown, int target, String filePath) {
        super((int)x, (int)y, (int)w, (int)h, imageUp, imageDown, target);
        mapPath = filePath;


    }


    @Override
    public void onClick(GameManager gameManager) {
        super.onClick(gameManager);

        LevelChoiceGameScreen.setLastButtonUsed(this);

        ((GridGameScreen)(gameManager.getScreens().get(4))).setLevelGame(mapPath);


    }
}
