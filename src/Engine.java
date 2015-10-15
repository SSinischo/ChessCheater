import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Engine {
	public static boolean isWhite;		//true if playing as white, value set by BoardCoords during initial scan
	private Writer writer;
	private BufferedReader reader;
	private String gameMoves;
	private Squire squire;
	private PieceMover mover;
	
	public Engine() throws Exception{
		Runtime rTime = Runtime.getRuntime();
		Process process = rTime.exec(UserVariables.enginePath);
		writer = new OutputStreamWriter(process.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		gameMoves = new String();
		
		BoardCoords board = new BoardCoords();
		squire = new Squire(board);
		mover = new PieceMover(board);
		
		if (isWhite){
			processMove(-1, -1);
		}
		else {
			int[] opponentMove = squire.senseMove(-1);
			processMove(opponentMove[0], opponentMove[1]);
		}
		
	}
	
	private void processMove(int movedFrom, int movedTo) throws Exception{
		if (movedFrom != -1){
			gameMoves += BoardCoords.intToCoords(movedFrom) + BoardCoords.intToCoords(movedTo) + " ";
		}
		writer.write("position startpos moves " + gameMoves + "\n");
		System.out.println("(DEBUG) Analyzing position.  We have " + ChessClock.timeString(ChessClock.selfTimeLeft) + " left on the clock.  Opponent has " + ChessClock.timeString(ChessClock.opponentTimeLeft) + " left on the clock.");
		
		String command = new String();
		if (isWhite){
			command = "go wtime " + ChessClock.selfTimeLeft + " btime " + ChessClock.opponentTimeLeft + "\n";
		}
		else {
			command = "go wtime " + ChessClock.opponentTimeLeft + " btime " + ChessClock.selfTimeLeft + "\n";
		}
		writer.write(command);
		writer.flush();
		
		String curLine = new String();
		while (!curLine.startsWith("bestmove")){
			curLine = reader.readLine();
		}
		String bestMoveFromString = curLine.substring(9, 11);
		String bestMoveToString = curLine.substring(11, 13);
		System.out.println("(GAME) Best move is " + bestMoveFromString + " to " + bestMoveToString);
		
		int bestMoveFromInt = BoardCoords.coordsToInt(bestMoveFromString);
		int bestMoveToInt = BoardCoords.coordsToInt(bestMoveToString);
		mover.movePiece(bestMoveFromInt, bestMoveToInt);
		gameMoves += bestMoveFromString + bestMoveToString + " ";
		int[] nextMove = squire.senseMove(bestMoveFromInt);
		processMove(nextMove[0], nextMove[1]);
	}
}
