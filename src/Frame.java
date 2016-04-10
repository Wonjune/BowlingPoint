

public class Frame {
	private int frameNum;
	private int framePoint;
	private int framePointSum;
	private boolean isVisible;
	private boolean isStrike;
	private boolean isSpare;
	private Throw throw1;
	private Throw throw2;
	private Throw throw3;
	
	public Frame(int frameNum, int framePoint){
		this.frameNum = frameNum;
		this.framePoint = framePoint;
		this.isVisible = false;
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}

	public int getFramePoint() {
		return framePoint;
	}

	public void setFramePoint(int framePoint) {
		this.framePoint = framePoint;
	}

	public Throw getThrow1() {
		if(throw1 == null)
			return null;
		return throw1;
	}

	public void setThrow1(Throw throw1) {
		this.throw1 = throw1;
	}

	public Throw getThrow2() {
		if(throw2 == null)
			return null;
		return throw2;
	}

	public void setThrow2(Throw throw2) {
		this.throw2 = throw2;
	}

	public Throw getThrow3() {
		if(throw3 == null)
			return null;
		return throw3;
	}

	public void setThrow3(Throw throw3) {
		this.throw3 = throw3;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}

	public int getFramePointSum() {
		return framePointSum;
	}

	public void setFramePointSum(int framePointSum) {
		this.framePointSum = framePointSum;
	}

	public int getNextThrow(){
		if(throw1 == null)
			return 1;
		if(throw2 == null)
			return 2;
		if(frameNum == 10 && throw3 == null)
			return 3;
		return 0;
	}
	
	public Throw getThrow(int throwNum){
		if(throw1 != null && throw1.getThrowNum() == throwNum)
			return throw1;
		if(throw2 != null && throw2.getThrowNum() == throwNum)
			return throw2;
		if(throw3 != null && throw3.getThrowNum() == throwNum)
			return throw3;
		return null;
	}

	public boolean isContainThrowNum(int throwNum){
		if(throw1 != null && throw1.getThrowNum() == throwNum)
			return true;
		if(throw2 != null && throw2.getThrowNum() == throwNum)
			return true;
		if(throw3 != null && throw3.getThrowNum() == throwNum)
			return true;
		return false;
	}
	
	public int getFrameSum(){
		int sum = 0;
		if(throw1 != null)
			sum += throw1.getPoint();
		if(throw2 != null)
			sum += throw2.getPoint();
		if(frameNum == 10 && throw3 != null)
			sum += throw3.getPoint();
		return sum;
	}
	
	public boolean isLastFrame(){
		if(getFrameNum() == 10)
			return true;
		return false;
	}
	
}
