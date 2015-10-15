public class ChessClock {
	public static int selfTimeMillis = UserVariables.selfTimeControl*60*1000;
	public static int opponentTimeMillis = UserVariables.opponentTimeControl*60*1000;
	public static int selfTimeLeft = selfTimeMillis;
	public static int opponentTimeLeft = opponentTimeMillis;
	public static int lastMoved = -1;
	
	public static void selfMoved(){
		if (lastMoved != -1){
			selfTimeLeft -= (System.currentTimeMillis()-lastMoved);
		}
		lastMoved = (int) System.currentTimeMillis();
	}
	
	public static void opponentMoved(){
		if (lastMoved != -1){
			opponentTimeLeft -= (System.currentTimeMillis()-lastMoved);
		}
		lastMoved = (int) System.currentTimeMillis();
	}
	
	public static void resetClocks(){
		selfTimeLeft = UserVariables.selfTimeControl*60*1000;
		opponentTimeLeft = UserVariables.opponentTimeControl*60*1000;
		lastMoved = -1;
	}
	
	public static String timeString(int timeMillis){
		int min = timeMillis/60000;
		int sec = (timeMillis/1000) % 60;
		return String.format("%02d:%02d", min, sec);
	}
}