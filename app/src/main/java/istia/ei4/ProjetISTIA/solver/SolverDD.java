package istia.ei4.ProjetISTIA.solver;


import java.util.ArrayList;
import java.util.List;

import driftingdroids.model.Move;
import istia.ei4.ProjetISTIA.GameManager;
import istia.ei4.ProjetISTIA.GridElement;
import driftingdroids.model.Board;
import driftingdroids.model.Solver;
import driftingdroids.model.Solution;
import istia.ei4.pm.ia.GameSolution;
import istia.ei4.pm.ia.IGameMove;
import istia.ei4.pm.ia.ricochet.ERRGameMove;
import istia.ei4.pm.ia.ricochet.RRGameMove;
import istia.ei4.pm.ia.ricochet.RRGetMap;
import istia.ei4.pm.ia.ricochet.RRPiece;

/**
 * Created by Pierre on 08/03/2015.
 */
public class SolverDD implements ISolver{

    private SolverStatus solverStatus;
    private Solver solver;
    private Solution solution;
    private RRPiece[] pieces;

    public SolverDD(){
        solver = null;
        solverStatus = SolverStatus.idle;
        solution = null;
        pieces = new RRPiece[4];
    }

    public void init(ArrayList<GridElement> elements){
        Board board = null;
        board = RRGetMap.createWorld(elements, pieces);
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
        GameSolution s = new GameSolution();

        solution.resetMoves();
        Move m = solution.getNextMove();
        while (m != null){

            ERRGameMove mv;
            switch(m.direction){
                case 0:
                    mv = ERRGameMove.UP;
                    break;
                case 1:
                    mv = ERRGameMove.RIGHT;
                    break;
                case 2:
                    mv = ERRGameMove.DOWN;
                    break;
                case 3:
                    mv = ERRGameMove.LEFT;
                    break;
                default:
                    mv = ERRGameMove.NOMOVE;
                    break;
            }
            System.out.print(m.direction+","+pieces[m.robotNumber].getColor()+";");
            s.addMove(new RRGameMove(pieces[m.robotNumber], mv));
            m = solution.getNextMove();
        }
        System.out.println("");
        return s;
    }

}
