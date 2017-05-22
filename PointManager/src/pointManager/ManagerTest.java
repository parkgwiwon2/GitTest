package pointManager;

public class ManagerTest {

	static String Name ="FGO";
	static int chargeTime = 5;
	static String formulra ="L+16";
	static int level = 120;

	static int setpoint= 20;


	public static void main(String args[]){
		pointManagerObject Fgo = new pointManagerObject(Name,chargeTime,formulra,level);
		Fgo.settingPoint(setpoint, 0);
		//Fgo.settingTime(setPoint, goalTime);
		//Fgo.calculateMaxPoint();
		Fgo.PrintState();
	}

}
