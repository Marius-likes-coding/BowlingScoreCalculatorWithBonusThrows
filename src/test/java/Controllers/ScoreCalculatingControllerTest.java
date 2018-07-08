package test.java.Controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.Controllers.ScoreCalculatingController;
import main.java.Models.Frame;

public class ScoreCalculatingControllerTest 
{
	private ScoreCalculatingController scoreCalculatingController;
	
	@Before
    public void setUp() 
	{
		scoreCalculatingController = new ScoreCalculatingController();
    }

	 
    @After
    public void tearDown() 
    {
    }

	@Test
	public void testParseFrames_normal_frames_are_calculated_correctly()
	{
		 List<Frame> allFrames = new ArrayList<>();
		 allFrames.add(new Frame(1,5));
		
		int score = scoreCalculatingController.calculateTotalScoreWithFrames(allFrames);
		
		assertEquals(score, 1+5);
	}

	@Test
	public void testParseFrames_spares_are_calculated_correctly()
	{
		 List<Frame> allFrames = new ArrayList<>();
		 allFrames.add(new Frame(1,9)); // spare
		 allFrames.add(new Frame(1,5)); 
		
		int score = scoreCalculatingController.calculateTotalScoreWithFrames(allFrames);
		
		assertEquals(score, (9+1 + 1) + (1 + 5));
	}

	@Test
	public void testParseFrames_single_strikes_are_calculated_correctly()
	{
		 List<Frame> allFrames = new ArrayList<>();
		 allFrames.add(new Frame(10)); // strike
		 allFrames.add(new Frame(2,2)); 
		 allFrames.add(new Frame(3,3)); 
	
		int score = scoreCalculatingController.calculateTotalScoreWithFrames(allFrames);
		
		assertEquals(score, (10 + (2+2)) + (2 + 2) + (3 + 3));
	}

	@Test
	public void testParseFrames_double_strikes_are_calculated_correctly()
	{
		 List<Frame> allFrames = new ArrayList<>();
		 allFrames.add(new Frame(10)); // strike
		 allFrames.add(new Frame(10)); // strike
		 allFrames.add(new Frame(3,3)); 
	
		int score = scoreCalculatingController.calculateTotalScoreWithFrames(allFrames);
		
		assertEquals(score, (10 + (10 + 3)) + (10 + (3+3)) + (3 + 3));
	}

	@Test
	public void testParseFrames_triple_strikes_are_calculated_correctly()
	{
		 List<Frame> allFrames = new ArrayList<>();
		 allFrames.add(new Frame(10)); // strike
		 allFrames.add(new Frame(10)); // strike
		 allFrames.add(new Frame(10)); // strike
		 allFrames.add(new Frame(3,3)); 
	
		int score = scoreCalculatingController.calculateTotalScoreWithFrames(allFrames);
		
		assertEquals(score, (10 + (10 + 10)) + (10 + (10 + 3)) + (10 + (3+3)) + (3 + 3));
	}

}