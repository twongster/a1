package a1;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This interface allows objects that implement it the ability to
 * change their heading after they've been created
 */
public interface ISteerable {
    void steerLeft();
    void steerRight();
    void updateHeading();
}
