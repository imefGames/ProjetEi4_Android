package istia.ei4.ProjetISTIA;

/**
 * Created by Pierre on 21/01/2015.
 */
public class GameButtonGoto extends GameButton {
    private int targetScreen;

    public GameButtonGoto(int x, int y, int w, int h, int imageUp, int imageDown, int target){
        super(x, y, w, h, imageUp, imageDown);
        this.targetScreen = target;
    }

    @Override
    public void onClick(GameManager gameManager) {

        gameManager.setGameScreen(this.targetScreen);
    }
}
