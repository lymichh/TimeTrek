package ui;

import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.Game;
import objects.ObjectManager;
import utilz.Constants;
import static utilz.Constants.ObjectConstants.*;
import static utilz.Constants.UI.URMButtons.*;
import utilz.LoadSave;

public class ObjectOverlay {
    
    private Playing playing;
    private UrmButton playB;
    private BufferedImage player, info;
    private int value;

    public ObjectOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        loadButton();
    }
    
    public void loadButton() {
        int playX = (int) (760 * Game.SCALE);
        int playY = (int) (10 * Game.SCALE);
        playB = new UrmButton(playX, playY, URM_SIZE, URM_SIZE, 0);
    }
    
    public void loadBackground() {
        player = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_BIG);
    }
    
    public void update() {
        playB.update();
    }
    
    public void draw(Graphics g) {
        switch(playing.getObjValue()){
            case CRANEO:
                info = LoadSave.GetSpriteAtlas(LoadSave.CRANEO_INFO);
                break;
            case HUEVO:
                info = LoadSave.GetSpriteAtlas(LoadSave.HUEVO_INFO);
                break;
            case HUELLA:
                info = LoadSave.GetSpriteAtlas(LoadSave.HUELLA_INFO);
                break;
            case PINTURA:
                info = LoadSave.GetSpriteAtlas(LoadSave.PINTURA_INFO);
                break;
            case CUCHILLO:
                info = LoadSave.GetSpriteAtlas(LoadSave.CUCHILLO_INFO);
                break;
            case RUEDA:
                info = LoadSave.GetSpriteAtlas(LoadSave.RUEDA_INFO);
                break;
            case CIVILIZACION:
                info = LoadSave.GetSpriteAtlas(LoadSave.CIVILIZACION_INFO);
                break;
            case MONEDA:
                info = LoadSave.GetSpriteAtlas(LoadSave.MONEDA_INFO);
                break;
            case ESCULTURA:
                info = LoadSave.GetSpriteAtlas(LoadSave.ESCULTURA_INFO);
                break;
            case LIBRO:
                info = LoadSave.GetSpriteAtlas(LoadSave.LIBRO_INFO);
                break;
            case MOLINO:
                info = LoadSave.GetSpriteAtlas(LoadSave.MOLINO_INFO);
                break;
            case BARCO:
                info = LoadSave.GetSpriteAtlas(LoadSave.BARCO_INFO);
                break;
            case BOMBA:
                info = LoadSave.GetSpriteAtlas(LoadSave.BOMBA_INFO);
                break;
            case PENICILINA:
                info = LoadSave.GetSpriteAtlas(LoadSave.PENICILINA_INFO);
                break;
            case INTERNET:
                info = LoadSave.GetSpriteAtlas(LoadSave.INTERNET_INFO);
                break;
        }
        g.drawImage(info, 200, (int) (Game.GAME_HEIGHT - 470*Game.SCALE), 550, 550, null);
        g.drawImage(player, 0, (int) (Game.GAME_HEIGHT - 300*Game.SCALE), 300, 300, null);
        playB.draw(g);
    }
    
    public void mousePressed(MouseEvent e) {
        if (isIn(e, playB)) {
            playing.getGame().getAudioPlayer().stopEffect();
            playing.getGame().getAudioPlayer().setVolume(0.9f);
            playB.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, playB)) {
            if (playB.isMousePressed()) {
                playing.unobjectGame();
            }
        }
        playB.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        playB.setMouseOver(false);
        if (isIn(e, playB)) {
            playB.setMouseOver(true);
        }
    }
    
    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(), e.getY());
    }
    
}
