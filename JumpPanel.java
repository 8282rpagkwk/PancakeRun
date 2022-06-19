
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.xml.transform.Source;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 8282r
 */
public class JumpPanel extends BasePanel implements KeyListener, Runnable{
    private final int ENEMY_LENGTH = 3;
    
    boolean isJump = false;
    boolean isDeath = false;
    boolean keepDeath = false;
    boolean isRun = false;
    boolean isStream = false;
    //int distance = 0;
    int toTitle = 0;
    long pretime;
    Player player = Player.getInstance();
    StageBG stage;
    JumpTests frame;
    Image playerImg;
    Timer mTimer;
    Timer[] eTimer;
    TimerTask mTask;
    TimerTask eTask;
    AudioInputStream ais;
    Clip bgm;
    Vector<Pancake> cakeVec = new Vector<Pancake>();
    Vector<Enemy> enemyVec = new Vector<Enemy>();
    //Dimension dim;
    /**
     * Creates new form JumpPanel
     */
    public JumpPanel(JumpTests obj) {
        super();
        initComponents();
        //(new Thread(this)).start();
        frame = obj;
        stage = new StageBG();
        mTimer = new Timer();
        eTimer = new Timer[ENEMY_LENGTH];
        mTask = new TimerTask() {
            @Override
            public void run() {
                if(!isDeath){
                    // ----- Background Move -----
                    if(distance < 300){
                        if(stage.bgY != -60) stage.bgY++;
                        if(stage.cldX != -500) stage.cldX -= 4;
                        else if(stage.cldX == -500) stage.cldX = 0;
                        if(stage.baseX != -605){
                            if(stage.baseX < -605) stage.baseX = 0;
                            stage.baseX -= 10; 
                        }
                    }
                    else {
                        if(stage.bgX < 0) stage.bgX+=4;
                        else if(stage.bgX >= 0) stage.bgX = -1267;
                        if(stage.cldX > -1820) stage.cldX -= 4;
                        else if(stage.cldX <= -1820) stage.cldX = 0;
                        if(stage.baseX != -605){
                            if(stage.baseX < -605) stage.baseX = 0;
                            stage.baseX -= 10; 
                        }
                    }
                    // ----- Player Life Decrease -----
                    if(player.life != 0){
                        if(player.life < 0){
                            player.life = 0;
                            isDeath = true;
                        }
                        player.life -= player.decrease;
                        distance++;
                    }
                    System.out.println("        In Jump - mTask");
                }
            }
        }; 
//        mTimer.scheduleAtFixedRate(mTask, 100, 100); //"0.1초 뒤 task 실행" 을 0.1초 마다 실행
        for(int i=0 ; i<eTimer.length ; i++) eTimer[i] = new Timer();
        Enemy fly = new Enemy(950, 200, 120, 88, Enemy.CANDYFLY);
        Enemy cream = new Enemy(830, -60, 185, 300, Enemy.ICECREAM);
        Enemy sheep = new Enemy(750, 300, 100, 71, Enemy.CREAMSHEEP);
        eTimer[0].schedule(new EnemyTimerTask(fly, enemyVec, eTimer[0], 2000, 1000), 100); //"2초 뒤 task 실행" 을 0 ~ 2초 마다 실행
        eTimer[1].schedule(new EnemyTimerTask(cream, enemyVec, eTimer[1], 5000, 2000), 100); //"2초 뒤 task 실행" 을 0 ~ 2초 마다 실행
        eTimer[2].schedule(new EnemyTimerTask(sheep, enemyVec, eTimer[2], 2500, 500), 100); //"2초 뒤 task 실행" 을 0 ~ 2초 마다 실행
//        this.addKeyListener(this);
//        this.requestFocus();
//        setFocusable(true);
//        dim = this.getSize();
//        System.out.println(dim);
    }
    
    @Override
    public void BGM() {
        try {
            ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(storage.getBgmClip())));
            bgm = AudioSystem.getClip();
//            bgm.open(ais);
//            bgm.start();
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        System.out.println("        In Jump - paint");
        //Using DoubleBuffering
        // ----- Background -----
        bufferImg = createImage(720, 490);
        bufferGraphic = bufferImg.getGraphics();
        paintComponent(bufferGraphic);
        if(distance < 300){
            bufferGraphic.drawImage(storage.getBG_stage1(), 0, stage.bgY, null);
            bufferGraphic.drawImage(storage.getCLD_stage1(), stage.cldX, 50, null);
            bufferGraphic.drawImage(storage.getBASE_stage1(), stage.baseX, 370, null);
        }
        else{
            bufferGraphic.drawImage(storage.getBG_stage2(), stage.bgX, 0, null);
            bufferGraphic.drawImage(storage.getCLD_stage2(), stage.cldX, 0, null);
            bufferGraphic.drawImage(storage.getBASE_stage2(), stage.baseX, 370, null);
        }
        // ----- Player Animation -----
        if(isDeath) { //Player Death Animation
            player.y = 240;
            Timer dTimer = new Timer();
            TimerTask dTask = new TimerTask() {
                @Override
                public void run() {
                    playerImg = storage.getDeathKeep_Player();
                    keepDeath = true; 
                }
            };
            dTimer.schedule(dTask, 400);
            if(!keepDeath) playerImg = storage.getDeath_Player();
        }
        else{
            if(player.y != 240) playerImg = storage.getJump_Player();
            else playerImg = storage.getRun_Player();
        }
//        bufferGraphic.setColor(Color.red);
//        bufferGraphic.drawRect(player.x, player.y, player.width, player.height);
        bufferGraphic.drawImage(playerImg, player.x, player.y, null);
        // ----- Enemy -----
        
        for(int i = enemyVec.size() -1; i >= 0; i--){
//            bufferGraphic.setColor(Color.red);
//            bufferGraphic.drawRect(enemy.x, enemy.y, enemy.width, enemy.height);
            Enemy enemy = enemyVec.get(i);
            
            if(distance < 300){
                switch(enemy.type)
                {
                    case Enemy.CANDYFLY:
                        bufferGraphic.drawImage(storage.getMID_candyfly(), enemy.x, enemy.y, null);
                        break;
                    case Enemy.ICECREAM:
                        bufferGraphic.drawImage(storage.getTOP_icecream(), enemy.x, enemy.y, null);
                        break;
                    case Enemy.CREAMSHEEP:
                        bufferGraphic.drawImage(storage.getBOT_creamsheep(), enemy.x, enemy.y, null);
                        break;
                }
            }
            else{
                switch(enemy.type)
                {
                    case Enemy.CANDYFLY:
                        bufferGraphic.drawImage(storage.getMID_honeybee(), enemy.x, enemy.y, null);
                        break;
                    case Enemy.ICECREAM:
                        bufferGraphic.drawImage(storage.getTOP_honeycookie(), enemy.x, enemy.y, null);
                        break;
                    case Enemy.CREAMSHEEP:
                        bufferGraphic.drawImage(storage.getBOT_honeycube(), enemy.x, enemy.y, null);
                        break;
                }
            }
            if(!isDeath) enemy.x -= enemy.speed;
        }    
        // ----- Pancake Shooting -----
        for(int i = cakeVec.size() -1; i >= 0; i--){
            Pancake cake = cakeVec.get(i);
//            bufferGraphic.setColor(Color.red);
//            bufferGraphic.drawRect(cake.x, cake.y, cake.width, cake.height);
            
            if(cake.onCollision == true){
                bufferGraphic.drawImage(storage.getPop_paritcle(), cake.x, cake.y, null);
                cake.x += 0;
            }
            else{
                bufferGraphic.drawImage(storage.getCakeImg(), cake.x, cake.y, null);
                cake.x += cake.speed;
            }
            
        }
        // ----- UI Part -----
        // ----- HP Bar -----
        bufferGraphic.setColor(new Color(50, 50, 50, 150));
        bufferGraphic.fillRect(20, 45, (int)(player.MAX_life / 2), 30);
        bufferGraphic.setColor(Color.ORANGE);
        bufferGraphic.fillRect(20, 45, (int)(player.life / 2), 30);
        bufferGraphic.drawImage(storage.getUI_hpIcon(), 10, 15, null);
        // ----- Distance Score -----
        bufferGraphic.setColor(Color.white);
        bufferGraphic.setFont(new Font("넥슨 풋볼고딕 B", Font.PLAIN, 24));
        bufferGraphic.drawString("달린 거리 : " + distance + "m", 271, 30);
        g.drawImage(bufferImg, 0, 0, null);
    }
    
    public void setRunnale(Boolean _isRun) {
        isRun = _isRun;
        if(isRun) {
            thread = new Thread(this);
            thread.start();
        } else {
            try {
                thread.join();
            } catch (Exception e) {
            }            
        }
    }
    
    public void PancakeShoot()
    {
        try {
            ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(storage.getCakeShootClip())));
            Clip cakeshoot = AudioSystem.getClip();
            cakeshoot.open(ais);
            cakeshoot.start();
            Pancake cake = new Pancake(player.x + 110, player.y + 50, 94, 55);
            cakeVec.add(cake);
        } catch (Exception e) {
        }
            
    }
    public void Jump()
    {
         isJump = true;
            try {
                ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(storage.getJumpClip())));
                Clip jump = AudioSystem.getClip();
                jump.open(ais);
                jump.start();
            }
            catch (Exception ex) {
           }
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!isDeath) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_W: 
                case KeyEvent.VK_UP: 
                    // ----- Jump Sound -----
                    if(player.y == 240) Jump();
                    break;
                case KeyEvent.VK_SPACE: 
                    // ----- Pancake Shooting Sound -----
                    PancakeShoot();
                    break;
            }
        }
        else{
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                this.setRunnale(false);
                System.exit(1);
            }
        }
        //System.out.println("Jump State : " + isJump);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public void run() {
        while(isRun){
            try {
                System.out.println("        In Jump - Thread.run");
                System.out.println("        In Jump - isRun : " + isRun);
                if(isRun && !isStream){
                    System.out.println("        In Jump - BGM/Task Start");
                    mTimer.scheduleAtFixedRate(mTask, 100, 100);
                    bgm.open(ais);
                    bgm.start();
                    isStream = true;
                }
                pretime=System.currentTimeMillis();
                if(!isDeath){
                    // ----- Player Jump  ----- 
                    if(isJump == true){
                        if(player.y < 150){
                            player.y = 150;
                            isJump = false;
                        }
                        player.y -= player.jumpPower;
                    }
                    else if(isJump == false){
                        player.y += player.jumpPower;
                        if(player.y >= 240){
                            player.y = 240;
                        }
                    }
                    // ----- Pancake Collision ----- 
                    for(int i = cakeVec.size() -1; i >= 0; i--){
                        Pancake cake = (Pancake)cakeVec.get(i);
                        for(int j = enemyVec.size() -1; j >= 0; j--){
                            Enemy enemy = (Enemy)enemyVec.get(j);
                            if(cake.intersects(enemy) && !cake.onCollision){
                                cake.onCollision = true;
                                enemyVec.remove(enemy);
                                Timer cTimer = new Timer();
                                TimerTask cTask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        cakeVec.remove(cake);
                                    }
                                };
                                cTimer.schedule(cTask, 350);
                                player.life += 20;
                                if(player.life > player.MAX_life) player.life = player.MAX_life;
                            }
                            if(enemy.x < 0) enemyVec.remove(enemy);
                        }
                        if(cake.x > 720) cakeVec.remove(cake);
                    }
                    // ----- Player Collision ----- 
                    for(int i = enemyVec.size() -1; i >= 0; i--){
                        Enemy enemy = (Enemy)enemyVec.get(i);
                        if(enemy.intersects(player)){
                            enemyVec.remove(enemy);
                            player.life -= 30;
                        }
                    }
                }
                if(keepDeath) toTitle++;
                if(toTitle > 66){
                    bgm.close();
                    System.out.println("        In Jump - BGM Closed");
                    frame.setState(JumpTests.STATE.TITLE);
                }
                Thread.sleep(33 - (System.currentTimeMillis()-pretime));
            } catch (Exception e) {
            }
            repaint();
        }
    }
}
