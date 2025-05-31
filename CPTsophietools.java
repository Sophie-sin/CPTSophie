import arc.*;

public class CPTsophietools{
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
		return strBubbleWords;
	}
	
	public static char MainMenu(Console con){
		char chrMainMenu;
		con.println("▶ play game (p)");
		con.println("▶ view leaderboard (v)");
		con.println("▶ add theme (a)");
		con.println("▶ help (h)");
		con.println("▶ quit (q)");
		chrMainMenu = con.getChar();
		return chrMainMenu;
	}
	
	public static void helpMenu(Console con){
		con.println("Welcome to the help menu!");
		con.println("Hope these instructions would help you on your journey:");
		con.println("1. To start gameplay, press 'p' in main menu");
		con.println("2. Enter username - username will be displayed on leaderboard");
		con.println("3. Multiple themes will be shown on the screen - type your desired theme");
		con.println("4. The number of underlines represent the number of letters of the word you have to guess");
		con.println("5. Win by guessing the correct word before the hangman is completed");
		con.println("6. Return to main menu by pressing 'r'");
		
	}
	
	public static void secretMenu(Console con){
		con.println("Oh hi!");
		con.println("You found the secret menu!");
		con.println("You know what? ");
		con.println("Killerwhales are actually dolphins.");
		con.println("\n\nHa! I bet you did not know :)");
	}
	
}
