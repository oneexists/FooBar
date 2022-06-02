import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
	private static final int BOARD_WIDTH = 8;
	private static final int[] KNIGHT_MOVEMENT = { 17, 15, 10, 6, -6, -10, -15, -17 };

	/**
	 * Validates position movement on board:
	 *   Finds column of source and destination positions
	 *   Ensures movement has not fallen off the edge of the board
	 */
	private static boolean isValid(int src, int dest) {
	    int columnDifference = (src % BOARD_WIDTH) - (dest % BOARD_WIDTH);
        if (columnDifference <= 3 && columnDifference >= -3) {
            return true;
        }
        return false;
	}

	/**
	 * Finds possible moves for a knight:
	 *   Finds all possible positions based on knight movement
	 *   Adds valid movement to possible positions
	 */
	private static List<Integer> findKnightMoves(int src) {
	    List<Integer> possiblePositions = new ArrayList<>();
        for (int move : KNIGHT_MOVEMENT) {
            if (isValid(src, src + move)) {
                possiblePositions.add(src + move);
            }
        }
        return possiblePositions;
	}

	public static int solution(int src, int dest) {
		class Node {
			int val;
			List<Node> children = new ArrayList<>();
			int distance = 0;

			Node(int val) {
				this.val = val;
			}
		}

    		Node root = new Node(src);
    		List<Integer> children = findKnightMoves(src);
    		List<Node> visited = new ArrayList<>();
	    	Queue<Node> stepQueue = new ArrayDeque<>();

		for (Integer child : children) {
    			root.children.add(new Node(child));
    		}

    		stepQueue.add(root);
    		while (! stepQueue.isEmpty()) {
    			Node current = stepQueue.poll();
    			if (current.val == dest) {
    				return current.distance;
    			}
    			if (! visited.contains(current)) {
    				visited.add(current);
    				for (Node child : current.children) {
    					child.distance = current.distance + 1;
    					for (int childVal : findKnightMoves(child.val)) {
    						child.children.add(new Node(childVal));
    					}
    					stepQueue.add(child);
    				}
    			}

    		}
    		return 0;
    	}
}
