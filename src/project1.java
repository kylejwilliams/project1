
import java.util.*;

/**
 * @author Kyle Williams
 * 15 February 2016
 * 
 * CS 4300 - Artificial Intelligence
 *
 * Missionaries and Cannibals problem utilizing iterative deepening search
 *
 */
public class project1 {

	/**
	 * Entry point of the program
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Problem problem = new Problem();
		
		Node result = iterativeDeepeningSearch(problem);
		
		Problem.generatePath(result);
		Problem.printPath();
	}
	
	/**
	 * Performs iterative deepening search until the solution to the problem is found or 
	 * the tree has been exhausted of possible solutions.
	 * 
	 * 
	 * @param problem 	a structure containing information about the problem being solved
	 * @return 			the solution to the problem if found. Otherwise an error
	 */
	public static Node iterativeDeepeningSearch(Problem problem) {
		int depth = 0;
			
		while (true) {
			depth++;
			
			Node result = depthLimitedSearch(problem, depth);
			
			if (result.state != Problem.error.state) return result;
		}
	}	
		
	/**
	 * Simple depth-limited search. Just calls a recursive version of itself for this 
	 * project
	 * 
	 * @param problem	a structure containing information about the problem being solved
	 * @param depth		the current depth within the tree that is being searched
	 * @return			the solution to the problem if one is found. Otherwise an error
	 */
	public static Node depthLimitedSearch(Problem problem, int depth) {
		return recursiveDepthLimitedSearch(makeNode(Problem.initialState), problem, depth);
	}
	
	/**
	 * recursively scans every node within the tree up to the specified depth limit for 
	 * a solution state. If one is found it returns the results, otherwise it returns an
	 * error and sent back to the iterative deepening search method to increase the depth
	 * for another search.
	 * 
	 * @param node		the current node within the tree that is being searched
	 * @param problem	a structure containing information about the problem being solved
	 * @param limit		specifies the maximum depth to search within the tree
	 * @return			the solution to the problem if it is found. Otherwise an error.
	 */
	public static Node recursiveDepthLimitedSearch(Node node, Problem problem, int limit) {
		
		if (Problem.goalTest(node.state)) return node;
		else if (limit == 0) return Problem.error;
		else {
			boolean errorOccured = false;
			
			for (ArrayList<Integer> action : problem.actions) {
				if (Problem.isValidOption(node, action)) {
					Node child = makeChildNode(node, action);
					node.children.add(child);
					
					Node result = recursiveDepthLimitedSearch(child, problem, limit - 1);
					
					if (result.state == Problem.error.state) errorOccured = true;
					else if (!(result.state.equals(Problem.error.state))) return result;
				}
			}
			
			if (errorOccured) return Problem.error;
		
			return Problem.error;
		}
	}
	
	/**
	 * This method represents a valid action within the state space being performed on a 
	 * node to generate another valid node. 
	 * 
	 * @param parent	The node that the child node will descend from within the tree
	 * @param action	The action that is taken on the parent node to produce the child node
	 * @return			A child node of the parent
	 */
	public static Node makeChildNode(Node parent, ArrayList<Integer> action) {
		Node child = new Node();
	
		child.state = Problem.result(parent.state, action);
		child.parent = parent;
		child.action = action;
	
		return child;
	}
	
	/**
	 * Given a state within the state space, it will create a new node to
	 * represent that state
	 * 
	 * @param state		The representation of the problem held by the node
	 * @return			A newly generated node with the given state
	 */
	public static Node makeNode(ArrayList<Integer> state) {
		Node node = new Node();
		node.state = state;
	
		return node;
	}
}

/**
 * A node is an object that represents various states within the state space of 
 * the problem. The nodes are arranged in a tree structure for fast searching for
 * a path between the problem's initial state and desired end state.
 * 
 * @author kyle
 *
 */
class Node {
	 // the state in the state space to which the node corresponds
	public ArrayList<Integer> state = new ArrayList<>();
	
	// the node in the search tree that generated this node
	public Node parent;
	
	// the action that was applied to the parent to generate the node
	public ArrayList<Integer> action = new ArrayList<>();
	
	// a container that holds all of the children of the node
	public ArrayList<Node> children = new ArrayList<>();
}

/**
 * The problem method contains the information for the problem trying to be solved.
 * A problem must be well-defined, with a starting state and ending state, as well
 * as valid actions that can be performed between the two while still getting results
 * within the state space. 
 * 
 * @author kyle
 *
 */
class Problem {
	// The beginning state of the problem
	public static ArrayList<Integer> initialState = new ArrayList<>();
	
	// A list of valid options that may be performed within the state space
	public ArrayList<ArrayList<Integer>> actions = new ArrayList<>();
	
	// The desired solution to the problem
	public static ArrayList<Integer> solution = new ArrayList<>();
	
	// A generic error message that is thrown when a solution is not found
	public static Node error = new Node();
	
	// a container that traces the intermediary nodes between the initial state and the solution
	public static LinkedList<Node> visitedNodes = new LinkedList<>();
	
	/**
	 * Constructor for the problem. Initializes the variables.
	 */
	Problem() {
		
		// A temporary arraylist to hold values that can be inserted into 
		// the 2D arraylists. Mostly because I can't initialize the values
		// during declaration.
		ArrayList<Integer> temp = new ArrayList<>();
		
		Problem.initialState.add(3);
		Problem.initialState.add(3);
		Problem.initialState.add(1);
		
		// Adding the valid actions to the arraylist
		// <1, 0, 1>, <2, 0, 1>, <0, 1, 1>, <0, 2, 1>, <1, 1, 1>
		temp.add(1);
		temp.add(0);
		temp.add(1);
		this.actions.add(new ArrayList<>(temp));
		temp.clear();
		
		temp.add(2);
		temp.add(0);
		temp.add(1);
		this.actions.add(new ArrayList<>(temp));
		temp.clear();
		
		temp.add(0);
		temp.add(1);
		temp.add(1);
		this.actions.add(new ArrayList<>(temp));
		temp.clear();
		
		temp.add(0);
		temp.add(2);
		temp.add(1);
		this.actions.add(new ArrayList<>(temp));
		temp.clear();
		
		temp.add(1);
		temp.add(1);
		temp.add(1);
		this.actions.add(new ArrayList<>(temp));
		temp.clear();
		
		// Solution: <0, 0, 0>
		temp.add(0);
		temp.add(0);
		temp.add(0);
		Problem.solution = new ArrayList<>(temp);
		temp.clear();
		
		// Just a generic, arbitrary error. Value picked because it exists outside of 
		// the state space. Any values greater than three or less than 0 could work.
		temp.add(-1);
		temp.add(-1);
		temp.add(-1);
		Problem.error.state = new ArrayList<>(temp);
		temp.clear();
	}
	
	/**
	 * This method compares an intermediary state to the desired state
	 * 
	 * @param possibleSolution		A node that is potentially a solution
	 * @return						true if the given node is a solution; false otherwise
	 */
	public static boolean goalTest(ArrayList<Integer> possibleSolution) {
		if (possibleSolution.equals(solution)) return true;
		return false;
	}
	
	/**
	 * This method returns the result of performing an action on a node within
	 * the state space to produce another intermediary node.
	 * 
	 * @param state		the representation of the node within the state space
	 * @param action	the action to be performed on the node
	 * @return			A new node generated by the state and action
	 */
	public static ArrayList<Integer> result(ArrayList<Integer> state, ArrayList<Integer> action) {
		ArrayList<Integer> result = new ArrayList<>();
		
		if (state.get(2) == 1) { // boat is on the wrong side; have to take it over 
			result.add(state.get(0) - action.get(0));
			result.add(state.get(1) - action.get(1));
			result.add(state.get(2) - action.get(2));
		}
		else if (state.get(2) == 0) { // boat is on the correct side
			result.add(state.get(0) + action.get(0));
			result.add(state.get(1) + action.get(1));
			result.add(state.get(2) + action.get(2));
		}
		else System.exit(0); // in case of an invalid state getting passed
		

			
		return result;
	}
	
	/**
	 * This method compares a node's state with an action to determine if the action is valid to be performed
	 * on the node. 
	 * 
	 * @param parentNode	the node upon which the actions are potentially performed
	 * @param action		A valid action within the state
	 * @return				true if performing the given action on the node is still within the state space; false otherwise
	 */
	public static boolean isValidOption(Node parentNode, ArrayList<Integer> action) {
		ArrayList<Integer> tempAction = new ArrayList<>(action);
		
		// if the boat is on the wrong side we must represent the action by subtracting the values
		// to represent the boat crossing over to the other side
		if (parentNode.state.get(2) == 1) { 
			for (int i = 0; i <= 2; i++)
				tempAction.set(i, tempAction.get(i)*(-1));
		}
		
		for (int i = 0; i <= 2; i++)
			tempAction.set(i, tempAction.get(i) + parentNode.state.get(i));
		
		if (((tempAction.get(0) < tempAction.get(1)) && (tempAction.get(0) != 0)) || // Too many cannibals on the wrong side
				tempAction.get(0) < 0 || // missionaries out of bounds
				tempAction.get(0) > 3 ||
				tempAction.get(1) < 0 || // cannibals out of bounds
				tempAction.get(1) > 3 ||
				tempAction.get(2) < 0 || // boat out of bounds
				tempAction.get(2) > 1 ||
				(3 - tempAction.get(0) < (3 - tempAction.get(1))) && (3 - tempAction.get(0)) != 0 ) { // Too many cannibals on the other side
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method generate the path that was taken between the initial state and the solution state, including 
	 * all intermediate values in-between.
	 * 
	 * @param node		A node within the solution path
	 */
	public static void generatePath(Node node) {
		if (node.parent != null) {
			visitedNodes.add(node);
			generatePath(node.parent);
		}
		else if (node.parent == null) {
			visitedNodes.add(node);
			return;
		}
	}
	
	/**
	 * This function takes a generated solution path and displays it to the console.
	 */
	public static void printPath() {
		while (!visitedNodes.isEmpty()) {
			System.out.println("Node: " + visitedNodes.getLast().state + " | Action that generated node: " + visitedNodes.getLast().action);
			visitedNodes.removeLast();
		}
	}
	
}
