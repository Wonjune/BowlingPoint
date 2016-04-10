

import java.util.ArrayList;

public class BowlingOutput {
	
	private String title;
	private String pointLine;
	private String sumLine;
	private int framePointSum;
	
	public BowlingOutput(){
		title = "1		2		3		4		5		6		7		8		9		10";
		pointLine = "";
		sumLine = "";
	}
	
	public void printPointSheet(ArrayList<Frame> frameList){
		System.out.println(title);
		pointLine = "";
		sumLine = "";
		Frame frame;
		Throw throwBall;
		
		//투구 포인트 라인 출력 
		for(int i = 0 ; i < frameList.size() ; i++){
			frame = frameList.get(i);
			switch(frame.getFrameNum()){
			case 10:
				pointLine += frame.getThrow1().getPrintPoint() + "	";
				if(frame.getThrow2() != null)
					pointLine += frame.getThrow2().getPrintPoint() + "	";
				else
					pointLine += " 	";
				if(frame.getThrow3() != null)
					pointLine += frame.getThrow3().getPrintPoint() + "	";
				else
					pointLine += " 	";
				break;
			default:
				pointLine += frame.getThrow1().getPrintPoint() + "	";
				if(frame.getThrow2() != null)
					pointLine += frame.getThrow2().getPrintPoint() + "	";
				else
					pointLine += " 	";
				break;
			}
		}
		
		//프레임 포인트 라인출력
		// 1) isVisible 이 true 인 라인까지만 출력
		for(int i = 0 ; i < frameList.size() ; i++){
			frame = frameList.get(i);
			if(frame.isVisible())
				sumLine += frame.getFramePointSum() + "		";
		}
		
		System.out.println(pointLine);
		System.out.println(sumLine);
	}
}
