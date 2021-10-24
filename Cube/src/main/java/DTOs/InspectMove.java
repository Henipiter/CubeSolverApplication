package DTOs;

import lombok.Data;
import org.apache.maven.shared.utils.StringUtils;

import java.util.ArrayList;

import static DTOs.MoveEnum.BLANK;
import static DTOs.MoveEnum.INVALID;

@Data
public class InspectMove {

    public static ArrayList<Move> stringToMoveList(String alg) {
        ArrayList<Move> result = new ArrayList<>();
        if (!alg.trim().isEmpty()) {
            alg = StringUtils.stripStart(alg, null);
            String[] splitAlg = alg.split(" ");
            for (String move : splitAlg) {
                result.add(new Move(InspectMove.getMove(move)));
            }
        }
        return result;
    }

    public static String moveListToString(ArrayList<Move> alg) {
        String result = "";
        for (Move move : alg) {
            if (move.getMoveEnum() != BLANK && move.getMoveTypeEnum() != MoveTypeEnum.BLANK) {
                result = result.concat(move.toString() + " ");
            }
        }
        if (!result.equals(""))
            return result.substring(0, result.length() - 1);
        return "";
    }

    public static Move getMove(String direction) {
        return new Move(recogniseMove(direction), recogniseType(direction));
    }

    public static ArrayList<Move> getReverseAlgorithm(ArrayList<Move> algorithm){
        ArrayList<Move> reverseAlg = new ArrayList<>();
        for (int i = algorithm.size()-1; i >=0 ; i--) {
            reverseAlg.add( getReverseMove(algorithm.get(i)) );
        }
        return reverseAlg;
    }

    public static Move getReverseMove(Move setup) {
        switch (setup.getMoveTypeEnum()){
            case SIMPLE:
                return new Move(setup.getMoveEnum(), MoveTypeEnum.PRIM);
            case PRIM:
                return new Move(setup.getMoveEnum(), MoveTypeEnum.SIMPLE);
        }
        return new Move(setup);
    }

    private static MoveEnum recogniseMove(String direction) {
        MoveEnum moveEnum = INVALID;
        if (direction.contains("'") || direction.contains("2"))
            direction = direction.substring(0, direction.length() - 1);
        for (MoveEnum i : MoveEnum.values()) {
            if (direction.equals(i.toString())) {
                moveEnum = i;
                break;
            }
        }
        return moveEnum;
    }

    private static MoveTypeEnum recogniseType(String direction) {
        MoveTypeEnum moveTypeEnum = MoveTypeEnum.INVALID;
        if (direction.length() == 2 || direction.length() == 3) {
            if (direction.charAt(direction.length() - 1) == '2')
                moveTypeEnum = MoveTypeEnum.DOUBLE;
            else if (direction.charAt(direction.length() - 1) == '\'')
                moveTypeEnum = MoveTypeEnum.PRIM;
        }
        if ((direction.length() == 2 && direction.charAt(1) == 'w') || direction.length() == 1)
            moveTypeEnum = MoveTypeEnum.SIMPLE;
        return moveTypeEnum;
    }
}