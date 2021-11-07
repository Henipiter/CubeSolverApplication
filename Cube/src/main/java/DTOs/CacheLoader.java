package DTOs;

public class CacheLoader {

    public static void load(){
        CubeOrientationCache.readColorBlindSetup();
        Algorithm.loadPermutations();
    }
}
