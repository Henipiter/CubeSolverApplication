package cubes;


import DTOs.Move;
import DTOs.MoveTypeEnum;

import java.util.logging.Logger;

import static DTOs.MoveTypeEnum.SIMPLE;
import static java.util.Arrays.deepEquals;

public class Cube1x1 extends Cube {

    char[][] cube = new char[6][1];
    char[] center = new char[]{'w', 'y', 'o', 'r', 'g', 'b'};
    private Logger logger = Logger.getLogger("Cube1x1");

    public Cube1x1() {
        for (int i = 0; i < 6; i++)
            cube[i][0] = center[i];
    }

    public Cube1x1(char[][] cube) {
        this.cube = cube;
    }


    private void moveElementary(int[] sideOrder) {
        char buffer;
        buffer = cube[sideOrder[0]][0];
        cube[sideOrder[0]][0] = cube[sideOrder[1]][0];
        cube[sideOrder[1]][0] = cube[sideOrder[2]][0];
        cube[sideOrder[2]][0] = cube[sideOrder[3]][0];
        cube[sideOrder[3]][0] = buffer;
    }

    private void moveX(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{4, 0, 5, 1});
                break;
            case DOUBLE:
                moveX(SIMPLE);
                moveX(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{0, 4, 1, 5});
                break;
        }
    }

    private void moveY(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{4, 2, 5, 3});
                break;
            case DOUBLE:
                moveY(SIMPLE);
                moveY(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{2, 4, 3, 5});
                break;
        }
    }

    private void moveZ(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{2, 0, 3, 1});
                break;
            case DOUBLE:
                moveZ(SIMPLE);
                moveZ(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{0, 2, 1, 3});
                break;
        }
    }

    @Override
    public void move(Move move) {
        MoveTypeEnum moveType = move.getMoveTypeEnum();
        switch (move.getMoveEnum()) {
            case x:
                moveX(moveType);
                break;
            case y:
                moveY(moveType);
                break;
            case z:
                moveZ(moveType);
                break;
            case BLANK:
                break;
            default:
                logger.info("Cannot do \"" + move.getMoveEnum().toString() + "\" move");
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
        return deepEquals(c.cube, this.cube);
    }
}
