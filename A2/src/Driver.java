public class Driver {

	
	public static void main(String args[])
	{
		//Reader r = new Reader();
		String fileName = args[0];
	    int size = Integer.parseInt(args[1]);

		//read in file to a 2D array of nodes to represent the maze.
		 
		
		System.out.println("loaded " + fileName);
		System.out.println("size = " + size);
		
		if(args[2].equals("d"))
		{
			//solve with our "dumb implementation"
			System.out.println("Solving with dumb solver");
			DumbSolver DumbResult = new DumbSolver(size, fileName);
			//DumbResult.dumbPossibleColors = r.possibleColorsForMaze;
			long startTime = System.nanoTime();
			DumbResult.solve();
			long finishTime = System.nanoTime();
			System.out.println("Start time: " + startTime);
			System.out.println("Finish time: " + finishTime);
			System.out.println("Finish time in Milliseconds " + ((finishTime - startTime) / 1000000));
		}else if(args[2].equals("s"))
		{
			//solve with our "smart implementation"
			System.out.println("Solving with smart solver");
			SmartSolver smartResult = new SmartSolver(size, fileName);
			//SmartSolver SmartResult = new SmartSolver(size,maze);
		}else
		{
			System.out.println("error loading solver type with input " + args[2]);
		}
		
		
		
		
		
	}	
}
