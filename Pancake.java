
import java.awt.Rectangle;

public class Pancake extends Rectangle{
    public int speed = 10;
    public boolean onCollision = false;
    
    public Pancake(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
}
