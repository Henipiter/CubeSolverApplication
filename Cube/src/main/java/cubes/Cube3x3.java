package cubes;

import DTOs.InspectMove;
import DTOs.MoveTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Logger;

import static DTOs.MoveTypeEnum.*;
import static java.util.Arrays.deepEquals;

@Getter
@Setter
public class Cube3x3 extends Cube {

    char[][] cube = new char[6][8];
    char[] center = new char[]{'w', 'y', 'o', 'r', 'g', 'b'};
    private Logger logger = Logger.getLogger("Cube3x3");

    public Cube3x3() {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 8; j++)
                cube[i][j] = center[i];
    }

    public Cube3x3(char[][] cube) {
        this.cube = cube;
    }

    public void rotate(boolean clockwise, int side) {
        if (clockwise) {
            changeFourFields(cube, side, new int[]{0, 5, 7, 2});
            changeFourFields(cube, side, new int[]{1, 3, 6, 4});
        } else {
            changeFourFields(cube, side, new int[]{0, 2, 7, 5});
            changeFourFields(cube, side, new int[]{1, 4, 6, 3});
        }
    }

    public void rotateCenter(int[] order) {
        char buffer;
        buffer = center[order[0]];
        for (int i = 0; i < 3; i++)
            center[order[i]] = center[order[i + 1]];
        center[order[3]] = buffer;
    }

    public void moveElementary_2(int[] sideOrder, int[][] field) {
        char[] buffer = new char[2];
        for (int i = 0; i < 2; i++)
            buffer[i] = cube[sideOrder[0]][field[0][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[0]][field[0][i]] = cube[sideOrder[1]][field[1][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[1]][field[1][i]] = cube[sideOrder[2]][field[0][1 - i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[2]][field[0][i]] = cube[sideOrder[3]][field[1][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[3]][field[1][1 - i]] = buffer[i];
    }

    public void moveElementary_3(int[] sideOrder, int[][] field) {
        char[] buffer = new char[3];
        for (int i = 0; i < 3; i++)
            buffer[i] = cube[sideOrder[0]][field[1][i]];
        for (int i = 0; i < 3; i++)
            cube[sideOrder[0]][field[1][i]] = cube[sideOrder[1]][field[0][i]];
        for (int i = 0; i < 3; i++)
            cube[sideOrder[1]][field[0][i]] = cube[sideOrder[2]][field[1][2 - i]];
        for (int i = 0; i < 3; i++)
            cube[sideOrder[2]][field[1][i]] = cube[sideOrder[3]][field[0][i]];
        for (int i = 0; i < 3; i++)
            cube[sideOrder[3]][field[0][2 - i]] = buffer[i];
    }

    private void moveM(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateCenter(new int[]{0, 4, 1, 5});
                moveElementary_2(new int[]{0, 4, 1, 5}, new int[][]{{1, 6}, {1, 6}});
                break;
            case DOUBLE:
                moveM(SIMPLE);
                moveM(SIMPLE);
                break;
            case SIMPLE:
                rotateCenter(new int[]{0, 5, 1, 4});
                moveElementary_2(new int[]{4, 0, 5, 1}, new int[][]{{1, 6}, {1, 6}});
                break;
        }
    }

    private void moveS(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateCenter(new int[]{0, 3, 1, 2});
                moveElementary_2(new int[]{0, 3, 1, 2}, new int[][]{{3, 4}, {1, 6}});
                break;
            case DOUBLE:
                moveS(SIMPLE);
                moveS(SIMPLE);
                break;
            case SIMPLE:
                rotateCenter(new int[]{3, 0, 2, 1});
                moveElementary_2(new int[]{3, 0, 2, 1}, new int[][]{{1, 6}, {3, 4}});
                break;
        }
    }

    private void moveE(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateCenter(new int[]{2, 4, 3, 5});
                moveElementary_2(new int[]{2, 4, 3, 5}, new int[][]{{3, 4}, {3, 4}});
                break;
            case DOUBLE:
                moveE(SIMPLE);
                moveE(SIMPLE);
                break;
            case SIMPLE:
                rotateCenter(new int[]{4, 2, 5, 3});
                moveElementary_2(new int[]{4, 2, 5, 3}, new int[][]{{3, 4}, {3, 4}});
                break;
        }
    }

    private void moveU(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotate(false, 0);
                moveElementary_3(new int[]{4, 2, 5, 3}, new int[][]{{0, 1, 2}, {0, 1, 2}});
                break;
            case DOUBLE:
                moveU(SIMPLE);
                moveU(SIMPLE);
                break;
            case SIMPLE:
                rotate(true, 0);
                moveElementary_3(new int[]{2, 4, 3, 5}, new int[][]{{0, 1, 2}, {0, 1, 2}});
                break;
        }
    }

    private void moveD(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotate(true, 1);
                moveElementary_3(new int[]{2, 4, 3, 5}, new int[][]{{5, 6, 7}, {5, 6, 7}});
                break;
            case DOUBLE:
                moveD(SIMPLE);
                moveD(SIMPLE);
                break;
            case SIMPLE:
                rotate(false, 1);
                moveElementary_3(new int[]{4, 2, 5, 3}, new int[][]{{5, 6, 7}, {5, 6, 7}});
                break;
        }
    }

    private void moveF(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotate(false, 4);
                moveElementary_3(new int[]{2, 0, 3, 1}, new int[][]{{7, 6, 5}, {2, 4, 7}});
                break;
            case DOUBLE:
                moveF(SIMPLE);
                moveF(SIMPLE);
                break;
            case SIMPLE:
                rotate(true, 4);
                moveElementary_3(new int[]{0, 2, 1, 3}, new int[][]{{2, 4, 7}, {7, 6, 5}});
                break;
        }
    }

    private void moveB(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotate(true, 5);
                moveElementary_3(new int[]{0, 2, 1, 3}, new int[][]{{5, 3, 0}, {0, 1, 2}});
                break;
            case DOUBLE:
                moveB(SIMPLE);
                moveB(SIMPLE);
                break;
            case SIMPLE:
                rotate(false, 5);
                moveElementary_3(new int[]{2, 0, 3, 1}, new int[][]{{0, 1, 2}, {5, 3, 0}});
                break;
        }
    }

    private void moveR(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotate(true, 3);
                moveElementary_3(new int[]{4, 0, 5, 1}, new int[][]{{2, 4, 7}, {2, 4, 7}});
                break;
            case DOUBLE:
                moveR(SIMPLE);
                moveR(SIMPLE);
                break;
            case SIMPLE:
                rotate(false, 3);
                moveElementary_3(new int[]{0, 4, 1, 5}, new int[][]{{2, 4, 7}, {2, 4, 7}});
                break;
        }
    }

    private void moveL(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotate(false, 2);
                moveElementary_3(new int[]{0, 4, 1, 5}, new int[][]{{0, 3, 5}, {0, 3, 5}});
                break;
            case DOUBLE:
                moveL(SIMPLE);
                moveL(SIMPLE);
                break;
            case SIMPLE:
                rotate(true, 2);
                moveElementary_3(new int[]{4, 0, 5, 1}, new int[][]{{0, 3, 5}, {0, 3, 5}});
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

    private void mover(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveR(PRIM);
                moveM(SIMPLE);
                break;
            case DOUBLE:
                moveR(DOUBLE);
                moveM(DOUBLE);
                break;
            case SIMPLE:
                moveR(SIMPLE);
                moveM(PRIM);
                break;
        }
    }

    private void movel(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveL(PRIM);
                moveM(PRIM);
                break;
            case DOUBLE:
                moveL(DOUBLE);
                moveM(DOUBLE);
                break;
            case SIMPLE:
                moveL(SIMPLE);
                moveM(SIMPLE);
                break;
        }
    }

    private void movef(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveF(PRIM);
                moveS(PRIM);
                break;
            case DOUBLE:
                moveF(DOUBLE);
                moveS(DOUBLE);
                break;
            case SIMPLE:
                moveF(SIMPLE);
                moveS(SIMPLE);
                break;
        }
    }

    private void moveb(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveB(PRIM);
                moveS(SIMPLE);
                break;
            case DOUBLE:
                moveB(DOUBLE);
                moveS(DOUBLE);
                break;
            case SIMPLE:
                moveB(SIMPLE);
                moveS(PRIM);
                break;
        }
    }

    private void moveu(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveU(PRIM);
                moveE(SIMPLE);
                break;
            case DOUBLE:
                moveU(DOUBLE);
                moveE(DOUBLE);
                break;
            case SIMPLE:
                moveU(SIMPLE);
                moveE(PRIM);
                break;
        }
    }

    private void moved(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveD(PRIM);
                moveE(PRIM);
                break;
            case DOUBLE:
                moveD(DOUBLE);
                moveE(DOUBLE);
                break;
            case SIMPLE:
                moveD(SIMPLE);
                moveE(SIMPLE);
                break;
        }
    }

    @Override
    public void move(InspectMove inspectMove) {
        MoveTypeEnum moveType = inspectMove.getMoveTypeEnum();
        switch (inspectMove.getMoveEnum()) {
            case M:
                moveM(moveType);
                break;
            case S:
                moveS(moveType);
                break;
            case E:
                moveE(moveType);
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
            case R:
                moveR(moveType);
                break;
            case L:
                moveL(moveType);
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
            case r:
                mover(moveType);
                break;
            case l:
                movel(moveType);
                break;
            case f:
                movef(moveType);
                break;
            case b:
                moveb(moveType);
                break;
            case u:
                moveu(moveType);
                break;
            case d:
                moved(moveType);
                break;
            default:
                logger.info("Cannot do \"" + inspectMove.getMoveEnum().toString() + "\" move");
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cube3x3)) {
            return false;
        }
        Cube3x3 c = (Cube3x3) o;
        return deepEquals(c.cube, this.cube);
    }
}
