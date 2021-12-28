package cubes;


import DTOs.Move;
import lombok.Getter;
import lombok.Setter;
import validations.ColorValidationFactory;
import validations.ColorValidator;

import java.util.ArrayList;
import java.util.logging.Logger;

import static DTOs.MoveTypeEnum.INVALID;

@Getter
@Setter
public class Cube {

    private static Logger logger = Logger.getLogger("Cube");
    protected char[][] cube;
    protected char[] center;

    public void move(String direction) {
        Move move = new Move(direction);
        if (move.getMoveTypeEnum() == INVALID)
            logger.info("Cannot do \"" + direction + "\" move");
        else {
            move(move);
        }
    }

    public ColorValidator validate(){
        return ColorValidationFactory.getValidator(this);
    }

    public void move(Move move) {
    }

    protected void changeFourFields(char[][] cube, int side, int[] fieldsOrder) {
        char buffer;
        buffer = cube[side][fieldsOrder[0]];
        for (int i = 0; i < fieldsOrder.length - 1; i++)
            cube[side][fieldsOrder[i]] = cube[side][fieldsOrder[i + 1]];
        cube[side][fieldsOrder[fieldsOrder.length - 1]] = buffer;
    }

    public void makeMoves(ArrayList<Move> algorithm) {
        algorithm.forEach(this::move);
    }

    public void makeMoves(String algorithm) {
        String[] splitAlg = algorithm.split(" ");
        for (String move : splitAlg) {
            move(move);
        }
    }

    public static boolean isSolved(Cube cube) {
        for (int i = 0; i < cube.getCube().length; i++) {
            for (int j = 1; j < cube.getCube()[0].length; j++) {
                if (cube.getCube()[i][0] != cube.getCube()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
