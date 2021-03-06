package istia.ei4.ProjetISTIA;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

import driftingdroids.model.Solver;
import istia.ei4.ProjetISTIA.solver.ISolver;
import istia.ei4.ProjetISTIA.solver.SolverRR;
import istia.ei4.ProjetISTIA.solver.SolverDD;
import istia.ei4.pm.ia.GameSolution;
import istia.ei4.pm.ia.IGameMove;
import istia.ei4.pm.ia.ricochet.RRGameMove;

/**
 * Created by Alain on 25/02/2015.
 */
public class GridGameScreen extends GameScreen {
    private boolean randomGrid = false;
    private Canvas canvasGrid;
    private Dictionary dict;

    private boolean isSolved = false;

    private ISolver solver;

    private ArrayList gridElements;
    private int imageGridID;
    private boolean imageLoaded = false;

    private String mapPath = "";

    private int xGrid = 0;
    private int yGrid = 100;

    private float gridSpace = 0;
    private int margin = 0;

    private int timeCpt = 0;
    private int nbCoups = 0;
    private long prevTime;

    public static void setSolverBFS(boolean solverBFS) {
        GridGameScreen.solverBFS = solverBFS;
    }

    private static boolean solverBFS = false;

    private int IAMovesNumber = 0;

    private boolean mustStartNext = false;

    private ArrayList<IGameMove> moves = null;

    private Thread t = null;

    private GameMouvementInterface gmi;
    private Bitmap bitmapGrid;
    RenderManager currentRenderManager;
    Map<String, Drawable> drawables = new HashMap<String, Drawable>();
    Map<String, Integer> colors = new HashMap<String, Integer>();
    private ArrayList<Move> allMoves= new ArrayList<>();

    private GameButtonGeneral buttonSolve;


    public GridGameScreen(GameManager gameManager){
        super(gameManager);

        gridSpace = (float)(67.5 * gameManager.getScreenWidth() /1080);
        xGrid = 0;
        yGrid = 1080/5;

        Bitmap.Config conf = Bitmap.Config.ARGB_4444;

        bitmapGrid = Bitmap.createBitmap((int)(16 * gridSpace), (int) (16 * gridSpace), conf);
        canvasGrid = new Canvas(bitmapGrid);
        currentRenderManager = gameManager.getRenderManager();

        prevTime = System.currentTimeMillis();

        gameManager.getRenderManager().loadImage(R.drawable.rj);
        gameManager.getRenderManager().loadImage(R.drawable.rb);
        gameManager.getRenderManager().loadImage(R.drawable.rv);
        gameManager.getRenderManager().loadImage(R.drawable.rr);

    }

    @Override
    public void create()
    {
        gmi = new GameMouvementInterface();

        xGrid = 0;
        yGrid = 1080/5;

        int y = yGrid+gameManager.getScreenWidth();
        int dy = gameManager.getScreenHeight()-y;
        int w = 8*gameManager.getScreenWidth()/20;
        int h = 8*dy/20;
        int y1 = y+dy/20;
        int y2 = y+11*dy/20;
        int x1 = gameManager.getScreenWidth()/20;
        int x2 = 11*gameManager.getScreenWidth()/20;

        this.instances.add(new GameButtonGeneral(x1, y1, w, h, R.drawable.bt_jeu_retour_up, R.drawable.bt_jeu_retour_down, new ButtonBack()));
        this.instances.add(new GameButtonGeneral(x2, y1, w, h, R.drawable.bt_jeu_reset_up, R.drawable.bt_jeu_reset_down, new ButtonRestart()));
        this.instances.add(new GameButtonGoto(x1, y2, w, h, R.drawable.bt_jeu_save_up, R.drawable.bt_jeu_save_down, 9));

        float ratioW = ((float)gameManager.getScreenWidth()) /((float)1080);
        float ratioH = ((float)gameManager.getScreenHeight()) /((float)1920);

        this.instances.add(new GameButtonGeneral((int)(870*ratioW), (int)(10*ratioH), (int)(200*ratioW), (int)(200*ratioH), R.drawable.bt_next_up, R.drawable.bt_next_down, new ButtonNext()));

        gameManager.getRenderManager().loadImage(R.drawable.bt_jeu_resolution_up);
        gameManager.getRenderManager().loadImage(R.drawable.bt_jeu_resolution_down);
        gameManager.getRenderManager().loadImage(R.drawable.bt_jeu_resolution_disabled);

        buttonSolve = new GameButtonGeneral(x2, y2, w, h, R.drawable.bt_jeu_resolution_up, R.drawable.bt_jeu_resolution_down, new ButtonSolution());
        buttonSolve.setImageDisabled(R.drawable.bt_jeu_resolution_disabled);


        buttonSolve.setEnabled(false);

        this.instances.add(buttonSolve);

        this.solver = new SolverDD();
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
        this.gmi.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager)
    {
        renderManager.setColor(Color.argb(255, 175, 167, 123));
        renderManager.paintScreen();

        renderManager.setColor(Color.BLACK);
        int textS = yGrid/2-50;
        renderManager.setTextSize(textS);
        renderManager.drawText(10, textS, "Nombre de coups: " + nbCoups);
        renderManager.drawText(10, yGrid/2+textS, "Temps: " + timeCpt/60 + ":" + timeCpt%60);

        if(imageLoaded)
        {
            gameManager.getRenderManager().drawImage(xGrid, yGrid, (int)(16*gridSpace) + xGrid, (int)(16*gridSpace) + yGrid,  imageGridID);
        }
        super.draw(renderManager);
        this.gmi.draw(renderManager);
    }

    public void update(GameManager gameManager){
        super.update(gameManager);

        if(mustStartNext)
        {
            allMoves.clear();

            buttonSolve.setEnabled(false);
            if(t != null){
                t.interrupt();
                moves = null;
                t = null;
            }
            int integer = -1;

            if(!mapPath.equals(""))
            {

//                int value = 0;
//                Scanner s = new Scanner(mapPath);

                Scanner in = new Scanner(mapPath).useDelimiter("[^0-9]+");
                integer = in.nextInt();

                //value = s.nextInt();
                System.out.println("Value mappath:"+integer);
            }

            System.out.println("B");
            if(integer >=0 && integer < 60)
            {
                mapPath = "Maps/generatedMap_"+(integer+1)+".txt";
                setLevelGame(mapPath);
            }
            else
            {
                setRandomGame(true);
            }
            System.out.println("C");

            mustStartNext = false;
        }
        if(System.currentTimeMillis() - prevTime > 1000L){
            timeCpt++;
            prevTime = System.currentTimeMillis();
        }
        this.gmi.update(gameManager);
        if(gameManager.getInputManager().backOccurred()){
            if(t != null){
                t.interrupt();
                moves = null;
                t = null;
            }
            gameManager.setGameScreen(1);
        }

        if(!isSolved && solver.getSolverStatus().isFinished())
        {
            isSolved = true;
            buttonSolve.setEnabled(true);
        }
    }

    @Override
    public void destroy(){
        if(t != null){
            t.interrupt();
            moves = null;
            t = null;
        }
    }

    public ArrayList getGridElements() {
        return gridElements;
    }

    public void setSavedGame(String mapPath)
    {
        this.mapPath = "";

        gridElements = MapObjects.extractDataFromString(FileReadWrite.readPrivateData(gameManager.getActivity(), mapPath));

        createGrid();
    }

    public void setLevelGame(String mapPath)
    {
        this.mapPath = mapPath;
        //setGame(mapPath);

        System.out.println("SetLevelGame");
        gridElements = MapObjects.extractDataFromString(FileReadWrite.readAssets(gameManager.getActivity(), mapPath));
        System.out.println("SetLevelGame, gridElements :"+gridElements.size());
       // this.solver = new SolverRR();

        createGrid();
    }

    public void setRandomGame(boolean random)
    {

        this.mapPath = "";  //La carte étant générée, elle n'a pas de chemin d'accès
        MapGenerator generatedMap = new MapGenerator();
        gridElements = generatedMap.get16DimensionalMap();

       // this.solver = new SolverDD();

        createGrid();


    }

    public void createGrid()
    {
        if(solverBFS)
        {
            this.solver = new SolverRR();
        }
        else
        {
            this.solver = new SolverDD();
        }
        IAMovesNumber = 0;
        isSolved = false;

        nbCoups = 0;
        timeCpt = 0;
        prevTime = System.currentTimeMillis();

        currentRenderManager.setTarget(canvasGrid);

        drawables.put("grid", currentRenderManager.getResources().getDrawable(R.drawable.grid));
        drawables.put("mh", currentRenderManager.getResources().getDrawable(R.drawable.mh));
        drawables.put("mv", currentRenderManager.getResources().getDrawable(R.drawable.mv));

        drawables.put("rv", currentRenderManager.getResources().getDrawable(R.drawable.rv));
        drawables.put("rr", currentRenderManager.getResources().getDrawable(R.drawable.rr));
        drawables.put("rj", currentRenderManager.getResources().getDrawable(R.drawable.rj));
        drawables.put("rb", currentRenderManager.getResources().getDrawable(R.drawable.rb));

        drawables.put("cv", currentRenderManager.getResources().getDrawable(R.drawable.cv));
        drawables.put("cr", currentRenderManager.getResources().getDrawable(R.drawable.cr));
        drawables.put("cj", currentRenderManager.getResources().getDrawable(R.drawable.cj));
        drawables.put("cb", currentRenderManager.getResources().getDrawable(R.drawable.cb));
        drawables.put("cm", currentRenderManager.getResources().getDrawable(R.drawable.cm));


        drawables.get("grid").setBounds(0, 0,(int)( 16 * gridSpace),(int)( 16 * gridSpace));
        drawables.get("grid").draw(canvasGrid);

        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;

            if (myp.getType().equals("cr") || myp.getType().equals("cv") || myp.getType().equals("cj") || myp.getType().equals("cb") || myp.getType().equals("cm")) {
                drawables.get(myp.getType()).setBounds((int)(myp.getX() * gridSpace),(int)( myp.getY() * gridSpace),(int)( (myp.getX() + 1) * gridSpace),(int)( (myp.getY()+1) * gridSpace));
                drawables.get(myp.getType()).draw(canvasGrid);
            }
        }

        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;

            if (myp.getType().equals("mh")) {
                drawables.get("mh").setBounds((int)(myp.getX() * gridSpace), (int)(myp.getY() * gridSpace -1), (int)((myp.getX() + 1) * gridSpace), (int)(myp.getY() * gridSpace + 1));
                drawables.get("mh").draw(canvasGrid);
            }

            if (myp.getType().equals("mv")) {
                drawables.get("mv").setBounds((int)(myp.getX() * gridSpace-1), (int)(myp.getY() * gridSpace), (int)(myp.getX() * gridSpace + 1), (int)((myp.getY() + 1) * gridSpace));
                drawables.get("mv").draw(canvasGrid);
            }
        }

        currentRenderManager.resetTarget();

        //On supprime l'image de fond si elle existe et on sauvegarde celle que l'on vient de créer
        if(imageLoaded == true)
        {
            currentRenderManager.unloadBitmap(imageGridID);
        }
        imageGridID = currentRenderManager.loadBitmap(bitmapGrid);
        imageLoaded = true;


        createRobots();


        this.solver.init(gridElements);

        buttonSolve.setEnabled(false);
        t = new Thread(solver, "solver");
        t.start();

    }

    public void createRobots()
    {
        colors.put("rr", Color.RED);
        colors.put("rb", Color.BLUE);
        colors.put("rv", Color.GREEN);
        colors.put("rj", Color.YELLOW);

        colors.put("cr", Color.RED);
        colors.put("cb", Color.BLUE);
        colors.put("cv", Color.GREEN);
        colors.put("cj", Color.YELLOW);

        ArrayList<GamePiece> aRetirer = new ArrayList<>();
        for(Object currentObject : this.instances)
        {
            if(currentObject.getClass() == GamePiece.class)
            {
                aRetirer.add((GamePiece)currentObject);
            }
        }
        for(GamePiece p : aRetirer)
        {
            this.instances.remove(p);
        }

        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;

            if (myp.getType().equals("rr") || myp.getType().equals("rv") || myp.getType().equals("rj") || myp.getType().equals("rb")) {

                GamePiece currentPiece = new GamePiece(myp.getX(), myp.getY(), colors.get(myp.getType()));
                currentPiece.setGridDimensions(xGrid, yGrid, gridSpace);

                this.instances.add(currentPiece);
            }
        }

    }

    public void activateInterface(GamePiece p, int x, int y){
        gmi.enable(true);
        gmi.setPosition(x-1, y);
        gmi.setTarget(p);
    }

    public void editDestination(GamePiece p, int direction, Boolean moved)
    {
        int xDestination = p.getxObjective();
        int yDestination = p.getyObjective();

        boolean canMove = true;

        if(!moved) {
            Move currentMove = new Move(p, p.getxObjective(), p.getyObjective());
            allMoves.add(currentMove);
        }

        for(Object instance : this.instances)
        {
            if(instance.getClass() == p.getClass() && p != instance && canMove)
            {
                switch(direction){
                    case 0:     // haut
                        canMove = collision((GamePiece) instance, xDestination, yDestination - 1, canMove);
                        break;
                    case 1:     // droite
                        canMove = collision((GamePiece) instance, xDestination+1, yDestination, canMove);
                        break;
                    case 2:     // bas
                        canMove = collision((GamePiece) instance, xDestination, yDestination + 1, canMove);
                        break;
                    case 3:     // gauche
                        canMove = collision((GamePiece) instance, xDestination-1, yDestination, canMove);
                        break;
                }
            }
        }

        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;

            if ((myp.getType().equals("mv")) && (direction == 1)) {  // droite
                canMove = collision(p, myp.getX() - 1, myp.getY(), canMove);
            }
            if ((myp.getType().equals("mv")) && (direction == 3)) {  // gauche
                canMove = collision(p, myp.getX(), myp.getY(), canMove);
            }
            if ((myp.getType().equals("mh")) && (direction == 0)) {  // haut
                canMove = collision(p, myp.getX(), myp.getY(), canMove);
            }
            if ((myp.getType().equals("mh")) && (direction == 2)) {  // bas
                canMove = collision(p, myp.getX(), myp.getY() - 1, canMove);
            }
        }

        if(canMove)
        {
            switch(direction){
                case 0:     // haut
                    yDestination -= 1;
                    break;
                case 1:     // droite
                    xDestination +=1;
                    break;
                case 2:     // bas
                    yDestination += 1;
                    break;
                case 3:     // gauche
                    xDestination -=1;
                    break;
            }
            p.setxObjective(xDestination);
            p.setyObjective(yDestination);

            editDestination(p, direction, true);
        }else{
            if(moved)
            {
                nbCoups++;
                //boolean b = gagne(p);

            }
            else
            {
                allMoves.remove(allMoves.size()-1);

            }
        }
    }

    public boolean gagne(GamePiece p)
    {
        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;
            {
                 if (myp.getType().equals("cm") && myp.getX() == p.getX() && myp.getY() == p.getY())
                {
                    sayWon();

                    return true;
                }
                else if((myp.getX() == p.getX()) && (myp.getY() == p.getY()) && (myp.getType().equals("cr") || myp.getType().equals("cv") || myp.getType().equals("cb") || myp.getType().equals("cj")))
                {
                    if(p.getColor() == colors.get((myp.getType())))
                    {
                        sayWon();

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void sayWon()
    {
        if(IAMovesNumber > 0)
        {
            gameManager.requestToast("IA : solution trouvée en "+IAMovesNumber+" coups.", true);
        }
        else
        {
            gameManager.requestToast("Gagné en "+nbCoups+" coups.", true);
        }
        updatePlayedMaps();
    }

    private void updatePlayedMaps()
    {
        if(mapPath.length() > 0) {
            addMapsPlayed();
            SparseArray<GameScreen> screens = gameManager.getScreens();
            LevelChoiceGameScreen.getLastButtonUsed().setImageUp(R.drawable.bt_start_up_played);
            LevelChoiceGameScreen.getLastButtonUsed().setImageDown(R.drawable.bt_start_down_played);
            /*
            for (int i = 0; i < screens.size(); i++) {
                if (screens.get(i).getClass() == LevelChoiceGameScreen.class) {
                    ((LevelChoiceGameScreen) screens.get(i)).createButtons();
                }
            }*/
        }
    }


    public void addMapsPlayed()
    {
        if(mapPath.length() > 0)
        {
            SaveManager saver = new SaveManager(gameManager.getActivity());

            if(!saver.getMapsStateSaved(mapPath, "mapsPlayed.txt"))
            {
                FileReadWrite.writePrivateData(gameManager.getActivity(), "mapsPlayed.txt", mapPath.substring(5)+"\n");
            }
        }
    }

    public Boolean collision(GamePiece p, int x, int y, boolean canMove)
    {
        if(p.getxObjective() == x && p.getyObjective() == y && canMove == true)
            return false;
        else if(canMove == false)
            return false;
        return true;
    }

    private class ButtonRestart implements IExecutor{

        public void execute(){
            ButtonBack bb = new ButtonBack();
            while(allMoves.size()>0)
            {
                bb.execute();
            }
            nbCoups = 0;
        }
    }

    private class ButtonNext implements IExecutor{

        public void execute(){
            mustStartNext = true;

        }
    }

    private class ButtonSolution implements IExecutor{
        public void execute(){
            GameSolution solution = solver.getSolution();
            showSolution(solution);
        }
    }

    public void doMovesInMemory()
    {

        if(moves != null)
        {

            if(moves.size() > 0)
            {

                IGameMove move = moves.get(0);

                if(move.getClass() == RRGameMove.class)
                {

                    for (Object currentObject : this.instances)
                    {
                        if(currentObject.getClass() == GamePiece.class)
                        {
                            if(((GamePiece)currentObject).getColor() == ((RRGameMove) move).getColor())
                            {
                                editDestination(((GamePiece) currentObject), translateIADirectionToGameDirection(((RRGameMove) move).getDirection()), false);
                            }
                        }
                    }
                }
                moves.remove(0);
            }
        }
    }

    private void showSolution(GameSolution solution)
    {
        ButtonRestart br = new ButtonRestart();
        br.execute();

        moves = solution.getMoves();
        IAMovesNumber = moves.size();

        doMovesInMemory();
    }

    private int translateIADirectionToGameDirection(int IADirection)
    {
        switch(IADirection){
            case 1:
                return 0;
            case 2:
                return 1;
            case 4:
                return 2;
            case 8:
                return 3;
            default:
                return -1;
        }
    }

    private class ButtonBack implements IExecutor{
        public void execute(){
            if(allMoves.size() > 0)
            {
                allMoves.get(allMoves.size()-1).goBack();

                allMoves.remove(allMoves.size()-1);
                nbCoups--;
            }
        }
    }
}
