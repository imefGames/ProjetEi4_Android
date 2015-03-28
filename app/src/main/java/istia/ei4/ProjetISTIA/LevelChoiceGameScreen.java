package istia.ei4.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 21/01/2015.
 */
public class LevelChoiceGameScreen extends GameScreen {


    public LevelChoiceGameScreen(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public void create() {
        int ws2 = this.gameManager.getScreenWidth()/2;
        int hs2 = this.gameManager.getScreenHeight()/2;

        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-900, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_0.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-900, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_2.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-900, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_1.txt"));

        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-600, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_3.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-600, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_4.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-600, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_5.txt"));

        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-300, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_6.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-300, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_7.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-300, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_8.txt"));

        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_9.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_10.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_11.txt"));

        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2+300, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_12.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2+300, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_13.txt"));
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2+300, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 4, "generatedMap_14.txt"));

    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.GRAY);
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