package istia.ei4.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 04/02/2015.
 */
public class CreditsGameScreen extends GameScreen {


    public CreditsGameScreen(GameManager gameManager){
        super(gameManager);
    }

    int hs2;

    @Override
    public void create() {
        int ws2 = this.gameManager.getScreenWidth()/2;
        hs2 = this.gameManager.getScreenHeight()/2;
        this.instances.add(new GameButtonGoto(7*ws2/4-128, 9*hs2/5-128, 128, 128, R.drawable.bt_back_up, R.drawable.bt_back_down, 0));
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        //renderManager.setColor(Color.BLUE);
        renderManager.setColor(Color.parseColor("#B0CC99"));
        renderManager.paintScreen();

        renderManager.setColor(Color.BLACK);
        int ts = hs2/10;
        renderManager.setTextSize((int)(2.0*ts));
        renderManager.drawText(10, 2*ts, "Application");
        renderManager.drawText(10, 4*ts, "créée par:");
        renderManager.setTextSize(ts);
        renderManager.drawText(10, 6*ts, "Alain CAILLAUD");
        renderManager.drawText(10, 7*ts, "Pierre MICHEL");
        renderManager.drawText(10, 8*ts, "Dans le cadre d'un projet");
        renderManager.drawText(10, 9*ts, "étudiant à l'ISTIA");
        renderManager.setTextSize((int)(1.5*ts));
        renderManager.drawText(10, 12*ts, "Basé sur le");
        renderManager.drawText(10, (int)(13.5*ts), "jeu de société:");
        renderManager.setTextSize(ts);
        renderManager.drawText(10, 15*ts, "Ricochet Robots(r)");

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
