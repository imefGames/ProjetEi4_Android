package istia.ei4.ProjetISTIA.solver;

import java.util.ArrayList;

import istia.ei4.ProjetISTIA.GridElement;
import istia.ei4.pm.ia.GameSolution;

/**
 * Created by Pierre on 15/04/2015.
 */
public interface ISolver extends Runnable {

    public void init(ArrayList<GridElement> elements);
    public void run();
    public SolverStatus getSolverStatus();
    public GameSolution getSolution();
}
