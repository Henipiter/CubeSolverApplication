package calculations;

import DTOs.InspectMove;
import DTOs.Move;
import cubes.Cube4x4;
import interpretations.Interpretation4x4Edges;

import java.util.ArrayList;

public class CalculateEdges4x4 {

    private Interpretation4x4Edges interpretation4x4Edges = new Interpretation4x4Edges();
    private Cube4x4 cube4x4;

    public CalculateEdges4x4(Cube4x4 cube4x4) {
        this.cube4x4 = cube4x4;
    }

    public ArrayList<Move> getMovesToPutUnpairedEdgeOn14or15Index(int edgePairIndex) {
        interpretation4x4Edges.interpretEdges(cube4x4);

        switch (edgePairIndex / 2) {
            case 0:
                return InspectMove.stringToMoveList("U' L");
            case 1:
                return InspectMove.stringToMoveList("U2 L");
            case 2:
                return InspectMove.stringToMoveList("U L");
            case 3:
                return InspectMove.stringToMoveList("L");
            case 4:
                return InspectMove.stringToMoveList("L2");
            case 5:
                return InspectMove.stringToMoveList("R2 F2");
            case 6:
            case 7:
                break;
            case 8:
                return InspectMove.stringToMoveList("D L'");
            case 9:
                return InspectMove.stringToMoveList("D2 L'");
            case 10:
                return InspectMove.stringToMoveList("D' L'");
            case 11:
                return InspectMove.stringToMoveList("L'");
        }
        return new ArrayList<>();
    }

    public ArrayList<Move> getMovesToPutUnpairedEdgeOn12Index(int edgePairIndex) {
        String edgeFrontToRight = "F R' F' R";
        String edgeUpToRight = "R U' R'";
        String rotateEdge = "R U R' " + edgeFrontToRight;
        interpretation4x4Edges.interpretEdges(cube4x4);

        String algSuffix = (edgePairIndex % 2 == 0 ? edgeUpToRight : edgeFrontToRight);
        String algPrefix = "";
        switch (edgePairIndex / 2) {
            case 0:
                algPrefix = "U2";
                break;
            case 1:
                algPrefix = "U";
                break;
            case 2:
                break;
            case 3:
                algPrefix = "U'";
                break;
            case 4:
                algPrefix = "L U' L'";
                break;
            case 5:
                algPrefix = "R' U R";
                algSuffix = (edgePairIndex % 2 == 1 ? edgeUpToRight : edgeFrontToRight);
                break;
            case 6:
                algPrefix = "";
                algSuffix = (edgePairIndex % 2 == 0 ? "" : rotateEdge);
                break;
            case 7:
                return new ArrayList<>();
            case 8:
                algPrefix = "M2";
                algSuffix = (edgePairIndex % 2 == 1 ? edgeUpToRight : edgeFrontToRight);
                break;
            case 9:
                algPrefix = "D' M'";
                break;
            case 10:
                algPrefix = "M'";
                break;
            case 11:
                algPrefix = "D M'";
                break;
        }
        return InspectMove.stringToMoveList(algPrefix + " " + algSuffix);
    }

    public static ArrayList<Move> getAlgorithmToJoinEdges() {
        return InspectMove.stringToMoveList("Uw' R U R' F R' F' R Uw");
    }
}
