import arc.*;

public class CPTSophie{
	public static void main(String[] args){
		Console con = new Console();
		//main menu
		String strMainMenu;
		con.println("▶ play game (p)");
		con.println("▶ view leaderboard (v)");
		con.println("▶ add theme (a)");
		con.println("▶ quit (q)");
		strMainMenu = con.readLine();
		
		//play game
		if(strMainMenu.equals("p")){
			con.clear();
			
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
			String strWord;
			strWord = "";
			
		}
		//view leaderboard
		
		//add theme
		
		//quit
		if(strMainMenu.equals("q")){
			con.clear();
		}
		
	}
	
	public static String randomWord(String strTheme){
		TextInputFile theme = new TextInputFile(strTheme);
		return"hi";
	}
	
}
