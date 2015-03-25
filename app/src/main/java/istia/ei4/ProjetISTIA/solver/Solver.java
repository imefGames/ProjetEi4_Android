package istia.ei4.ProjetISTIA.solver;


/**
 * Created by Pierre on 08/03/2015.
 */
public class Solver extends Thread {

    private SolverStatus solverStatus           = SolverStatus.idle;

    public Solver(){
        // TODO: initialize the solver
    }

    @Override
    public void run() {
        // TODO: faire le solveur de problèmes
    }

    public SolverStatus getSolverStatus(){
        return this.solverStatus;
    }

}
