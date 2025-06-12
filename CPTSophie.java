/*
 * Name: Hangman
 * Purpose: Generates a random word in the chosen theme, shows underlines 
           according to the number of letters in the word. User wins by 
           guessing the word correctly, outout a random letter and body
           part if guessed wrong, loses if all body parts are shown.
  * Author: Sophie Sin
  * Date : 12-6-2025
  * V2.5.0
 */

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
				int intName;
				con.setDrawColor(Color.WHITE);
				con.drawRect(100,100,600,100);
				con.println(" ");
				con.println(" ");
				con.println(" ");
				con.println("\n\n              ▶ Enter your name: (max 10 characters)");
				con.print("                ");
				strName = con.readLine();
				intName = strName.length();
				while(intName > 10){
					con.clear();
					con.println(" ");
					con.println(" ");
					con.println(" ");
					con.println("\n\n              ▶ Enter your name: (max 10 characters)");
					con.print("                ");
					strName = con.readLine();
					intName = strName.length();
				}
				System.out.println(strName);
				if(strName.equals("statitan")){
					intSaves = 1;
					con.println("\n\n          Special Feature: Saves +1");
					con.sleep(1000);
					con.println("          You just saved one victim!");
					con.sleep(1500);
				}
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
					strChosenTheme = CPTsophietools.theme(con,strName);
					con.clear();
					
					//find theme
					strThemes = CPTsophietools.checkTheme(strThemes, strChosenTheme);
					while(!strChosenTheme.equals(strThemes)){
						con.clear();
						strChosenTheme = CPTsophietools.theme(con,strName);
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
					boolean blnWin;
					String strWord = strRandomWord[0][0];
					intWord = strWord.length();
					con.println("\n\n      Enter a word with "+intWord+" letters:");
					System.out.println("TEST WORD COUNT: "+intWord);
					CPTsophietools.hangman(con, intAttempt);
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
					String strPrintLetter[];
					int intRand;
					int intPrint;
					int intBlank;
					strPrintLetter = new String[intWord];
					for(intBlank = 0; intBlank < intWord; intBlank++){
						strPrintLetter[intBlank] = " ";
					}
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
						strPrintLetter[intRand] = strLetter[intRand][0];
						strLetter[intRand][0] = "";
						for(intPrint = 0; intPrint < intWord; intPrint++){
							con.print(strPrintLetter[intPrint]);
						}
						con.println("");
						con.print("                                     ");
						for(intCount = 0; intCount<intWord; intCount++){
							con.print("_");
						}
						con.print("\n\n                                     ");
						strGuessWord = con.readLine();
					}
					
					//win or lose scenario
					blnWin = strGuessWord.equals(strWord);
					System.out.println("TEST GUESS: "+strGuessWord);
					System.out.println("TEST WORD: "+strWord);
					System.out.println("TEST CONDITION: "+blnWin);
					con.clear();
					con.repaint();
					
					CPTsophietools.winScenario(con, blnWin, strWord);
					con.sleep(2000);
					con.drawString("  ▶ press any button to continue",770,620);
					con.repaint();
					char chrNext;
					chrNext = con.getChar();

					//continue or not
					con.clear();
					CPTsophietools.continueGame(con);
					chrGame = con.getChar();
					
					//record number of saves
					if(blnWin == true){
						intSaves = intSaves + 1;
					}else{
						intSaves = intSaves + 0;
					}
					System.out.println("TEST SAVES: "+intSaves);
				}
				//not continue
				//print name and number of saves to leaderboard
				con.setDrawColor(Color.BLACK);
				con.fillRect(0,0,1280,720);
				con.setDrawColor(Color.WHITE);
				con.drawString("You saved "+intSaves+" victims", 500,300);
				con.drawString("  ▶ return (r)",1000,600);
				System.out.println("TEST SAVES: "+intSaves);
				System.out.println("TEST NAME: "+strName);
				con.repaint();
				TextOutputFile leaderboard = new TextOutputFile ("leaderboard.txt",true);
				leaderboard.println(strName);
				leaderboard.println(intSaves);
				leaderboard.close();
				
				//return to main menu
				while(chrReturn != 'r'){
					chrReturn = con.getChar();
				}
				if(chrReturn == 'r'){
					con.clear();
					chrMainMenu = CPTsophietools.MainMenu(con);
					System.out.println("TEST: "+chrMainMenu);
					con.clear();
					blnLoop = true;
				}
			}
							
			//view leaderboard
			else if(chrMainMenu=='v'){
				blnLoop = false;
				chrReturn = ' ';
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
			else if(chrMainMenu=='a'){
				blnLoop = false;
				String strNewTheme;
				String strNewWord = "";
				con.setDrawColor(Color.BLACK);
				con.fillRect(0,0,1280,720);
				con.repaint();
				con.clear();
				//new theme
				TextOutputFile newTheme = new TextOutputFile("themes.txt", true);
				con.println("Enter theme name (including .txt): ");
				strNewTheme = con.readLine();
				newTheme.println(strNewTheme);
				newTheme.close();
				//words into theme
				TextOutputFile newWords = new TextOutputFile(strNewTheme, true);
				con.println("\n\nEnter words: minimum 7 letters long, press enter to continue, type 'stop' to stop");
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
			else if(chrMainMenu=='q'){
				blnLoop = false;
				con.closeConsole();
			}
			
			//help menu
			else if(chrMainMenu=='h'){
				blnLoop = false;
				chrReturn = ' ';
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
			else if(chrMainMenu=='s'){
				blnLoop = false;
				chrReturn = ' ';
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
