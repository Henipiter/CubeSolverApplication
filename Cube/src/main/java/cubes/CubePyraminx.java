package cubes;

import DTOs.InspectMove;

import java.util.ArrayList;
import java.util.logging.Logger;

import static DTOs.MoveEnum.*;
import static DTOs.MoveTypeEnum.*;
import static DTOs.MoveTypeEnum.INVALID;
import static java.util.Arrays.deepEquals;

public class CubePyraminx extends Cube {

    char[][] cube = new char[4][9];
    char[] center = new char[]{'g', 'y', 'r', 'b'};
    private Logger logger = Logger.getLogger("CubePyraminx");

    public CubePyraminx() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 9; j++)
                cube[i][j] = center[i];
    }

    public CubePyraminx(char[][] cube) {
        this.cube = cube;
    }

    public void rotate(boolean clockwise, int side) {
        if (clockwise) {
            changeFourFields(cube, side, new int[]{0,8,4});
            changeFourFields(cube, side, new int[]{1,3,6});
            changeFourFields(cube, side, new int[]{2,7,5});
        } else {
            changeFourFields(cube, side, new int[]{0,4,8});
            changeFourFields(cube, side, new int[]{1,6,3});
            changeFourFields(cube, side, new int[]{2,5,7});
        }
    }

    public void rotateCenter(int[] order) {
        char buffer;
        buffer = center[order[0]];
        for (int i = 0; i < 3; i++)
            center[order[i]] = center[order[i + 1]];
        center[order[3]] = buffer;
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

    private void moveU(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveu(new InspectMove(u, PRIM));
                moveElementary_3(new int[]{0, 2, 3},
                        new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}});
                break;
            case SIMPLE:
                moveu(new InspectMove(u, SIMPLE));
                moveElementary_3(new int[]{0, 3, 2},
                        new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}});
                break;
        }
    }

    private void moveR(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                mover(new InspectMove(r, PRIM));
                moveElementary_3(new int[]{0, 3, 1},
                        new int[][]{{3, 7, 6}, {6, 5, 1}, {6, 5, 1}});
                break;
            case SIMPLE:
                mover(new InspectMove(r, SIMPLE));
                moveElementary_3(new int[]{0, 1, 3},
                        new int[][]{{3, 7, 6}, {6, 5, 1}, {6, 5, 1}});
                break;
        }
    }

    private void moveL(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                movel(new InspectMove(l, PRIM));
                moveElementary_3(new int[]{0, 1, 2},
                        new int[][]{{1, 5, 6}, {6, 7, 3}, {6, 7, 3}});
                break;
            case SIMPLE:
                movel(new InspectMove(l, SIMPLE));
                moveElementary_3(new int[]{0, 2, 1},
                        new int[][]{{1, 5, 6}, {6, 7, 3}, {6, 7, 3}});
                break;
        }
    }

    private void moveB(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveb(new InspectMove(b, PRIM));
                moveElementary_3(new int[]{3, 2, 1},
                        new int[][]{{3, 7, 6}, {6, 5, 1}, {1, 2, 3}});
                break;
            case SIMPLE:
                moveb(new InspectMove(b, SIMPLE));
                moveElementary_3(new int[]{3, 1, 2},
                        new int[][]{{3, 7, 6}, {1, 2, 3}, {6, 5, 1}});
                break;
        }
    }

    private void moveUw(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveU(new InspectMove(U, PRIM));
                rotate(false, 1);
                moveElementary_3(new int[]{0, 2, 3},
                        new int[][]{{4, 5, 6, 7, 8}, {4, 5, 6, 7, 8}, {4, 5, 6, 7, 8}});
                break;
            case SIMPLE:
                moveU(new InspectMove(U, SIMPLE));
                rotate(true, 1);
                moveElementary_3(new int[]{0, 3, 2},
                        new int[][]{{4, 5, 6, 7, 8}, {4, 5, 6, 7, 8}, {4, 5, 6, 7, 8}});
                break;
        }
    }

    private void moveRw(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveR(new InspectMove(R, PRIM));
                rotate(false, 2);
                moveElementary_3(new int[]{0, 3, 1},
                        new int[][]{{0, 2, 1, 5, 4}, {8, 7, 3, 2, 0}, {8, 7, 3, 2, 0}});
                break;
            case SIMPLE:
                moveR(new InspectMove(R, SIMPLE));
                rotate(true, 2);
                moveElementary_3(new int[]{0, 1, 3},
                        new int[][]{{0, 2, 1, 5, 4}, {8, 7, 3, 2, 0}, {8, 7, 3, 2, 0}});
                break;
        }
    }

    private void moveLw(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveL(new InspectMove(l, PRIM));
                rotate(false, 3);
                moveElementary_3(new int[]{0, 1, 2},
                        new int[][]{{0, 2, 3, 7, 8}, {4, 5, 1, 2, 0}, {4, 5, 1, 2, 0}});
                break;
            case SIMPLE:
                moveL(new InspectMove(l, SIMPLE));
                rotate(true, 3);
                moveElementary_3(new int[]{0, 2, 1},
                        new int[][]{{0, 2, 3, 7, 8}, {4, 5, 1, 2, 0}, {4, 5, 1, 2, 0}});

                break;
        }
    }

    private void moveBw(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveB(new InspectMove(B, PRIM));
                rotate(false, 0);
                moveElementary_3(new int[]{3, 2, 1},
                        new int[][]{{0, 2, 1, 5, 4}, {8, 7, 3, 2, 0}, {4, 5, 6, 7, 8},});
                break;
            case SIMPLE:
                moveB(new InspectMove(B, SIMPLE));
                rotate(true, 0);
                moveElementary_3(new int[]{3, 1, 2},
                        new int[][]{{0, 2, 1, 5, 4}, {4, 5, 6, 7, 8}, {8, 7, 3, 2, 0}});
                break;
        }
    }

    private void moveu(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveElementary_1(new int[]{0, 2, 3}, new int[]{0, 0, 0});
                break;
            case SIMPLE:
                moveElementary_1(new int[]{0, 3, 2}, new int[]{0, 0, 0});
                break;
        }
    }

    private void mover(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveElementary_1(new int[]{0, 3, 1}, new int[]{8, 4, 4});
                break;
            case SIMPLE:
                moveElementary_1(new int[]{0, 1, 3}, new int[]{8, 4, 4});
                break;
        }
    }

    private void movel(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveElementary_1(new int[]{0, 1, 2}, new int[]{4, 8, 8});
                break;
            case SIMPLE:
                moveElementary_1(new int[]{0, 2, 1}, new int[]{4, 8, 8});
                break;
        }
    }

    private void moveb(InspectMove inspectMove) {
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveElementary_1(new int[]{3, 2, 1}, new int[]{8, 4, 0});
                break;
            case SIMPLE:
                moveElementary_1(new int[]{3, 1, 2}, new int[]{8, 0, 4});
                break;
        }
    }

    @Override
    public void moveUsingString(String direction) {
        InspectMove inspectMove = new InspectMove(direction);
        if (inspectMove.getMoveTypeEnum() == INVALID)
            logger.info("Cannot do \"" + direction + "\" move");
        else {
            move(inspectMove);
        }
    }

    @Override
    public void move(InspectMove inspectMove) {
        // logger.info("Do " + inspectMove.getMove().toString() + inspectMove.getMoveTypeEnum().toString() + " move");
        switch (inspectMove.getMoveEnum()) {
            case U:
                moveU(inspectMove);
                break;
            case B:
                moveB(inspectMove);
                break;
            case R:
                moveR(inspectMove);
                break;
            case L:
                moveL(inspectMove);
                break;
            case Uw:
                moveUw(inspectMove);
                break;
            case Bw:
                moveBw(inspectMove);
                break;
            case Rw:
                moveRw(inspectMove);
                break;
            case Lw:
                moveLw(inspectMove);
                break;
            case r:
                mover(inspectMove);
                break;
            case l:
                movel(inspectMove);
                break;
            case b:
                moveb(inspectMove);
                break;
            case u:
                moveu(inspectMove);
                break;
            default:
                logger.info("Cannot do \"" + inspectMove.getMoveEnum().toString() + "\" move");
                break;
        }

    }

    public void makeMoves(ArrayList<InspectMove> algorithm) {
        for (InspectMove move : algorithm) {
            move(move);
        }
    }

    public void makeMovesUsingString(String algorithm) {
        String[] splitAlg = algorithm.split(" ");
        for (String move : splitAlg) {
            moveUsingString(move);
        }
    }

    public char[][] getCube() {
        return cube;
    }

    public void setCube(char[][] cube) {
        this.cube = cube;
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
}