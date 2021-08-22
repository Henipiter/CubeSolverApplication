package DTOs;

import lombok.Data;
import org.apache.maven.shared.utils.StringUtils;

import java.util.ArrayList;

import static DTOs.MoveEnum.BLANK;
import static DTOs.MoveEnum.INVALID;

/** Parse moves for cubes
 *
 */
@Data
public class InspectMove {
    private MoveTypeEnum moveTypeEnum;
    private MoveEnum moveEnum;

    public static ArrayList<InspectMove> createAndReturnArrayListFromString(String alg){
        ArrayList<InspectMove> result = new ArrayList<>();
        if(!alg.trim().isEmpty()){
            alg=StringUtils.stripStart(alg, null);
            String[] splitAlg = alg.split(" ");
            for (String move : splitAlg) {
                result.add(new InspectMove(move));
            }
        }
        return result;
    }

    public InspectMove(MoveEnum moveEnum, MoveTypeEnum moveTypeEnum){
        this.moveEnum = moveEnum;
        this.moveTypeEnum = moveTypeEnum;
    }

    public InspectMove(String direction){
        recogniseType(direction);
        recogniseMove(direction);
    }

    public InspectMove(InspectMove inspectMove){
        this.moveEnum = inspectMove.getMoveEnum();
        this.moveTypeEnum = inspectMove.getMoveTypeEnum();
    }

    private void recogniseMove(String direction){
        moveEnum = INVALID;
        if(direction.contains("'") || direction.contains("2"))
            direction = direction.substring(0, direction.length() - 1);
        for(MoveEnum i : MoveEnum.values()){
            if( direction.equals(i.toString())){
                moveEnum=i;
                break;
            }
        }
    }

    private void recogniseType(String direction){
        moveTypeEnum = MoveTypeEnum.INVALID;
        if(direction.length()==2 || direction.length()==3){
            if( direction.charAt(direction.length()-1) == '2')
                moveTypeEnum = MoveTypeEnum.DOUBLE;
            else if( direction.charAt(direction.length()-1) == '\'')
                moveTypeEnum = MoveTypeEnum.PRIM;
        }
        if(( direction.length()==2 && direction.charAt(1)=='w')|| direction.length()==1 )
            moveTypeEnum = MoveTypeEnum.SIMPLE;
    }




    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InspectMove)) {
            return false;
        }
        InspectMove c = (InspectMove) o;
        return c.getMoveEnum() == this.getMoveEnum() &&
                c.getMoveTypeEnum() == this.getMoveTypeEnum();
    }

    @Override
    public String toString() {
        return moveEnum.toString()+moveTypeEnum.toString();
    }

    public static String algToString(ArrayList<InspectMove> alg){
        String result = "";
        for( InspectMove move : alg){
            if(move.getMoveEnum()!=BLANK) {
                result = result.concat(move.toString() + " ");
            }
        }
        if(!result.equals(""))
            return result.substring(0, result.length()-1);
        return "";
    }
}
