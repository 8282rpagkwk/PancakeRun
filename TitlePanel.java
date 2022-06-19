
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 8282r
 */
public class TitlePanel extends BasePanel implements MouseListener{
    AudioInputStream ais;
    Clip bgm;
    boolean isStream = false;
    JumpTests frame;
    
    public TitlePanel(JumpTests obj){
        super();
        frame = obj;
        this.addMouseListener(this);
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

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        bufferGraphic.drawImage(storage.getTitle(), 0, 0, null);
        g.drawImage(bufferImg, 0, 0, null);
        System.out.println("        In Title - paint");
    }
    
    @Override
    public void BGM() {
        try {
            ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(storage.getTitlebgmClip())));
            bgm = AudioSystem.getClip();
//            bgm.open(ais);
//            bgm.start();
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isRun) {            
            try {
                System.out.println("        In Title - Thread.run");
                System.out.println("        In Title - isRun : " + isRun);
                if(isRun && !isStream){
                    System.out.println("        In Title - BGM Streaming");
                    bgm.open(ais);
                    bgm.start();
                    isStream = true;
                } 
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("        In Title - mousePressed");
        bgm.close();
        System.out.println("        In Title - BGM Closed");
        frame.setState(JumpTests.STATE.GAME);
    }

    @Override
    public void mouseReleased(MouseEvent e) {   }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) { }
    
}
