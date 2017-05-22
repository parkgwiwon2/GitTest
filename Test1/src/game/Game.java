package game;
import java.util.Calendar;
import java.util.Calendar;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

public class Game {

	private int id;
	private String name;
	private int chargeTime;
	private String formulra;
	private int level;
	private int maxPoint;

	private int setPoint;
	private int nowPoint;
	private int goalPoint;

	private Calendar setTime;
	private Calendar goalTime;
	private Calendar fullChargeTime;

	Game(String name, int charGeTime, int maxPoint){
		this.name = name;
		this.chargeTime = charGeTime;
		this.maxPoint = maxPoint;
	}

	Game(String name, int charGeTime, String formulra, int level){
		this.name = name;
		this.chargeTime = charGeTime;
		this.formulra = formulra;
		this.level = level;

		calculateMaxPoint();
	}

	public void settingPoint(int setPoint, int goalPoint){
		this.setPoint = setPoint;
		this.goalPoint= goalPoint;
		this.setTime = Calendar.getInstance();
		calculateCargeTime();
	}

	public void settingTime(int setPoint, Calendar goalTime){
		this.setPoint = setPoint;
		this.goalTime = goalTime;
		this.setTime = Calendar.getInstance();
		Long timeDiff = this.goalTime.getTimeInMillis() - this.setTime.getTimeInMillis();
		int chargePoint = (int)(timeDiff/60/5);
		this.goalPoint = this.setPoint+chargePoint;
		calculateFullCargeTime();

	}

	public void calculateCargeTime(){
		boolean noGoalPoint = goalPoint == 0 ? true : false;
		int chargePoint = (noGoalPoint ? this.maxPoint-setPoint :this.maxPoint-goalPoint);
		chargePoint *= this.chargeTime;

		this.goalTime = this.setTime;
		this.goalTime.add(Calendar.MINUTE,chargePoint);
		if(noGoalPoint)
			this.fullChargeTime=this.goalTime;
		else
			calculateFullCargeTime();

	}

	private void calculateFullCargeTime(){
		int chargePoint =  this.maxPoint-setPoint;
		chargePoint *= this.chargeTime;
		this.fullChargeTime.add(Calendar.MINUTE,chargePoint);

	}



	public void calculateMaxPoint(){
		 ScriptEngineManager mgr = new ScriptEngineManager();
		 ScriptEngine engine = mgr.getEngineByName("JavaScript");

		 this.formulra.replace("L", Integer.toString(this.level));

		 try {
			this.maxPoint = (Integer)engine.eval(this.formulra);
			System.out.println(this.formulra);

		 } catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void calculrateNowPoint(){
		Calendar now = Calendar.getInstance();
		Long timeDiff = now.getTimeInMillis() - this.setTime.getTimeInMillis();
		int chargePoint = (int)(timeDiff/60/5);
		this.nowPoint = this.setPoint+chargePoint;

	}


	public void PrintState(){
		calculrateNowPoint();
		System.out.println("Name : "+this.name);
		System.out.println("Lv : "+this.level);
		System.out.println("Point : "+this.nowPoint+"/"+this.maxPoint);
		System.out.println("goalTime : "+this.goalTime.getTime());
		System.out.println("FulChargelTime : "+this.fullChargeTime.getTime());

	}

	public String getName() {
		return name;
	}


	public int getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(int chargeTime) {
		this.chargeTime = chargeTime;
	}
	public String getFormulra() {
		return formulra;
	}
	public void setFormulra(String formulra) {
		this.formulra = formulra;
	}

	public void setPoint(int point) {
	}
	public int getGoalPoint() {
		return goalPoint;
	}
	public void setGoalPoint(int goalPoint) {
		this.goalPoint = goalPoint;
	}
	public Calendar getSetTime() {
		return setTime;
	}
	public void setSetTime(Calendar setTime) {
		this.setTime = setTime;
	}
	public Calendar getGoalTime() {
		return goalTime;
	}
	public void setGoalTime(Calendar goalTime) {
		this.goalTime = goalTime;
	}
	public Calendar getFullChargeTime() {
		return fullChargeTime;
	}
	public void setFullChargeTime(Calendar fullChargeTime) {
		this.fullChargeTime = fullChargeTime;
	}






}
