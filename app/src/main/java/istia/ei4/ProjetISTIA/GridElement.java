package istia.ei4.ProjetISTIA;

/**
 * Created by Alain on 21/01/2015.
 */
public class GridElement {

    public static int dimension = 2 ;
    //Variable de classe

    private int x ;
    private int y ;
    private String type;
    //Variables d'instance

    public GridElement(){
        this(0,0, "mh") ;
    }
    //Constructeur par défaut

    public GridElement(int x, int y, String objectType){
        this.setX(x) ;
        this.setY(y) ;
        this.setType(objectType);
    }
    //Constructeur avec argument

    public int getX(){
        return this.x ;
    }

    public void setX(int x){
        this.x = x ;
    }
    //Accesseurs correspondant à la variable x.

    public int getY(){
        return this.y ;
    }

    public void setY(int y){
        this.y = y ;
    }
    //Accesseurs correspondant à la variable y.

    public String getType(){
        return this.type ;
    }

    public void setType(String objectType){
        this.type = objectType ;
    }
    //Accesseurs correspondant à la variable y.

    public static int quelleDimension(){
        return dimension ;
    }
}