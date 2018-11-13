import java.util.*;

public class SmartSolver 
{
    final private int size;
    final private Node[][] maze;

    /* TODO:
     * 1.) Run dumbSolver search by picking least constrained for color order.
     * 2.) Remember to Recalculate constraints after each color has finished.
     * 
     * 
     * NOTE: the stack of colors filled contains StartNodes instead of chars for the smart solver.
     */


    Reader reader = new Reader();
    Random rand = new Random();
	public ArrayList<Character> smartPossibleColors = new ArrayList<Character>();
	
	public ArrayList<StartNode> startNodes = new ArrayList<StartNode>(); //an array list for storing the possible colors's start nodes for this maze
	//smartPossibleColors = reader.possibleColorsForMaze;

	
    private Stack<StartNode> colorsFilled = new Stack<>(); //stack to keep track of colors completed so we can back track.


    public SmartSolver(int size, String fileName)
    {
        Node[][] maze = reader.readFile(fileName, size);
        System.out.println("---------------------------------- ");
		System.out.println("Started smart solver for maze size: " + size);
        System.out.println("Initial maze: ");
        printMaze(size, maze);
		this.size = size;
		this.maze = maze;
		
		populateStartNodeArray();
		calcConstraints(); //initialize the constraints (from the orig maze)
		
		//dumb search choosing the most constrained 
		StartNode t = getMostConstrained();
		System.out.println("initial most constrained color is :" + t.value);
		
		
		
		System.out.println("Dumb Solver Solved Maze : ");
		printMaze(size, maze);
		System.out.println("------------------------- ");
    }

    
    
    //method to return the most constrained startNode or "color" from the list of possible colors.
    public StartNode getMostConstrained()
    {
    	StartNode lowest = new StartNode(-1, -1, 100000, 'a'); //temporary lowest start node
    	for (StartNode n : startNodes)
    	{
    		if(!colorsFilled.contains(n))	//if we haven't already done the color
    		{
    			if(n.constraint < lowest.constraint) //if the current node's constraint value is less than the lowest
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
    			System.out.println(n.value + " c = " + n.constraint);
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
}