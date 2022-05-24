// 209407162 Noam Maimon

package Helpers;


import Geometry.Point;
import Geometry.Rectangle;

import java.awt.Color;

/**
 * final class to store in elegant way all the constant variable in this program.
 */
public final class Constants {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final Rectangle SCREEN_RECTANGLE = new Rectangle(
            new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT
    );
    public static final int SCREEN_BORDERS_THICK = 10;
    public static final int PADDLE_HEIGHT = 20;
    public static final Color SCREEN_COLOR = new Color(0, 150, 0);
    public static final int FONT_SIZE = 20;
}
