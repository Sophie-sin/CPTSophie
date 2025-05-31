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
				/*String strName;
				TextOutputFile leaderboard = new TextOutputFile ("leaderboard.txt",true);
				con.println("Enter your name: ");
				strName = con.readLine();
				leaderboard.close();
				con.clear();*/
				
				//choose theme
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
				
				//game start
				//load word
				String strWord[][];
				strWord = CPTsophietools.randomWord(strChosenTheme);
				//con.println(strWord[0][0]);
				themes.close();
				con.clear();
				
				//load hangman pole, underlines for word
				int intWord;
				int intCount;
				intWord = strWord[0][0].length();
				//con.println(intWord);
				BufferedImage imgPole = con.loadImage("hangman.png");
				con.drawImage(imgPole, 0, 0);
				for(intCount = 0; intCount<intWord; intCount++){
					con.print("_");
				}
				
				//user guesses the word, output body part and one letter
				String strGuessWord;
				String strLetter;
				strGuessWord = con.readLine();
				if(!strGuessWord.equals(strWord)){
					con.clear();
					
				}
				
				
			}
			//view leaderboard
			
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
			
			else{
				chrMainMenu = CPTsophietools.MainMenu(con);
				con.clear();
			}
			
		}
		
		
		
	}
	
		
}
