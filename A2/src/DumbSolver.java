import java.util.*;
public class DumbSolver {
	final private int size;
	final private Node[][] maze; //orig maze
	
    private Stack<Character> colorsFilled = new Stack<>(); //stack to keep track of colors completed so we can back track.


	Reader reader = new Reader();
	Random rand = new Random();
	
	//Dumb solver with random variable and value ordering (no forward checking).
	public DumbSolver(int size, Node[][] maze)
	{
		System.out.println("---------------------------------- ");
		System.out.println("Started dumb solver for maze size: " + size);
		System.out.println("Initial maze: ");
		printMaze(size, maze);
		this.size = size;
		this.maze = maze;
		//solve();
		
		System.out.println("Dumb Solver Solved Maze : ");
		printMaze(size, maze);
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
	public void solve() 
	{
		boolean finished = false;
		while(!finished)
		{
			if(finishCheck(maze,size)) //finish check for end
			{
				finished = true;
				break;
			}
			
			//pick a random variable to use
			int randomColor = rand.nextInt(reader.possibleColorsForMaze.size());
			char color = reader.possibleColorsForMaze.get(randomColor);
			
			
			for(int i = 0;i <= maze.length; i++)
			{
				for(int j = 0; j <= maze[0].length; j++)
				{
					if(maze[i][j].value == color)
					{
						if(dumbSearch(color, i, j) == false)
						{
							//delete current color & delete popped color from stack. (lower case only)
							delete(Character.toLowerCase(color));
							color = colorsFilled.pop();
							delete(Character.toLowerCase(color));
						}			
					}
				}
			}
		}
	}

	
	//Helper method for backtracking to delete a color from the maze (by replacing all of it's lower case chars).
	private void delete(char color)
	{
		for(int i = 0; i <= maze.length; i++)
		{
			for(int j = 0; j <= maze.length; j++)
			{
				if(maze[i][j].value == color)
				{
					maze[i][j].value = '_';
				}
			}
		}
	}
	
	
	private boolean dumbSearch(char color, int startx, int starty)
	{
		int currentX = startx;
		int currentY = starty;
		
		
		char GoalColor = Character.toUpperCase(color);
		ArrayList<Character> checked = new ArrayList<Character>();
		Boolean colorFinished = false;
		
		while(colorFinished == false)
		{
			//check for stuck - if we have a full list of checked then return false.
			if(checked.size() == 4)
			{
				return false;
			}
			
			if(colorFinishedCheck(color, currentX, currentY)) //if an adjacent node is a finish for the current color.
			{
				return true;
			}
			
			int next = rand.nextInt(4); //pick a direction.
			
			switch(next)
			{
			case 0: //north
				//check for adjacent capitol letter
				if(isFree(currentX, currentY-1))
				{
					//change current x and y
					//checked list reset to 0.
					//set new x and y node value to be the color character
				}else //north is not free
				{
					//add north to checked.
					checked.add('n');
				}
				break;
			case 1: //east
				break;
			case 2://south
				break;
			case 3://west
				break;
			default: 
				break;
			}
			
		}
		
		//check direction
			//if capitol then return true break out.
			//if open then "place lower case there" and change current location to that location.
			//if not valid check another random. (up to 4 directions)
		
		//return false if cannot complete color
	}

	//check adjacent spots for finish capitol letter
	public boolean colorFinishedCheck(char c, int x, int y)
	{
		char colorGoal = Character.toUpperCase(c);
		
		if(x != Reader.getStartX(c) && y-1 != Reader.getStartY(c) && maze[x][y-1].value == colorGoal)//north check
		{
			
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
			for(int i = 0; i < x; i++)
			{
				for(int j = 0; j < x; j++)
				{
					System.out.print(maze[i][j].value);
				}
				System.out.println("");
			}
			System.out.println("");
		}
		
		
				
		
		
		//checking the coord x,y to see if it is free
		private boolean isFree(int x, int y)
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

