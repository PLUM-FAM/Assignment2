import java.util.*;
public class DumbSolver extends Solver{
	
	
	//Dumb solver with random variable and value ordering (no forward checking).
	public DumbSolver(int size, String fileName)
	{
		super(size, fileName);		
		System.out.println("---------------------------------- ");
		System.out.println("Started dumb solver for maze size: " + size);
		System.out.println("Initial maze: ");
		printMaze(size, maze);	
	}
	
	//set up solve funcion, calls dumbSearch every time a color is to be completed
	public void solve() 
	{
		/*	once finished is set to true (finish check returns true), while loop breaks
		*	and solve ends
		*/
		boolean finished = false;
		while(!finished)
		{
			//finish check for end
			if(finishCheck(maze,size))
			{
				finished = true;
				break;
			}
			
			//pick a random variable to use
			int randomColor = rand.nextInt(reader.possibleColorsForMaze.size());
			
			/*	possibleColorsForMaze is an uppercase ArrayList to keep track of 
			 *	only the colors that are present in the maze we are trying to solve
			 *	(some mazes have more colors than others)
			 */
			char color = reader.possibleColorsForMaze.get(randomColor);

			//loop while we pick a color that hasn't been finished yet
			while(colorsFilled.contains(color))
			{	
				randomColor = rand.nextInt(reader.possibleColorsForMaze.size());
				color = reader.possibleColorsForMaze.get(randomColor);
			}

			//set color to uppercase to search through the maze and find the start nodes
			Character.toUpperCase(color);
			for(int i = 0;i <= maze.length - 1; i++)
			{
				for(int j = 0; j <= maze[0].length - 1; j++)
				{
					if(maze[i][j].value == color && i == reader.getStartX(color) && j == reader.getStartY(color))
					{
						//if color gets stuck (dumbSearch == false), 
						if(dumbSearch(color, i, j) == false)
						{
							//delete (lowercase) current color & delete popped color from stack.
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
						
						i = 10000;
						j = 10000;	
					}
				}
			}
		}
		System.out.println("Dumb Solver Solved Maze : ");
		printMaze(size, maze);
		System.out.println("------------------------- ");
	}

	
	
	
	//Attempt to solve a color randomly, if stuck, break.
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
			//pick a random direction.
			int next = rand.nextInt(4); 
			//loop while we pick a direction we've already checked so no direction is checked twice
			while(checked.contains(next))
			{
				next = rand.nextInt(4);
			}

			//switch for directions
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

			//if an adjacent node is a finish node for the current color.
			if(colorFinishedCheck(color, currentX, currentY)) 
			{
				//colorsFilled.add(color);
				colorFinished = true;
				return true;
			}

			//check for stuck - if we have a full list of checked then return false.
			if(checked.size() == 4)
			{
				return false;
			}
		}

		//finished maze check
		if(colorFinished)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
} 

