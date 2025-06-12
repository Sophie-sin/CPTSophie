import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class CPTTest{
	public static void main(String[] args){
		Console con = new Console("Hangman TEST", 1280, 720);
		boolean blnWin = false;
		char chrNext;
		String strWord = "hi";
		con.drawString("You saved 2 victims", 500,300);
		con.sleep(2000);
		CPTsophietools.winScenario(con, blnWin,strWord);
		chrNext = con.getChar();
		CPTsophietools.continueGame(con);
	}
}
