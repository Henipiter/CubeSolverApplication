package DTOs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Algorithm {
    private static final String PermY = "R U' R' U' R U R' F' R U R' U' R' F R";
    private static final String PermT = "R U R' U' R' F R F' F R U' R' U' R U R' F'";
    private static final String PermJ = "R U2 R' U' R U2 L' U R' U' L";
    private static final String Perm7 = "L U' R' U L' U2 R U' R' U2 R";
    private static final String PermR = "U' L U2 L' U2 L F' L' U' L U L F L2 U2";

    private static final Map<String, String> permutations = new HashMap<>();

    public Algorithm() {
        permutations.put("Y", PermY);
        permutations.put("T", PermT);
        permutations.put("J", PermJ);
        permutations.put("7", Perm7);
        permutations.put("R", PermR);
    }

    public String getPerm(String permutation) {
        return permutations.get(permutation);
    }

    public ArrayList<Move> getPermAlg(String permutation) {
        return InspectMove.stringToMoveList(permutations.get(permutation));
    }
}

