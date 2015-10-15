import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;

public class Squire {
	private BoardCoords board;
	private Robot sherlock;
	private int[][] sensorCoords = new int[65][];
	
	public Squire(BoardCoords b) throws Exception{
		board = b;
		sherlock = new Robot();
		for (int i = 1; i < 65; i++)
			sensorCoords[i] = new int[]{(board.getCoords(i)[0] + board.getSquareSize()/2 - 1), (board.getCoords(i)[1] - board.getSquareSize()/2 + 1)};
	}
	
	public int[] senseMove(int lastMoveFrom) throws Exception{
		int sensorOffColorDark = UserVariables.darkSquareSpace.getRGB();
		int sensorOffColorLight = UserVariables.lightSquareSpace.getRGB();
		if (lastMoveFrom != -1){
			System.out.println("(DEBUG) Waiting for opponent's move...");
			int curSensorColor = -1;
			int[] movedFromCenter = {board.getCoords(lastMoveFrom)[0], board.getCoords(lastMoveFrom)[1]};
			while (!(curSensorColor == sensorOffColorDark || curSensorColor == sensorOffColorLight))
				curSensorColor = sherlock.getPixelColor(movedFromCenter[0], movedFromCenter[1]).getRGB();
			System.out.println("(DEBUG) Opponent moved - starting new scan...");
		}
		else
			System.out.println("(DEBUG) Waiting for opponent's first move - starting new scan...");
		ChessClock.opponentMoved();
		
		int movedFrom = -1;
		int movedTo = -1;
		
		while (movedFrom == -1 || movedTo == -1){
			for (int i = 1; i < 65; i++){
				int curSensorColor = sherlock.getPixelColor(sensorCoords[i][0], sensorCoords[i][1]).getRGB();
				if (!(curSensorColor == sensorOffColorDark || curSensorColor == sensorOffColorLight))
					if (curSensorColor == sherlock.getPixelColor(board.getCoords(i)[0], board.getCoords(i)[1]).getRGB())
						movedFrom = i;
					else 
						movedTo = i;
			}
		}
		System.out.println("(GAME) Opponent moved a piece from " + BoardCoords.intToCoords(movedFrom) + " to " + BoardCoords.intToCoords(movedTo));
		return new int[]{movedFrom, movedTo};
		}
	}
