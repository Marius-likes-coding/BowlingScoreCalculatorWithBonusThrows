package test.java.Views;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.Models.Frame;
import main.java.Views.FramePrinter;

public class FramePrinterTest 
{	
	private FramePrinter framePrinter;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
    public void setUp() 
	{
	    System.setOut(new PrintStream(outContent));
	    framePrinter = new FramePrinter();
    }

	 
    @After
    public void tearDown() 
    {
        System.setOut(originalOut);
    }


	@Test
	public void testPrintFrames_strike()
	{
		List<Frame> allFrames = new ArrayList<>();
		allFrames.add(new Frame(10,0));
		
		framePrinter.printFrames(allFrames);
		
	    assertTrue(outContent.toString().contains("Frame : X "));
	}

	@Test
	public void testPrintFrames_spare()
	{
		List<Frame> allFrames = new ArrayList<>();
		allFrames.add(new Frame(1,9));
		
		framePrinter.printFrames(allFrames);
		
	    assertTrue(outContent.toString().contains("Frame : 1 / "));
	}

	@Test
	public void testPrintFrames_normal_frame()
	{
		List<Frame> allFrames = new ArrayList<>();
		allFrames.add(new Frame(1,3));
		
		framePrinter.printFrames(allFrames);
		
	    assertTrue(outContent.toString().contains("Frame : 1 3 "));
	}
	
}