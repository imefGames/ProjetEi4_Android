package istia.ei4.pm.ia.ricochet;

import istia.ei4.pm.ia.IGameMove;

/**
 *
 * @author Pierre Michel
 * @author Alain Caillaud
 */
public class RRGameMove implements IGameMove{
  
  public RRGameMove(RRPiece actor, ERRGameMove move){
    this.actor = actor;
    this.move = move;
  }
  
  public int getColor(){
    return this.actor.getColor();
  }
  
  public int getDirection(){
    return this.move.getDirection();
  }
  
  public ERRGameMove getMove(){
    return move;
  }
  
  @Override
  public String toString(){
    return String.format("%d -> %s", this.actor.getId(), this.move.toString());
  }
  
  private RRPiece actor;
  private ERRGameMove move;
}
