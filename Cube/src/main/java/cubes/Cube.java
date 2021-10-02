package cubes;


import DTOs.InspectMove;
import lombok.Getter;

import java.util.ArrayList;
import java.util.logging.Logger;

@Getter
public class Cube {

    private Logger logger = Logger.getLogger("Cube");
    private char[][] cube;
    private char[] center;

    public void moveUsingString(String direction){}
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
