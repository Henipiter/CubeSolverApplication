package cubes;


import DTOs.InspectMove;
import lombok.Getter;

import java.util.ArrayList;
import java.util.logging.Logger;

import static DTOs.MoveTypeEnum.INVALID;

@Getter
public class Cube {

    private Logger logger = Logger.getLogger("Cube");
    private char[][] cube;
    private char[] center;

    public void moveUsingString(String direction){
        InspectMove inspectMove = new InspectMove(direction);
        if(inspectMove.getMoveTypeEnum()== INVALID)
            logger.info("Cannot do \""+direction+"\" move");
        else {
            move(inspectMove);
        }
    }

    public void move(InspectMove inspectMove){}

    protected void changeFourFields(char[][] cube, int side, int[] fieldsOrder){
        char buffer;
        buffer = cube[side][fieldsOrder[0]];
        for(int i=0;i<fieldsOrder.length-1;i++)
            cube[side][fieldsOrder[i]] = cube[side][fieldsOrder[i+1]];
        cube[side][fieldsOrder[fieldsOrder.length-1]] = buffer;
    }

    public void makeMoves(ArrayList<InspectMove> algorithm){
        for( InspectMove move : algorithm ){
            move(move);
        }
    }

    public void makeMovesUsingString(String algorithm){
        String[] splitAlg = algorithm.split(" ");
        for( String move : splitAlg){
            moveUsingString(move);
        }
    }

    public static boolean isSolved(Cube cube){
        for (int i = 0; i < cube.getCenter().length; i++) {
            for (int j = 0; j < cube.getCube()[0].length; j++) {
                if(cube.getCenter()[i] != cube.getCube()[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

}
