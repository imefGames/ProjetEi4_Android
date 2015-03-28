package istia.ei4.ProjetISTIA;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Pierre on 21/01/2015.
 */
public class LevelChoiceGameScreen extends GameScreen {


    public LevelChoiceGameScreen(GameManager gameManager){
        super(gameManager);
    }

    private int test = 0;
    @Override
    public void create() {



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

        String mapPath = "";
        SaveManager saver  =new SaveManager(gameManager.getActivity());

        mapPath = "Maps/generatedMap_0.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-900, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_1.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-900, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_2.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-900, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));

        mapPath = "Maps/generatedMap_3.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-600, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_4.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-600, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_5.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-600, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));

        mapPath = "Maps/generatedMap_6.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2-300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_7.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2-300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_8.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2-300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));

        mapPath = "Maps/generatedMap_9.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_10.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_11.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));

        mapPath = "Maps/generatedMap_12.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-512+30, hs2+300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_13.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2-128, hs2+300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));
        mapPath = "Maps/generatedMap_14.txt";
        this.instances.add(new GameButtonGotoSavedGame(ws2+256-30, hs2+300, 256, 256, saver.getButton(mapPath, true), saver.getButton(mapPath, false), 4, mapPath));



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