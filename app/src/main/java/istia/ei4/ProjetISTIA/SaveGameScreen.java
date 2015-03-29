package istia.ei4.ProjetISTIA;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Alain on 29/03/2015.
 */
public class SaveGameScreen extends GameScreen{

    private ArrayList gridElements;

    public SaveGameScreen(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public void create() {
        int ws2 = this.gameManager.getScreenWidth()/2;
        int hs2 = this.gameManager.getScreenHeight()/2;
        this.instances.add(new GameButtonGotoRandomGame(ws2 - 128, hs2 - 512, 256, 256, R.drawable.bt_start_up_random, R.drawable.bt_start_down_random, 4));
        this.instances.add(new GameButtonGoto(ws2-128, hs2-128, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 5));
    }



    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.GREEN);
        renderManager.paintScreen();
/*
        renderManager.setColor(Color.BLACK);
        int textS = yGrid/2-50;
        renderManager.setTextSize(textS);
        renderManager.drawText(10, textS, "Nombre de coups: " + nbCoups);*/

        super.draw(renderManager);
    }

    @Override
    public void update(GameManager gameManager) {
        super.update(gameManager);
        if(gameManager.getInputManager().backOccurred()){
            gameManager.setGameScreen(gameManager.getPreviousScreenKey());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

