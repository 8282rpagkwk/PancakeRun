
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class EnemyTimerTask extends TimerTask {
    private Enemy enemy;
    private Vector<Enemy> enemyVec;
    private Timer eTimer;
    int range;
    int defaultTime;

    public EnemyTimerTask(Enemy enemy, Vector<Enemy> enemyVec, Timer eTimer, int range, int defaultTime) {
        this.enemy = enemy;
        this.enemyVec = enemyVec;
        this.eTimer = eTimer;
        this.range = range;
        this.defaultTime = defaultTime;
    }
    
    @Override
    public void run() {
        enemyVec.add((Enemy)enemy.clone());
        eTimer.cancel();
        eTimer= new Timer();
        eTimer.schedule(new EnemyTimerTask(enemy, enemyVec, eTimer, range, defaultTime), ((int)((Math.random()*range)+defaultTime)));
    }
}