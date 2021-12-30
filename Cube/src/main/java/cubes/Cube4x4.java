package cubes;

import DTOs.Move;
import DTOs.MoveTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Logger;

import static DTOs.MoveTypeEnum.PRIM;
import static DTOs.MoveTypeEnum.SIMPLE;
import static java.util.Arrays.deepEquals;

@Setter
@Getter
public class Cube4x4 extends Cube {

    private Logger logger = Logger.getLogger("Cube4x4");

    private void initCenters() {
        this.center = new char[]{'w', 'y', 'o', 'r', 'g', 'b'};
    }
    public static Cube4x4 empty(){
        return new Cube4x4(new char[]{'x','x','x','x','x','x'});
    }

    public Cube4x4(char[] center) {
        this.cube = new char[6][16];
        this.center = center;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 16; j++)
                cube[i][j] = center[i];
    }

    public Cube4x4() {
        this.cube = new char[6][16];
        initCenters();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 16; j++)
                cube[i][j] = center[i];
    }

    public Cube4x4(char[][] cube) {
        this.cube = cube;
        initCenters();
    }

    private void rotateSide(boolean clockwise, int side) {
        if (clockwise) {
            changeFourFields(cube, side, new int[]{0, 12, 15, 3});
            changeFourFields(cube, side, new int[]{1, 8, 14, 7});
            changeFourFields(cube, side, new int[]{2, 4, 13, 11});
            changeFourFields(cube, side, new int[]{5, 9, 10, 6});
        } else {
            changeFourFields(cube, side, new int[]{0, 3, 15, 12});
            changeFourFields(cube, side, new int[]{1, 7, 14, 8});
            changeFourFields(cube, side, new int[]{2, 11, 13, 4});
            changeFourFields(cube, side, new int[]{5, 6, 10, 9});
        }
    }

    public void rotateCenter(int[] order) {
        char buffer;
        buffer = center[order[0]];
        for (int i = 0; i < 3; i++)
            center[order[i]] = center[order[i + 1]];
        center[order[3]] = buffer;
    }

    private void moveElementary(int[] sideOrder, int[][] field) {
        char[] buffer = new char[4];
        for (int i = 0; i < 4; i++)
            buffer[i] = cube[sideOrder[0]][field[1][i]];
        for (int i = 0; i < 4; i++)
            cube[sideOrder[0]][field[1][i]] = cube[sideOrder[1]][field[0][i]];
        for (int i = 0; i < 4; i++)
            cube[sideOrder[1]][field[0][i]] = cube[sideOrder[2]][field[1][3 - i]];
        for (int i = 0; i < 4; i++)
            cube[sideOrder[2]][field[1][i]] = cube[sideOrder[3]][field[0][i]];
        for (int i = 0; i < 4; i++)
            cube[sideOrder[3]][field[0][3 - i]] = buffer[i];
    }

    private void moveR(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(true, 3);
                moveElementary(new int[]{4, 0, 5, 1}, new int[][]{{3, 7, 11, 15}, {3, 7, 11, 15}});
                break;
            case DOUBLE:
                moveR(SIMPLE);
                moveR(SIMPLE);
                break;
            case SIMPLE:
                rotateSide(false, 3);
                moveElementary(new int[]{0, 4, 1, 5}, new int[][]{{3, 7, 11, 15}, {3, 7, 11, 15}});
                break;
        }
    }

    private void moveRIn(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{4, 0, 5, 1}, new int[][]{{2, 6, 10, 14}, {2, 6, 10, 14}});
                break;
            case DOUBLE:
                moveRIn(SIMPLE);
                moveRIn(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{0, 4, 1, 5}, new int[][]{{2, 6, 10, 14}, {2, 6, 10, 14}});
                break;
        }
    }

    private void moveL(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(false, 2);
                moveElementary(new int[]{0, 4, 1, 5}, new int[][]{{0, 4, 8, 12}, {0, 4, 8, 12}});
                break;
            case DOUBLE:
                moveL(SIMPLE);
                moveL(SIMPLE);
                break;
            case SIMPLE:
                rotateSide(true, 2);
                moveElementary(new int[]{4, 0, 5, 1}, new int[][]{{0, 4, 8, 12}, {0, 4, 8, 12}});
                break;
        }
    }

    private void moveLIn(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{0, 4, 1, 5}, new int[][]{{1, 5, 9, 13}, {1, 5, 9, 13}});
                break;
            case DOUBLE:
                moveLIn(SIMPLE);
                moveLIn(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{4, 0, 5, 1}, new int[][]{{1, 5, 9, 13}, {1, 5, 9, 13}});
                break;
        }
    }

    private void moveU(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(false, 0);
                moveElementary(new int[]{4, 2, 5, 3}, new int[][]{{0, 1, 2, 3}, {0, 1, 2, 3}});
                break;
            case DOUBLE:
                moveU(SIMPLE);
                moveU(SIMPLE);
                break;
            case SIMPLE:
                rotateSide(true, 0);
                moveElementary(new int[]{2, 4, 3, 5}, new int[][]{{0, 1, 2, 3}, {0, 1, 2, 3}});
                break;
        }
    }

    private void moveUIn(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{4, 2, 5, 3}, new int[][]{{4, 5, 6, 7}, {4, 5, 6, 7}});
                break;
            case DOUBLE:
                moveUIn(SIMPLE);
                moveUIn(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{2, 4, 3, 5}, new int[][]{{4, 5, 6, 7}, {4, 5, 6, 7}});
                break;
        }
    }

    private void moveD(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(true, 1);
                moveElementary(new int[]{2, 4, 3, 5}, new int[][]{{12, 13, 14, 15}, {12, 13, 14, 15}});
                break;
            case DOUBLE:
                moveD(SIMPLE);
                moveD(SIMPLE);
                break;
            case SIMPLE:
                rotateSide(false, 1);
                moveElementary(new int[]{4, 2, 5, 3}, new int[][]{{12, 13, 14, 15}, {12, 13, 14, 15}});
                break;
        }
    }

    private void moveDIn(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{2, 4, 3, 5}, new int[][]{{8, 9, 10, 11}, {8, 9, 10, 11}});
                break;
            case DOUBLE:
                moveDIn(SIMPLE);
                moveDIn(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{4, 2, 5, 3}, new int[][]{{8, 9, 10, 11}, {8, 9, 10, 11}});
                break;
        }
    }

    private void moveF(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(false, 4);
                moveElementary(new int[]{2, 0, 3, 1}, new int[][]{{15, 14, 13, 12}, {3, 7, 11, 15}});
                break;
            case DOUBLE:
                moveF(SIMPLE);
                moveF(SIMPLE);
                break;
            case SIMPLE:
                rotateSide(true, 4);
                moveElementary(new int[]{0, 2, 1, 3}, new int[][]{{3, 7, 11, 15}, {15, 14, 13, 12}});
                break;
        }
    }

    private void moveFIn(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{2, 0, 3, 1}, new int[][]{{11, 10, 9, 8}, {2, 6, 10, 14}});
                break;
            case DOUBLE:
                moveFIn(SIMPLE);
                moveFIn(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{0, 2, 1, 3}, new int[][]{{2, 6, 10, 14}, {11, 10, 9, 8}});
                break;
        }
    }

    private void moveB(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(true, 5);
                moveElementary(new int[]{0, 2, 1, 3}, new int[][]{{12, 8, 4, 0}, {0, 1, 2, 3}});
                break;
            case DOUBLE:
                moveB(SIMPLE);
                moveB(SIMPLE);
                break;
            case SIMPLE:
                rotateSide(false, 5);
                moveElementary(new int[]{2, 0, 3, 1}, new int[][]{{0, 1, 2, 3}, {12, 8, 4, 0}});
                break;
        }
    }

    private void moveBIn(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary(new int[]{0, 2, 1, 3}, new int[][]{{13, 9, 5, 1}, {4, 5, 6, 7}});
                break;
            case DOUBLE:
                moveBIn(SIMPLE);
                moveBIn(SIMPLE);
                break;
            case SIMPLE:
                moveElementary(new int[]{2, 0, 3, 1}, new int[][]{{4, 5, 6, 7}, {13, 9, 5, 1}});
                break;
        }
    }

    private void moveX(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveR(PRIM);
                moveM(SIMPLE);
                moveL(SIMPLE);
                break;
            case DOUBLE:
                moveX(SIMPLE);
                moveX(SIMPLE);
                break;
            case SIMPLE:
                moveR(SIMPLE);
                moveM(PRIM);
                moveL(PRIM);
                break;
        }
    }

    private void moveY(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveU(PRIM);
                moveE(SIMPLE);
                moveD(SIMPLE);
                break;
            case DOUBLE:
                moveY(SIMPLE);
                moveY(SIMPLE);
                break;
            case SIMPLE:
                moveU(SIMPLE);
                moveE(PRIM);
                moveD(PRIM);
                break;
        }
    }

    private void moveZ(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveF(PRIM);
                moveS(PRIM);
                moveB(SIMPLE);
                break;
            case DOUBLE:
                moveZ(SIMPLE);
                moveZ(SIMPLE);
                break;
            case SIMPLE:
                moveF(SIMPLE);
                moveS(SIMPLE);
                moveB(PRIM);
                break;
        }
    }

    private void moveRTwin(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveR(PRIM);
                moveRIn(PRIM);
                break;
            case DOUBLE:
                moveRTwin(SIMPLE);
                moveRTwin(SIMPLE);
                break;
            case SIMPLE:
                moveR(SIMPLE);
                moveRIn(SIMPLE);
                break;
        }
    }

    private void moveLTwin(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveL(PRIM);
                moveLIn(PRIM);
                break;
            case DOUBLE:
                moveLTwin(SIMPLE);
                moveLTwin(SIMPLE);
                break;
            case SIMPLE:
                moveL(SIMPLE);
                moveLIn(SIMPLE);
                break;
        }
    }

    private void moveUTwin(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveU(PRIM);
                moveUIn(PRIM);
                break;
            case DOUBLE:
                moveUTwin(SIMPLE);
                moveUTwin(SIMPLE);
                break;
            case SIMPLE:
                moveU(SIMPLE);
                moveUIn(SIMPLE);
                break;
        }
    }

    private void moveDTwin(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveD(PRIM);
                moveDIn(PRIM);
                break;
            case DOUBLE:
                moveDTwin(SIMPLE);
                moveDTwin(SIMPLE);
                break;
            case SIMPLE:
                moveD(SIMPLE);
                moveDIn(SIMPLE);
                break;
        }
    }

    private void moveFTwin(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveF(PRIM);
                moveFIn(PRIM);
                break;
            case DOUBLE:
                moveFTwin(SIMPLE);
                moveFTwin(SIMPLE);
                break;
            case SIMPLE:
                moveF(SIMPLE);
                moveFIn(SIMPLE);
                break;
        }
    }

    private void moveBTwin(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveB(PRIM);
                moveBIn(PRIM);
                break;
            case DOUBLE:
                moveBTwin(SIMPLE);
                moveBTwin(SIMPLE);
                break;
            case SIMPLE:
                moveB(SIMPLE);
                moveBIn(SIMPLE);
                break;
        }
    }

    private void moveM(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateCenter(new int[]{0, 4, 1, 5});
                moveRIn(SIMPLE);
                moveLIn(PRIM);
                break;
            case DOUBLE:
                moveM(SIMPLE);
                moveM(SIMPLE);
                break;
            case SIMPLE:
                rotateCenter(new int[]{0, 5, 1, 4});
                moveRIn(PRIM);
                moveLIn(SIMPLE);
                break;
        }
    }

    private void moveS(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateCenter(new int[]{0, 3, 1, 2});
                moveFIn(PRIM);
                moveBIn(SIMPLE);
                break;
            case DOUBLE:
                moveS(SIMPLE);
                moveS(SIMPLE);
                break;
            case SIMPLE:
                rotateCenter(new int[]{3, 0, 2, 1});
                moveFIn(SIMPLE);
                moveBIn(PRIM);
                break;
        }
    }

    private void moveE(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateCenter(new int[]{2, 4, 3, 5});
                moveUIn(SIMPLE);
                moveDIn(PRIM);
                break;
            case DOUBLE:
                moveE(SIMPLE);
                moveE(SIMPLE);
                break;
            case SIMPLE:
                rotateCenter(new int[]{4, 2, 5, 3});
                moveUIn(PRIM);
                moveDIn(SIMPLE);
                break;
        }
    }

    @Override
    public void move(Move move) {
        MoveTypeEnum moveType = move.getMoveTypeEnum();
        switch (move.getMoveEnum()) {
            case R:
                moveR(moveType);
                break;
            case L:
                moveL(moveType);
                break;
            case U:
                moveU(moveType);
                break;
            case D:
                moveD(moveType);
                break;
            case F:
                moveF(moveType);
                break;
            case B:
                moveB(moveType);
                break;
            case r:
                moveRIn(moveType);
                break;
            case l:
                moveLIn(moveType);
                break;
            case u:
                moveUIn(moveType);
                break;
            case d:
                moveDIn(moveType);
                break;
            case f:
                moveFIn(moveType);
                break;
            case b:
                moveBIn(moveType);
                break;
            case Rw:
                moveRTwin(moveType);
                break;
            case Lw:
                moveLTwin(moveType);
                break;
            case Uw:
                moveUTwin(moveType);
                break;
            case Dw:
                moveDTwin(moveType);
                break;
            case Fw:
                moveFTwin(moveType);
                break;
            case Bw:
                moveBTwin(moveType);
                break;
            case x:
                moveX(moveType);
                break;
            case y:
                moveY(moveType);
                break;
            case z:
                moveZ(moveType);
                break;
            case M:
                moveM(moveType);
                break;
            case S:
                moveS(moveType);
                break;
            case E:
                moveE(moveType);
                break;
            case BLANK:
                break;
            default:
                logger.info("Cannot do \"" + move.getMoveEnum().toString() + "\" move");
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cube4x4)) {
            return false;
        }
        Cube4x4 c = (Cube4x4) o;
        return deepEquals(c.cube, this.cube);
    }
}
