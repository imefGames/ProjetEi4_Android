package istia.ei4.pm.ia.ricochet;

import istia.ei4.ProjetISTIA.FileReadWrite;
import istia.ei4.ProjetISTIA.GridElement;
import istia.ei4.ProjetISTIA.MapObjects;
import istia.ei4.pm.ia.*;
import java.util.ArrayList;


public class MainIA {

	public static void main(String[] args) {
    
        RRWorld world = null;
        String text = null;
        RRGameState baseState = new RRGameState(null, null);
        FileReadWrite myFile = new FileReadWrite();
        text = myFile.read("generatedMap_153.txt");
        ArrayList<GridElement> elements = MapObjects.extractDataFromString(text);
        world = RRGetMap.createWorld(elements, baseState);

        RREndCondition endCondition = new RREndCondition();
        world.precomputeGrid();

        RRSolver solver = new RRSolver(10, world, baseState, endCondition);

        GameSolution solution = solver.solve();

        if(solution == null){
            //System.out.println("No Solution!");
        }else{
            for(IGameMove m : solution.getMoves()){
                System.out.println(m);
            }
        }
    }
}
