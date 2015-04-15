package istia.ei4.ProjetISTIA.solver;

import java.util.ArrayList;
import java.util.List;

import driftingdroids.model.*;
import istia.ei4.ProjetISTIA.GridElement;
import istia.ei4.pm.ia.GameSolution;
import istia.ei4.pm.ia.ricochet.RRGetMap;

/**
 * Created by Pierre on 15/04/2015.
 */
public interface ISolver extends Runnable {

    public void init(ArrayList<GridElement> elements);
    public void run();
    public SolverStatus getSolverStatus();
    public GameSolution getSolution();
}
