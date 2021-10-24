package DTOs;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class SolutionLBL implements Solution {
    private final ArrayList<Move> algorithm;
}
