public class Play {

    /** colors for use in terminal console */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_TITLE = "\u001B[42m" + "\u001B[30m";


    /** clears console */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /** Keeps looping the game until player wants to stop */
    public static void main(String[] args) {
        Wordle game = new Wordle();
        boolean play = true;
        while (play) {
            clearScreen();
            System.out.print(ANSI_TITLE + "WORDLE" + ANSI_RESET);
            System.out.println("\nPRESS ENTER TO PLAY");
            try{System.in.read();}
            catch(Exception e){}
            clearScreen();
            System.out.println("GUESS THE 5 LETTER WORD IN 6 GUESSES! " + ANSI_RESET);
            game.runGame();
            try{System.in.read();}
            catch(Exception e){}
        }
    }
}
