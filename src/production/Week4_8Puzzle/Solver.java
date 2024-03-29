/**
 * This file is created by @Muffin_C on 3/3/15 16:18.
 * This file is part of Project algs4partI.
 */
public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private SearchNode prev;
        private int moves;
        private Board board;

        private SearchNode(SearchNode prev, Board board, int moves) {
            this.prev = prev;
            this.moves = moves + 1;
            this.board = board;
        }


        @Override
        public int compareTo(SearchNode searchNode) {
            int oPriority = searchNode.moves + (searchNode.board.manhattan());
            if (moves + board.manhattan() > oPriority) {
                return 1;
            } else if (moves + board.manhattan() < oPriority) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>(10);
    private MinPQ<SearchNode> minPQtwin = new MinPQ<SearchNode>(10);
    private LinkedQueue<Board> queue = new LinkedQueue<Board>();
    private LinkedQueue<Board> queueTwin = new LinkedQueue<Board>();

    private boolean isSolvable = false;

    private int moves;

    public Solver(Board initial) {

        minPQtwin.insert(new SearchNode(null, initial.twin(), -1));
        minPQ.insert(new SearchNode(null, initial, -1));

        boolean notFound = true;
        boolean thisFound = false;
        boolean twinFound = false;

        while (notFound) {
            SearchNode minPriority = minPQ.delMin();
            SearchNode minPriorityTwin = minPQtwin.delMin();

            queue.enqueue(minPriority.board);
            queueTwin.enqueue(minPriorityTwin.board);

            moves = minPriority.moves;

            for (Board foo : minPriority.board.neighbors()) {
                if (minPriority.prev == null || !foo.equals(minPriority.prev.board)) {
                    minPQ.insert(new SearchNode(minPriority, foo, minPriority.moves));
                }
            }

            for (Board foo : minPriorityTwin.board.neighbors()) {
                if (minPriorityTwin.prev == null || !foo.equals(minPriorityTwin.prev.board)) {
                    minPQtwin.insert(new SearchNode(minPriorityTwin, foo, minPriorityTwin.moves));
                }
            }

            if (minPriority.board.isGoal()) {
                thisFound = true;
            }

            if (minPriorityTwin.board.isGoal()) {
                twinFound = true;
            }

            notFound = !(thisFound || twinFound);
        }

        if (thisFound) {
            isSolvable = true;
        }

    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return isSolvable() ? moves : -1;
    }

    public Iterable<Board> solution() {
        return isSolvable() ? queue : null;
    }

    public static void main(String[] args) {
    }
}
