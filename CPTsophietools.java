import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class CPTsophietools{
	public static String checkTheme(String strTheme, String strChosenTheme){
		TextInputFile themes = new TextInputFile ("themes.txt");
		while(themes.eof() == false && !strChosenTheme.equalsIgnoreCase(strTheme)){
			strTheme = themes.readLine();
		}
		themes.close();
		return strTheme;
	}
	
	
	public static String[][] randomWord(String strTheme){
		TextInputFile theme = new TextInputFile(strTheme);
		String strWords;
		String strBubbleWords[][];
		int intRandNum;
		String strRandNum;
		int intCount = 0;
		int intCount2 = 0;
		//load words into arrays
		//read line# then load data
		while(theme.eof()==false){
			strWords = theme.readLine();
			intCount = intCount + 1;
			
		}
		theme.close();
		
		TextInputFile themefile = new TextInputFile(strTheme);
		strBubbleWords = new String[intCount][2];
		while(themefile.eof()==false){
			strBubbleWords[intCount2][0] = themefile.readLine();
			intRandNum = (int)(Math.random()*100+1);
			strRandNum = ("")+intRandNum+("");
			strBubbleWords[intCount2][1] = strRandNum;
			intCount2 = intCount2 + 1;
		}
		themefile.close();
		//bubble sort
		String strTempWord;
		String strTempNum;
		int intCount3;
		for(intCount3 = 0; intCount3<intCount-1; intCount3++){
			for(intCount = 0; intCount<intCount2-1; intCount++){
				if(Integer.parseInt(strBubbleWords[intCount][1])>Integer.parseInt(strBubbleWords[intCount+1][1])){
					//swap word
					strTempWord = strBubbleWords[intCount][0];
					strBubbleWords[intCount][0] = strBubbleWords[intCount+1][0];
					strBubbleWords[intCount+1][0] = strTempWord;
					//swap int
					strTempNum = strBubbleWords[intCount][1];
					strBubbleWords[intCount][1] = strBubbleWords[intCount+1][1];
					strBubbleWords[intCount+1][1] = strTempNum;
				}
			}
		}
		for(intCount = 0; intCount<intCount2; intCount++){
			System.out.println(strBubbleWords[intCount][0]+" - "+strBubbleWords[intCount][1]);
		}
		return strBubbleWords;
	
	}
	
	public static char MainMenu(Console con){
		char chrMainMenu;
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,1280,720);
		BufferedImage imgLogo = con.loadImage("logo.png");
		con.drawImage(imgLogo,0,0);
		con.setDrawColor(Color.WHITE);
		con.drawString("▶ play game (p)",120,300);
		con.drawString("▶ view leaderboard (v)",120,350);
		con.drawString("▶ add theme (a)",120,400);
		con.drawString("▶ help (h)",120,450);
		con.drawString("▶ quit (q)",120,500);
		chrMainMenu = con.getChar();
		return chrMainMenu;
	}
	
	public static void helpMenu(Console con){
		con.println("\n\n  Welcome to the help menu!");
		con.println("\n\n  Hope these instructions would help you on your journey:");
		con.setDrawColor(Color.WHITE);
		con.drawRect(25,165,1100,200);
		con.println("\n\n   1. To start gameplay, press 'p' in main menu");
		con.println("   2. Enter username - username will be displayed on leaderboard");
		con.println("   3. Multiple themes will be shown on the screen - type your desired theme");
		con.println("   4. The number of underlines represent the number of letters of the word you have to guess");
		con.println("   5. Win by guessing the correct word before the hangman is completed");
		con.println("   6. Return to main menu by pressing 'r'");
		con.drawString("  ▶ return (r)",1000,600);
	}
	
	public static char secretMenu(Console con){
		con.println("\n\n  Oh hi!");
		con.sleep(1000);
		con.println("  You found the secret menu!");
		con.sleep(1000);
		con.println("  You know what? ");
		con.sleep(2000);
		con.println("\n\n  Killerwhales are actually dolphins.");
		con.sleep(2000);
		con.println("\n\n  Ha! I bet you did not know :)");
		con.sleep(2000);
		char chrReturn = 'r';
		return chrReturn;
	}
	
	public static void leaderboard(Console con){
		String strName;
		int intSaves;
		int intRow;
		int intCount = 0;
		TextInputFile leaderboard = new TextInputFile("leaderboard.txt");
		//read file
		while(leaderboard.eof()==false){
			strName = leaderboard.readLine();
			intSaves = leaderboard.readInt();
			intCount = intCount + 1;
		}
		leaderboard.close();
		//load into array
		String strLeaderboard[][];
		strLeaderboard = new String[intCount][2];
		leaderboard = new TextInputFile("leaderboard.txt");
		for(intRow = 0; intRow < intCount; intRow++){
			strLeaderboard[intRow][0] = leaderboard.readLine();
			strLeaderboard[intRow][1] = leaderboard.readLine();
		}
		//bubble sort
		int intRow2 = 0;
		String strTempName;
		String strTempSaves;
		for(intRow2 = 0; intRow2 < intCount-1; intRow2++){
			for(intRow = 0; intRow < intCount-1-intRow2; intRow++){
				if(Integer.parseInt(strLeaderboard[intRow][1])<Integer.parseInt(strLeaderboard[intRow+1][1])){
					//swap name
					strTempName = strLeaderboard[intRow][0];
					strLeaderboard[intRow][0] = strLeaderboard[intRow+1][0];
					strLeaderboard[intRow+1][0] = strTempName;
					//swap saves
					strTempSaves = strLeaderboard[intRow][1];
					strLeaderboard[intRow][1] = strLeaderboard[intRow+1][1];
					strLeaderboard[intRow+1][1] = strTempSaves;
				}
			}
		}
		con.setDrawColor(Color.WHITE);
		con.drawString("Leaderboard - TOP 10", 500, 50);
		con.drawString("\n\nRANK           NAME           SAVES",400,100);
		con.drawLine(400,90,830,90);
		con.drawLine(400,140,830,140);
		int intRank = 0;
		int inty = 150;
			
		for(intRow = 0; intRow < 10; intRow++){
			intRank = intRank+1;
			con.setDrawColor(new Color(51,51,51));
			con.fillRect(400, inty, 430, 30);
			con.setDrawColor(Color.WHITE);
			con.drawString(intRank+"", 420, inty);
			con.drawString(strLeaderboard[intRow][0]+"", 550, inty);
			con.drawString(strLeaderboard[intRow][1]+"", 770, inty);
			inty = inty + 50;
		}
		con.drawString("  ▶ return (r)",1000,600);
	}
	
	public static String theme(Console con){
		String strThemes;
		String strChosenTheme;
		
		con.println("\n\n                  We discovered that there was a mysterious group that kidnapped people...");
		con.println("                                        Please, save them! Good luck...");		
		con.sleep(2000);
		con.println("\n\n  Choose a theme:");
		con.println(" ");
		TextInputFile themes = new TextInputFile ("themes.txt");
		while(themes.eof()==false){
			strThemes = themes.readLine();
			con.println("  ▶ "+strThemes);
		}
		con.println(" ");
		con.println(" ");
		con.setDrawColor(Color.WHITE);
		con.drawRect(50,400,500,150);			
		con.println("\n\n      ▶ Type your chosen theme:");
		con.print("        ");
		strChosenTheme = con.readLine();
		themes.close();	
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,1280,720);
		return strChosenTheme;
	}
	
	public static String[][] randomLetter(String strWord, int intWord){
		int intCount;
		int intRand;
		String strLetter[][];
		strLetter = new String[intWord][2];
		for(intCount = 0; intCount < intWord; intCount++){
			strLetter[intCount][0] = strWord.substring(intCount,intCount+1);
			intRand = (int)(Math.random()*100+1);
			strLetter[intCount][1] = intRand+"";
		}
		//bubble sort random letter
		int intCount2 = 0;
		String strTempLetter;
		String strTempNum;
		int intSpace;
		for(intCount2 = 0; intCount2 < intWord-1; intCount2++){
			for(intCount = 0; intCount < intWord-1-intCount2; intCount++){
				if(Integer.parseInt(strLetter[intCount][1])<Integer.parseInt(strLetter[intCount+1][1])){
					//swap letter
					strTempLetter = strLetter[intCount][0];
					strLetter[intCount][0] = strLetter[intCount+1][0];
					strLetter[intCount+1][0] = strTempLetter;
					//swap rand num
					strTempNum = strLetter[intCount][1];
					strLetter[intCount][1] = strLetter[intCount+1][1];
					strLetter[intCount+1][1] = strTempNum;
				}
			}
		}
		//rearrange to correct position
		for(intWord = 0; intWord < intCount2+1; intWord++){
			System.out.println(strLetter[intWord][0]+" - "+strLetter[intWord][1]);
			for(intSpace = 0; intSpace < intCount; intSpace++){
				strLetter[intSpace][0] = (" ")+strLetter[intSpace][0];
			}
		}
		
		return strLetter;
	}
	
	public static void hangman(Console con,int intAttempt){
		BufferedImage imgPole = con.loadImage("hangman.png");
		BufferedImage imglhand = con.loadImage("lefthand.png");
		BufferedImage imgrhand = con.loadImage("righthand.png");
		BufferedImage imglleg = con.loadImage("leftleg.png");
		BufferedImage imgrleg = con.loadImage("rightleg.png");
		BufferedImage imgblood1 = con.loadImage("blood1.png");
		BufferedImage imgblood2 = con.loadImage("blood2.png");
		con.setDrawColor(Color.WHITE);
		if(intAttempt == 0){
			con.drawImage(imgPole, 100, 200);
		}else if(intAttempt == 1){
			con.drawImage(imgPole, 100, 200);
			con.fillOval(270, 230, 50, 50);
		}else if(intAttempt == 2){
			con.drawImage(imgPole, 100, 200);
			con.fillOval(270, 230, 50, 50);
			con.fillRect(290, 250, 7, 80);
		}else if(intAttempt == 3){
			con.drawImage(imgPole, 100, 200);
			con.fillOval(270, 230, 50, 50);
			con.fillRect(290, 250, 7, 80);
			con.drawImage(imglhand, -145, -40);
		}else if(intAttempt == 4){
			con.drawImage(imgPole, 100, 200);
			con.fillOval(270, 230, 50, 50);
			con.fillRect(290, 250, 7, 80);
			con.drawImage(imglhand, -145, -40);
			con.drawImage(imgrhand, -120, -40);
		}else if(intAttempt == 5){
			con.drawImage(imgPole, 100, 200);
			con.fillOval(270, 230, 50, 50);
			con.fillRect(290, 250, 7, 80);
			con.drawImage(imglhand, -145, -40);
			con.drawImage(imgrhand, -120, -40);
			con.drawImage(imglleg, -145, -5);
			con.drawImage(imgblood1,0,0);
		}else if(intAttempt == 6){
			con.drawImage(imgPole, 100, 200);
			con.fillOval(270, 230, 50, 50);
			con.fillRect(290, 250, 7, 80);
			con.drawImage(imglhand, -145, -40);
			con.drawImage(imgrhand, -120, -40);
			con.drawImage(imglleg, -145, -5);
			con.drawImage(imgrleg, -120, -5);
			con.drawImage(imgblood2,0,0);
		}else{
			con.clear();
		}
	}
	public static void winScenario(Console con, boolean blnWin){
		BufferedImage imgPole = con.loadImage("hangman.png");
		BufferedImage imgMan = con.loadImage("man.png");
		BufferedImage imgblood2 = con.loadImage("blood2.png");
		BufferedImage imgwin = con.loadImage("win.png");
		BufferedImage imglose = con.loadImage("lose.png");
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,1280,720);
		con.setDrawColor(Color.WHITE);
		con.drawImage(imgPole, 100, 200);
		if(blnWin = true){
			con.drawImage(imgMan,-50,-20);
			con.drawImage(imgwin,0,0);
			con.drawString("Congratulations!",800,500);
			con.drawString("You successfully rescued the victim!▼", 800,550);
		}else{
			con.drawImage(imgblood2,0,0);
			con.drawImage(imglose,0,0);
			con.drawString("....",800,450);
			con.drawString("...........",800,500);
			con.drawString("...You tried your best.▼",800,550);
		}
	}
	
}
