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

        createButtons();
    }

    public void createButtons()
    {

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

        String mapPath = "";
        SaveManager saver = new SaveManager(gameManager.getActivity());

        float ratioW = ((float)gameManager.getScreenWidth()) / ((float)1080);
        float ratioH = ((float)gameManager.getScreenHeight()) / ((float)1920);

        System.out.println("SaveGameScreen");

        mapPath = getMapPath(0);
        this.instances.add(new GameButtonGotoSavedGame(78*ratioW, 45*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(1);
        this.instances.add(new GameButtonGotoSavedGame(412*ratioW, 45*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(2);
        this.instances.add(new GameButtonGotoSavedGame(746*ratioW, 45*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));

        mapPath = getMapPath(3);
        this.instances.add(new GameButtonGotoSavedGame(78*ratioW, 356*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(4);
        this.instances.add(new GameButtonGotoSavedGame(412*ratioW, 356*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(5);
        this.instances.add(new GameButtonGotoSavedGame(746*ratioW, 356*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));

        mapPath = getMapPath(6);
        this.instances.add(new GameButtonGotoSavedGame(78*ratioW, 667*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(7);
        this.instances.add(new GameButtonGotoSavedGame(412*ratioW, 667*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(8);
        this.instances.add(new GameButtonGotoSavedGame(746*ratioW, 667*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));

        mapPath = getMapPath(9);
        this.instances.add(new GameButtonGotoSavedGame(78*ratioW, 978*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(10);
        this.instances.add(new GameButtonGotoSavedGame(412*ratioW, 978*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(11);
        this.instances.add(new GameButtonGotoSavedGame(746*ratioW, 978*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));

        mapPath = getMapPath(12);
        this.instances.add(new GameButtonGotoSavedGame(78*ratioW, 1289*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(13);
        this.instances.add(new GameButtonGotoSavedGame(412*ratioW, 1289*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));
        mapPath = getMapPath(14);
        this.instances.add(new GameButtonGotoSavedGame(746*ratioW, 1289*ratioH, 256*ratioH, 256*ratioW, saver.getButtonSaved(mapPath, true), saver.getButtonSaved(mapPath, false), 4, mapPath));

        int y = 1080/5+gameManager.getScreenWidth();
        int dy = gameManager.getScreenHeight()-y;
        int w = 8*gameManager.getScreenWidth()/20;
        int h = 8*dy/20;
        int y1 = y+dy/20;
        int y2 = y+11*dy/20;
        int x1 = gameManager.getScreenWidth()/20;
        int x2 = 11*gameManager.getScreenWidth()/20;

        this.instances.add(new GameButtonGotoBack((int)(54*ratioW), (int)(1600*ratioH), (int)(432*ratioH), (int)(250*ratioW), R.drawable.bt_page_gauche_up, R.drawable.bt_page_gauche_down));
    }

    private String getMapPath(int levelInScreen)
    {
        return "map_"+levelInScreen+".txt";
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.YELLOW);
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

