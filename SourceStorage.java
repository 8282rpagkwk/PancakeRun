
import java.awt.Image;
import javax.swing.ImageIcon;

public class SourceStorage {
    private Image run_Player;
    private Image jump_Player;
    private Image death_Player;
    private Image deathKeep_Player;
    private Image cakeImg;
    private Image BG_stage1;
    private Image BG_stage2;
    private Image CLD_stage1;
    private Image CLD_stage2;
    private Image BASE_stage1;
    private Image BASE_stage2;
    private Image UI_hpIcon;
    private Image MID_candyfly;
    private Image TOP_icecream;
    private Image BOT_creamsheep;
    private Image MID_honeybee;
    private Image TOP_honeycookie;
    private Image BOT_honeycube;
    private Image pop_paritcle;
    private Image Title;
    private String jumpClip = "src/jump.wav";
    private String bgmClip = "src/bgm.wav";
    private String cakeShootClip = "src/pancakeshoot.wav";
    private String titlebgmClip = "src/title_bgm.wav";

    public SourceStorage() {
        run_Player = new ImageIcon(getClass().getResource("player.gif")).getImage();
        jump_Player = new ImageIcon(getClass().getResource("player_jump.png")).getImage();
        death_Player = new ImageIcon(getClass().getResource("player_death.gif")).getImage();
        deathKeep_Player = new ImageIcon(getClass().getResource("player_deathkeep.png")).getImage();
        cakeImg = new ImageIcon(getClass().getResource("pancake.gif")).getImage();
        BG_stage1 = new ImageIcon(getClass().getResource("stage1_bg.png")).getImage();
        CLD_stage1 = new ImageIcon(getClass().getResource("stage1_bg_cloud.png")).getImage();
        BASE_stage1 = new ImageIcon(getClass().getResource("stage1_flatform.png")).getImage();
        BG_stage2 = new ImageIcon(getClass().getResource("stage2_bg.png")).getImage();
        CLD_stage2 = new ImageIcon(getClass().getResource("stage2_bg_cloud.png")).getImage();
        BASE_stage2 = new ImageIcon(getClass().getResource("stage2_flatform.png")).getImage();
        UI_hpIcon = new ImageIcon(getClass().getResource("hp_icon.png")).getImage();
        MID_candyfly = new ImageIcon(getClass().getResource("candyfly_mid.gif")).getImage();
        TOP_icecream = new ImageIcon(getClass().getResource("icecream_top.png")).getImage();
        BOT_creamsheep = new ImageIcon(getClass().getResource("creamsheep_bot.png")).getImage();
        MID_honeybee = new ImageIcon(getClass().getResource("honeybee_mid.gif")).getImage();
        TOP_honeycookie = new ImageIcon(getClass().getResource("honeycookie_top.png")).getImage();
        BOT_honeycube = new ImageIcon(getClass().getResource("honeycube_bot.png")).getImage();
        pop_paritcle = new ImageIcon(getClass().getResource("pop.gif")).getImage();
        Title = new ImageIcon(getClass().getResource("title.png")).getImage();
    }

    public Image getRun_Player() {
        return run_Player;
    }

    public Image getJump_Player() {
        return jump_Player;
    }
    
    public Image getDeath_Player() {
        return death_Player;
    }

    public Image getDeathKeep_Player() {
        return deathKeep_Player;
    }

    public Image getCakeImg() {
        return cakeImg;
    }

    public Image getBG_stage1() {
        return BG_stage1;
    }

    public Image getCLD_stage1() {
        return CLD_stage1;
    }

    public Image getBASE_stage1() {
        return BASE_stage1;
    }

    public Image getUI_hpIcon() {
        return UI_hpIcon;
    }

    public String getJumpClip() {
        return jumpClip;
    }

    public String getBgmClip() {
        return bgmClip;
    }

    public String getCakeShootClip() {
        return cakeShootClip;
    }

    public Image getMID_candyfly() {
        return MID_candyfly;
    }

    public Image getTOP_icecream() {
        return TOP_icecream;
    }

    public Image getBOT_creamsheep() {
        return BOT_creamsheep;
    }

    public Image getPop_paritcle() {
        return pop_paritcle;
    }

    public Image getBG_stage2() {
        return BG_stage2;
    }

    public Image getCLD_stage2() {
        return CLD_stage2;
    }

    public Image getBASE_stage2() {
        return BASE_stage2;
    }

    public Image getMID_honeybee() {
        return MID_honeybee;
    }

    public Image getTOP_honeycookie() {
        return TOP_honeycookie;
    }

    public Image getBOT_honeycube() {
        return BOT_honeycube;
    }

    public Image getTitle() {
        return Title;
    }

    public String getTitlebgmClip() {
        return titlebgmClip;
    }
    
}
