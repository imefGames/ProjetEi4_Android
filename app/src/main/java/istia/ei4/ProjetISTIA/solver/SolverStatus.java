package istia.ei4.ProjetISTIA.solver;

/**
 * Created by Pierre on 08/03/2015.
 */
public enum SolverStatus{
    idle(false, 0),
    solving(false, 1),
    solved(true, 0),
    missingData(true, 1),
    noSolution(true, 2);

    private final boolean status;
    private final int code;

    private SolverStatus(boolean status, int code){
        this.status = status;
        this.code  = code;
    }

    @Override
    public String toString()
    {
        String ret = "";
        if(this.status){
            ret.concat("Finished: ");
        }else{
            ret.concat("Not Finished: ");
        }
        ret.concat(""+this.code);
        return ret;
    }

    public boolean isFinished(){
        return this.status;
    }

    public int getCode(){
        return this.code;
    }
}