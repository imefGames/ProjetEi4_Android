package istia.ei4.ProjetISTIA;

/**
 * Created by Alain on 25/02/2015.
 */
public class GameButtonGotoRandomGame extends GameButtonGoto{
    public GameButtonGotoRandomGame(int x, int y, int w, int h, int imageUp, int imageDown, int target)
    {
        super(x,y,w,h,imageUp,imageDown,target);
    }

    @Override
    public void onClick(GameManager gameManager) {
        super.onClick(gameManager);

        ((GridGameScreen)(gameManager.getScreens().get(4))).setRandomGame(true);


    }
}
