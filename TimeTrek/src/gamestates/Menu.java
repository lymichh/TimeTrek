package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods{
    
    private MenuButton[] buttons = new MenuButton[4];
    private BufferedImage backgroundImgMenu, backgroundImg, nameGame;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        //loadBackground();
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
        nameGame = LoadSave.GetSpriteAtlas(LoadSave.NAME_GAME);
    }
    
    public void loadBackground(){
        backgroundImgMenu = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int)(backgroundImgMenu.getWidth() * Game.SCALE);
        menuHeight = (int)(backgroundImgMenu.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int)(45 * Game.SCALE);
    }
    
    private void loadButtons(){
        buttons[0] = new MenuButton((int)(120 * Game.SCALE), (int)(340 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton((int)(320 * Game.SCALE), (int)(340 * Game.SCALE), 3, Gamestate.TUTORIAL);
        buttons[2] = new MenuButton((int)(520 * Game.SCALE), (int)(340 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[3] = new MenuButton((int)(720 * Game.SCALE), (int)(340 * Game.SCALE), 2, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        
        g.drawImage(backgroundImgMenu, menuX, menuY, menuWidth, menuHeight, null);
        
        g.drawImage(nameGame, Game.GAME_WIDTH/5, 60, 500, 200, null);
        
        for(MenuButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isIn(e,mb)){
                mb.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isIn(e,mb)){
                if(mb.isMousePressed())
                    mb.applyGameState();
                if (mb.getState() == Gamestate.PLAYING){
                    game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
                }
                break;
            }
        }
        resetButtons();
    }
    
    public void resetButtons(){
        for(MenuButton mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb : buttons)
            mb.setMouseOver(false);
        
        for(MenuButton mb : buttons)
            if(isIn(e,mb)){
                mb.setMouseOver(true);
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            Gamestate.state = Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
