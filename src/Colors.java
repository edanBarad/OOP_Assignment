import java.awt.Color;
import java.util.Random;

/**
 * Enum for predefined ball colors.
 * Includes a method to get a random color.
 */
public enum Colors {
    BLACK(Color.BLACK),
    BLUE(Color.BLUE),
    CYAN(Color.CYAN),
    DARK_GRAY(Color.DARK_GRAY),
    GRAY(Color.GRAY),
    GREEN(Color.GREEN),
    LIGHT_GRAY(Color.LIGHT_GRAY),
    MAGENTA(Color.MAGENTA),
    ORANGE(Color.ORANGE),
    PINK(Color.PINK),
    RED(Color.RED),
    WHITE(Color.WHITE),
    YELLOW(Color.YELLOW);

    private final Color color;

    // Constructor
    Colors(Color color) {
        this.color = color;
    }

    // Accessor for the Color object
    public Color getColor() {
        return this.color;
    }

    // Get a random color from the enum
    public static Color random() {
        Colors[] values = values();
        return values[new Random().nextInt(values.length)].color;
    }
}