package game;



public class test {

	String Name ="FGO";
	int chargeTime = 5;
	String formulra ="L+16";
	int level = 120;

	int setpoint= 30;


	public void main(){
		Game Fgo = new Game(Name,chargeTime,formulra,level);
		Fgo.settingPoint(setpoint, 0);
		Fgo.calculateMaxPoint();



	}

}
