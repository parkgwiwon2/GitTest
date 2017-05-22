package robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import robot.input_thread.Thread1.Thread2;

public class input_thread {



	static class Thread1 extends Thread{

		static Robot robot;


		public void run(){

			try{
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis());
			//-----------------------------------------
			int count=26;
			//-----------------------------------------
			robot.delay(200);
			robot.setAutoDelay(70);
			robot.setAutoWaitForIdle(true);
			robot.mouseMove(1270, 7);
			leftClick();

			for (int i=0; i<count; i++ ){
				try{
				if(isInterrupted())
					return;
				robot.mouseMove(640,6);
				leftClick();
				//robot.delay(300);
				//leftClick();
				type(KeyEvent.VK_CONTROL,KeyEvent.VK_C);
				robot.delay(20);
				type(KeyEvent.VK_DOWN);
				robot.delay(20);
				robot.mouseMove(-380,9);
				leftClick();
				type(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
				type(KeyEvent.VK_CONTROL,KeyEvent.VK_V);
				robot.delay(20);
				type(KeyEvent.VK_TAB);
				robot.delay(20);}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}

			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
		}


		private void leftClick()
		{
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.delay(20);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(20);
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

		static class Thread2 extends Thread{

			@SuppressWarnings("resource")
			public void run(){

				Thread1 t1 = new Thread1();
				t1.start();
				Scanner scan = new Scanner(System.in);
				String message;
				message = scan.nextLine();
				if(!message.equals("")){
					t1.interrupt();
					t1.stop();

				}

			}
		}
	}
	public static void main(String [] arg){


		//	Thread1 t1 = new Thread1();

		Thread2 t2 = new Thread2();

		//		t1.start();

		t2.start();

	}


}
