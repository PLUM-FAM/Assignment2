import java.util.*;
public class DumbSolver {
	final private int size;
	final private Node[][] maze; //orig maze

	Reader reader = new Reader();
	Random rand = new Random();
	
	private Stack <Node> s = new Stack<>();
	
	private int[][] startColor;
	private int[][] endColor;
	
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
			int randomColor = rand.nextInt(reader.possibleColorsForMaze.length);
			char color = reader.possibleColorsForMaze.get(randomColor);
			for(int i = 0; i++; i <= maze.length)
			{
				for(int j = 0; j++; j <= maze[0].length)
				{
					if(maze[i][j] == color)
					{
						
					}
				}
			}
		}
		return currentMaze;
	}

	public boolean solveUtil()
	{
		if(solveUtil)
		{
			return true;
		}
		else
		{
			maze[][] = -1;
		}
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
		
		private void dumbSearch(char[][] maze, char color)
		{
			//capitolize color char = end node check thing
			char GoalColor = Character.toUpperCase(color);
			
			//random number for nsew
			
			//check direction
				//if capitol  then return true break out.
				//if open then "place lower case there" and change current location to that location.
				//if not valid check another random. (up to 4 directions)
			
			//backtrack the current color. reset(color)
		}
				
		
		
		//checking the coord x,y to see if it is free
		private boolean isFree(Node[][] maze, int size, int x, int y)
		{
			if((x >= 0 && x < size) && (y>=0 && y < size)) //if coord is within the bounds of the maze
			{
				if(maze[x][y].value == '_')
				{
					return true;
				}
			}
			return false;
		}
}

