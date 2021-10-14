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
        String scramble = "R D2 F' U2 L D2 L D2 U2 R F2 L' F2 R2 B R F2 L U R F";
        cube3x3.makeMoves(scramble);
        methodStrategy = new MethodStrategy(new LBLSolver());
        solveAlgorithm = methodStrategy.solve(cube3x3);
        System.out.println(solveAlgorithm);
cube2x2.makeMoves(scrambleAlg);
//        methodStrategy = new MethodStrategy(new BLDSolver());
//        solveAlgorithm = methodStrategy.solve(cube2x2);
//        System.out.println(solveAlgorithm);


    }


}
