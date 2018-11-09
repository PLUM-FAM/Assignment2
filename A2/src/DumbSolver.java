import java.util.*;
public class DumbSolver {
	final private int size;
	final private Node[][] maze; //orig maze
	
	
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
		
		
		System.out.println("Dumb Solver Solved Maze : ");
		printMaze(size, maze);
		System.out.println("------------------------- ");

	}
	
	
	public void solve() 
	{
		boolean finished = false;
		while(!finished)
		{
			System.out.println("First line inside while(!finished)");
			if(finishCheck(maze,size)) //finish check for end
			{
				System.out.println("Inside finishCheck");
				finished = true;

				break;
			}
			
			//pick a random variable to use
			System.out.println(reader.possibleColorsForMaze.size());
			int randomColor = rand.nextInt(reader.possibleColorsForMaze.size());
			char color = reader.possibleColorsForMaze.get(randomColor);
			
			
			for(int i = 0;i <= maze.length; i++)
			{
				System.out.println("a");
				for(int j = 0; j <= maze[0].length; j++)
				{
					System.out.println("b");
					if(maze[i][j].value == color)
					{
						System.out.println("c");
						if(dumbSearch(color, i, j) == false)
						{
							System.out.println("d");
							//delete current color & delete popped color from stack. (lower case only)
							delete(Character.toLowerCase(color));
							color = colorsFilled.pop();
							delete(Character.toLowerCase(color));
							i = maze.length + 1;
							j = maze[0].length + 1;
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
			System.out.println("X: " + currentX + " Y: " + currentY);

			//check for stuck - if we have a full list of checked then return false.
			if(checked.size() == 4)
			{
				return false;
			}
			
			//if an adjacent node is a finish for the current color.
			if(colorFinishedCheck(color, currentX, currentY)) 
			{
				return true;
			}
			
			//pick a direction.
			int next = rand.nextInt(4); 
			
			switch(next)
			{
				//north	
				case 0: 
					//check for adjacent capitol letter
					if(isFree(currentX, currentY - 1))
					{
						//change current y
						currentY = currentY - 1;
						//checked list reset to 0.
						checked.clear();
						//set new x and y node value to be the color character
						maze[currentX][currentY].value = color;
					}
					//north is not free
					else 
					{
						//add north to checked.
						checked.add('n');
					}
					break;
				case 1: //east
					if(isFree(currentX + 1, currentY))
					{
						//change current x
						currentX = currentX + 1;
						//checked list reset to 0.
						checked.clear();
						//set new x and y node value to be the color character
						maze[currentX][currentY].value = color;
					}
					//east is not free
					else
					{
						//add east to checked
						checked.add('e');
					}
					break;
				case 2://south
					if(isFree(currentX, currentY + 1))
					{
						//change current y
						currentY = currentY + 1;
						//checked list reset to 0.
						checked.clear();
						//set new x and y node value to be the color character
						maze[currentX][currentY].value = color;
					}
					//south is not free
					else
					{
						//add south to checked
						checked.add('s');
					}
					break;
				case 3://west
					if(isFree(currentX - 1, currentY))
					{
						//change current y
						currentX = currentX - 1;
						//checked list reset to 0.
						checked.clear();
						//set new x and y node value to be the color character
						maze[currentX][currentY].value = color;
					}
					//west is not free
					else
					{
						//add west to checked
						checked.add('w');
					}
					break;
			default: 
				System.out.println("Default reached");
				break;
			}
			
		}
		return false;
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
		
		//north check
		if(x != reader.getStartX(c) && y-1 != reader.getStartY(c) && maze[x][y-1].value == colorGoal)
		{
			return true;
		}
		//south check
		else if(x != reader.getStartX(c) && y+1 != reader.getStartY(c) && maze[x][y+1].value == colorGoal)
		{
			return true;
		}
		//west check
		else if(x-1 != reader.getStartX(c) && y != reader.getStartY(c) && maze[x-1][y].value == colorGoal)
		{
			return true;
		}
		//east check
		else if(x+1 != reader.getStartX(c) && y != reader.getStartY(c) && maze[x+1][y].value == colorGoal)
		{
			return true;
		}
		return false;
	}

	
	
	
	public boolean finishCheck(Node[][] m, int x)
	{
		for(int i = 0; i < x-1; i++)
		{
			for(int j = 0; j < x-1; j++)
			{
				if(maze[j][i].value == '_')
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

