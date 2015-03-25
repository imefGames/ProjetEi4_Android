package istia.ei4.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 21/01/2015.
 */
public class MainMenuGameScreen extends GameScreen {

    public MainMenuGameScreen(GameManager gameManager){
        super(gameManager);
    }

    private long prevBack;

    @Override
    public void create() {
        this.prevBack = System.currentTimeMillis();

        int ws2 = this.gameManager.getScreenWidth()/2;

        int hs2 = this.gameManager.getScreenHeight()/2;
        this.instances.add(new GameButtonGoto(ws2-128, hs2-512, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down, 1));
        this.instances.add(new GameButtonGoto(ws2-128, hs2-128, 256, 256, R.drawable.bt_settings_up, R.drawable.bt_settings_down, 2));
        this.instances.add(new GameButtonGoto(ws2 - 128, hs2 + 256, 256, 256, R.drawable.bt_credits_up, R.drawable.bt_credits_down, 3));
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.WHITE);
        renderManager.paintScreen();
        super.draw(renderManager);
    }

    @Override
    public void update(GameManager gameManager) {
        super.update(gameManager);
        if(gameManager.getInputManager().eventHasOccurred() && gameManager.getInputManager().backOccurred()){
            long dt = System.currentTimeMillis() - this.prevBack;
            this.prevBack = System.currentTimeMillis();
            if(dt<2000){
                gameManager.requestEnd();
            }else{
                gameManager.requestToast("Appuyer encore pour quitter.");
            }
        }
    }
}
