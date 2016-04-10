

// 2인 랜덤 플레이는 1프레임씩 번갈아가며 플레이하는 부분은 완전히 구현되지 못하고 각각 플레이하도록 되어 있습니다.(시간 상 구현을 못함)
// 사용자가 직접 값을 입력할 경우는 BowlingMain 생성자의 파라미터를 false로 주면 됩니다.
public class BowlingRandomPlay {
	public static void main(String[] args){
		BowlingMain play1 = new BowlingMain(true);
		BowlingMain play2 = new BowlingMain(true);
		
		play1.playGame();
		play2.playGame();
	}
}
