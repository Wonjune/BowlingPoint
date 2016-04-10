

public class Throw {
	private int throwNum;
	private int point;
	private String printPoint;
	
	public Throw(){
		this.throwNum = 0;
		this.point = 0;
	}
	
	public Throw(int throwNum, int point){
		this.throwNum = throwNum;
		this.point = point;
	}

	public int getThrowNum() {
		return throwNum;
	}

	public void setThrowNum(int throwNum) {
		this.throwNum = throwNum;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPrintPoint() {
		return printPoint;
	}

	public void setPrintPoint(String printPoint) {
		this.printPoint = printPoint;
	}
	
	
}
