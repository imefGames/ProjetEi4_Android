package istia.ei4.ProjetISTIA;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;

import java.util.Random;

/**
 * Created by Pierre on 21/01/2015.
 */
public class RenderManager {
    private Canvas target, mainTarget;
    private Paint brush;
    private Resources resources;
    private SparseArray<Drawable> resourceMap;
    private Random random;

    /*
     * Constructeur de la classe RenderManager.
     * @param Référence des resources du projet.
     */
    public RenderManager(Resources resources){
        this.target = null;
        this.brush = new Paint();
        this.brush.setColor(Color.WHITE);
        this.resources = resources;
        this.resourceMap = new SparseArray<Drawable>();
        this.random = new Random();
    }

    /*
     * Change le canvas cible principale sur laquelle le RenderManager va déssiner.
     * @param Référence du canvas à cibler.
     */
    public void setMainTarget(Canvas target){
        this.mainTarget = target;
        this.target = target;
    }

    /*
     * Change le canvas cible sur laquelle le RenderManager va déssiner.
     * @param Référence du canvas à cibler.
     */
    public void setTarget(Canvas target){
        this.target = target;
    }

    /*
     * Le canvas cible sur laquelle le RenderManager va déssiner est de nouveau la cible principale.
     * @param Référence du canvas à cibler.
     */
    public void resetTarget(){
        this.target = this.mainTarget;
    }

    /*
     * Change la couleur par défaut du RenderManager.
     * @param Nouvelle couleur à adopter.
     */
    public void setColor(int color){
        this.brush.setColor(color);
    }

    /*
     * Paint la totalité du canvas cible à la couleur par défaut.
     */
    public void paintScreen(){
        this.target.drawColor(this.brush.getColor());
    }

    /*
     * Déssine un cercle sur le canvas cible aux coordonnées (x, y), de rayon radius et à la couleur par défaut.
     * @param Position x du cercle
     * @param Position y du cercle
     * @param Rayon du cercle
     */
    public void drawCircle(float x, float y, int radius){
        this.target.drawCircle(x, y, radius, this.brush);
    }

    /*
     * Déssine un rectangle à la couleur par défaut sur le canvas cible entre les points (x1, y1) et (x2, y2).
     * @param Position x du premier point du rectangle
     * @param Position y du premier point du rectangle
     * @param Position x du second point du rectangle
     * @param Position y du second point du rectangle
     */
    public void drawRect(float x1, float y1, float x2, float y2){
        this.target.drawRect(x1, y1, x2, y2, this.brush);
    }

    /*
     * Déssine une image sur le canvas cible entre les points (x1, y1) et (x2, y2).
     * @param Position x du premier point du rectangle contenant l'image
     * @param Position y du premier point du rectangle contenant l'image
     * @param Position x du second point du rectangle contenant l'image
     * @param Position y du second point du rectangle contenant l'image
     * @param Index de l'image à déssiner
     */
    public void drawImage(int x1, int y1, int x2, int y2, int image){
        Drawable d = this.resourceMap.get(image);
        if (d == null) {
            return;
        }
        d.setBounds(x1, y1, x2, y2);
        d.draw(this.target);
    }

    /*
     * Charge en mémoire une image donnée.
     * @param index de l'image à charger en mémoire
     */
    public void loadImage(int image){
        this.resourceMap.append(image, this.resources.getDrawable(image));
    }

    /*
     * Affiche un bitmap.
     * @param référence du Bitmap à afficher
     * @param position x du point en haut à gauche du bitmap
     * @param position y du point en haut à gauche du bitmap
     */
    public void drawBitmap(Bitmap bmp, float x, float y){
        this.target.drawBitmap(bmp, x, y, null);
    }

    /*
     * Charge en mémoire un bitmap.
     * @param référence du Bitmap à afficher
     */
    public int loadBitmap(Bitmap bmp){
        int id = this.random.nextInt();
        while(this.resourceMap.indexOfKey(id) >= 0){
            id = this.random.nextInt();
        }
        this.resourceMap.append(id, new BitmapDrawable(this.resources, bmp));
        return id;
    }

    /*
     * Décharge en mémoire une image.
     * @param index de l'image
     */
    public void unloadBitmap(int id){
        if(this.resourceMap.indexOfKey(id) >= 0){
            this.resourceMap.delete(id);
        }
    }

    public Resources getResources()
    {
        return resources;
    }

    public void drawText(int x, int y, String str){
        this.target.drawText(str, x, y, this.brush);
    }

    public void setTextSize(int s){
        this.brush.setTextSize(s);
    }
}
