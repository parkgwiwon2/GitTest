package robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.SynchronousQueue;

/**
 * A Java Robot example class.
 *
 * Caution: Using the Java Robot class improperly can cause
 * a lot of system problems. I had to reboot my Mac ~10
 * times yesterday while trying to debug this code.
 *
 * I created this class to demonstrate the Java Robot
 * class on a Mac OS X system, though it should run on Linux
 * or Windows as well.
 *
 * On a Mac system, I place the TextEdit text editor in the
 * upper-left corner of the screen, and put a bunch of blank lines
 * in the editor. Then I run this Java Robot example from
 * Eclipse or the Unix command line.
 *
 * It types the three strings shown in the code below into
 * the text editor.
 *
 * Many thanks to the people on the Mac Java-dev mailing list
 * for your help.
 *
 * @author Alvin Alexander, http://devdaily.com
 *
 */
public class JavaRobotExample2_bpp
{
	Robot robot = new Robot();

	public static void main(String[] args) throws AWTException
	{
		new JavaRobotExample2_bpp();
	}

	public JavaRobotExample2_bpp() throws AWTException
	{
		System.out.println(System.currentTimeMillis());
		//-----------------------------------------
		int count=18;
		//-----------------------------------------
		robot.delay(3000);
		robot.setAutoDelay(70);
		robot.setAutoWaitForIdle(true);
		robot.mouseMove(1270, 7);
		leftClick();

		for (int i=0; i<count; i++ ){

			//robot.mouseMove(-1024, 0);  //画面左上
			robot.mouseMove(-120,120);
			leftClick();
			robot.mouseMove(410,240);
			leftClick();
			//robot.delay(300);
			//leftClick();
			type(KeyEvent.VK_CONTROL,KeyEvent.VK_C);
			robot.mouseMove(-500,230);
			leftClick();
			type(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
			type(KeyEvent.VK_CONTROL,KeyEvent.VK_V);
			robot.mouseMove(-520,420);
			leftClick();
			robot.delay(700);
			robot.mouseMove(-840,860);
			leftClick();
			robot.delay(700);
			robot.mouseMove(-750,570);
			leftClick();
			robot.mouseMove(410,240);
			robot.delay(200);
			leftClick();
			robot.delay(200);
			type(KeyEvent.VK_LEFT);    //他アド用
			robot.delay(200);
			type(KeyEvent.VK_CONTROL,KeyEvent.VK_C);
			robot.delay(200);
			type(KeyEvent.VK_DOWN);
			robot.delay(200);
			type(KeyEvent.VK_RIGHT);   //他アド用
			robot.delay(200);
			robot.mouseMove(-610,610);
			leftClick();
			type(KeyEvent.VK_CONTROL,KeyEvent.VK_V);
			robot.mouseMove(-610,660);
			leftClick();
			type(KeyEvent.VK_CONTROL,KeyEvent.VK_V);
			robot.mouseMove(-510,800);
			leftClick();
			robot.delay(3000);
			robot.mouseMove(-200,80);
			leftClick();
		}



		//    type(KeyEvent.VK_CONTROL,KeyEvent.VK_A);

		//    robot.delay(4000);
		//    robot.mouseMove(40, 130);
		//    robot.delay(500);
		//
		//    leftClick();
		//    robot.delay(500);
		//    leftClick();
		//
		//    robot.delay(500);
		//    type("Hello, world");
		//
		//    robot.mouseMove(40, 160);
		//    robot.delay(500);
		//
		//    leftClick();
		//    robot.delay(500);
		//    leftClick();
		//
		//    robot.delay(500);
		//    type("This is a test of the Java Robot class");
		//
		//    robot.delay(50);
		//    type(KeyEvent.VK_DOWN);
		//
		//    robot.delay(250);
		//    type("Four score and seven years ago, our fathers ...");
		//
		//    robot.delay(1000);
		System.out.println(System.currentTimeMillis());
		System.exit(0);
	}

	private void leftClick()
	{
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(200);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(1500);
	}

	private void type(int i)
	{
		robot.delay(40);
		robot.keyPress(i);
		robot.keyRelease(i);
	}
	private void type(int one,int two)
	{
		robot.delay(40);
		robot.keyPress(one);
		robot.keyPress(two);
		robot.keyRelease(one);
		robot.keyRelease(two);
	}

	private void type(String s)
	{
		byte[] bytes = s.getBytes();
		for (byte b : bytes)
		{
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123) code = code - 32;
			robot.delay(40);
			robot.keyPress(code);
			robot.keyRelease(code);
		}
	}
}