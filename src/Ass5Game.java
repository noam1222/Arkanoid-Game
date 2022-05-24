// 209407162 Noam Maimon

import GameHandler.GameLevel;
import Helpers.Constants;

/**
 * game run class.
 */
public class Ass5Game {
    /**
     * main func of the game run class.
     * @param args for command line parameters in compilation process - DON'T USE IN THIS PROGRAM.
     */
    public static void main(String[] args) {
        GameLevel g = new GameLevel(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        g.initialize();
        g.run();
    }
}
