
import java.awt.Rectangle;

public class Player extends Rectangle{
    public int jumpPower = 0;
    public float MAX_life = 1000.0f;
    public float life = 1000.0f;
    public float decrease = 4.3f;
    
    static Player obj = null;
    
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.jumpPower = 10;
    }
    
//    public Player() {
//        x = 10;
//        y = 240;
//        jumpPower = 10;
//    }

    public static Player getInstance(){
        if(obj == null)
            obj = new Player(10, 240, 138, 131);
        return obj;
    }
}
