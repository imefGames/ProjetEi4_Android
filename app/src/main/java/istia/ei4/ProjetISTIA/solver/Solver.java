package istia.ei4.ProjetISTIA.solver;


import java.util.ArrayList;

import istia.ei4.ProjetISTIA.GridElement;
import istia.ei4.pm.ia.AGameState;
import istia.ei4.pm.ia.GameSolution;
import istia.ei4.pm.ia.IGameMove;
import istia.ei4.pm.ia.ricochet.RREndCondition;
import istia.ei4.pm.ia.ricochet.RRGameMove;
import istia.ei4.pm.ia.ricochet.RRGameState;
import istia.ei4.pm.ia.ricochet.RRGetMap;
import istia.ei4.pm.ia.ricochet.RRPiece;
import istia.ei4.pm.ia.ricochet.RRSolver;
import istia.ei4.pm.ia.ricochet.RRWorld;

/**
 * Created by Pierre on 08/03/2015.
 */
public class Solver implements Runnable {

    private SolverStatus solverStatus;
    private RRSolver solver;
    private GameSolution solution;

    public Solver(){
        solver = null;
        solverStatus = SolverStatus.idle;
        solution = null;
    }

    public void init(ArrayList<GridElement> elements){

        RRWorld world = null;
        String text = null;

        RRGameState baseState = new RRGameState(null, null);

        world = RRGetMap.createWorld(elements, baseState);

        RREndCondition endCondition = new RREndCondition();

        world.precomputeGrid();

        solver = new RRSolver(10, world, baseState, endCondition);
    }

    public void run() {

        if(solver == null){
            return;
        }

        solverStatus = SolverStatus.solving;

        System.out.println("------ IA START ------");
        solution = solver.solve();

        if(solution == null){
            System.out.println("------ IA NO SOLUTION ------");
            solverStatus = SolverStatus.noSolution;
        }else{
            System.out.println("------ IA SOLUTION ------");
            solverStatus = SolverStatus.solved;
            for(IGameMove m : solution.getMoves()){
                RRGameMove move = (RRGameMove)m;
                System.out.println(move.getColor() + " -> " + move.getMove());
            }
        }
        System.out.println("------ IA DONE ------");
    }

    public SolverStatus getSolverStatus(){
        return this.solverStatus;
    }

    public GameSolution getSolution(){
        return this.solution;
    }

}
