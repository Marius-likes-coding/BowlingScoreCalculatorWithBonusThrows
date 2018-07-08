package main.java.Controllers;

import java.util.List;

import main.java.Models.Frame;

/**
 * This class is occupied with calculating the bowling total score from a list of frames.
 * @author Marius K
 */
public class ScoreCalculatingController 
{

	/**
	 * Calculates the total bowling score from a list of frames, taking into account the special rules for strikes and spares.
	 * @param allFrames
	 * @return the total bowling score of all the frames
	 */
	public int calculateTotalScoreWithFrames(List<Frame> allFrames) 
	{
		int totalScore = 0;
		
		Frame currentFrame = null;
		for (int i = 0; i < allFrames.size(); i++)
		{
			currentFrame = allFrames.get(i);

			totalScore += currentFrame.getFrameScore(); // no matter what type of frame, the sum of the knocked down pins from the two throws is always added
			
			if (currentFrame.isStrike())
			{
				// If we have a strike we add the number of knocked down pins of the next two throws to the score of the spare (which is 10)
				if ((i+1) < allFrames.size())
				{
					Frame nextFrame = allFrames.get(i+1);
					totalScore += nextFrame.getThrowPinsKnockedDown(0);
					
					if (nextFrame.getNumberOfThrows() > 1)
					{
						totalScore += nextFrame.getThrowPinsKnockedDown(1);
					}
					else if ((i+2) < allFrames.size())
					{
						Frame afternextFrame = allFrames.get(i+2);
						totalScore += afternextFrame.getThrowPinsKnockedDown(0);
					}
				}
			}
			else if (currentFrame.isSpare())
			{
				// If we have a spare we add the number of knocked down pins of the next throw to the score of the spare (which is 10)
				if ((i+1) < allFrames.size())
				{
					Frame nextFrame = allFrames.get(i+1);
					totalScore += nextFrame.getThrowPinsKnockedDown(0);
				}
			}
		}
		
		return totalScore;
	}

}
