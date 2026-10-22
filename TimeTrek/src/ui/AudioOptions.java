package ui;

import gamestates.Gamestate;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import main.Game;
import utilz.Constants;

public class AudioOptions {
    
    private VolumeButton volumeButton;
    private SoundButton musicButton, sfxButton;
    private Game game;

    public AudioOptions(Game game) {
        this.game = game;
        createSoundButton();
        createVolumeButton();
    }
    
    public void createVolumeButton(){
        int vX = (int)(309 * Game.SCALE);
        int vY = (int)(281 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, Constants.UI.VolumeButtons.SLIDER_WIDTH, Constants.UI.VolumeButtons.VOLUME_HEIGHT);
    }
    
    public void createSoundButton(){
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int)(141 * Game.SCALE);
        int sfxY = (int)(187 * Game.SCALE);
        
        musicButton = new SoundButton(soundX, musicY, Constants.UI.PauseButtons.SOUND_SIZE, Constants.UI.PauseButtons.SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, Constants.UI.PauseButtons.SOUND_SIZE, Constants.UI.PauseButtons.SOUND_SIZE);
    }
    
    public void update(){
        musicButton.update();
        sfxButton.update();
        volumeButton.update();
    }
    
    public void draw(Graphics g){
        //sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);
        //volume buttons
        volumeButton.draw(g);
    }
    
    public void mouseDragged(MouseEvent e){
        if(volumeButton.isMousePressed()){
            float valueBefore = volumeButton.getFloatValue();
            volumeButton.chageX(e.getX());
            float valueAfter = volumeButton.getFloatValue();
            if(valueBefore != valueAfter){
                game.getAudioPlayer().setVolume(valueAfter);
            }
        }
    }
    
    public void mousePressed(MouseEvent e) {
        if(isIn(e,musicButton))
            musicButton.setMousePressed(true);
        else if(isIn(e,sfxButton))
            sfxButton.setMousePressed(true);
        else if(isIn(e,volumeButton))
            volumeButton.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if(isIn(e,musicButton)){
            if(musicButton.isMousePressed())
                musicButton.setMuted(!musicButton.isMuted());
                game.getAudioPlayer().toggleSongMute();
        }else if(isIn(e,sfxButton)){
            if(sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
                game.getAudioPlayer().toggleEffectMute();
        }
        musicButton.resetBools();
        sfxButton.resetBools();
        volumeButton.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);
        
        if (isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if (isIn(e, volumeButton))
            volumeButton.setMouseOver(true);

    }
    
    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(), e.getY());
    }
    
}

