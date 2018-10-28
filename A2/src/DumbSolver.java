import java.util.*;
public class DumbSolver {
	final private int size;
	final private Node[][] maze; //orig maze
	
	
	//Dumb solver with random variable and value ordering (no forward checking).
	public DumbSolver(int size, Node[][] maze)
	{
		System.out.println("---------------------------------- ");
		System.out.println("Started dumb solver for maze size: " + size);
		System.out.println("Initial maze: ");
		printMaze(size, maze);
		this.size = size;
		this.maze = maze;
		Node[][] solvedMaze = solve(maze);
		
		System.out.println("Dumb Solver Solved Maze : ");
		printMaze(size, solvedMaze);
		System.out.println("------------------------- ");

	}
	
	/*
	 * BT(Level)
		If all variables assigned
			PRINT Value of each Variable
			RETURN or EXIT (RETURN for more solutions)
			(EXIT for only one solution)
		V := PickUnassignedVariable()
		Variable[Level] := V
		Assigned[V] := TRUE
		for d := each member of Domain(V)
			Value[V] := d
			OK := TRUE
			for each constraint C such that
					V is a variable of C
					and all other variables of C are assigned.
				if C is not satisfied by the current set of assignments
				OK := FALSE
			if(OK)
				BT(Level+1)
		return
	*/
	public Node[][] solve(Node[][] orig) 
	{
		Node[][] currentMaze = orig; //current maze
		boolean finished = false;
		while(!finished)
		{
			if(finishCheck(currentMaze,size)) //finish check for end
			{
				finished = true;
				break;
			}
			
			//pick a random variable to use
			
		}
		return currentMaze;
	}


	public boolean finishCheck(Node[][] m, int x)
	{
		for(int i = 0; i < x-1; i++)
		{
			for(int j = 0; j < x-1; j++)
			{
				if(maze[j][i].value == '_')
				{return true;}
			}
		}
		return false;
	}
		
	//helper method for printing 2D arrays.
		private void printMaze(int x, Node[][] maze) {
			System.out.println("\nMaze " + x + ": ");
			for(int i = 0; i < x-1; i++)
			{
				for(int j = 0; j < x-1; j++)
				{
					System.out.print(maze[j][i].value);
				}
				System.out.println("");
			}
			System.out.println("");
		}
}

