package istia.ei4.pm.ia;

import java.util.ArrayList;

public abstract class SolverBFS {

	public SolverBFS(int maxDepth, AWorld world, AGameState baseState, IEndCondition endCondition){
    this.maxDepth = maxDepth;
    this.world = world;
    this.baseState = baseState;
    this.endCondition = endCondition;
	}
	
	public GameSolution solve(){
        int nHashes = 1;
        long[] hashes = new long[100000];
        hashes[0] = baseState.computeHash(world);
        currentDepth = 0;

        ArrayList<AGameState> nextLayer = new ArrayList<>();
        nextLayer.add(baseState);

        long t0 = System.currentTimeMillis();
        while(currentDepth < this.maxDepth){
          //System.out.println(currentDepth + " -> " + nextLayer.size() + " -> " + (System.currentTimeMillis()-t0));
          currentDepth++;
          ArrayList<AGameState> currentLayer = (ArrayList<AGameState>)(nextLayer.clone());
          nextLayer.clear();
          for(AGameState state : currentLayer){
            ArrayList<AGameState> derivedStates = state.computeDerivedStates(this.world);
            ///
            for(AGameState s : derivedStates){
              //check if solution
              if(endCondition.checkEnd(this.world, s)){
                AGameState upState = s;
                GameSolution solution = new GameSolution();
                while(upState.getParentState() != null){
                  solution.insertMove(upState.getPreviousMove());
                  upState = upState.getParentState();
                }
                //System.out.println("----\n" + (System.currentTimeMillis()-t0) + "\n----");
                return solution;
              }
              //check if hash already exists
              boolean hashFound = this.additionnalRemovalCriteria(s);
              if(!hashFound){
                long stateHash = s.computeHash(world);
                for(int i=0; i<nHashes; i++){
                  hashFound |= stateHash == hashes[i];
                }
                if(!hashFound){
                  hashes[nHashes] = stateHash;
                  nHashes++;
                  nextLayer.add(s);
                }
              }
            }
            ///
          }
        }
        return null;
	}
  
  public abstract boolean additionnalRemovalCriteria(AGameState state);
  
  protected final int maxDepth;
  
  protected int currentDepth;
  protected AWorld world;
  protected AGameState baseState;
  protected IEndCondition endCondition;
	
}
