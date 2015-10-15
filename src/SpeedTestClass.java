import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class SpeedTestClass {

	public static void main(String[] args) throws Exception {
		
		
		Robot bot = new Robot();
		
		int curX = 0;
		int curY = 0;
		
		int startTime = 0;
		int timeTook = 0;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		startTime = (int) System.currentTimeMillis();
		Rectangle captureSize = new Rectangle(screenSize);
		BufferedImage image = bot.createScreenCapture(captureSize);

		while (image.getRGB(curX, curY) != -992843){
			if (curX+1 < screenSize.getWidth()){
				curX++;
			}
			else if (curY+1 < screenSize.getHeight()){
				curX = 0;
				curY ++;
			}
			else{
				throw new Exception("Board not found!");
			}
		}
		
		timeTook = (int) (System.currentTimeMillis()-startTime);
		System.out.println(timeTook);
		
		curX = 0;
		curY = 0;
		
		startTime = (int) System.currentTimeMillis();
		
		while (bot.getPixelColor(curX, curY).getRGB() != -992843){
			if (curX+1 < screenSize.getWidth()){
				curX++;
			}
			else if (curY+1 < screenSize.getHeight()){
				curX = 0;
				curY ++;
			}
			else{
				throw new Exception("Board not found!");
			}
		}
		
		timeTook = (int) (System.currentTimeMillis()-startTime);
		System.out.println(timeTook);

	}

}
