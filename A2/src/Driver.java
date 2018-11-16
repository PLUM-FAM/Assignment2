/*
 * driver class takes in argument inputs to determine the correct solver to call and to pass on the size of the maze (an int)
 * 
 * args 0 = filename	(string)
 * args 1 = size of maze (int)
 * args 2 = solver to call (char d or s)
 * 
 * 
 */

public class Driver {
	public static void main(String args[])
	{
		//Reader r = new Reader();
		String fileName = args[0];
	    int size = Integer.parseInt(args[1]);		 
		
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
<<<<<<< HEAD
			//System.out.println("Start time: " + startTime);
			//System.out.println("Finish time: " + finishTime);
			System.out.println("'Dumb' Finish time in Milliseconds " + ((finishTime - startTime) / 1000000));
=======
			//System.out.println("Start time: " + startTime); //show start and end ns (removed for assignment submission)
			//System.out.println("Finish time: " + finishTime);
			System.out.println("'Dumb' Finish time in Milliseconds " + ((finishTime - startTime) / 1000000)); //calculate ms from ns
>>>>>>> ada37ee2f16c029a6a6e98b86325f8fb3144b42b
			System.out.println("Dumb solver variable assignments: " + DumbResult.variableAssignments);
		}else if(args[2].equals("s"))
		{
			//solve with our "smart implementation"
			System.out.println("Solving with smart solver");
			SmartSolver smartResult = new SmartSolver(size, fileName);
			long startTime = System.nanoTime();
			smartResult.solve();
			long finishTime = System.nanoTime();
<<<<<<< HEAD
			//System.out.println("Start time: " + startTime);
			//System.out.println("Finish time: " + finishTime);
			System.out.println("'Smart' Finish time in Milliseconds " + ((finishTime - startTime) / 1000000));
=======
			//System.out.println("Start time: " + startTime); //show start and end ns (removed for assignment submission)
			//System.out.println("Finish time: " + finishTime);
			System.out.println("'Smart' Finish time in Milliseconds " + ((finishTime - startTime) / 1000000)); //calculate ms from ns
>>>>>>> ada37ee2f16c029a6a6e98b86325f8fb3144b42b
			System.out.println("Smart solver variable assignments: " + smartResult.variableAssignments);
		}else
		{
			System.out.println("error loading solver type with input " + args[2]);
		}
	}	
}
