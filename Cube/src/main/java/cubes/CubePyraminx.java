package cubes;

import DTOs.Move;
import DTOs.MoveTypeEnum;

import java.util.ArrayList;
import java.util.logging.Logger;

import static DTOs.MoveTypeEnum.*;
import static java.util.Arrays.deepEquals;

public class CubePyraminx extends Cube {

    private Logger logger = Logger.getLogger("CubePyraminx");

    public CubePyraminx() {
        this.cube = new char[4][9];
        initCenters();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 9; j++)
                cube[i][j] = center[i];
    }

    private void initCenters() {
        this.center = new char[]{'g', 'y', 'r', 'b'};
    }

    public CubePyraminx(char[][] cube) {
        this.cube = cube;
        this.center = new char[]{'g', 'y', 'r', 'b'};
    }

    public void rotateCube(boolean clockwise, int side) {
        if (clockwise) {
            changeFourFields(cube, side, new int[]{0, 8, 4});
            changeFourFields(cube, side, new int[]{1, 3, 6});
            changeFourFields(cube, side, new int[]{2, 7, 5});
        } else {
            changeFourFields(cube, side, new int[]{0, 4, 8});
            changeFourFields(cube, side, new int[]{1, 6, 3});
            changeFourFields(cube, side, new int[]{2, 5, 7});
        }
    }

    public void rotateSide(int[] order) {
        char buffer;
        buffer = center[order[0]];
        for (int i = 0; i < order.length - 1; i++)
            center[order[i]] = center[order[i + 1]];
        center[order[order.length - 1]] = buffer;
    }

    public void moveElementary_1(int[] sideOrder, int[] field) {
        char buffer = cube[sideOrder[0]][field[0]];
        cube[sideOrder[0]][field[0]] = cube[sideOrder[1]][field[1]];
        cube[sideOrder[1]][field[1]] = cube[sideOrder[2]][field[2]];
        cube[sideOrder[2]][field[2]] = buffer;
    }

    public void moveElementary_3(int[] sideOrder, int[][] field) {
        char[] buffer = new char[field[0].length];
        for (int i = 0; i < field[0].length; i++) {
            buffer[i] = cube[sideOrder[0]][field[0][i]];
            cube[sideOrder[0]][field[0][i]] = cube[sideOrder[1]][field[1][i]];
            cube[sideOrder[1]][field[1][i]] = cube[sideOrder[2]][field[2][i]];
            cube[sideOrder[2]][field[2][i]] = buffer[i];
        }
    }

    private void moveU(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveu(PRIM);
                moveElementary_3(new int[]{0, 2, 3},
                        new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}});
                break;
            case SIMPLE:
                moveu(SIMPLE);
                moveElementary_3(new int[]{0, 3, 2},
                        new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}});
                break;
        }
    }

    private void moveR(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                mover(PRIM);
                moveElementary_3(new int[]{0, 3, 1},
                        new int[][]{{3, 7, 6}, {6, 5, 1}, {6, 5, 1}});
                break;
            case SIMPLE:
                mover(SIMPLE);
                moveElementary_3(new int[]{0, 1, 3},
                        new int[][]{{3, 7, 6}, {6, 5, 1}, {6, 5, 1}});
                break;
        }
    }

    private void moveL(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                movel(PRIM);
                moveElementary_3(new int[]{0, 1, 2},
                        new int[][]{{1, 5, 6}, {6, 7, 3}, {6, 7, 3}});
                break;
            case SIMPLE:
                movel(SIMPLE);
                moveElementary_3(new int[]{0, 2, 1},
                        new int[][]{{1, 5, 6}, {6, 7, 3}, {6, 7, 3}});
                break;
        }
    }

    private void moveB(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveb(PRIM);
                moveElementary_3(new int[]{3, 2, 1},
                        new int[][]{{3, 7, 6}, {6, 5, 1}, {1, 2, 3}});
                break;
            case SIMPLE:
                moveb(SIMPLE);
                moveElementary_3(new int[]{3, 1, 2},
                        new int[][]{{3, 7, 6}, {1, 2, 3}, {6, 5, 1}});
                break;
        }
    }

    private void moveUw(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(new int[]{0, 2, 3});
                moveU(PRIM);
                rotateCube(false, 1);
                moveElementary_3(new int[]{0, 2, 3},
                        new int[][]{{4, 5, 6, 7, 8}, {4, 5, 6, 7, 8}, {4, 5, 6, 7, 8}});
                break;
            case SIMPLE:
                rotateSide(new int[]{0, 3, 2});
                moveU(SIMPLE);
                rotateCube(true, 1);
                moveElementary_3(new int[]{0, 3, 2},
                        new int[][]{{4, 5, 6, 7, 8}, {4, 5, 6, 7, 8}, {4, 5, 6, 7, 8}});
                break;
        }
    }

    private void moveRw(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(new int[]{0, 3, 1});
                moveR(PRIM);
                rotateCube(false, 2);
                moveElementary_3(new int[]{0, 3, 1},
                        new int[][]{{0, 2, 1, 5, 4}, {8, 7, 3, 2, 0}, {8, 7, 3, 2, 0}});
                break;
            case SIMPLE:
                rotateSide(new int[]{0, 1, 3});
                moveR(SIMPLE);
                rotateCube(true, 2);
                moveElementary_3(new int[]{0, 1, 3},
                        new int[][]{{0, 2, 1, 5, 4}, {8, 7, 3, 2, 0}, {8, 7, 3, 2, 0}});
                break;
        }
    }

    private void moveLw(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(new int[]{0, 1, 2});
                moveL(PRIM);
                rotateCube(false, 3);
                moveElementary_3(new int[]{0, 1, 2},
                        new int[][]{{0, 2, 3, 7, 8}, {4, 5, 1, 2, 0}, {4, 5, 1, 2, 0}});
                break;
            case SIMPLE:
                rotateSide(new int[]{0, 2, 1});
                moveL(SIMPLE);
                rotateCube(true, 3);
                moveElementary_3(new int[]{0, 2, 1},
                        new int[][]{{0, 2, 3, 7, 8}, {4, 5, 1, 2, 0}, {4, 5, 1, 2, 0}});

                break;
        }
    }

    private void moveBw(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                rotateSide(new int[]{3, 2, 1});
                moveB(PRIM);
                rotateCube(false, 0);
                moveElementary_3(new int[]{3, 2, 1},
                        new int[][]{{0, 2, 1, 5, 4}, {8, 7, 3, 2, 0}, {4, 5, 6, 7, 8},});
                break;
            case SIMPLE:
                rotateSide(new int[]{3, 1, 2});
                moveB(SIMPLE);
                rotateCube(true, 0);
                moveElementary_3(new int[]{3, 1, 2},
                        new int[][]{{0, 2, 1, 5, 4}, {4, 5, 6, 7, 8}, {8, 7, 3, 2, 0}});
                break;
        }
    }

    private void moveu(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary_1(new int[]{0, 2, 3}, new int[]{0, 0, 0});
                break;
            case SIMPLE:
                moveElementary_1(new int[]{0, 3, 2}, new int[]{0, 0, 0});
                break;
        }
    }

    private void mover(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary_1(new int[]{0, 3, 1}, new int[]{8, 4, 4});
                break;
            case SIMPLE:
                moveElementary_1(new int[]{0, 1, 3}, new int[]{8, 4, 4});
                break;
        }
    }

    private void movel(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary_1(new int[]{0, 1, 2}, new int[]{4, 8, 8});
                break;
            case SIMPLE:
                moveElementary_1(new int[]{0, 2, 1}, new int[]{4, 8, 8});
                break;
        }
    }

    private void moveb(MoveTypeEnum moveType) {
        switch (moveType) {
            case PRIM:
                moveElementary_1(new int[]{3, 2, 1}, new int[]{8, 4, 0});
                break;
            case SIMPLE:
                moveElementary_1(new int[]{3, 1, 2}, new int[]{8, 0, 4});
                break;
        }
    }

    @Override
    public void move(String direction) {
        Move move = new Move(direction);
        if (move.getMoveTypeEnum() == INVALID)
            logger.info("Cannot do \"" + direction + "\" move");
        else {
            move(move);
        }
    }

    @Override
    public void move(Move move) {
        MoveTypeEnum moveType = move.getMoveTypeEnum();
        switch (move.getMoveEnum()) {
            case U:
                moveU(moveType);
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
            case Uw:
                moveUw(moveType);
                break;
            case Bw:
                moveBw(moveType);
                break;
            case Rw:
                moveRw(moveType);
                break;
            case Lw:
                moveLw(moveType);
                break;
            case r:
                mover(moveType);
                break;
            case l:
                movel(moveType);
                break;
            case b:
                moveb(moveType);
                break;
            case u:
                moveu(moveType);
                break;
            case BLANK:
                break;
            default:
                logger.info("Cannot do \"" + move.getMoveEnum().toString() + "\" move");
                break;
        }
    }

    public void makeMoves(ArrayList<Move> algorithm) {
        for (Move move : algorithm) {
            move(move);
        }
    }

    public void makeMoves(String algorithm) {
        String[] splitAlg = algorithm.split(" ");
        for (String move : splitAlg) {
            move(move);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CubePyraminx)) {
            return false;
        }
        CubePyraminx c = (CubePyraminx) o;
        return deepEquals(c.cube, this.cube);
    }

    @Override
    public char[] getCenter() {
        return center;
    }
}
