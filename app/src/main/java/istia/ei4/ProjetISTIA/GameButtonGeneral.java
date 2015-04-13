package istia.ei4.ProjetISTIA;

/**
 * Created by Pierre on 21/01/2015.
 */
public class GameButtonGeneral extends GameButton {
    private IExecutor executor;

    public GameButtonGeneral(int x, int y, int w, int h, int imageUp, int imageDown, IExecutor executor){
        super(x, y, w, h, imageUp, imageDown);
        this.executor = executor;
    }

    @Override
    public void onClick(GameManager gameManager) {
            executor.execute();
    }
}
