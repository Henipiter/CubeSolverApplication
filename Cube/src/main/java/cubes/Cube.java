package cubes;


import DTOs.InspectMove;
import exceptions.IncorectMoveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import static java.util.Arrays.deepEquals;

public class Cube {

    private Logger logger = Logger.getLogger("Cube");
    private char[][] cube;
    private char[] center;
    /** Make single move (e.g. R', R, R2) on cube
     * If incorrect, log a message on console
     * @param direction
     */
    public void moveUsingString(String direction){}
    public void move(InspectMove inspectMove){}

    protected void changeFourFields(char[][] cube, int side, char[] fieldsOrder){
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


    public char[][] getCube() {
        return cube;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == this) {
//            return true;
//        }
//
//        if (o instanceof Cube2x2) {
//            Cube2x2 c = (Cube2x2) o;
//            return deepEquals(c.cube,this.cube);
//        }
//        if (o instanceof Cube4x4) {
//            Cube4x4 c = (Cube4x4) o;
//            return deepEquals(c.cube,this.cube);
//        }
//        return false;
//
//    }

}
