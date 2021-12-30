package cache;

import DTOs.Algorithm;

public class CacheLoader {

    public static void load(){
        Algorithm.loadPermutations();
        FileElementCache.loadAll();
        CubeOrientationCache.load();
    }
}
