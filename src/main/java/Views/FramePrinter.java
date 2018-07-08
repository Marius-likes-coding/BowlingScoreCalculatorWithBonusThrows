package main.java.Views;

import java.util.List;

import main.java.Models.Frame;

/**
 * Prints the list of frames to standard output.
 * @author Marius K
 */
public class FramePrinter 
{

	/**
	 * Prints the list of frames to standard output, considering special cases like strikes and spares.
	 * @param allFrames
	 */
	public void printFrames(List<Frame> allFrames) 
	{
		int previousPinsKnockedDown = -1;
		for (Frame frame : allFrames)
		{
			System.out.print("Frame : ");
			
			for (int i=0; i < frame.getNumberOfThrows(); i++)
			{
				int currentPinsKnockedDown = frame.getThrowPinsKnockedDown(i);
				
				if (currentPinsKnockedDown == 10 && previousPinsKnockedDown != 0)
				{
					System.out.print("X ");
				}
				else if (previousPinsKnockedDown + currentPinsKnockedDown == 10 && previousPinsKnockedDown != 10)
				{
					System.out.print("/ ");
				}
				else
				{
					System.out.print(currentPinsKnockedDown + " ");
				}
				
				previousPinsKnockedDown = currentPinsKnockedDown;
			}
			System.out.print("\n");
			previousPinsKnockedDown = -1;
		}
	}
}
