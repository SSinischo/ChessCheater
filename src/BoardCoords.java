import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

//BoardCoords.java
//ChessCheater v0.2
//finds the board on the screen, reports its dimension and coordinates of individual squares

public class BoardCoords {
	private int screenSize;
	private int[][] squareCoords = new int[65][]; 	//[3][0] = c1, x dimension.  [9][1] = a2, y dimension, etc.
	private int squareSize;						//width or height of board square in pixels
	
	public BoardCoords() throws Exception{
		Robot bot = new Robot();
		Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage boardSearchImage = bot.createScreenCapture(screenSize);
		System.out.println("(INIT) Scanning for board...");
		
		int[] scanPos = {0, 0};
		Color cornerSquareColor = UserVariables.lightSquareSpace;
		
		while (boardSearchImage.getRGB(scanPos[0], scanPos[1]) != cornerSquareColor.getRGB()){
			if (scanPos[0] + 1 < screenSize.width){
				scanPos[0]++;
			}
			else if (scanPos[1] + 1 < screenSize.height){
				scanPos[0] = 0;
				scanPos[1]++;
			}
			else {
				throw new Exception("(FATAL) Board not found!");
			}
		}
			
		int[] cornerPos = {scanPos[0], scanPos[1]};
			
		while (boardSearchImage.getRGB(scanPos[0], scanPos[1]) == cornerSquareColor.getRGB()){
				scanPos[1]++;
		}
			
		squareSize = scanPos[1]-cornerPos[1];

		System.out.println("(INIT) Board found at " + cornerPos[0] + ", " + cornerPos[1] + "!  Square size: " + squareSize + "px");
			
		if (boardSearchImage.getRGB(squareSize/2 + cornerPos[0], squareSize/2 + cornerPos[1]) == -1){
				Engine.isWhite = false;
		}
		else {
			Engine.isWhite = true;
		}
			
		int curSquare = 0;
		for (int a = 0; a < 8; a++){
			for (int b = 0; b < 8; b++){
				if (Engine.isWhite){
					curSquare = 65 - (a*8 + (8-b));
				}
				else {
					curSquare = a*8 + (8-b);
				}
				squareCoords[curSquare] = new int[]{b*squareSize + squareSize/2 + cornerPos[0], a*squareSize + squareSize/2 + cornerPos[1]};
			}
		}
	}
	
	public static String intToCoords(int i){
		String returnString = "";  
		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};
		returnString += letters[(i-1)%8];
		returnString += ((i-1)/8)+1;
		return returnString;
	}
	
	public static int coordsToInt(String c){
		String letters = "xabcdefgh";
		int row = letters.indexOf(c.charAt(0));
		int rank = Integer.parseInt(c.substring(1));
		return ((rank-1)*8 + row);
	}
	
	public int[] getCoords(int i){
		return squareCoords[i];
	}
	
	public int[] getCoords(String s){
		return squareCoords[coordsToInt(s)];
	}
	
	public int getSquareSize(){
		return squareSize;
	}
}
