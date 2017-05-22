package robot;
import java.awt.List;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;

public class mouseSample {


	public static void main(String[] args) throws InterruptedException {
		//MouseTest();
		MyMouseTest();
	}
	public static void MouseTest() {
		for (int i = 0; i < 10; i++) {
			PointerInfo pointerInfo = MouseInfo.getPointerInfo();
			System.out.println("Mouse Position : " + pointerInfo.getLocation());
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException ex) {
				break;
			}
		}
	}

	public static void MyMouseTest() throws InterruptedException {

		ArrayList<MousePoint> MPS = new ArrayList<MousePoint>();
		MPS.add(new MousePoint(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().x));
		long delay=0;

		do{
			Point nowPoint = MouseInfo.getPointerInfo().getLocation();
			MousePoint mp =  new MousePoint(nowPoint.x, nowPoint.y);
			if(MPS.get(MPS.size()-1).equals(mp)){
				delay= (delay==0) ? System.currentTimeMillis() :delay;
				continue;
			}
			if(delay!=0){
				delay = (System.currentTimeMillis()-delay);
				MousePoint dmp = new MousePoint(-5000, (int)delay);
				MPS.add(dmp);
				System.out.println("Delay times : " + dmp.toString());
				delay=0;}
			MPS.add(mp);

			System.out.println("Mouse Position : " + nowPoint.getLocation());

			Thread.sleep(30L);

		}
		while(true);


	}


}

