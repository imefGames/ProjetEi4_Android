package istia.ei4.ProjetISTIA;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Pierre on 21/01/2015.
 */
public class LevelChoiceGameScreen extends GameScreen {

    private int test = 0;
    private int firstLevel = 0;
    private int leftScreen = -1;
    private int rightScreen = -1;

    /*
     * Game screen de choix de niveaux
     * @param firstLevel : numero de la première carte, 0 -> generatedMap_0.txt
     * @param leftScreen : reference du gameScreen de choix de niveau précédent (-1 s'il n'y en a pas)
     * @param rightScreen : reference du gameScreen de choix de niveau suivant (-1 s'il n'y en a pas)
     */
    public LevelChoiceGameScreen(GameManager gameManager, int firstLev, int lScreen, int rScreen){

        super(gameManager);
        this.firstLevel = firstLev;
        this.leftScreen = lScreen;
        this.rightScreen = rScreen;

        createButtons();
    }



    public GameButtonGotoLevelGame  testButton= null;

    @Override
    public void create() {


        //Il faut charger ces deux images dès le début de l'application,
        //Ces deux lignes sont necessaires
        gameManager.getRenderManager().loadImage(R.drawable.bt_start_up_played);
        gameManager.getRenderManager().loadImage(R.drawable.bt_start_down_played);

        gameManager.getRenderManager().loadImage(R.drawable.bt_page_droite_down);
        gameManager.getRenderManager().loadImage(R.drawable.bt_page_droite_up);
        gameManager.getRenderManager().loadImage(R.drawable.bt_page_gauche_down);
        gameManager.getRenderManager().loadImage(R.drawable.bt_page_gauche_up);

        createButtons();

    }

    public void createButtons()
    {
        int ws2 = this.gameManager.getScreenWidth()/2;
        int hs2 = this.gameManager.getScreenHeight()/2;

        ArrayList<GameButtonGotoLevelGame> aRetirer = new ArrayList<>();
        for(Object currentObject : this.instances)
        {
            if(currentObject.getClass() == GameButtonGotoLevelGame.class)
            {
                aRetirer.add((GameButtonGotoLevelGame)currentObject);
            }
        }
        for(GameButtonGotoLevelGame p : aRetirer)
        {
            this.instances.remove(p);
        }

        String mapPath = "";
        SaveManager saver = new SaveManager(gameManager.getActivity());

        float ratioW = ((float)gameManager.getScreenWidth()) /((float)1080);
        float ratioH = ((float)gameManager.getScreenHeight()) /((float)1920);

        mapPath = getMapPath(0);
        this.instances.add(new GameButtonGotoLevelGame(78*ratioW, 45*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(1);
        this.instances.add(new GameButtonGotoLevelGame(412*ratioW, 45*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(2);
        this.instances.add(new GameButtonGotoLevelGame(746*ratioW, 45*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));

        mapPath = getMapPath(3);
        this.instances.add(new GameButtonGotoLevelGame(78*ratioW, 356*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(4);
        this.instances.add(new GameButtonGotoLevelGame(412*ratioW, 356*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(5);
        this.instances.add(new GameButtonGotoLevelGame(746*ratioW, 356*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));

        mapPath = getMapPath(6);
        this.instances.add(new GameButtonGotoLevelGame(78*ratioW, 667*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(7);
        this.instances.add(new GameButtonGotoLevelGame(412*ratioW, 667*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(8);
        this.instances.add(new GameButtonGotoLevelGame(746*ratioW, 667*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));

        mapPath = getMapPath(9);
        this.instances.add(new GameButtonGotoLevelGame(78*ratioW, 978*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(10);
        this.instances.add(new GameButtonGotoLevelGame(412*ratioW, 978*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(11);
        this.instances.add(new GameButtonGotoLevelGame(746*ratioW, 978*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));

        mapPath = getMapPath(12);
        this.instances.add(new GameButtonGotoLevelGame(78*ratioW, 1289*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(13);
        this.instances.add(new GameButtonGotoLevelGame(412*ratioW, 1289*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));
        mapPath = getMapPath(14);
        this.instances.add(new GameButtonGotoLevelGame(746*ratioW, 1289*ratioH, 256*ratioH, 256*ratioW, saver.getButtonLevels(mapPath, true), saver.getButtonLevels(mapPath, false), 4, mapPath));


        int y = 1080/5+gameManager.getScreenWidth();
        int dy = gameManager.getScreenHeight()-y;
        int w = 8*gameManager.getScreenWidth()/20;
        int h = 8*dy/20;
        int y1 = y+dy/20;
        int y2 = y+11*dy/20;
        int x1 = gameManager.getScreenWidth()/20;
        int x2 = 11*gameManager.getScreenWidth()/20;

        if(this.leftScreen > 0)
            this.instances.add(new GameButtonGoto((int)(54*ratioW), (int)(1600*ratioH), (int)(432*ratioH), (int)(250*ratioW), R.drawable.bt_page_gauche_up, R.drawable.bt_page_gauche_down, this.leftScreen));

        if(this.rightScreen > 0)
            this.instances.add(new GameButtonGoto((int)(594*ratioW), (int)(1600*ratioH), (int)(432*ratioH), (int)(250*ratioW), R.drawable.bt_page_droite_up, R.drawable.bt_page_droite_down, this.rightScreen));

    }

    private String getMapPath(int levelInScreen)
    {
        levelInScreen+=firstLevel;
        return "Maps/generatedMap_"+levelInScreen+".txt";
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