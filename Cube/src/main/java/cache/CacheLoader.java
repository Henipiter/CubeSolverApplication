package cache;

import DTOs.Algorithm;

public class CacheLoader {

    public static void load(){
//        CubeOrientationCache.readColorBlindSetup();
        Algorithm.loadPermutations();
        MarksCache.load();
        ReplacerCache.load();
        SetupCache.load();
        CubeOrientationCache.load();
    }
}
