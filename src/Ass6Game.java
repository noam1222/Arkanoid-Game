import Animations.AnimationRunner;
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
public class Ass6Game {
    /**
     * main func of the game run class.
     *
     * @param args for command line parameters in compilation process - represent levels order.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60), gui.getKeyboardSensor());

        List<LevelInformation> levels = getLevels(args);
        gameFlow.runLevels(levels);
    }

    /**
     * get the levels base on levels array.
     *
     * @param args the levels array.
     * @return List of the levels.
     */
    private static List<LevelInformation> getLevels(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        for (String s : args) {
            LevelInformation li = getLevelInformation(s);
            if (li != null) {
                levels.add(li);
            }
        }
        if (levels.isEmpty())  {
            for (int i = 1; i <= 4; i++) {
                LevelInformation li = getLevelInformation(Integer.toString(i));
                if (li != null) {
                    levels.add(li);
                }
            }
        }
        return levels;
    }

    /**
     * get level information object.
     *
     * @param levelNumber the number of the requested level.
     * @return the level information requested.
     */
    private static LevelInformation getLevelInformation(String levelNumber) {
        switch (levelNumber) {
            case "1":
                return new Level1();
            case "2":
                return new Level2();
            case "3":
                return new Level3();
            case "4":
                return new Level4();
            default:
                return null;
        }
    }
}
