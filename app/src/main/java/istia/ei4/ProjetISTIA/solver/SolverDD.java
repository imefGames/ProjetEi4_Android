package istia.ei4.ProjetISTIA.solver;


import java.util.ArrayList;
import java.util.List;

import istia.ei4.ProjetISTIA.GridElement;
import driftingdroids.model.Board;
import driftingdroids.model.Solver;
import driftingdroids.model.Solution;
import istia.ei4.pm.ia.GameSolution;
import istia.ei4.pm.ia.ricochet.RRGetMap;

/**
 * Created by Pierre on 08/03/2015.
 */
public class SolverDD implements ISolver {

    private SolverStatus solverStatus;
    private Solver solver;
    private Solution solution;

    public SolverDD(){
        solver = null;
        solverStatus = SolverStatus.idle;
        solution = null;
    }

    public void init(ArrayList<GridElement> elements){
        Board board = null;
        board = RRGetMap.createWorld(elements);
        solver = Solver.createInstance(board);
    }

    @Override
    public void run() {

        if(solver == null){
            return;
        }

        solverStatus = SolverStatus.solving;

        try {
            List<Solution> solutions = solver.execute();
            if(solutions.size() != 0){
                solution = solutions.get(0);
                System.out.println(solution.toString());
                solverStatus = SolverStatus.solved;
            }else{
                solverStatus = SolverStatus.noSolution;
            }
        }catch(InterruptedException e){
            solverStatus = SolverStatus.noSolution;
        }
    }

    public SolverStatus getSolverStatus(){
        return this.solverStatus;
    }

    public GameSolution getSolution(){
        //TODO: Traduire la solution
        return null;
    }

}
