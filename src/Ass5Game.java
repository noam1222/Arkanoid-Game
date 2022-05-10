// 209407162 Noam Maimon

/**
 * game run class.
 */
public class Ass5Game {
    /**
     * main func of the game run class.
     * @param args for command line parameters in compilation process - DON'T USE IN THIS PROGRAM.
     */
    public static void main(String[] args) {
        Game g = new Game(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        g.initialize();
        g.run();
    }
}
