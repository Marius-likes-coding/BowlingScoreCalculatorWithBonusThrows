package main.java.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent one bowling frame which consists of two consecutive throws.
 * @author Marius K
 */
public class Frame 
{
	public static final int TOTAL_PINS_IN_FRAME = 10;
	private List<Integer> throwList;

	
	public Frame(int... _throwList)
	{
		throwList = new ArrayList<>();
		
		for(int i = 0; i < _throwList.length; i++){
			throwList.add(_throwList[i]);
	    }
	}


	public int getThrowPinsKnockedDown(int throwIndex) 
	{
		return throwList.get(throwIndex);
	}

	public void addThrowPinsKnockedDown(int pinsKnockedDown) 
	{
		this.throwList.add(pinsKnockedDown);
	}
	
	public int getNumberOfThrows()
	{
		return throwList.size();
	}

	
	/**
	 * @return true if the frame is a strike, that is that all the pins where knocked down by the first throw, otherwise false
	 */
	public boolean isStrike()
	{
		return getThrowPinsKnockedDown(0) == TOTAL_PINS_IN_FRAME;
	}

	/**
	 * @return true if the frame is a spare, that is that all the remaining pins where knocked down by the second throw, otherwise false
	 */
	public boolean isSpare()
	{
		return getThrowPinsKnockedDown(0) + getThrowPinsKnockedDown(1) == TOTAL_PINS_IN_FRAME;
	}

	/**
	 * @return true if the frame is valid, that is that the sum of the knocked down pins from the two throws does not exceed the total number of pins, otherwise false
	 * one must keep in mind the special rule for the 10th frame where a bonus throw can be given when either a strike or a spare was thrown
	 */
	public boolean isValid()
	{
		if (throwList.size() == 1)
			return isStrike();
		else if (throwList.size() == 2)
			return getThrowPinsKnockedDown(0) + getThrowPinsKnockedDown(1) <= TOTAL_PINS_IN_FRAME;
		else // bonus throw because 10th frame (3 throws), only the case if strike or spare
			return (isStrike() || isSpare()) && throwList.stream().mapToInt(i -> i.intValue()).sum() <= 30; 
	}
	
	/**
	 * @return true if the frame contains 3 throws, that is the 10th frame where a bonus throw can be given when either a strike or a spare was thrown
	 */
	public boolean isLastFrameWithBonusThrow()
	{
		return throwList.size() == 3;
	}
	

	/**
	 * @return the sum of the knocked down pins from the two throws
	 */
	public int getFrameScore()
	{
		return throwList.stream().mapToInt(i -> i.intValue()).sum();
	}
}