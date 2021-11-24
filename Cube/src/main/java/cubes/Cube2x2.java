package cubes;


import DTOs.Move;
import DTOs.MoveTypeEnum;
import lombok.Data;

import java.util.logging.Logger;

import static DTOs.MoveEnum.*;
import static DTOs.MoveTypeEnum.SIMPLE;
import static java.util.Arrays.deepEquals;

@Data
public class Cube2x2 extends Cube {

    private static Logger logger = Logger.getLogger("Cube2x2");

    private void initCenters() {
        this.center = new char[]{'w', 'y', 'o', 'r', 'g', 'b'};
    }

    public Cube2x2() {
        this.cube = new char[6][4];
        initCenters();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                cube[i][j] = center[i];
            }
        }
    }

    public Cube2x2(char[][] cube) {
        this.cube = cube;
        initCenters();
    }

    public void rotateSide(boolean clockwise, int side) {
        if (clockwise) {
            changeFourFields(cube, side, new int[]{0, 2, 3, 1});
        } else {
            changeFourFields(cube, side, new int[]{0, 1, 3, 2});
        }
    }

    private void moveElementary(int[] sideOrder, int[][] field) {
        char[] buffer = new char[2];
        for (int i = 0; i < 2; i++)
            buffer[i] = cube[sideOrder[0]][field[1][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[0]][field[1][i]] = cube[sideOrder[1]][field[0][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[1]][field[0][i]] = cube[sideOrder[2]][field[1][1 - i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[2]][field[1][i]] = cube[sideOrder[3]][field[0][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[3]][field[0][1 - i]] = buffer[i];
    }

    private void moveR(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                rotateSide(true, 3);
                moveElementary(new int[]{4, 0, 5, 1}, new int[][]{{1, 3}, {1, 3}});
                break;
            case DOUBLE:
                moveR(new Move(R, SIMPLE));
                moveR(new Move(R, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(false, 3);
                moveElementary(new int[]{0, 4, 1, 5}, new int[][]{{1, 3}, {1, 3}});
                break;
        }
    }

    private void moveL(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                rotateSide(false, 2);
                moveElementary(new int[]{0, 4, 1, 5}, new int[][]{{0, 2}, {0, 2}});
                break;
            case DOUBLE:
                move.setMoveTypeEnum(MoveTypeEnum.SIMPLE);
                moveL(new Move(L, SIMPLE));
                moveL(new Move(L, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(true, 2);
                moveElementary(new int[]{4, 0, 5, 1}, new int[][]{{0, 2}, {0, 2}});
                break;
        }
    }

    private void moveU(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                rotateSide(false, 0);
                moveElementary(new int[]{4, 2, 5, 3}, new int[][]{{0, 1}, {0, 1}});
                break;
            case DOUBLE:
                moveU(new Move(U, SIMPLE));
                moveU(new Move(U, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(true, 0);
                moveElementary(new int[]{2, 4, 3, 5}, new int[][]{{0, 1}, {0, 1}});
                break;
        }
    }

    private void moveD(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                rotateSide(true, 1);
                moveElementary(new int[]{2, 4, 3, 5}, new int[][]{{2, 3}, {2, 3}});
                break;
            case DOUBLE:
                moveD(new Move(D, SIMPLE));
                moveD(new Move(D, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(false, 1);
                moveElementary(new int[]{4, 2, 5, 3}, new int[][]{{2, 3}, {2, 3}});
                break;
        }
    }

    private void moveF(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                rotateSide(false, 4);
                moveElementary(new int[]{2, 0, 3, 1}, new int[][]{{3, 2}, {1, 3}});
                break;
            case DOUBLE:
                moveF(new Move(F, SIMPLE));
                moveF(new Move(F, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(true, 4);
                moveElementary(new int[]{0, 2, 1, 3}, new int[][]{{1, 3}, {3, 2}});
                break;
        }
    }

    private void moveB(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                rotateSide(true, 5);
                moveElementary(new int[]{0, 2, 1, 3}, new int[][]{{2, 0}, {0, 1}});
                break;
            case DOUBLE:
                moveB(new Move(B, SIMPLE));
                moveB(new Move(B, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(false, 5);
                moveElementary(new int[]{2, 0, 3, 1}, new int[][]{{0, 1}, {2, 0}});
                break;
        }
    }

    private void moveX(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                moveR(new Move("R'"));
                moveL(new Move("L"));
                break;
            case DOUBLE:
                moveX(new Move(x, SIMPLE));
                moveX(new Move(x, SIMPLE));
                break;
            case SIMPLE:
                moveR(new Move("R"));
                moveL(new Move("L'"));
                break;
        }
    }

    private void moveY(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                moveU(new Move("U'"));
                moveD(new Move("D"));
                break;
            case DOUBLE:
                moveY(new Move(y, SIMPLE));
                moveY(new Move(y, SIMPLE));
                break;
            case SIMPLE:
                moveU(new Move("U"));
                moveD(new Move("D'"));
                break;
        }
    }

    private void moveZ(Move move) {
        switch (move.getMoveTypeEnum()) {
            case PRIM:
                moveF(new Move("F'"));
                moveB(new Move("B"));
                break;
            case DOUBLE:
                moveZ(new Move(z, SIMPLE));
                moveZ(new Move(z, SIMPLE));
                break;
            case SIMPLE:
                moveF(new Move("F"));
                moveB(new Move("B'"));
                break;
        }
    }

    @Override
    public void move(Move move) {
        switch (move.getMoveEnum()) {
            case R:
                moveR(move);
                break;
            case L:
                moveL(move);
                break;
            case U:
                moveU(move);
                break;
            case D:
                moveD(move);
                break;
            case F:
                moveF(move);
                break;
            case B:
                moveB(move);
                break;
            case x:
                moveX(move);
                break;
            case y:
                moveY(move);
                break;
            case z:
                moveZ(move);
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
        if (!(o instanceof Cube2x2)) {
            return false;
        }
        Cube2x2 c = (Cube2x2) o;
        return deepEquals(c.cube, this.cube);
    }
}
