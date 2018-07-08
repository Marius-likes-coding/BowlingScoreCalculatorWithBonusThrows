package test.java.Models;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.Models.Frame;

public class FrameTest 
{	
	@Before
    public void setUp() 
	{
    }

	 
    @After
    public void tearDown() 
    {
    }
    

	@Test
	public void testIsStrike()
	{
		Frame frame = new Frame(10,0);
		assertTrue(frame.isStrike());
	}

	@Test
	public void testIsSpare()
	{
		Frame frame = new Frame(5,5);
		assertTrue(frame.isSpare());
	}

	@Test
	public void testIsValid_normal() 
	{
		Frame frame = new Frame(5,5);
		assertTrue(frame.isValid());
	}

	@Test
	public void testIsValid_strike() 
	{
		Frame frame = new Frame(10,0);
		assertTrue(frame.isValid());
	}

	@Test
	public void testIsValid_zero_knocked_down() 
	{
		Frame frame = new Frame(0,0);
		assertTrue(frame.isValid());
	}

	@Test
	public void testIsValid_false_if_sum_of_knocked_down_pins_larger_than_10() 
	{
		Frame frame = new Frame(6,5);
		assertFalse(frame.isValid());
	}

	@Test
	public void testGetFrameScore()
	{
		Frame frame = new Frame(4,5);
		assertEquals(frame.getFrameScore(), 9);
	}

}
