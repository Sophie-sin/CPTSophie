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
	
	public static void MainMenu(Console con){
		con.println("▶ play game (p)");
		con.println("▶ view leaderboard (v)");
		con.println("▶ add theme (a)");
		con.println("▶ quit (q)");
	}
}
