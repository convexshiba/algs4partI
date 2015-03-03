import java.util.Iterator;

/**
 * This file is created by @Muffin_C on 3/3/15 16:18.
 * This file is part of Project algs4partI.
 */
public class Solver {

    MinPQ<Board> minPQ = new MinPQ<Board>(10);

    public Solver(Board initial) {

    }          // find a solution to the initial board (using the A* algorithm)

    public boolean isSolvable() {
        return true;
    }           // is the initial board solvable?

    public int moves() {
        return 0;
    }                    // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return null;
            }
        };
    }     // sequence of boards in a shortest solution; null if unsolvable

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }// solve a slider puzzle (given below)
}