
public class GameOfStrings {

	int maxRounds = 0;
	int minIndex = 0;
	int maxIndex = 1;
	
	public GameOfStrings() {
		
	}
	public static void main(String[] args) {
		/*String S = "ABCD";
		System.out.println(S.substring(0, 1));
		System.out.println(S.substring(1));*/
		GameOfStrings gos = new GameOfStrings();
		gos.startGame("ABCD", "AB");
		System.out.println(gos.maxRounds);
	}
	
/*	private void computeMaxRounds(String S, String T) {
		int minIndex = 0;
		int maxIndex = 1;
		
		
	}*/
	
	private String getSubString(String S) {
		return S.substring(minIndex, maxIndex);
	}

	private void startGame(String S, String T) {
		
		while(S.length()!=0) {
			String subStr = getSubString(S);
			if(subStr.equals(T)) {
				//remove substring from S and continue
				S = new String(S.substring(maxIndex));
			}
			else {
				maxIndex++;
			}
			
			maxRounds++;
		}
	}
	
}
