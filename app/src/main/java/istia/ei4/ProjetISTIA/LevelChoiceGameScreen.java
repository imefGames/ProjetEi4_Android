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

//        System.out.println("right Screen is :"+this.rightScreen);
//        System.out.println("left Screen is :"+this.leftScreen);

        System.out.println("A");
        createButtons();
    }



    public GameButtonGotoSavedGame  testButton= null;

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

        System.out.println("B");

        createButtons();

    }

    public void createButtons() //Todo : corriger bug boutton qui devient invisible la 1ère fois
    {
        int ws2 = this.gameManager.getScreenWidth()/2;
        int hs2 = this.gameManager.getScreenHeight()/2;

        ArrayList<GameButtonGotoSavedGame> aRetirer = new ArrayList<>();
        for(Object currentObject : this.instances)
        {
            if(currentObject.getClass() == GameButtonGotoSavedGame.class)
            {
                aRetirer.add((GameButtonGotoSavedGame)currentObject);
            }
        }
        for(GameButtonGotoSavedGame p : aRetirer)
        {
            this.instances.remove(p);
        }

        System.out.println("Create buttons");

        String mapPath = "";
        SaveManager saver = new SaveManager(gameManager.getActivity());


        mapPath = getMapPath(0);
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-900, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(1);
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-900, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(2);
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-900, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));

        mapPath = getMapPath(3);
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-600, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(4);
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-600, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(5);
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-600, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));

        mapPath = getMapPath(6);
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(7);
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(8);
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));

        mapPath = getMapPath(9);
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(10);
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(11);
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));

        mapPath = getMapPath(12);
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2+300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(13);
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2+300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = getMapPath(14);
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2+300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));


        int y = 1080/5+gameManager.getScreenWidth();
        int dy = gameManager.getScreenHeight()-y;
        int w = 8*gameManager.getScreenWidth()/20;
        int h = 8*dy/20;
        int y1 = y+dy/20;
        int y2 = y+11*dy/20;
        int x1 = gameManager.getScreenWidth()/20;
        int x2 = 11*gameManager.getScreenWidth()/20;

        System.out.println("rightScreen : "+this.rightScreen);

        if(this.leftScreen > 0)
            this.instances.add(new GameButtonGoto(x1, y2, w, h, R.drawable.bt_page_gauche_up, R.drawable.bt_page_gauche_down, this.leftScreen));

        if(this.rightScreen > 0)
            this.instances.add(new GameButtonGoto(x2, y2, w, h, R.drawable.bt_page_droite_up, R.drawable.bt_page_droite_down, this.rightScreen));

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