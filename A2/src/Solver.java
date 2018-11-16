import java.util.*;
public class Solver {
	final int size;
	final Node[][] maze; //orig maze
	
	Reader reader = new Reader();
	Random rand = new Random();
    Stack<Character> colorsFilled = new Stack<>();

    int variableAssignments = 0;
    
	//parent constructor
	public Solver(int size, String fileName)
	{
		Node[][] maze = reader.readFile(fileName,size);
		this.size = size;
		this.maze = maze;
		/*	colorsFilled is an uppercase stack to keep track of previous colors
		*	completed, used for backtracking dumb method
		*/

	}
	
	// Method to check if the current color being solved, is complete
		public boolean colorFinishedCheck(char c, int x, int y)
		{
			// ensuring the colorGoal is an uppercase character
			char colorGoal = Character.toUpperCase(c);

			/*
			 *	 NORTH CHECKING 
			 */ 
			try
			{
				if(x - 1 != reader.getStartX(c) && y != reader.getStartY(c) && maze[x - 1][y].value == colorGoal)
				{
					colorsFilled.add(c);
					return true;
				}
			}
			catch(IndexOutOfBoundsException e){}


			/*
			 *	 SOUTH CHECKING
			*/
			try
			{
				if(x + 1 != reader.getStartX(c) && y != reader.getStartY(c) && maze[x + 1][y].value == colorGoal)
				{
					colorsFilled.add(c);
					return true;
				}
			}
			catch(IndexOutOfBoundsException e){}


			/*
			 * 	WEST CHECKING
			*/
			try
			{
				if(x != reader.getStartX(c) && y - 1 != reader.getStartY(c) && maze[x][y - 1].value == colorGoal)
				{
					colorsFilled.add(c);
					return true;
				}
			}
			catch(IndexOutOfBoundsException e){}


			/*
			 * EAST CHECKING   
			*/
			try
			{
				if(x != reader.getStartX(c) && y + 1 != reader.getStartY(c) && maze[x][y + 1].value == colorGoal)
				{
					colorsFilled.add(c);
					return true;
				}
			}
			catch(IndexOutOfBoundsException e){}

			return false;
		 }

		
		
		// method to check if the maze has been solved 
		protected boolean finishCheck(Node[][] m, int x)
		{
			for(int i = 0; i <= size - 1; i++)
			{
				for(int j = 0; j <= size - 1; j++)
				{
					// loops though each node in the maze. If an underscore is detected, then the maze is not complete
					if(maze[i][j].value == '_')
					{
						return false;
					}
					
				}
			}

			//If the maze is filled, but not every color was completed, reset the maze
			if(reader.possibleColorsForMaze.size() != colorsFilled.size())
			{
				for(Character n : colorsFilled)
				{
					//System.out.println(n);
				}
				//System.out.println();
				for(Character n : reader.possibleColorsForMaze)
				{
					//System.out.println(n);
				}
				//printMaze(size, maze);
				colorsFilled.clear();
				resetMaze();
				return false;
			}

			return true;
		}


		// Method to reset all nodes in maze back to the intial given maze 
		protected void resetMaze()
		{
			for(int i = 0; i <= size - 1; i++)
			{
				for(int j = 0; j <= size - 1; j++)
				{
					// check if the node has a lowercase value, if so change it back to an underscore
					// this leaves the uppercase values alone (original maze) 
					if(Character.isLowerCase(maze[i][j].value))
					{
						maze[i][j].value = '_';
					}
				}
			}
		
		} // end of resetMaze method

			
		// Helper method for printing 2D arrays.
		protected void printMaze(int x, Node[][] maze) {
				
			// print out the current maze 
			System.out.println("\nMaze " + x + ": ");
				
			// loop through the array and print each node
			for(int i = 0; i < x; i++)
			{
				for(int j = 0; j < x; j++)
				{
					System.out.print(maze[i][j].value);
				}
				System.out.println("");
			}
			System.out.println("");
		
		} //end of printMaze
			

		//checking the coord x,y to see if it is free
		protected boolean isFree(int x, int y)
		{
			//if coord is within the bounds of the maze
			if((x >= 0 && x < size) && (y>=0 && y < size)) 
			{
				//checking if the current node in the maze is a underscore (free space)
				if(maze[x][y].value == '_')
				{
					return true;
				}
			}
			return false;

		 } //end of isFree method
		
		//Helper method for backtracking to delete a color from the maze (by replacing all of it's lower case chars).
		protected void delete(char color)
		{
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
			try
			{
				colorsFilled.remove(Character.toUpperCase(color));
			} catch(ArrayIndexOutOfBoundsException e)
			{}
		}
}
