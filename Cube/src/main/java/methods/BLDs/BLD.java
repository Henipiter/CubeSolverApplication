package methods.BLDs;

import DTOs.Solution;

import java.util.ArrayList;

public interface BLD {
    <T extends Solution> ArrayList<T> solve();
}
