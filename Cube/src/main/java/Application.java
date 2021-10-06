import DTOs.Move;
import cubes.Cube2x2;
import cubes.Cube3x3;
import methods.LBLs.LBLSolver;
import methods.MethodStrategy;
import scramblers.ScramblerFactory;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

        Cube2x2 cube2x2 = new Cube2x2();
        Cube3x3 cube3x3 = new Cube3x3();

        ScramblerFactory scramblerFactory = new ScramblerFactory();
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(cube2x2);

        MethodStrategy methodStrategy;
        String solveAlgorithm;
        String scramble = "D R' F' L2 D B' R2 D2 R U2 F R2 B' L2 D2 B' L2 B2 U2 F'";
        cube3x3.makeMovesUsingString(scramble);
        methodStrategy = new MethodStrategy(new LBLSolver());
        solveAlgorithm = methodStrategy.solve(cube3x3);
        System.out.println(solveAlgorithm);
cube2x2.makeMoves(scrambleAlg);
//        methodStrategy = new MethodStrategy(new BLDSolver());
//        solveAlgorithm = methodStrategy.solve(cube2x2);
//        System.out.println(solveAlgorithm);


    }


}
