package istia.ei4.pm.ia.ricochet;

import android.graphics.Color;

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



    colors.put("rr", Color.RED);
    colors.put("rb", Color.BLUE);
    colors.put("rv", Color.GREEN);
    colors.put("rj", Color.YELLOW);
    colors.put("cr", Color.RED);
    colors.put("cb", Color.BLUE);
    colors.put("cv", Color.GREEN);
    colors.put("cj", Color.YELLOW);
    colors.put("cm", 0);      // no color

    
    //Todo : Remplacer par les lignes précédentes

      /*
    colors.put("rr", 1);
    colors.put("rb", 2);
    colors.put("rv", 3);
    colors.put("rj", 4);
    colors.put("cr", 1);
    colors.put("cb", 2);
    colors.put("cv", 3);
    colors.put("cj", 4);
    colors.put("cm", 0);
    */
    
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
  
}
