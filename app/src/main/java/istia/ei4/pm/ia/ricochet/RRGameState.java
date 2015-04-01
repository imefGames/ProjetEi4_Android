package istia.ei4.pm.ia.ricochet;

import istia.ei4.pm.ia.AGameState;
import istia.ei4.pm.ia.AWorld;
import istia.ei4.pm.ia.IGameMove;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Pierre Michel
 */
public class RRGameState extends AGameState {
  
  public RRGameState(AGameState parentState, IGameMove previousMove){
    super(parentState, previousMove);
    if(parentState != null){
      this.allPieces = new ArrayList<>();
      this.mainPieces = new RRPiece[((RRGameState)parentState).mainPieces.length];
      this.secondaryPieces = new RRPiece[((RRGameState)parentState).secondaryPieces.length];
      for(int i=0; i<((RRGameState)parentState).mainPieces.length; i++){
        this.mainPieces[i] = new RRPiece(((RRGameState)parentState).mainPieces[i]);
      }
      for(int i=0; i<((RRGameState)parentState).secondaryPieces.length; i++){
        this.secondaryPieces[i] = new RRPiece(((RRGameState)parentState).secondaryPieces[i]);
      }
      this.allPieces.addAll(Arrays.asList(mainPieces));
      this.allPieces.addAll(Arrays.asList(secondaryPieces));
    }
  }
  
  public void setPieces(RRPiece[] mainPieces, RRPiece[] secondaryPieces){
    this.allPieces = new ArrayList<>();
    this.allPieces.addAll(Arrays.asList(mainPieces));
    this.allPieces.addAll(Arrays.asList(secondaryPieces));
    this.mainPieces = mainPieces;
    this.secondaryPieces = secondaryPieces;
  }
  
  @Override
  public ArrayList<AGameState> computeDerivedStates(AWorld world) {
    RRWorld w = ((RRWorld)world);
    for(RRPiece piece : this.allPieces){
      w.putPiece(piece, true);
    }
    RRGameState ns;
    for(int i=0; i<this.mainPieces.length; i++){
      //UP
      ns = new RRGameState(this, new RRGameMove(this.mainPieces[i], ERRGameMove.UP));
      w.doMove(ns.mainPieces[i], ns, ERRGameMove.UP);
      this.derivedStates.add(ns);
      //DOWN
      ns = new RRGameState(this, new RRGameMove(this.mainPieces[i], ERRGameMove.DOWN));
      w.doMove(ns.mainPieces[i], ns, ERRGameMove.DOWN);
      this.derivedStates.add(ns);
      //LEFT
      ns = new RRGameState(this, new RRGameMove(this.mainPieces[i], ERRGameMove.LEFT));
      w.doMove(ns.mainPieces[i], ns, ERRGameMove.LEFT);
      this.derivedStates.add(ns);
      //RIGHT
      ns = new RRGameState(this, new RRGameMove(this.mainPieces[i], ERRGameMove.RIGHT));
      w.doMove(ns.mainPieces[i], ns, ERRGameMove.RIGHT);
      this.derivedStates.add(ns);
    }
    
    for(int i=0; i<this.secondaryPieces.length; i++){
      //UP
      ns = new RRGameState(this, new RRGameMove(this.secondaryPieces[i], ERRGameMove.UP));
      w.doMove(ns.secondaryPieces[i], ns, ERRGameMove.UP);
      this.derivedStates.add(ns);
      //DOWN
      ns = new RRGameState(this, new RRGameMove(this.secondaryPieces[i], ERRGameMove.DOWN));
      w.doMove(ns.secondaryPieces[i], ns, ERRGameMove.DOWN);
      this.derivedStates.add(ns);
      //LEFT
      ns = new RRGameState(this, new RRGameMove(this.secondaryPieces[i], ERRGameMove.LEFT));
      w.doMove(ns.secondaryPieces[i], ns, ERRGameMove.LEFT);
      this.derivedStates.add(ns);
      //RIGHT
      ns = new RRGameState(this, new RRGameMove(this.secondaryPieces[i], ERRGameMove.RIGHT));
      w.doMove(ns.secondaryPieces[i], ns, ERRGameMove.RIGHT);
      this.derivedStates.add(ns);
    }
    for(RRPiece piece : this.allPieces){
      w.putPiece(piece, false);
    }
    return this.derivedStates;
  }
  
  @Override
  public long computeHash(AWorld world){
    long ret = 0L;
    Arrays.sort(this.mainPieces);
    for(int i=0; i<this.mainPieces.length; i++){
      ret |= ((long)(mainPieces[i].getX())) << (8*i);
      ret |= ((long)(mainPieces[i].getY())) << (4 + 8*i);
    }
    Arrays.sort(this.secondaryPieces);
    for(int i=0; i<this.secondaryPieces.length; i++){
      ret |= ((long)(secondaryPieces[i].getX())) << (32 + 8*i);
      ret |= ((long)(secondaryPieces[i].getY())) << (36 + 8*i);
    }
    return ret;
  }
  
  public ArrayList<RRPiece> getPieces(){
    return this.allPieces;
  }
  
  public RRPiece[] getMainPieces(){
    return this.mainPieces;
  }
  
  @Override
  public String toString(){
    String str = "";
    str += this.hashCode();
    str += "\nMain Piece:\n";
    for(RRPiece p : this.mainPieces){
      str += String.format("%d -> x:%d, y:%d, color:%d\n", p.hashCode(), p.getX(), p.getY(), p.getColor());
    }
    str += "Secondary Pieces:\n";
    for(RRPiece p : this.secondaryPieces){
      str += String.format("%d -> x:%d, y:%d, color:%d\n", p.hashCode(), p.getX(), p.getY(), p.getColor());
    }
    
    return str;
  }
  
  private ArrayList<RRPiece> allPieces;
  private RRPiece[] secondaryPieces;
  private RRPiece[] mainPieces;
  
}
