

import java.util.ArrayList;

public class BowlingCalculation {
	
	private boolean isFinished;
	
	public void initialize(){		
		// 기타 플래그 값 초기화
		isFinished = false;

	}
	
	public Frame calcPoint(Frame frame){
		
		// framePoint 계산하여 매핑
		frame.setFramePoint(frame.getFrameSum());
		
		switch(frame.getFrameNum()){
		case 10:
			// 첫번째 투구
			//스트라이크 처리
			if(frame.getThrow1() != null && frame.getThrow1().getPoint() == 10 && frame.getNextThrow() == 2){
				frame.setStrike(true);
				frame.setVisible(false);
				frame.getThrow1().setPrintPoint("X");
			}
			
			// 두번째 투구 
			if(frame.getThrow2() != null && frame.getThrow2().getPoint() >= 0 && frame.getNextThrow() == 3){
				// 스페어 처리
				if(!frame.isStrike() && frame.getFramePoint() == 10){
					frame.setSpare(true);
					frame.setVisible(false);
					frame.getThrow2().setPrintPoint("/");
				// 첫번째 투구에서 스트라이크, 두번째 투구에서 다시 스트라이크
				}else if(frame.isStrike() && frame.getFramePoint() == 20){
					frame.setVisible(false);
					frame.getThrow2().setPrintPoint("X");
				// 첫번째 투구에서 스트라이크, 두번째 투구에서 잔여핀 발생
				}else if(frame.isStrike() && frame.getFramePoint() != 20){
					frame.setVisible(false);
				}else{
					frame.setVisible(true);
				}
			}
			
			// 세번째 투구 : 스페어 상태에서 스트라이크할 경우 처리, 스트라이크 상태에서  다시 스트라이크 칠 경우 처리, 스트라이크 상황에서 스페어 처리
			if(frame.getThrow3() != null && frame.getThrow3().getPoint() >= 0){
				// 두번째 투구에서 스페어 상황일 때 스트라이크
				if(!frame.isStrike() && frame.isSpare() && frame.getFramePoint() == 30){
					frame.setStrike(true);
					frame.setVisible(true);
					frame.getThrow3().setPrintPoint("X");
				// 첫번째, 두번째 모두 스트라이크 상황에서 스트라이크
				}else if(!frame.isSpare() && frame.isStrike() && frame.getFramePoint() == 30){
					frame.setVisible(true);
					frame.getThrow3().setPrintPoint("X");
				// 세번째 투구에서 스페어
				}else if(!frame.isSpare() && frame.isStrike() && frame.getFramePoint() == 20){
					frame.setSpare(true);
					frame.setVisible(true);
					frame.getThrow3().setPrintPoint("/");
				}else{
					frame.setVisible(true);
				}
			}
			break;
		default:
			// 첫번째 투구로 스트라이크하면 스트라이크 처리
			if(frame.getThrow1() != null && frame.getThrow1().getPoint() == 10){
				frame.setStrike(true);
				frame.setVisible(false);
				frame.getThrow1().setPrintPoint("X");
			}else{
				frame.setStrike(false);
			}
			
			// 두번째 투구로 스페어하면 스페어 처리
			if(frame.getThrow2() != null && frame.getThrow2().getPoint() >= 0){
				if(frame.getFramePoint() == 10){
					frame.setSpare(true);
					frame.setVisible(false);
					frame.getThrow2().setPrintPoint("/");
				}else{
					frame.setSpare(false);
					frame.setVisible(true);
				}
			}
		}
		
		// 10번 프레임의 로직 처리
		// 10번 프레임의 3번째 투구를 해야할지 판정한다. 마지막 투구였다고 판단하면 isFinished = true 처리한다.
		if((frame.getNextThrow() == 3 && frame.getFramePoint() < 10) || 
				(frame.getFrameNum() == 10 && frame.getThrow3() != null)){
			isFinished = true;
		}
		
		return frame;
	}
	
	public ArrayList<Frame> calcBonus(ArrayList<Frame> frameList, int throwCount, int frameNum){
		
		Frame chkFrame;
		Throw chkThrow;
		int lastThrowPoint = 0;
		int beforeThrowPoint = 0;

		for(int i = frameNum - 1 ; i >= 0 ; i--){
			chkFrame = frameList.get(i);
			// 최종 투구 점수 받기
			if(chkFrame.isContainThrowNum(throwCount))
				lastThrowPoint = chkFrame.getThrow(throwCount).getPoint();
			
			// 이전 투구 점수 받기
			if(chkFrame.isContainThrowNum(throwCount - 1))
				beforeThrowPoint = chkFrame.getThrow(throwCount - 1).getPoint();
		}
		
		for(int i = 0 ; i < frameNum ; i++){
			chkFrame = frameList.get(i);
			if(chkFrame.getFrameNum() < frameNum){
				// 1) 최종 투구로 부터 2턴 전 투구가 스트라이크인 경우 해당 투구가 속한 프레임의 점수에 보너스 점수를 합산하고 visible 처리를 한다.
				if(chkFrame.isContainThrowNum(throwCount - 2) && chkFrame.isStrike() == true){
					chkFrame.setFramePoint(chkFrame.getFramePoint() + beforeThrowPoint + lastThrowPoint);
					chkFrame.setVisible(true);
					break;
				}
				
				// 2) 최종 투구로 부터 1턴 전 투구가 스페어인 경우 해당 투구가 속한 프레임의 점수에 보너스 점수를 합산하고 visible 처리를 한다.
				if(chkFrame.isContainThrowNum(throwCount - 1) && chkFrame.isSpare() == true){
					chkFrame.setFramePoint(chkFrame.getFramePoint() + lastThrowPoint);
					chkFrame.setVisible(true);
					break;
				}
			}

		}
		
		// 10번 프레임의 로직 처리
		
		return frameList;
	}
	
	public boolean isFinished(){
		return isFinished;
	}

	public ArrayList<Frame> calcFramePointSum(ArrayList<Frame> frameList) {
		int framePointSum = 0;
		for(Frame frame : frameList){
			if(frame.isVisible()){
				framePointSum += frame.getFramePoint();
				frame.setFramePointSum(framePointSum);
			}
		}
		return frameList;
	}
	
}
