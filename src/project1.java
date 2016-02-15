
import java.util.*;

/** 
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
 *
 *
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
 *
 * @author Kyle Williams
 *
 **/
/*public class project1 {
	
	*//**
	 * @param args command line arguments
	 *//*
	public static void main(String[] args) {
		Problem problem = new Problem();
		int limit = 11;
		
		System.out.print("initial state: ");
		System.out.println(problem.initialState);
		System.out.print("solution: ");
		System.out.println(problem.solution);
		
		Node result = 
				DepthLimitedSearch(problem, limit);
		
		System.out.print("result: ");
		System.out.println(result.state);
		
		if (result.state.equals(problem.solution)) {
			System.out.println("solution found!");
		}
		else {
			System.out.println("failure!");
		}
	}
	
	
	*//**
	 * @param problem	The current problem trying to be solved, as represented
	 * 					as vectors.The problem contains the initial state and
	 * 					the solution state, as well as a list of valid actions
	 * 					that may be performed to reach the solution. 
	 * @param limit		The maximum depth for the search. The decision tree 
	 * 					will not search further than this limit. Small values
	 * 					for the limit may not produce a solution and large 
	 * 					values may have a long computation time.
	 * @return			This function returns a recursive call to the depth
	 * 					limited search. This call will either return the valid 
	 * 					solution if one exists, or will return a failure 
	 * 					message
	 *//*
	private static Node DepthLimitedSearch(Problem problem, int limit) {
		return RecursiveDLS(makeNode(problem.initialState), problem, limit);
		
	}
	
	*//**
	 * @param node		An object that holds a given state of the problem (a 
	 * 					intermediate step between the initial state and the '
	 * 					solution), as well as it's child node, and the action 
	 * 					that was performed to create said node.
	 * @param problem	The problem trying to be solved
	 * @param limit		The maximum depth to search through the tree for a 
	 * 					solution
	 * @return			Either the solution to the problem upon success, or
	 * 					a failure message, to be debugged.
	 *//*
	private static Node RecursiveDLS(Node node, Problem problem, int limit) {
		if (problem.goalTest(node)) return node;
		else if (limit == 0) return new Node(problem.cutoffError);
		else {
			boolean cutoffOccured = false;
			
			for (ArrayList<Integer> action : problem.validActions(node)) {
				System.out.print("Current Action: ");
				System.out.println(action);
				Node child = makeChildNode(problem, node, action);
				Node result = RecursiveDLS(child, problem, limit - 1);
				
				if (result.state == problem.cutoffError) cutoffOccured = true;
				else if (result.state != problem.cutoffError) return result;
			}
			
			if (cutoffOccured) return new Node(problem.cutoffError);
			else return new Node(problem.failureError);
		}
	}
	
	*//**
	 * @param node		A new node to be created
	 * @return			The newly created node
	 *//*
	public static Node makeNode(ArrayList<Integer> node) {
		Node newNode = new Node(node);
		return newNode;
	}
	
	*//**
	 * @param problem 	the problem trying to be solved
	 * @param node		the parent node that the child is derived from
	 * @param action	the action to be performed on the parent node to get 
	 * 					the child node
	 * @return			A new node that is one level deeper within the decision
	 * 					tree
	 *//*
	public static Node makeChildNode(Problem problem, Node node, 
			ArrayList<Integer> action) {
		
		ArrayList<Integer> tempState = new ArrayList<Integer>();
		
		for (int i = 0; i < node.state.size(); i++) {
			tempState.add(node.state.get(i) + action.get(i));
		}
		
		Node tempNode = new Node(tempState);
		node.childrenNodes.add(tempNode);

		return tempNode;
	}
}

*//**
 * @author kyle
 *
 *//*
class Problem {
	public ArrayList<Integer> initialState = new ArrayList<Integer>();
	public ArrayList<Integer> solution = new ArrayList<Integer>();
	public ArrayList<ArrayList<Integer>> actionList = 
			new ArrayList<ArrayList<Integer>>();
	public ArrayList<Integer> cutoffError = new ArrayList<Integer>();
	public ArrayList<Integer> failureError = new ArrayList<Integer>();
	

	public Problem() {
		initialState.add(3); // number of missionaries
		initialState.add(3); // number of cannibals
		initialState.add(1); // boat on wrong side?
		
		for (int i = 0; i < 3; i++) {
			solution.add(0); 
		}
		
		int possibleActions[][] = { 
				{ 1, 0, 1 }, 
                { 2, 0, 1 }, 
                { 0, 1, 1 }, 
                { 0, 2, 1 }, 
                { 1, 1, 1 } };
		
		ArrayList<Integer> currentAction = new ArrayList<Integer>();
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 3; x++) {
				currentAction.add(possibleActions[y][x]);
			}
			actionList.add(new ArrayList<Integer>(currentAction));
			currentAction.clear();
		}
		
		for (int i = 0; i < 3; i++)
			cutoffError.add(-1);
		
		for (int i = 0; i < 3; i++)
			failureError.add(-2);
	}
	
	*//**
	 * @param node		A node being evaluated for possible actions to take
	 * @return			a list of valid actions that can be performed on the 
	 * 					node
	 *//*
	public ArrayList<ArrayList<Integer>> validActions(Node node) {
		ArrayList<ArrayList<Integer>> actions = 
				new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tempState = new ArrayList<Integer>();
		
		for (ArrayList<Integer> action : actionList) {
			System.out.print("Action: ");
			System.out.println(action);
			if (node.state.get(2) == 1) { // boat is on the wrong side
				for (int i = 0; i < action.size(); i++)
					tempState.add(node.state.get(i) - action.get(i));
			} 
			else if (node.state.get(2) == 0) { // boat is on the right side
				for (int i = 0; i < action.size(); i++)
					tempState.add(node.state.get(i) + action.get(i));
			}
			
			if (tempState.get(0) < tempState.get(1)) // more cannibals than missionaries
				tempState.clear();
			else if (tempState.get(0) < 0 || tempState.get(0) > 3) // missionaries out of bounds
				tempState.clear();
			else if (tempState.get(1) < 0 || tempState.get(1) > 3) // cannibals out of bounds
				tempState.clear();
			else if (tempState.get(2) < 0 || tempState.get(2) > 1) // boat out of bounds
				tempState.clear();
			else {
				if (action.get(2) == 1) {
					for (int i = 0; i < 3; i++)
						action.set(i, (-1)*action.get(i));
				}
				actions.add(action);
			}
		}
		
		return actions;
	}
	
	*//**
	 * @param possibleSolution	A given node to be tested for equality with the
	 * 							problem's solution
	 * @return
	 *//*
	public boolean goalTest(Node possibleSolution) {
		if (solution.equals(possibleSolution.state)) return true;
		else return false;
	}
}

*//**
 * @author kyle
 *
 *//*
class Node {
	public ArrayList<Integer> state = new ArrayList<Integer>();
	public ArrayList<Node> childrenNodes = new ArrayList<Node>();
	
	Node(ArrayList<Integer> node) {
		state = node;
	}
}*/

class project1 {
	
	public static void main(String[] args) {
		Problem problem = new Problem();
		
		Node result = iterativeDeepeningDepthFirstSearch(problem);
		System.out.println("Result: " + result);
		
		// DEBUGGING
		//ArrayList<Integer> state = new ArrayList<>();
		//state.add(3);
		//state.add(3);
		//state.add(1);
		//Node test = makeNode(state);
		//makeChildrenNodes(test);
		
		//for (Node node : test.children) {
		//	System.out.println(node.state);
		//}
		
		//System.out.println(isActionValid(action));
	}
	
	public static Node iterativeDeepeningDepthFirstSearch(Problem problem) {
		int depth = 0;
		Node result = new Node();
		
		
		while (true) {
			depth++;
			result = depthLimitedSearch(problem, problem.initialState, depth);
			
			if (result != null)
				return result;
		}
	}
	
	public static Node depthLimitedSearch(Problem problem, Node node, int depth) {
		if (node.state == problem.solution.state) {
			return node;
		}
		else if (depth > 0) {
			makeChildrenNodes(problem, node);
			//System.out.println("depth: " + depth + " current Node: " + node.state + " visited nodes: " + problem.visitedNodes);
			Node result;
			for (Node child : node.children) {
				problem.visitedNodes.add(child.state);
				//if (problem.visitedNodes.contains(child.state)) break;
				result = depthLimitedSearch(problem, child, depth - 1);
				
				if (result != null)
					return result;
			}
		}
		
		return null;
	}
	
	public static Node makeNode(ArrayList<Integer> state) {
		Node node = new Node();
		node.state = state;
		return node;
	}
	
	@SuppressWarnings("boxing")
	public static void makeChildrenNodes(Problem problem, Node node) {
		ArrayList<ArrayList<Integer>> possibleActions = new ArrayList<>();
		ArrayList<Integer> action = new ArrayList<>();
		
		// <1, 0, 1>
		action.add(1);
		action.add(0);
		action.add(1);
		possibleActions.add(action);
		action = new ArrayList<>();
		
		// <2, 0, 1>
		action.add(2);
		action.add(0);
		action.add(1);
		possibleActions.add(action);
		action = new ArrayList<>();
		
		// <0, 1, 1>
		action.add(0);
		action.add(1);
		action.add(1);
		possibleActions.add(action);
		action = new ArrayList<>();
		
		// <0, 2, 1>
		action.add(0);
		action.add(2);
		action.add(1);
		possibleActions.add(action);
		action = new ArrayList<>();
		
		// <1, 1, 1>
		action.add(1);
		action.add(1);
		action.add(1);
		possibleActions.add(action);
		action = new ArrayList<>();
		
		if (node.state.get(2) == 1) { // boat is on the wrong side
			for (int i = 0; i < possibleActions.size(); i++) {
				for (int j = 0; j < possibleActions.get(i).size(); j++) {
					possibleActions.get(i).set(j, (-1)*possibleActions.get(i).get(j));
				}
			}
		}
		
		for (ArrayList<Integer> currentAction : possibleActions) {
			for (int i = 0; i < currentAction.size(); i++) {
				action.add(currentAction.get(i) + node.state.get(i));
			}
			
			if (isActionValid(action, problem.visitedNodes))
				node.children.add(makeNode(action));
			
			action = new ArrayList<>();
		}
		
	}
	
	@SuppressWarnings("boxing")
	public static boolean isActionValid(ArrayList<Integer> action, ArrayList<ArrayList<Integer>> visitedNodes) {
		for (ArrayList<Integer> test : visitedNodes) {
			//System.out.println("Action: " + action);
			//System.out.println("Visited Nodes: " + visitedNodes);
			//System.out.println(action == test);
			
			if (action == test) {
				System.out.println("invalid action");
				return false;
			}
		}
		if (action.get(0) >= action.get(1) && 
				action.get(0) >= 0 &&
				action.get(0) <= 3 &&
				action.get(1) >= 0 &&
				action.get(1) <= 3 &&
				action.get(2) >= 0 &&
				action.get(2) <= 1) {	
			return true;
		}
		return false;
	}
}

class Node {
	public ArrayList<Integer> state = new ArrayList<>();
	public ArrayList<Node> children = new ArrayList<>();
}

class Problem {
	public Node initialState = new Node();
	public Node solution = new Node();
	public ArrayList<ArrayList<Integer>> visitedNodes = new ArrayList<>();
	
	@SuppressWarnings("boxing")
	public Problem() {
		this.initialState.state.add(3);
		this.initialState.state.add(3);
		this.initialState.state.add(1);
		
		this.solution.state.add(0);
		this.solution.state.add(0);
		this.solution.state.add(0);
	}
	
}