package istia.ei4.pm.ia.ricochet;

import driftingdroids.model.Board;
import istia.ei4.ProjetISTIA.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author usrlocal
 */
public class RRGetMap {
  
  public static RRWorld createWorld(ArrayList<GridElement> gridElements, RRGameState baseState)
  {
    RRWorld currentWorld = new RRWorld();
    
    ArrayList<GridElement> robots = new ArrayList<GridElement>();
    GridElement cible = null;
    
    Map<String, Integer> colors = new HashMap<String, Integer>();
    
        /*
    colors.put("rr", Color.RED);
    colors.put("rb", Color.BLUE);
    colors.put("rv", Color.GREEN);
    colors.put("rj", Color.YELLOW);
    colors.put("cr", Color.RED);
    colors.put("cb", Color.BLUE);
    colors.put("cv", Color.GREEN);
    colors.put("cj", Color.YELLOW);
     */
    
    //Todo : Remplacer par les lignes précédentes
    colors.put("rr", 1);
    colors.put("rb", 2);
    colors.put("rv", 3);
    colors.put("rj", 4);
    colors.put("cr", 1);
    colors.put("cb", 2);
    colors.put("cv", 3);
    colors.put("cj", 4);
    colors.put("cm", 0);
    
    
    for (Object element : gridElements) {
      GridElement myp = (GridElement) element;

      if (myp.getType().equals("mh")) {
        currentWorld.setHorizontalWall(myp.getX(), myp.getY());
      }
      if (myp.getType().equals("mv")) {
          currentWorld.setVerticalWall(myp.getX(), myp.getY());    
      }
      if (myp.getType().equals("cr") || myp.getType().equals("cv") ||myp.getType().equals("cb") ||myp.getType().equals("cj") ||myp.getType().equals("cm")) {
          cible = myp;  
      }
      if (myp.getType().equals("rr") || myp.getType().equals("rv") ||myp.getType().equals("rb") ||myp.getType().equals("rj")) {
          robots.add(myp);
      }    
    }
    
    ArrayList<RRPiece> mainL = new ArrayList<RRPiece>();
    ArrayList<RRPiece> secondL = new ArrayList<RRPiece>();
    
    String types[] = {"cr","cb","cv","cj"};
    
    int cpt = 0;
    
    if(cible.getType().equals("cm"))
    {
      for(GridElement robot : robots)
      {
        mainL.add(new RRPiece(robot.getX(), robot.getY(), colors.get(robot.getType()), cpt));
        cpt++;
      }
    }
    else{          
      for(String type : types)
      {
        if(cible.getType().equals(type))
        {
          for(GridElement robot : robots)
          {
            if(robot.getType().charAt(1) == cible.getType().charAt(1))
            {
                mainL.add(new RRPiece(robot.getX(), robot.getY(), colors.get(robot.getType()), cpt));
              cpt++;
            }
            else
            {
              secondL.add(new RRPiece(robot.getX(), robot.getY(), colors.get(robot.getType()), cpt));
              cpt++;
            }
          }
        }
      }
    }
    
    //System.out.println("Cible y :");
    //System.out.println(cible.getType());
    currentWorld.setObjective(cible.getX(), cible.getY(), colors.get(cible.getType()));
    
    RRPiece[] mainLA, secLA;
    mainLA = new RRPiece[mainL.size()];
    for(int i=0; i<mainL.size(); i++){
      mainLA[i] = mainL.get(i);
    }
    secLA = new RRPiece[secondL.size()];
    for(int i=0; i<secondL.size(); i++){
      secLA[i] = secondL.get(i);
    }
    baseState.setPieces(mainLA, secLA);       
    
    
    return currentWorld;
  }

    public static Board createWorld(ArrayList<GridElement> gridElements)
    {
        Board board = Board.createBoardFreestyle(null, 16, 16, 4);
        board.removeGoals();

        ArrayList<GridElement> robots = new ArrayList<>();
        GridElement cible = null;

        Map<String, Integer> colors = new HashMap<>();

        colors.put("rr", 0);
        colors.put("rb", 1);
        colors.put("rv", 2);
        colors.put("rj", 3);
        colors.put("cr", 0);
        colors.put("cb", 1);
        colors.put("cv", 2);
        colors.put("cj", 3);
        colors.put("cm", -1);


        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;

            if (myp.getType().equals("mh")) {
                board.setWall(myp.getX() | (myp.getY() << 4), "N", true);
            }
            if (myp.getType().equals("mv")) {
                board.setWall(myp.getX() | (myp.getY() << 4), "W", true);
            }
            if (myp.getType().equals("cr") || myp.getType().equals("cv") ||myp.getType().equals("cb") ||myp.getType().equals("cj") ||myp.getType().equals("cm")) {
                board.addGoal(myp.getX()|(myp.getY()<<4), colors.get(myp.getType()), 1);
            }
            if (myp.getType().equals("rr") || myp.getType().equals("rv") ||myp.getType().equals("rb") ||myp.getType().equals("rj")) {
                board.setRobot(colors.get(myp.getType()), myp.getX()|(myp.getY()<<4), false);
            }
        }

        return board;
    }
  
}
