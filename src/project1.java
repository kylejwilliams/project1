
/* 
 * PSEUDOCODE - Depth-Limited Search
 *
 * function DepthLimitedSearch(problem, limit) returns a solution, or failure/cutoff
 *     return RecursiveDLS(MakeNode(problem.InitialState), problem, limit)
 *     
 * function RecursiveDLS(node, problem, limit) returns a solution or failure/cutoff
 *     if problem.GoalTest(node.State) then return Solution(node)
 *     else if limit = 0 then return cutoff
 *     else
 *         cutoffOccured? <- false
 *         for each action in problem.Actions(node.State) do
 *             child<-ChildNode(problem, node, action)
 *             result<-RecursiveDLS(child, problem, limit - 1)
 *             if result = cutoff then cutoffOccured<-true
 *             else if result != failure then return result
 *         if cutoffOccured? then return cutoff else return failure
 */

/*
 * IMPLEMENTATION DETAILS
 * 
 * problem      := convert <3, 3, 1> to <0, 0, 0>, using vector addition/subtraction
 * limit        := the maximum depth to search for a solution
 * solution     := every person on the right side, represented by <0, 0, 0>
 * failure      := more cannibals than missionaries on one side. (b > a)
 * cutoff       := current depth, to be compared with the limit
 * node         := current representation of the field
 * State        := represented as a vector <a, b, c>, where a := number of missionaries 
 *                     on the wrong side, b := number of cannibals on the wrong side, 
 *                     and c := whether the boat is on the wrong side or not 
 * InitialState := vector with total number on the wrong side, <3, 3, 1>
 * action       := represented with vector addition/subtraction. Moving to the correct
 *                     side is subtraction, and moving to the wrong side is addition.
 *                     actions: <1, 0, 1>, <2, 0, 1>, <0, 1, 1>, <0, 2, 1>, <1, 1, 1>
 * result       := holds the final solution/failure resulting from recursion
 * actionList   := holds all of the previous performed actions
 * 
 */

import java.util.*;

/**
 * @author kyle
 *
 */
/**
 * @author kyle
 *
 */
public class project1 {
	
	/**
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @param initial
	 * @param solution
	 * @param limit
	 * @return
	 */
	private static Vector<Integer> DepthLimitedSearch(Vector<Integer> initial, Vector<Integer> solution, int limit) {
		return RecursiveDLS(initial, initial, solution, limit);
		
	}
	
	/**
	 * @param node
	 * @param initial
	 * @param solution
	 * @param limit
	 * @return
	 */
	private static Vector<Integer> RecursiveDLS(Vector<Integer> node, Vector<Integer> initial, Vector<Integer> solution, int limit) {
		return node;
	}

}


/**
 * @author kyle
 *
 */
class Problem {
	public Vector<Integer> initialState = new Vector<Integer>();
	public Vector<Integer> solution = new Vector<Integer>();
	public Vector<Vector<Integer>> actionList = new Vector<Vector<Integer>>();
	
	Problem() {
		initialState.add(3);
		initialState.add(3);
		initialState.add(1);
		
		solution.add(0);
		solution.add(0);
		solution.add(0);
	}
	
	public boolean goalTest(Vector<Integer> possibleSolution) {
		if (solution.equals(possibleSolution)) return true;
		else return false;
	}
}

