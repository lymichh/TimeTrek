package ui;

import gamestates.Gamestate;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.Constants;
import utilz.Constants.UI.*;
import utilz.LoadSave;

public class MenuButton {
    
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = Buttons.B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;
    
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }
    
    private void initBounds(){
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, Buttons.B_WIDTH, Buttons.B_HEIGHT);
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * Buttons.B_WIDTH_DEFAULT, rowIndex * Buttons.B_HEIGHT_DEFAULT, Buttons.B_WIDTH_DEFAULT, Buttons.B_HEIGHT_DEFAULT);
        }
    }
    
    public void draw(Graphics g){
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, Buttons.B_WIDTH, Buttons.B_HEIGHT, null);
    }
    
    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    
    public void applyGameState(){
        Gamestate.state = state;
    }
    
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
    
    public Gamestate getState(){
        return state;
    }
}
