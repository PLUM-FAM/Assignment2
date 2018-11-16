import java.util.*;

public class SmartSolver extends Solver
{
    //final private int size;
    //final private Node[][] maze;

    /* TODO:
     * 1.) Run dumbSolver search by picking least constrained for color order.
     */
	
	
	public ArrayList<Character> smartPossibleColors = new ArrayList<Character>();
	public ArrayList<StartNode> startNodes = new ArrayList<StartNode>(); //an array list for storing the possible colors's start nodes for this maze
	//smartPossibleColors = reader.possibleColorsForMaze;
    private Stack<Character> colorsFilled = new Stack<>(); //stack to keep track of colors completed so we can back track.


	int countCounter = 0;

    public SmartSolver(int size, String fileName)
    {
    	super(size, fileName);
        System.out.println("---------------------------------- ");
		System.out.println("Started smart solver for maze size: " + size);
        System.out.println("Initial maze: ");
        printMaze(size, maze);
    }
    
    
    public void solve()
    {
    	populateStartNodeArray();
    	StartNode nextColor;
    	//initialize lastcolor's value to X and Z because they are not valid color values(for this assignment)
    	StartNode lastColor = new StartNode(-1, -1, 100000, 'X'); 
    	StartNode lastLastColor = new StartNode(-1,-1, 100000, 'Z');
		char temp;

		char colorChar;
    	boolean finished = false;
    	int count = 0;
		while(!finished)
		{
			//System.out.println("-------------");
			//printMaze(size,maze);
			
			//finish check for end
			if(finishCheck(maze,size))
			{
				finished = true;
				break;
			}
			
			calcConstraints();
			//If all constraints are zero, reset the maze
			boolean allZero = false;
			for(StartNode n : startNodes)
			{
				if(n.constraint != 0)
				{
					allZero = true;
				}
			}
			if(allZero == false)
			{
				resetMaze();
				//System.out.println("__________________________");
				//System.out.println("Maze reset");
				//printMaze(size, maze);
			}
			//pick the most constrained value to use
			nextColor = getMostConstrained();
			colorChar = nextColor.value;
			
			//error detection for "back and forth" stuck
			if(nextColor.value == lastColor.value || nextColor.value == lastLastColor.value)
			{
				count++;
				//System.out.println(count);
			}
			
			if(count >= 10)
			{
				try{
					temp = colorsFilled.pop();
					delete(Character.toLowerCase(temp));
				}catch(EmptyStackException e)
				{}
				try{
					temp = colorsFilled.pop();
					delete(Character.toLowerCase(temp));
				}catch(EmptyStackException e)
				{}
				try{
					temp = colorsFilled.pop();
					delete(Character.toLowerCase(temp));
				}catch(EmptyStackException e)
				{}
					
				countCounter++;
				count = 0; //reset count
			}

			if(countCounter >= 2)
			{
				count = 0;
				countCounter = 0;
				resetMaze();
			}
			
			lastLastColor = lastColor;
			lastColor = nextColor;
				
			calcConstraints();
			nextColor = getMostConstrained();

			//System.out.println("the current color is :" + nextColor.value);
			
			
			
			
			

			//set color to uppercase to search through the maze and find the start nodes
			Character.toUpperCase(colorChar);
			for(int i = 0;i <= maze.length - 1; i++)
			{
				for(int j = 0; j <= maze[0].length - 1; j++)
				{
					if(maze[i][j].value == colorChar && i == reader.getStartX(colorChar) && j == reader.getStartY(colorChar))
					{
						//if color gets stuck (dumbSearch == false), 
						if(dumbSearch(colorChar, i, j) == false)
						{
							//delete (lowercase) current color & delete popped color from stack.
							delete(Character.toLowerCase(colorChar));
							try{
								colorChar = colorsFilled.pop();
								delete(Character.toLowerCase(colorChar));
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
		
		System.out.println("Smart Solver Solved Maze : ");
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
  						variableAssignments++;
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
  						variableAssignments++;
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
  						variableAssignments++;
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
  						variableAssignments++;
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
  	
  	
    
    
    //method to return the most constrained startNode or "color" from the list of possible colors.
    public StartNode getMostConstrained()
    {
    	StartNode lowest = new StartNode(-1, -1, 100000, 'x'); //temporary lowest start node
    	for (StartNode n : startNodes)
    	{
    		if(!colorsFilled.contains(n))	//if we haven't already done the color
    		{
    			if(n.constraint < lowest.constraint && n.constraint != 0) //if the current node's constraint value is less than the lowest and isn't already solved
    			{
    				lowest = n;				 //set new lowest startNode
    			}
    		}
    	}
    	return lowest;
    }
    
    
    public void populateStartNodeArray()
    {
        StartNode startB = new StartNode(reader.getStartX('B'), reader.getStartY('B'), 0, 'B');
        StartNode startA = new StartNode(reader.getStartX('A'), reader.getStartY('A'), 0, 'A');
        StartNode startW = new StartNode(reader.getStartX('W'), reader.getStartY('W'), 0, 'W');
        StartNode startR = new StartNode(reader.getStartX('R'), reader.getStartY('R'), 0, 'R');
        StartNode startP = new StartNode(reader.getStartX('P'), reader.getStartY('P'), 0, 'P');
        StartNode startD = new StartNode(reader.getStartX('D'), reader.getStartY('D'), 0, 'D');
        StartNode startO = new StartNode(reader.getStartX('O'), reader.getStartY('O'), 0, 'O');
        StartNode startG = new StartNode(reader.getStartX('G'), reader.getStartY('G'), 0, 'G');
        StartNode startK = new StartNode(reader.getStartX('K'), reader.getStartY('K'), 0, 'K');
        StartNode startY = new StartNode(reader.getStartX('Y'), reader.getStartY('Y'), 0, 'Y');
        StartNode startQ = new StartNode(reader.getStartX('Q'), reader.getStartY('Q'), 0, 'Q');
        
        
        //for each of the possible colors. narrow down the ones possible in this version of the maze and add them to the startNodes arraylist
        if(searchChar('B'))
        {
            startNodes.add(startB);
            //System.out.println("Added: B");
        }
        if(searchChar('A'))
        {
            startNodes.add(startA);
        }
        if(searchChar('W'))
        {
            startNodes.add(startW);
        }
        if(searchChar('R'))
        {
            startNodes.add(startR);
        }
        if(searchChar('P'))
        {
            startNodes.add(startP);
        }
        if(searchChar('D'))
        {
            startNodes.add(startD);
        }
        if(searchChar('O'))
        {
            startNodes.add(startO);
        }
        if(searchChar('G'))
        {
            startNodes.add(startG);
        }
        if(searchChar('K'))
        {
            startNodes.add(startK);
        }
        if(searchChar('Y'))
        {
            startNodes.add(startY);
        }
        if(searchChar('Q'))
        {
            startNodes.add(startQ);
        }
    }
    	
	//Method for calculating the constraint values for starting nodes
	private void calcConstraints()
	{
		for (StartNode n : startNodes)
		{
			int count = 0;
			int x = n.x;
			int y = n.y;
			
			try {
				//check north
				if(maze[x-1][y].value == '_')
				{
					count++;
				}
			}
			catch(IndexOutOfBoundsException e)
			{}
				
			try {
				//check east
				if(maze[x][y+1].value == '_')
				{
					count++;
				}
			}
			catch(IndexOutOfBoundsException e)
			{}
				
			try {
				//check south
				if(maze[x+1][y].value == '_')
				{
					count++;
				}
			}
			catch(IndexOutOfBoundsException e)
			{}
				
			try {
				//check west
				if(maze[x][y-1].value == '_')
				{
					count++;
				}
			}
			catch(IndexOutOfBoundsException e)
			{}
			
			n.constraint = count;
		}
	}
	
	
	//helper method for searching for a char in the maze, return true if the char is in the maze.
	private Boolean searchChar(char c)
	{
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(maze[i][j].value == c)
				{return true;}
			}
		}
		return false;
	}
}