package istia.ei4.pm.ia.ricochet;

/**
 *
 * @author Pierre Michel
 */
public class RRGridCell {
  
  public RRGridCell(){
    this.directions = 15;
    this.precomputedNumber = 0;
  }
  
  public void setWall(ERRGameMove move, boolean state){
    if(state){
      this.directions |= move.getDirection();
    }else{
      this.directions &= ~move.getDirection();
    }
  }
  
  public boolean getWall(ERRGameMove move){
    return (this.directions & move.getDirection()) != 0;
  }
  
  public boolean doMove(RRPiece pos, ERRGameMove move){
    boolean didMove = this.getWall(move);
    if(didMove){
      switch(move.getDirection()){
        case 1: //UP
          pos.decY();
          break;
        case 2: //RIGHT
          pos.incX();
          break;
        case 4: //DOWN
          pos.incY();
          break;
        case 8: //LEFT
          pos.decX();
          break;
      }
    }
    return didMove;
  }
  
  public void putPiece(boolean state){
    if(state){
      this.directions |= 16;
    }else{
      this.directions &= ~16;
    }
  }
  
  public boolean hasPiece(){
    return (this.directions & 16) != 0;
  }
  
  public int getPrecomutedNumber(){
    return this.precomputedNumber;
  }
  
  public void setPrecomutedNumber(int precomputedNumber){
    this.precomputedNumber = precomputedNumber;
  }
  
  private int directions, precomputedNumber = 0;
  
}
