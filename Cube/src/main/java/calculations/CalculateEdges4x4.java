package calculations;

import DTOs.InspectMove;
import cubes.Cube4x4;
import interpretations.Interpretation4x4Edges;

import java.util.ArrayList;

public class CalculateEdges4x4 {

    private Interpretation4x4Edges interpretation4x4Edges = new Interpretation4x4Edges();
    private Cube4x4 cube4x4;

    public CalculateEdges4x4(Cube4x4 cube4x4) {
        this.cube4x4 = cube4x4;
    }

    public static ArrayList<InspectMove> getParityOLLAlgorithm(){
        return InspectMove.createAndReturnArrayListFromString("r2 B2 U2 l U2 r' U2 r U2 F2 r F2 l' B2 r2");
    }

    public static ArrayList<InspectMove> getParityPLLAlgorithm(){
        return InspectMove.createAndReturnArrayListFromString("r2 U2 r2 Uw2 r2 u2");
    }

    public ArrayList<InspectMove> getMovesToPutUnpairedEdgeOn14or15Index(int edgePairIndex){
        interpretation4x4Edges.interpretEdges(cube4x4);

        switch (edgePairIndex/2){
            case 0:
                return InspectMove.createAndReturnArrayListFromString("U' L");
            case 1:
                return InspectMove.createAndReturnArrayListFromString("U2 L");
            case 2:
                return InspectMove.createAndReturnArrayListFromString("U L");
            case 3:
                return InspectMove.createAndReturnArrayListFromString("L");
            case 4:
                return InspectMove.createAndReturnArrayListFromString("L2");
            case 5:
                return InspectMove.createAndReturnArrayListFromString("R2 F2");
            case 6:
            case 7:
                break;
            case 8:
                return InspectMove.createAndReturnArrayListFromString("D L'");
            case 9:
                return InspectMove.createAndReturnArrayListFromString("D2 L'");
            case 10:
                return InspectMove.createAndReturnArrayListFromString("D' L'");
            case 11:
                return InspectMove.createAndReturnArrayListFromString("L'");

        }
        return new ArrayList<>();
    }

    public ArrayList<InspectMove> getMovesToPutUnpairedEdgeOn12Index(int edgePairIndex) {
        String edgeFrontToRight = "F R' F' R";
        String edgeUpToRight = "R U' R'";
        String rotateEdge = "R U R'"+" "+edgeFrontToRight;
        interpretation4x4Edges.interpretEdges(cube4x4);

        String algPostfix = (edgePairIndex % 2 == 0 ? edgeUpToRight : edgeFrontToRight);
        String algPrefix="";
        switch (edgePairIndex/2){
            case 0:
                algPrefix="U2";
                break;
            case 1:
                algPrefix="U";
                break;
            case 2:
                break;
            case 3:
                algPrefix="U'";
                break;
            case 4:
                algPrefix="L U' L'";
                break;
            case 5:
                algPrefix="R' U R";
                algPostfix = (edgePairIndex % 2 == 1 ? edgeUpToRight : edgeFrontToRight);
                break;
            case 6:
                algPrefix="";
                algPostfix = (edgePairIndex % 2 == 0 ? "" : rotateEdge);
                break;
            case 7:
                return new ArrayList<>();
            case 8:
                algPrefix="M2";
                algPostfix = (edgePairIndex % 2 == 1 ? edgeUpToRight : edgeFrontToRight);
                break;
            case 9:
                algPrefix="D' M'";
                break;
            case 10:
                algPrefix="M'";
                break;
            case 11:
                algPrefix="D M'";
                break;

        }
        return InspectMove.createAndReturnArrayListFromString(algPrefix+" "+algPostfix);

    }

    public static ArrayList<InspectMove> getAlgorithmToJoinEdges(){
        return InspectMove.createAndReturnArrayListFromString("Uw' R U R' F R' F' R Uw");
    }



}
