// 209407162 Noam Maimon

import GameHandler.AnimationRunner;
import GameHandler.GameFlow;
import Helpers.Constants;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import Levels.LevelInformation;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * game run class.
 */
public class Ass5Game {
    /**
     * main func of the game run class.
     * @param args for command line parameters in compilation process - DON'T USE IN THIS PROGRAM.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60), gui.getKeyboardSensor());

        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        gameFlow.runLevels(levels);
    }
}
