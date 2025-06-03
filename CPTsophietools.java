import arc.*;

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
		con.println("Leaderboard");
		con.println("TOP 10");
		con.println("NAME      SAVES");
		int intRank = 0;
		for(intRow = 0; intRow < 10; intRow++){
			intRank = intRank+1;
			con.print(intRank+strLeaderboard[intRow][0]);
			con.println(strLeaderboard[intRow][1]);
		}
	}
	
	public static String theme(Console con){
		String strThemes;
		String strChosenTheme;
					
		con.println("Choose a theme:");
		con.println(" ");
		TextInputFile themes = new TextInputFile ("themes.txt");
		while(themes.eof()==false){
			strThemes = themes.readLine();
			con.println("▶ "+strThemes);
		}			
		con.println("\n\n▶ Type your chosen theme:");
		strChosenTheme = con.readLine();
		themes.close();	
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
	
}
