package istia.ei4.ProjetISTIA;

import android.graphics.Color;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Pierre on 25/02/2015.
 */
public class ChargeGameScreen extends GameScreen {

    private ArrayList<File> fileList;
    private int selectedFile = 0;
    private int sWidth;
    private int sHeight;
    private GameMouvementInterface gmi;

    public ChargeGameScreen(GameManager gameManager){
        super(gameManager);
        this.sWidth = gameManager.getScreenWidth();
        this.sHeight = gameManager.getScreenHeight();
    }

    @Override
    public void create(){
        this.instances.add(new GamePiece(0, 0, Color.RED));
        gmi = new GameMouvementInterface();
        this.instances.add(gmi);
    }

    @Override
    public void load(RenderManager renderManager){
        super.load(renderManager);
        loadFiles();
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.WHITE);
        renderManager.paintScreen();
        /*for(int i=0; i<this.fileList.size(); i++){
            if(i+1==this.selectedFile){//this.fileList.get(i).exists()
                renderManager.setColor(Color.DKGRAY);
            }else{
                renderManager.setColor(Color.BLACK);
            }
            renderManager.drawRect(50, 200+i*250, sWidth-50, 400+i*250);
        }*/
        super.draw(renderManager);
    }

    public void update(GameManager gameManager){
        super.update(gameManager);
        InputManager inputManager = gameManager.getInputManager();
        if(inputManager.upOccurred()) {
            int tx, ty;
            tx = (int)inputManager.getTouchX();
            ty = (int)inputManager.getTouchY();
            selectedFile = 0;
            for (int i = 0; i < this.fileList.size(); i++) {
                if(tx>=50 && ty>200 + i * 250 && tx<sWidth-50 && ty<400 + i * 250){
                    selectedFile = i+1;
                    break;
                }
            }
        }
    }

    private void loadFiles(){
        //charger la liste des fichiers
        this.fileList = new ArrayList<File>();
        File levelsDirectory = new File("/sdcard/levels/");
        levelsDirectory.mkdirs();
        for (int i=1; i<=3; i++) {
            File file = new File("/sdcard/levels/save" + i);
            fileList.add(file);
        }
    }
}
