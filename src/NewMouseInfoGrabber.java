
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;

public class NewMouseInfoGrabber {

	public static void main(String[] args) throws Exception{
		Robot bot = new Robot();
		
		while (true){
			int xc = (int)MouseInfo.getPointerInfo().getLocation().getX();
			int yc = (int)MouseInfo.getPointerInfo().getLocation().getY();
			Color pixelColor = bot.getPixelColor(xc, yc);
			
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.flush();
			
			System.out.println("MouseX: " + xc);
			System.out.println("MouseY: " + yc);
			System.out.println("R: " + pixelColor.getRed() + " G: " + pixelColor.getGreen() + " B: " + pixelColor.getBlue());
		}
	}
}
