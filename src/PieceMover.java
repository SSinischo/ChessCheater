import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class PieceMover {
	
	private BoardCoords board;
	private Robot kasperov;
	
	public PieceMover(BoardCoords b) throws Exception{
		board = b;
		kasperov = new Robot();
	}

	public void movePiece(int moveFrom, int moveTo) throws Exception {
		int[] fromCoords = board.getCoords(moveFrom);
		int[] toCoords = board.getCoords(moveTo);
		
		int deltaX = (toCoords[0] - fromCoords[0]) / 20;
		int deltaY = (toCoords[1] - fromCoords[1]) / 20;
		int mouseReturnPos[] = {(int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY()};
		
		kasperov.mouseMove(fromCoords[0], fromCoords[1]);
		kasperov.mousePress(InputEvent.BUTTON1_MASK);
		
		for (int i = 0; i < 20; i++){
			kasperov.mouseMove(fromCoords[0] + i*deltaX, fromCoords[1] + i*deltaY);
			Thread.sleep(5);
		}
		kasperov.mouseMove(toCoords[0], toCoords[1]);
		kasperov.mouseRelease(InputEvent.BUTTON1_MASK);
		ChessClock.selfMoved();
		kasperov.mouseMove(mouseReturnPos[0], mouseReturnPos[1]);
	}

}
