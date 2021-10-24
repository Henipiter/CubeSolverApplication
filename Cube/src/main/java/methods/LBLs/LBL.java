package methods.LBLs;

import DTOs.Solution;

import java.util.ArrayList;

public interface LBL {
    <T extends Solution> ArrayList<T> solve(char firstCenterColor);
}
