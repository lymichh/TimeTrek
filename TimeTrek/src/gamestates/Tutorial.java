package gamestates;

import entities.Player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import levels.LevelManager;
import main.Game;
import ui.PauseButton;
import ui.UrmButton;
import static utilz.Constants.Environment.*;
import static utilz.Constants.UI.URMButtons.*;
import utilz.LoadSave;

public class Tutorial extends State implements Statemethods {

    private Player player;
    private LevelManager levelManager;
    private Playing playing;
    private BufferedImage backgroundImg;
    private UrmButton menuB;
    private BufferedImage bigCloud, smallCloud;
    private int[] smallCloudsPos;
    private Random rnd = new Random();
    private BufferedImage key_a, key_w, key_d, key_j, key_k, key_space, arriba, derecha, izquierda, ataque1, ataque2;

    public Tutorial(Game game) {
        super(game);
        initClasses();
        loadImgs();
        loadButton();
    }

    public void initClasses() {
        playing = new Playing(game);
        levelManager = new LevelManager(game);
        player = new Player(100, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE), playing);
        player.loadLvlData(levelManager.getLevelTutorial().getLevelData());
    }

    public void loadImgs() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
        bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
        smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
        smallCloudsPos = new int[8];
        for (int i = 0; i < smallCloudsPos.length; i++) {
            smallCloudsPos[i] = (int) (90 * Game.SCALE) + rnd.nextInt((int) (100 * Game.SCALE));
        }
    }

    public void loadButton() {
        int menuX = (int) (760 * Game.SCALE);
        int menuY = (int) (10 * Game.SCALE);
        menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
    }

    @Override
    public void update() {
        menuB.update();
        player.update();
        levelManager.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        drawClouds(g);
        levelManager.drawTutorial(g, 0);
        menuB.draw(g);
        player.render(g, 0);
        key_a = LoadSave.GetSpriteAtlas(LoadSave.KEY_A);
        key_w = LoadSave.GetSpriteAtlas(LoadSave.KEY_W);
        key_d = LoadSave.GetSpriteAtlas(LoadSave.KEY_D);
        key_j = LoadSave.GetSpriteAtlas(LoadSave.KEY_J);
        key_k = LoadSave.GetSpriteAtlas(LoadSave.KEY_K);
        key_space = LoadSave.GetSpriteAtlas(LoadSave.KEY_SPACE);
        derecha = LoadSave.GetSpriteAtlas(LoadSave.FLECHA_Derecha);
        arriba = LoadSave.GetSpriteAtlas(LoadSave.FLECHA_ARRIBA);
        izquierda = LoadSave.GetSpriteAtlas(LoadSave.FLECHA_Izquierda);
        ataque1 = LoadSave.GetSpriteAtlas(LoadSave.ATAQUE_1);
        ataque2 = LoadSave.GetSpriteAtlas(LoadSave.ATAQUE_2);
        int x = 100;
        
        g.drawImage(key_w,  x + 60, 360 , key_w.getWidth()*3, key_w.getHeight()*3, null);
        g.drawImage(key_space, x +60, 400 , key_space.getWidth()*3, key_space.getHeight()*3, null);
        g.drawImage(arriba, x +15, 360 , arriba.getWidth(), arriba.getHeight(), null);
        
        
        g.drawImage(key_a,x + 230, 380 , key_a.getWidth()*3, key_a.getHeight()*3, null);
        g.drawImage(izquierda, x +150, 380 , izquierda.getWidth(), izquierda.getHeight(), null);
        
        g.drawImage(key_d, x +280, 380 , key_d.getWidth()*3, key_d.getHeight()*3, null);
        g.drawImage(derecha, x +330, 380 , derecha.getWidth(), derecha.getHeight(), null);
        
        g.drawImage(key_j, x +480, 380 , key_j.getWidth()*3, key_j.getHeight()*3, null);
        g.drawImage(ataque1, x +440, 380 , ataque1.getWidth(), ataque1.getHeight(), null);
        
        
        g.drawImage(key_k, x +550, 380 , key_k.getWidth()*3, key_k.getHeight()*3, null);
        g.drawImage(ataque2, x +600, 380 , ataque2.getWidth(), ataque2.getHeight(), null);
    }

    private void drawClouds(Graphics g) {
        for (int i = 0; i < 3; i++) {
            g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH, (int) (204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
        }

        for (int i = 0; i < smallCloudsPos.length; i++) {
            g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i, smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB)) {
            menuB.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
            }
        }

        menuB.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        if (isIn(e, menuB)) {
            menuB.setMouseOver(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.state = Gamestate.MENU;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_W:
                player.setJump(true);
                break;
            case KeyEvent.VK_J:
                player.setAttacking(true);
                break;
            case KeyEvent.VK_K:
                player.powerAttack();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_W:

                player.setJump(false);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        player.setAttacking(true);
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}
