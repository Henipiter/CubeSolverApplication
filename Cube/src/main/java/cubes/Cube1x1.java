package cubes;


import DTOs.InspectMove;
import DTOs.MoveTypeEnum;

import java.util.logging.Logger;

import static DTOs.MoveEnum.*;
import static DTOs.MoveTypeEnum.SIMPLE;
import static java.util.Arrays.deepEquals;

public class Cube1x1 extends Cube {

    char[][] cube = new char[6][1];
    char[] center = new char[]{ 'w','y','o','r','g','b' };
    private Logger logger = Logger.getLogger("Cube1x1");

    public Cube1x1(){
        for (int i = 0; i < 6; i++)
                cube[i][0] = center[i];
    }

    public Cube1x1(char[][] cube){
        this.cube = cube;
    }


    private void moveElementary(int[] sideOrder){
        char buffer;
        buffer = cube[sideOrder[0]][0];
        cube[sideOrder[0]][0] = cube[sideOrder[1]][0];
        cube[sideOrder[1]][0] = cube[sideOrder[2]][0];
        cube[sideOrder[2]][0] = cube[sideOrder[3]][0];
        cube[sideOrder[3]][0] = buffer;
    }

    private void moveX(InspectMove inspectMove){

        switch (inspectMove.getMoveTypeEnum()){
            case PRIM:
                moveElementary( new int[]{4,0,5,1});
                break;
            case DOUBLE:
                moveX(new InspectMove(x, SIMPLE));
                moveX(new InspectMove(x, SIMPLE));
                break;
            case SIMPLE:
                moveElementary(new int[]{ 0,4,1,5 });
                break;
        }
    }

    private void moveY(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()){
            case PRIM:
                moveElementary( new int[]{4,2,5,3});
                break;
            case DOUBLE:
                moveY(new InspectMove(y, SIMPLE));
                moveY(new InspectMove(y, SIMPLE));
                break;
            case SIMPLE:
                moveElementary(new int[]{ 2,4,3,5});
                break;
        }
    }


    private void moveZ(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()){
            case PRIM:
                moveElementary( new int[]{2,0,3,1});
                break;
            case DOUBLE:
                moveZ(new InspectMove(z, SIMPLE));
                moveZ(new InspectMove(z, SIMPLE));
                break;
            case SIMPLE:
                moveElementary(new int[]{ 0,2,1,3 });
                break;
        }
    }


    @Override
    public void moveUsingString(String direction) {
        InspectMove inspectMove = new InspectMove(direction);
        if(inspectMove.getMoveTypeEnum()== MoveTypeEnum.INVALID)
            logger.info("Cannot do \""+direction+"\" move");
        else{
            move(inspectMove);
        }
    }

    @Override
    public void move(InspectMove inspectMove){
        switch (inspectMove.getMoveEnum()){
            case x:
                moveX(inspectMove);
                break;
            case y:
                moveY(inspectMove);
                break;
            case z:
                moveZ(inspectMove);
                break;
            default:
                logger.info("Cannot do \""+inspectMove.getMoveEnum().toString()+"\" move");
                break;
        }

    }

    public void setCube(char[][] cube) {
        this.cube = cube;
    }

    public char[][] getCube() {
        return cube;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Cube1x1)) {
            return false;
        }
        Cube1x1 c = (Cube1x1) o;
        return deepEquals(c.cube,this.cube);
    }
}
