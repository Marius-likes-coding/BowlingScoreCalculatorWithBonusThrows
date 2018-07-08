package main.java.Controllers;

import main.java.Models.Frame;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is occupied with parsing the frames from a list of throws.
 * @author Marius K
 */
public class ParsingController 
{
	public static final int TOTAL_PINS_IN_FRAME = 10;
	public static final int TOTAL_FRAMES_IN_GAME = 10;
	
	public ParsingController() 
	{
	}

	/**
	 * Parses the list of throws
	 * @param throwList List of strings containing the number of knocked down pins at each throw
	 * @return a list of frames, a frame consists of two throws (a strike frame does not have a second throw)
	 * @throws NumberFormatException (if an input string is not a correct number of knocked down throws)
	 * @throws IllegalArgumentException (if one of the throws or frames does not comply with the bowling rules and restrictions)
	 */
	public List<Frame> parseGame(String[] throwList) throws NumberFormatException,IllegalArgumentException
	{
		 List<Frame> allFrames = new ArrayList<>();
		 
		int index = 0;
		Frame currentFrame = null;
		while (index < throwList.length)
		{
			int currentThrow = parseThrow(throwList[index], index);
			
			if (currentFrame != null)
			{
				// the frame started by the previous throw is not yet complete 
				currentFrame.addThrowPinsKnockedDown(currentThrow);
				
				if ( index+2 == throwList.length && allFrames.size()+1 == TOTAL_FRAMES_IN_GAME && (currentFrame.isSpare() || currentFrame.isStrike()) )
				{
					index++;
					currentThrow = parseThrow(throwList[index], index);
					currentFrame.addThrowPinsKnockedDown(currentThrow);
				}
				
				addFrameToListIfValid(allFrames, currentFrame);
				currentFrame = null; // now it is complete
			}
			else 
			{
				// the previous frame was complete, start a new one with the current throw
				currentFrame = new Frame();
				currentFrame.addThrowPinsKnockedDown(currentThrow);
				
				if (currentFrame.isStrike() && allFrames.size()+1 != TOTAL_FRAMES_IN_GAME) // no second throw when a strike occurred
				{
					addFrameToListIfValid(allFrames, currentFrame);
					currentFrame = null;
				}
			}
			index++;
		}
		
		// We have 9 frames parsed but there are more throws, we must parse the 10. frame which is possibly a frame with a bonus throw
		if  (currentFrame != null || allFrames.size() > TOTAL_FRAMES_IN_GAME)
		{
			throw new IllegalArgumentException("The given throws do not fit a valid sequence of frames. "
					+ "We want either a partial game or a complete game that gives a third throw on the 10th frame if a strike or spare occured.");
		}
		
		return allFrames;
	}

	/**
	 * Add the currentFrame to the frame list if it is a valid frame, that is, its sum is smaller than 10 (the total amount of pins)
	 * @param allFrames
	 * @param currentFrame
	 */
	private void addFrameToListIfValid(List<Frame> allFrames, Frame currentFrame) 
	{
		if (!currentFrame.isValid())
		{
			throw new IllegalArgumentException("The sum of knocked down pins of the two throws of frame number " + allFrames.size()+1 + " exceeds the total amount of pins (" + TOTAL_PINS_IN_FRAME + ")");
		}
		allFrames.add(currentFrame);
	}

	/**
	 * Parsed the number of knocked down pins from the given string and throws the appropriate errors if the number is in wrong format or not at all a number.
	 * @param string the throw to parse
	 * @param index the index of the throw in the given list of throws
	 * @return the parsed number of knocked down pins for the given throw
	 * @throws NumberFormatException (if an input string is not a correct number of knocked down throws)
	 * @throws IllegalArgumentException (if one of the throws or frames does not comply with the bowling rules and restrictions)
	 */
	private int parseThrow(String string, int index) throws NumberFormatException,IllegalArgumentException 
	{
		int pinsKnockedDownByThrow;
		
		try
		{
			pinsKnockedDownByThrow = Integer.parseInt(string);
		}
		catch(NumberFormatException e)
		{
			throw new NumberFormatException("Throw at index " + index + " has an invalid number of knocked down pins (must be an integer between 0 and " + TOTAL_PINS_IN_FRAME + ").");
		}
	   
		if (!isCorrectNumberOfKnockedDownPins(pinsKnockedDownByThrow))
		{
			throw new IllegalArgumentException("Throw at index " + index + " has an invalid number of knocked down pins (must be an integer between 0 and " + TOTAL_PINS_IN_FRAME + ").");
		}
	   
		return pinsKnockedDownByThrow;
	}

	/**
	 * @param pinsKnockedDownByThrow
	 * @return true if the number of knocked down pins is in the range (0,10), false otherwise
	 */
	private boolean isCorrectNumberOfKnockedDownPins(int pinsKnockedDownByThrow) 
	{
		return 0 <= pinsKnockedDownByThrow && pinsKnockedDownByThrow <= TOTAL_PINS_IN_FRAME;
	}

}
