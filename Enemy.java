
import java.awt.Rectangle;

public class Enemy extends Rectangle{
    public static final int CANDYFLY = 0;
    public static final int ICECREAM = 1;
    public static final int CREAMSHEEP = 2;
    
    public int speed = 8;
    public int type = 0;
    
    public Enemy(int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        this.type = type;
    }
}
