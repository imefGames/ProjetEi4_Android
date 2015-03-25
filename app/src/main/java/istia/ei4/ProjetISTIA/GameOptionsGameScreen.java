package istia.ei4.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 21/01/2015.
 */
public class GameOptionsGameScreen extends GameScreen {


    public GameOptionsGameScreen(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public void create() {
        int ws2 = this.gameManager.getScreenWidth()/2;
        int hs2 = this.gameManager.getScreenHeight()/2;
        this.instances.add(new GameButtonGotoRandomGame(ws2 - 128, hs2 - 128, 256, 256, R.drawable.bt_back_up, R.drawable.bt_back_down, 4));
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.GREEN);
        renderManager.paintScreen();
        super.draw(renderManager);
    }

    @Override
    public void update(GameManager gameManager) {
        super.update(gameManager);
        if(gameManager.getInputManager().backOccurred()){
            gameManager.setGameScreen(0);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
