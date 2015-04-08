package istia.ei4.ProjetISTIA;

import android.util.SparseArray;
import android.widget.Toast;

/**
 * Created by Pierre on 04/02/2015.
 */
public class GameManager {
    private GameScreen currentScreen;



    private GameScreen previousScreen;
    private SparseArray<GameScreen> screens;
    private InputManager inputManager;
    private RenderManager renderManager;
    private int sWidth, sHeight;
    private MainActivity activity;

    public MainActivity getActivity() {
        return activity;
    }

    /*
         * Constructeur de la classe GameManager.
         * @param Référence du manager d'entrées (InputManager).
         * @param Référence du manager de l'affichage (RenderManager).
         * @param Largeur de l'écran.
         * @param Hauteur de l'écran.
         */
    public GameManager(InputManager inputManager, RenderManager renderManager, int sWidth, int sHeight, MainActivity activity){
        this.inputManager = inputManager;
        this.renderManager = renderManager;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.screens = new SparseArray<GameScreen>();
        this.activity = activity;
        //list of all screens
        this.screens.append(0, new MainMenuGameScreen(this));
        this.screens.append(1, new GameOptionsGameScreen(this));
        this.screens.append(2, new SettingsGameScreen(this));
        this.screens.append(3, new CreditsGameScreen(this));
        this.screens.append(4, new GridGameScreen(this));
        this.screens.append(5, new LevelChoiceGameScreen(this, 0, -1, 6));
        this.screens.append(6, new LevelChoiceGameScreen(this, 15, 5, 7));
        this.screens.append(7, new LevelChoiceGameScreen(this, 30, 6, 8));
        this.screens.append(8, new LevelChoiceGameScreen(this, 45, 7, -1));
        this.screens.append(9, new SaveGameScreen(this));

        //end of list of all screens
        this.currentScreen = this.screens.get(0);
        this.previousScreen = this.screens.get(0);
    }

    public void requestEnd(){
        this.activity.closeApp();
    }

    public void requestToast(CharSequence str, boolean big){
        this.activity.doToast(str, big);
    }

    public SparseArray<GameScreen> getScreens(){
        return this.screens;
    }

    /*
     * Retourne la largeur de l'écran.
     * @return Largeur de l'écran
     */
    public int getScreenWidth(){
        return this.sWidth;
    }

    /*
     * Retourne la hauteur de l'écran.
     * @return Hauteur de l'écran
     */
    public int getScreenHeight(){
        return this.sHeight;
    }

    /*
     * Retourne la référence du manager d'affichage.
     * @return Manager d'affichage
     */
    public RenderManager getRenderManager(){
        return this.renderManager;
    }

    /*
     * Retourne la référence du manager d'entrées
     * @return Manager d'entrées
     */
    public InputManager getInputManager(){
        return this.inputManager;
    }

    /*
     * Retourne la référence de l'écran de jeu en cours d'utilisation.
     * @return Référence d'un écran de jeu
     */
    public GameScreen getCurrentScreen(){
        return this.currentScreen;
    }

    /*
     * Met à jour l'écran de jeu en cours d'utilisation.
     * C'est-à-dire que tous les Objets appartenant à cet écran seront eux aussi mis à jour.
     */
    public void update(){
        this.currentScreen.update(this);
    }

    /*
     * Affiche l'écran de jeu en cours d'utilisation.
     * C'est-à-dire que tous les Objets appartenant à cet écran seront affichés.
     */
    public void draw(){
        this.currentScreen.draw(this.renderManager);
    }

    /*
     * Détruit tous les écrans de jeu existants ainsi que tous les Objets dans ces écrans de jeu.
     */
    public void destroy() {
        for(int i=0; i<this.screens.size(); i++){
            this.screens.get(this.screens.keyAt(i)).destroy();
        }
    }

    /*
     * Modifie l'écran de jeu actuel. Permet donc de passer d'un écran de jeu à un autre.
     * @param Index du nouvel écran de jeu
     */
    public void setGameScreen(int nextScreen){

            if(screens.indexOfValue(this.previousScreen) != nextScreen)
            {
                this.previousScreen = this.currentScreen;
            }
        this.currentScreen = this.screens.get(nextScreen);
    }

    public int getPreviousScreenKey()
    {
        return screens.indexOfValue(this.previousScreen);
    }
}
