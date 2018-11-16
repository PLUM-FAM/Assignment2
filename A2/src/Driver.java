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
			long startTime = System.nanoTime();
			DumbResult.solve();
			long finishTime = System.nanoTime();
			System.out.println("Start time: " + startTime);
			System.out.println("Finish time: " + finishTime);
			System.out.println("'Dumb' Finish time in Milliseconds " + ((finishTime - startTime) / 1000000));
			System.out.println("Dumb solver variable assignments: " + DumbResult.variableAssignments);
		}else if(args[2].equals("s"))
		{
			//solve with our "smart implementation"
			System.out.println("Solving with smart solver");
			SmartSolver smartResult = new SmartSolver(size, fileName);
			long startTime = System.nanoTime();
			smartResult.solve();
			long finishTime = System.nanoTime();
			System.out.println("Start time: " + startTime);
			System.out.println("Finish time: " + finishTime);
			System.out.println("'Smart' Finish time in Milliseconds " + ((finishTime - startTime) / 1000000));
			System.out.println("Smart solver variable assignments: " + smartResult.variableAssignments);
		}else
		{
			System.out.println("error loading solver type with input " + args[2]);
		}
		
		
		
		
		
	}	
}
