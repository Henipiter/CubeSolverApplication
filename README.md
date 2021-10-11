# CubeSolverApplication

Create cube:
- Cube3x3 cube3x3 = new Cube3x3();

Get scramble algorithm:
- ScramblerFactory scramblerFactory = new ScramblerFactory();
- ArrayList<Move> scrambleAlg = scramblerFactory.getScrambleAlgorithm(cube3x3);
  
Scramble cube:
- cube3x3.makeMoves(scrambleAlg);

Choose method:
 - MethodStrategy methodStrategy = new MethodStrategy(new LBLSolver());

Get solving algorithm:
 - String solveAlgorithm = methodStrategy.solve(cube3x3());
