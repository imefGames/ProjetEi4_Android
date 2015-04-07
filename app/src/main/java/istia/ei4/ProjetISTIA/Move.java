package istia.ei4.ProjetISTIA;

/**
 * Created by Alain on 25/03/2015.
 */
public class Move {


    private GamePiece _p;
    private int _x;
    private int _y;

    public Move(GamePiece p, int x, int y)
    {
        _p = p;
        _x = x;
        _y = y;
    }

    public void goBack()
    {
        _p.setX(_x);
        _p.setY(_y);
        _p.setxObjective(_x);
        _p.setyObjective(_y);
    }
}
