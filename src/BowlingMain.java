

import java.util.ArrayList;

public class BowlingMain {
	
	BowlingCalculation calc;
	BowlingInput input;
	BowlingOutput output;
	
	int throwCount;
	int frameNum;
	Frame frame;
	Throw throwBall;
	ArrayList<Frame> frameList;
	boolean random;
	
	public BowlingMain(boolean random){
		this.random = random;
	}
	
	public void playGame(){
		//초기화
		initialize();
		
		while(!calc.isFinished()){
			
			//투구횟수 갱신
			throwCount++;
			
			// 투구를 프레임에 매핑
			if(frame == null
					|| frame.getNextThrow() == 0 
					|| frame.getNextThrow() == 1 // 각 프레임의 최초투구
					|| (frameNum != 10 && frame.getNextThrow() == 2 && frame.isStrike())){	//첫번째 투구가 스트라이크일 때, 다음 프레임으로 넘어간다
				frameNum++;
				
				// 점수 입력
				throwBall = input.getInputValue(random, frameNum, new Throw(throwCount, 0));
				
				frame = new Frame(frameNum, 0);
				frame.setThrow1(throwBall);
				frame = calc.calcPoint(frame);
				
				// 프레임을 리스트에 매핑
				frameList.add(frame);
			}else if(frame.getNextThrow() == 2){
				// 점수 입력
				throwBall = input.getInputValue(random, frameNum, new Throw(throwCount, 0));
				// 2번째 투구 시 프레임 점수 합산이 10점을 초과하면 10점이 되도록 2번째 투구 점수를 조정한다. 
				if(frameNum != 10 && (frame.getFrameSum() + throwBall.getPoint()) > 10 ){
					throwBall.setPoint(10 - frame.getFrameSum());
				}
				// 10프레임의 첫번째 투구가 스트라이크가 아닌 경우, 2번째 투구 시 프레임 점수 합산이 10점을 초과하면 10점이 되도록 2번째 투구 점수를 조정한다. 
				if(frameNum == 10 && !frame.isStrike() && (frame.getFrameSum() + throwBall.getPoint()) > 10 ){
					throwBall.setPoint(10 - frame.getFrameSum());
				}
				frame.setThrow2(throwBall);
				frame = calc.calcPoint(frame);
			}else if(frame.getNextThrow() == 3){
				// 점수 입력
				throwBall = input.getInputValue(random, frameNum, new Throw(throwCount, 0));
				if(frame.getFrameSum() < 20 && (frame.getFrameSum() + throwBall.getPoint()) > 20 ){
					throwBall.setPoint(20 - frame.getFrameSum());
				}
				frame.setThrow3(throwBall);
				frame = calc.calcPoint(frame);
			}

			// Strike, Spare 보너스 계산
			frameList = calc.calcBonus(frameList, throwCount, frameNum);
			
			// 프레임 합산 점수 계산
			frameList = calc.calcFramePointSum(frameList);
			
			// 점수 테이블 출력
			output.printPointSheet(frameList);
		}
		
		//종료선언
	}
	
	public void initialize(){
		output = new BowlingOutput();
		calc = new BowlingCalculation();
		calc.initialize();
		input = new BowlingInput(calc);
		
		throwCount = 0;
		frameNum = 0;
		frameList = new ArrayList<Frame>();
		
	}
	
	public static void main(String[] args){
		BowlingMain main = new BowlingMain(false);
		main.playGame();
	}
}
