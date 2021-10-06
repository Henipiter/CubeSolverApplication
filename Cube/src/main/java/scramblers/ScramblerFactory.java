package scramblers;

import DTOs.Move;
import cubes.*;

import java.util.ArrayList;

public class ScramblerFactory {

    public ArrayList<Move> getScrambleAlgorithm(Cube cube){
        Scrambler scrambler = getScrambler(cube);
        return scrambler.getScramble();
    }

    private Scrambler getScrambler(Cube cube){
        if( cube instanceof Cube2x2 ){
            return new Scrambler2x2();
        }
        else if( cube instanceof Cube3x3){
            return new Scrambler3x3();
        }
        else if( cube instanceof Cube4x4){
            return new Scrambler4x4();
        }
        else if( cube instanceof CubePyraminx){
            return new ScramblerPyraminx();
        }
        return null;
    }

}
