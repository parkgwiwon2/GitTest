package robot;

public class MousePoint {
	int x;
	int y;
	public MousePoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public boolean equals(MousePoint mp) {
		if(this.x==mp.x &&  this.y==mp.y)
			return true;
		else
			return false;
	}

	 public String toString() {
	        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
	    }

}
