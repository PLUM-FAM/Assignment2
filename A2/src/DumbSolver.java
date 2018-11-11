import java.util.*;
public class DumbSolver {
	final private int size;
	final private Node[][] maze; //orig maze
	

	/*TODO:
	 * 1.) Maze sometimes gets stuck after completing a color, not sure why.
	 * 	Stuck on print line "<Direction> is <color>."
	 * 2.) If there are only two colors left, there is an instance where we will 
	 *  keep adding a specific color to the stack, getting stuck, and popping the color, 
	 * 	conitinuing to pick the wrong specific color, and constantly adding and popping
	 *  the same color.
	 * 3.) 
	 * 
	 */
    private Stack<Character> colorsFilled = new Stack<>(); //stack to keep track of colors completed so we can back track.
	public ArrayList<Character> dumbPossibleColors = new ArrayList<Character>();

	Reader reader = new Reader();
	Random rand = new Random();
	
	//Dumb solver with random variable and value ordering (no forward checking).
	public DumbSolver(int size, String fileName)
	{
		Node[][] maze = reader.readFile(fileName, size);

		System.out.println("---------------------------------- ");
		System.out.println("Started dumb solver for maze size: " + size);
		System.out.println("Initial maze: ");
		printMaze(size, maze);
		this.size = size;
		this.maze = maze;		

	}
	
	
	public void solve() 
	{
		boolean finished = false;
		while(!finished)
		{
			//System.out.println("First line inside while(!finished)");
			if(finishCheck(maze,size)) //finish check for end
			{
				//System.out.println("Inside finishCheck");
				printMaze(size, maze);
				finished = true;

				break;
			}
			
			//pick a random variable to use
			int randomColor = rand.nextInt(reader.possibleColorsForMaze.size());
			
			//possibleColorsForMaze is uppercase
			char color = reader.possibleColorsForMaze.get(randomColor);
			System.out.println("Color initially picked " + color);

			//colorsFilled is uppercase
			//loop while we pick a color that hasn't been finished yet
			while(colorsFilled.contains(color))
			{	
				randomColor = rand.nextInt(reader.possibleColorsForMaze.size());
				color = reader.possibleColorsForMaze.get(randomColor);
			}

			Character.toUpperCase(color);
			System.out.println("Color picked: " + color);
			
			for(int i = 0;i <= maze.length - 1; i++)
			{
				//System.out.println("a");
				for(int j = 0; j <= maze[0].length - 1; j++)
				{
					//System.out.println("b");
					if(maze[i][j].value == color)
					{
						//System.out.println("c");
						printMaze(size, maze);
						System.out.println("Maze print before dumbSearch for " + color + " is called.");
						if(dumbSearch(color, i, j) == false)
						{
							//System.out.println("d");
							//delete current color & delete popped color from stack. (lower case only)
							delete(Character.toLowerCase(color));
							try{
								color = colorsFilled.pop();
								delete(Character.toLowerCase(color));
							}catch(EmptyStackException e)
							{
								i = 10000;
								j = 10000;
							}
							
						}	
						else
						{
							i = 10000;
							j = 10000;
						}	
					}
				}
			}
		}
		System.out.println("Dumb Solver Solved Maze : ");
		printMaze(size, maze);
		System.out.println("------------------------- ");
	}

	
	//Helper method for backtracking to delete a color from the maze (by replacing all of it's lower case chars).
	private void delete(char color)
	{
		printMaze(size, maze);
		System.out.println("Color to delete: " + color);
		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				if(maze[i][j].value == color)
				{
					maze[i][j].value = '_';
				}
			}
		}
		printMaze(size, maze);
	}
	
	
	private boolean dumbSearch(char color, int startx, int starty)
	{
		int currentX = startx;
		int currentY = starty;
		
		char setColor = Character.toLowerCase(color);
		
		
		//char GoalColor = Character.toUpperCase(color);
		ArrayList<Integer> checked = new ArrayList<Integer>();
		Boolean colorFinished = false;
		
		while(colorFinished == false)
		{
			//pick a direction.
			int next = rand.nextInt(4); 
			while(checked.contains(next))
			{
				next = rand.nextInt(4);
			}
			switch(next)
			{
				//north	
				case 0: 
					//check for adjacent capitol letter
					if(isFree(currentX - 1, currentY))
					{
						//change current y
						currentX = currentX - 1;
						//checked list reset to 0.
						checked.clear();
						//set new x and y node value to be the color character
						maze[currentX][currentY].value = setColor;
					}
					//north is not free
					else 
					{
						//add north to checked.
						checked.add(0);
					}
					break;
				case 1: //east
					if(isFree(currentX, currentY + 1))
					{
						//change current x
						currentY = currentY + 1;
						//checked list reset to 0.
						checked.clear();
						//set new x and y node value to be the color character
						maze[currentX][currentY].value = setColor;
					}
					//east is not free
					else
					{
						//add east to checked
						checked.add(1);
					}
					break;
				case 2://south
					if(isFree(currentX + 1, currentY))
					{
						//change current y
						currentX = currentX + 1;
						//checked list reset to 0.
						checked.clear();
						//set new x and y node value to be the color character
						maze[currentX][currentY].value = setColor;
					}
					//south is not free
					else
					{
						//add south to checked
						checked.add(2);
					}
					break;
				case 3://west
					if(isFree(currentX, currentY - 1))
					{
						//change current y
						currentY = currentY - 1;
						//checked list reset to 0.
						checked.clear();
						//set new x and y node value to be the color character
						maze[currentX][currentY].value = setColor;
					}
					//west is not free
					else
					{
						//add west to checked
						checked.add(3);
					}
					break;
			default: 
				System.out.println("Default reached");
				break;
			}

			//if an adjacent node is a finish for the current color.
			if(colorFinishedCheck(color, currentX, currentY)) 
			{
				colorsFilled.add(color);
				colorFinished = true;
				return true;
			}

			//check for stuck - if we have a full list of checked then return false.
			if(checked.size() == 4)
			{
				return false;
			}
		}

		if(colorFinished)
		{
			return true;
		}
		else
		{
			return false;
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

		// maze[x][y-1].value = 'N';
		// maze[x][y+1].value = 'S';
		// maze[x-1][y].value = 'W';
		// maze[x+1][y].value = 'E';
		// return true;
		try{
			//north check
			if(x - 1 != reader.getStartX(c) && y != reader.getStartY(c) && maze[x - 1][y].value == colorGoal)
			{
				System.out.println("Check North");
				System.out.println("North is " + colorGoal);
				return true;
			}
		}catch(IndexOutOfBoundsException e)
		{

		}
		try{
			//south check
			if(x + 1 != reader.getStartX(c) && y != reader.getStartY(c) && maze[x + 1][y].value == colorGoal)
			{
				System.out.println("Check South");
				System.out.println("South is " + colorGoal);
				return true;
			}
		}catch(IndexOutOfBoundsException e)
		{

		}
		try{
			//west check
			if(x != reader.getStartX(c) && y - 1 != reader.getStartY(c) && maze[x][y - 1].value == colorGoal)
			{
				System.out.println("Check West");
				System.out.println("West is " + colorGoal);
				return true;
			}
		}catch(IndexOutOfBoundsException e)
		{

		}
		try{
			//east check
			if(x != reader.getStartX(c) && y + 1 != reader.getStartY(c) && maze[x][y + 1].value == colorGoal)
			{
				System.out.println("Check East");
				System.out.println("East is " + colorGoal);
				return true;
			}
		}catch(IndexOutOfBoundsException e)
		{

		}
		return false;
	 }

	
	
	
	public boolean finishCheck(Node[][] m, int x)
	{
		for(int i = 0; i < x-1; i++)
		{
			for(int j = 0; j < x-1; j++)
			{
				if(maze[i][j].value == '_')
				{return false;}
			}
		}
		return true;
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

