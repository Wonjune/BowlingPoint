

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BowlingInput {
	
	StringBuffer inputMention;
	BowlingCalculation calc;
	
	public BowlingInput(BowlingCalculation calc){
		this.calc = calc;
	}
	
	public Throw getInputValue(boolean random, int frameNum, Throw throwBall){
		String input = getInput(random, frameNum);
		while(!checkInputValue(input)){
			System.out.println("입력 가능한 값을 입력하세요. (1-10, F)");
			input = getInput(random, frameNum);
		}
		
		// F 또는 f 값이 들어오면 0점으로 처리
		if("F".equals(input) || "f".equals(input)){
			throwBall.setPoint(0);
		}else{
			throwBall.setPoint(Integer.parseInt(input)); 
			throwBall.setPrintPoint(input);
		}

		return throwBall;
	}
	
	public String getInput(boolean random, int frameNum){
		String input = "";
		inputMention = new StringBuffer(frameNum + "Frame 핀 수 : ");
		System.out.print(inputMention.toString());
		if(random){
			// 랜덤 입력인 경우는 자동으로 1 ~ 10 사이 정수 생성
			input = getRandomInput();
			System.out.println(input);
		}else{
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				input = br.readLine();
				System.out.println("");
			}catch(Exception e){}
		}
		
		return input;
	}
	
	public boolean checkInputValue(String input){
		int numVal = 0;
		boolean isOkay = false;
		try{
			// 입력값이 숫자인지, 숫자면 0 ~ 10 사이의 숫자인지 체크
			numVal = Integer.parseInt(input);
			if(numVal >= 0 && numVal <= 10)
				isOkay = true;
			else
				isOkay = false;
			
			return isOkay;
		}catch(NumberFormatException ne){
			// 입력값이 문자이면 F 값인지 체크
			if("F".equals(input) || "f".equals(input))
				isOkay = true;
			else
				isOkay = false;
			
			return isOkay;
		}	
	}
	
	public String getRandomInput(){
		int randomVal = (int)(Math.random() * 10 + 1);
		return String.valueOf(randomVal);
	}
	
}
