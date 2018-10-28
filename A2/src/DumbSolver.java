import java.util.*;
public class DumbSolver {
	private int size;
	private char[][] maze;
	
	public DumbSolver(int size, char[][] maze)
	{
		size = this.size;
		maze = this.maze;
		char[][] solvedMaze = solve();
		
		System.out.println("Solved Maze : ");
		printMaze(size, solvedMaze);
	}
	
	
	
	public char[][] solve() 
	{
		char[][] currentMaze = maze;

		return currentMaze;
	}
	
	
	
	
	//helper method for printing 2D arrays.
	private void printMaze(int x, char[][] maze) {
		System.out.println("\nMaze " + x + ": ");
		for(int i = 0; i < x-1; i++)
		{
			for(int j = 0; j < x-1; j++)
			{
				System.out.print(maze[j][i]);
			}
			System.out.println("");
		}
	}
}

