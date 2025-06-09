import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class CPTSophie{
	public static void main(String[] args){
		Console con = new Console("Hangman", 1280, 720);
		//main menu
		char chrMainMenu = ' ';
		char chrReturn;
		chrMainMenu = CPTsophietools.MainMenu(con);
		con.clear();
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,1280,720);
		
		
		//loop
		boolean blnLoop = true;
		while(blnLoop == true){
			chrReturn = ' ';
		
			//play game
			if(chrMainMenu=='p'){
				con.clear();
				con.setDrawColor(Color.BLACK);
				con.fillRect(0,0,1280,720);
				blnLoop = false;
				
				//ask for username
				String strName;
				int intSaves = 0;
				TextOutputFile leaderboard = new TextOutputFile ("leaderboard.txt",true);
				con.setDrawColor(Color.WHITE);
				con.drawRect(100,100,600,100);
				con.println(" ");
				con.println(" ");
				con.println(" ");
				con.println("\n\n              ▶ Enter your name: (max 10 characters)");
				con.print("                ");
				strName = con.readLine();
				leaderboard.close();
				con.setDrawColor(Color.BLACK);
				con.fillRect(0,0,1280,720);
				con.clear();
				
				//game play loop
				char chrGame = 'y';
				while(chrGame == 'y'){
					//choose theme
					String strThemes;
					String strChosenTheme;
					strThemes = "";
					strChosenTheme = CPTsophietools.theme(con);
					con.clear();
					
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
					int intAttempt = 0;
					boolean blnWin = true;
					
					String strWord = strRandomWord[0][0];
					intWord = strWord.length();
					System.out.println("TEST WORD COUNT: "+intWord);
					CPTsophietools.hangman(con, intAttempt);
					con.println("                              ");
					con.println("                              ");
					con.println("                              ");
					con.println("                              ");
					con.println("                              ");
					con.println("                              ");
					con.println("                              ");
					con.println("                              ");
					con.println("                              ");
					con.println("                              ");
					con.print("                                     ");
					for(intCount = 0; intCount<intWord; intCount++){
						con.print("_");
					}
					
					//user guesses the word, output body part and one letter
					String strGuessWord;
					String strLetter[][];
					int intRand;
					int intSpace;
					con.print("\n\n                                     ");
					strGuessWord = con.readLine();
					strLetter = CPTsophietools.randomLetter(strWord,intWord);
					
					//guessed wrong
					while(!strGuessWord.equals(strWord)&&intAttempt<6){
						con.clear();
						blnWin = false;
						intAttempt = intAttempt+1;
						intRand = (int)(Math.random()*intWord);
						while(strLetter[intRand][0] == ""){
							intRand = (int)(Math.random()*intWord);
						}
						CPTsophietools.hangman(con, intAttempt);
						con.println("                              ");
						con.println("                              ");
						con.println("                              ");
						con.println("                              ");
						con.println("                              ");
						con.println("                              ");
						con.println("                              ");
						con.println("                              ");
						con.println("                              ");
						con.print("                                     ");
						//Test
						//con.println(strLetter[0][0]);
						for(intSpace = 0; intSpace < intRand; intSpace++){
							strLetter[intRand][0]=(" ")+strLetter[intRand][0];
						}
						con.println(strLetter[intRand][0]);
						strLetter[intRand][0] = "";
						con.print("                                     ");
						for(intCount = 0; intCount<intWord; intCount++){
							con.print("_");
						}
						con.print("\n\n                                     ");
						strGuessWord = con.readLine();
					}
					
					//win or lose scenario
					con.clear();
					con.repaint();
					if(!strGuessWord.equals(strWord)){
						blnWin = false;
					}else if(strGuessWord.equals(strWord)){
						blnWin = true;
					}
					CPTsophietools.winScenario(con, blnWin);
					con.sleep(2000);

					//continue or not
					con.clear();
					CPTsophietools.continueGame(con);
					chrGame = con.getChar();
					
					//record number of saves
					intSaves = intSaves + 1;
					System.out.println("TEST SAVES: "+intSaves);
				}
				//not continue
				//print name and number of saves to leaderboard
				con.setDrawColor(Color.BLACK);
				con.fillRect(0,0,1280,720);
				con.setDrawColor(Color.WHITE);
				con.drawString("  ▶ return (r)",1000,600);
				System.out.println("TEST SAVES: "+intSaves);
				System.out.println("TEST NAME: "+strName);
				con.repaint();
				
				//return to main menu
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
				chrReturn = CPTsophietools.secretMenu(con);
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
