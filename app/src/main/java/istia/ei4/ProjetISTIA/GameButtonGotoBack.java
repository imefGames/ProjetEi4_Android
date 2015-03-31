package istia.ei4.ProjetISTIA;

/**
 * Created by Alain on 31/03/2015.
 */
public class GameButtonGotoBack extends GameButton {

    public GameButtonGotoBack(int x, int y, int w, int h, int imageUp, int imageDown){
        super(x, y, w, h, imageUp, imageDown);
    }

    @Override
    public void onClick(GameManager gameManager) {

        gameManager.setGameScreen(gameManager.getPreviousScreenKey());
    }
}
