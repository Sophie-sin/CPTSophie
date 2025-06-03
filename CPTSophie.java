import arc.*;
import java.awt.image.BufferedImage;

public class CPTSophie{
	public static void main(String[] args){
		Console con = new Console("Hangman", 1280, 720);
		//main menu
		char chrMainMenu = ' ';
		char chrReturn;
		chrMainMenu = CPTsophietools.MainMenu(con);
		con.clear();
		
		//loop
		boolean blnLoop = true;
		while(blnLoop == true){
			chrReturn = ' ';
		
			//play game
			if(chrMainMenu=='p'){
				con.clear();
				blnLoop = false;
				
				//ask for username
				String strName;
				TextOutputFile leaderboard = new TextOutputFile ("leaderboard.txt",true);
				con.println("Enter your name: ");
				strName = con.readLine();
				leaderboard.close();
				con.clear();
				
				//choose theme
				String strThemes;
				String strChosenTheme;
				strThemes = "";
				strChosenTheme = CPTsophietools.theme(con);
				
				//find theme
				strThemes = CPTsophietools.checkTheme(strThemes, strChosenTheme);
				while(!strChosenTheme.equals(strThemes)){
					con.clear();
					strChosenTheme = CPTsophietools.theme(con);
					strThemes = CPTsophietools.checkTheme(strThemes, strChosenTheme);
				}
				con.clear();
				
				//game start
				//load word
				String strRandomWord[][];
				strRandomWord = CPTsophietools.randomWord(strChosenTheme);
				System.out.println("TEST WORD: "+strRandomWord[0][0]);
				
				
				//load hangman pole, underlines for word
				int intWord;
				int intCount;
				
				String strWord = strRandomWord[0][0];
				intWord = strWord.length();
				System.out.println("TEST WORD COUNT: "+intWord);
				BufferedImage imgPole = con.loadImage("hangman.png");
				con.drawImage(imgPole, 0, 0);
				con.println("                              ");
				con.print("                           ");
				for(intCount = 0; intCount<intWord; intCount++){
					con.print("_");
				}
				
				//user guesses the word, output body part and one letter
				String strGuessWord;
				String strLetter[][];
				con.print("\n\n                           ");
				strGuessWord = con.readLine();
				strLetter = CPTsophietools.randomLetter(strWord,intWord);
				
				//guessed wrong
				if(!strGuessWord.equals(strWord)){
					con.clear();
					con.drawImage(imgPole, 0, 0);
					con.println("                              ");
					con.print("                           ");
					con.println(strLetter[0][0]);
					con.print("                           ");
					for(intCount = 0; intCount<intWord; intCount++){
						con.print("_");
					}
				}
				
				//win scenario
				
				//lose scenario
				
				//continue or not
				//return to main menu
				//chrMainMenu = CPTsophietools.MainMenu(con);
				
			}
							
			//view leaderboard
			if(chrMainMenu=='v'){
				blnLoop = false;
				CPTsophietools.leaderboard(con);
				while(chrReturn != 'r'){
					chrReturn = con.getChar();
				}
				if(chrReturn == 'r'){
					con.clear();
					chrMainMenu = CPTsophietools.MainMenu(con);
					con.clear();
					blnLoop = true;
				}
			}
			
			//add theme
			if(chrMainMenu=='a'){
				blnLoop = false;
				String strNewTheme;
				String strNewWord = "";
				con.clear();
				//new theme
				TextOutputFile newTheme = new TextOutputFile("themes.txt", true);
				con.println("Enter theme name (including .txt): ");
				strNewTheme = con.readLine();
				newTheme.println(strNewTheme);
				newTheme.close();
				//words into theme
				TextOutputFile newWords = new TextOutputFile(strNewTheme, true);
				con.println("Enter words: press enter to continue, type 'stop' to stop");
				strNewWord = con.readLine();
				while(!strNewWord.equals("stop")){
					newWords.println(strNewWord);
					strNewWord = con.readLine();
				}
				con.println("New Theme is successfully added!");
				//return to main menu
				con.clear();
				chrMainMenu = CPTsophietools.MainMenu(con);

			}
			
			//quit
			if(chrMainMenu=='q'){
				blnLoop = false;
				con.closeConsole();
			}
			
			//help menu
			if(chrMainMenu=='h'){
				blnLoop = false;
				CPTsophietools.helpMenu(con);
				while(chrReturn != 'r'){
					chrReturn = con.getChar();
				}
				if(chrReturn == 'r'){
					con.clear();
					chrMainMenu = CPTsophietools.MainMenu(con);
					con.clear();
					blnLoop = true;
				}
			}
			
			//secret menu
			if(chrMainMenu=='s'){
				blnLoop = false;
				CPTsophietools.secretMenu(con);
				while(chrReturn != 'r'){
					chrReturn = con.getChar();
				}
				if(chrReturn == 'r'){
					con.clear();
					chrMainMenu = CPTsophietools.MainMenu(con);
					con.clear();
					blnLoop = true;
				}
			}
			
			else{
				chrMainMenu = CPTsophietools.MainMenu(con);
				con.clear();
			}
			
		}
		
		
	}
	
		
}
