package istia.ei4.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 04/02/2015.
 */
public class SettingsGameScreen extends GameScreen {

    private GameButtonGeneral buttonBFS = null;
    private GameButtonGeneral buttonIDDFS = null;
    private int hs2;
    private int ws2;
    private boolean solverBFS = false;


    public SettingsGameScreen(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public void create() {
        gameManager.getRenderManager().loadImage(R.drawable.bt_bfs_up);
        gameManager.getRenderManager().loadImage(R.drawable.bt_bfs_down);
        gameManager.getRenderManager().loadImage(R.drawable.bt_iddfs_down);
        gameManager.getRenderManager().loadImage(R.drawable.bt_bfs_down);
        ws2 = this.gameManager.getScreenWidth()/2;
        hs2 = this.gameManager.getScreenHeight()/2;

        int width = this.gameManager.getScreenWidth();
        int height = this.gameManager.getScreenHeight();

        float ratioW = ((float)gameManager.getScreenWidth()) /((float)1080);
        float ratioH = ((float)gameManager.getScreenHeight()) /((float)1920);

        buttonBFS = new GameButtonGeneral((int)(270*ratioW), (int)((400)*ratioH),(int) (270*2*ratioW),(int) (128*ratioH), R.drawable.bt_bfs_up, R.drawable.bt_bfs_down, new setBFS());
        buttonIDDFS = new GameButtonGeneral((int)(270*ratioW), (int)((600)*ratioH),(int) (270*2*ratioW),(int) (128*ratioH), R.drawable.bt_iddfs_up, R.drawable.bt_iddfs_down, new setIDDFS());

        buttonBFS.setImageDisabled(R.drawable.bt_bfs_down);
        buttonIDDFS.setImageDisabled(R.drawable.bt_iddfs_down);

        this.instances.add(buttonBFS);
        this.instances.add(buttonIDDFS);
        this.instances.add(new GameButtonGoto(ws2-128, hs2-128, 256, 256, R.drawable.bt_back_up, R.drawable.bt_back_down, 0));
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.YELLOW);
        renderManager.setColor(Color.parseColor("#FFFDAE"));
        renderManager.paintScreen();

        int ts = hs2/10;

        renderManager.setColor(Color.BLACK);
        renderManager.setTextSize(ts);
        renderManager.drawText(10, 1*ts, "Choix du solveur :");
        if(solverBFS)
            renderManager.drawText(10, 3*ts, "BFS");
        else
            renderManager.drawText(10, 3*ts, "IDDFS");
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

    private class setBFS implements IExecutor{
        public void execute() {
            GridGameScreen.setSolverBFS(true);
            solverBFS = true;

            //buttonBFS.setImageUp(R.drawable.bt_bfs_down);
            //buttonIDDFS.setImageUp(R.drawable.bt_iddfs_up);
        }
    }

    private class setIDDFS implements IExecutor{
        public void execute() {
            GridGameScreen.setSolverBFS(false);
            solverBFS = false;

            //buttonBFS.setImageUp(R.drawable.bt_bfs_up);
            //buttonIDDFS.setImageUp(R.drawable.bt_iddfs_up);
        }
    }
}
