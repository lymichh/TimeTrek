package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] dinoArr, soldadoArr, dienteArr, reyArr, guerreroArr;
    private ArrayList<Dino> dinos = new ArrayList<>();
    private ArrayList<DienteDeSable> diente_sable = new ArrayList<>();
    private ArrayList<Soldier> soldados = new ArrayList<>();
    private ArrayList<Rey> reyes = new ArrayList<>();
    private ArrayList<FinalEnemy> guerreros = new ArrayList<>();

    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
    }

    public void loadEnemies(Level level) {
        dinos = level.getDinos();
        soldados = level.getSoldados();
        diente_sable = level.getDientes();
        reyes = level.getReyes();
        guerreros = level.getGuerreros();
    }

    public void update(int[][] lvlData, Player player) {
        boolean isAnyActive = false;
        for (Dino c : dinos) {
            if (c.isActive()) {
                c.update(lvlData, player);
                isAnyActive = true;
            }
        }
        
        for (DienteDeSable d : diente_sable) {
            if (d.isActive()) {
                d.update(lvlData, player);
                isAnyActive = true;
            }
        }
        
        for (Soldier s : soldados) {
            if (s.isActive()) {
                s.update(lvlData, player);
                isAnyActive = true;
            }
        }
        
        for (Rey r : reyes) {
            if (r.isActive()) {
                r.update(lvlData, player);
                isAnyActive = true;
            }
        }
        
        for (FinalEnemy f : guerreros) {
            if (f.isActive()) {
                f.update(lvlData, player);
                isAnyActive = true;
            }
        }
        
        if (!isAnyActive) {
            playing.setLevelCompleted(true);
        }
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawDinosaurios(g, xLvlOffset);
        drawSoldados(g, xLvlOffset);
        drawDienteDeSable(g, xLvlOffset);
        drawReyes(g, xLvlOffset);
        drawGuerreros(g, xLvlOffset);
    }

    private void drawDinosaurios(Graphics g, int xLvlOffset) {
        for (Dino c : dinos) {
            if (c.isActive()) {
                g.drawImage(dinoArr[c.getState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - DINO_DRAWOFFSET_X + c.flipX(),
                        (int) c.getHitbox().y - DINO_DRAWOFFSET_Y, DINO_WIDTH * c.flipW(), DINO_HEIGHT, null);
            }
        }

    }
    
    private void drawDienteDeSable(Graphics g, int xLvlOffset) {
        for (DienteDeSable d : diente_sable) {
            if (d.isActive()) {
                g.drawImage(dienteArr[d.getState()][d.getAniIndex()], (int) d.getHitbox().x - xLvlOffset - DIENTE_DRAWOFFSET_X + d.flipX(),
                        (int) d.getHitbox().y - DIENTE_DRAWOFFSET_Y, DIENTE_WIDTH * d.flipW(), DIENTE_HEIGHT, null);
            }
        }

    }
    
    private void drawSoldados(Graphics g, int xLvlOffset) {
        for (Soldier s : soldados) {
            if (s.isActive()) {
                g.drawImage(soldadoArr[s.getState()][s.getAniIndex()], (int) s.getHitbox().x - xLvlOffset - SOLDADO_DRAWOFFSET_X + s.flipX(),
                        (int) s.getHitbox().y - SOLDADO_DRAWOFFSET_Y, SOLDADO_WIDTH * s.flipW(), SOLDADO_HEIGHT, null);
            }
        }

    }
    
    private void drawReyes(Graphics g, int xLvlOffset) {
        for (Rey r : reyes) {
            if (r.isActive()) {
                g.drawImage(reyArr[r.getState()][r.getAniIndex()], (int) r.getHitbox().x - xLvlOffset - REY_DRAWOFFSET_X + r.flipX(),
                        (int) r.getHitbox().y - REY_DRAWOFFSET_Y, REY_WIDTH * r.flipW(), REY_HEIGHT, null);
            }
        }

    }
    
    private void drawGuerreros(Graphics g, int xLvlOffset) {
        for (FinalEnemy f : guerreros) {
            if (f.isActive()) {
                g.drawImage(guerreroArr[f.getState()][f.getAniIndex()], (int) f.getHitbox().x - xLvlOffset - GUERRERO_DRAWOFFSET_X + f.flipX(),
                        (int) f.getHitbox().y - GUERRERO_DRAWOFFSET_Y, GUERRERO_WIDTH * f.flipW(), GUERRERO_HEIGHT, null);
            }
        }

    }
    
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Dino c : dinos) {
            if (c.isActive()) {
                if(c.getState() != DEAD && c.getState() != HIT)
                    if (attackBox.intersects(c.getHitbox())) {
                        c.hurt(10);
                        return;
                    }
            }
        }
        
        for (DienteDeSable d : diente_sable) {
            if (d.isActive()) {
                if(d.getState() != DEAD && d.getState() != HIT)
                    if (attackBox.intersects(d.getHitbox())) {
                        d.hurt(10);
                        return;
                    }
            }
        }
        
        for (Soldier s : soldados) {
            if (s.isActive()) {
                if(s.getState() != DEAD && s.getState() != HIT)
                    if (attackBox.intersects(s.getHitbox())) {
                        s.hurt(10);
                        return;
                    }
            }
        }
        
        for (Rey r : reyes) {
            if (r.isActive()) {
                if(r.getState() != DEAD && r.getState() != HIT)
                    if (attackBox.intersects(r.getHitbox())) {
                        r.hurt(10);
                        return;
                    }
            }
        }
        
        for (FinalEnemy f : guerreros) {
            if (f.isActive()) {
                if(f.getState() != DEAD && f.getState() != HIT)
                    if (attackBox.intersects(f.getHitbox())) {
                        f.hurt(10);
                        return;
                    }
            }
        }
    }

    private void loadEnemyImgs() {
        dinoArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.DINOSAURIO_SPRITE), 9, 5, DINO_WIDTH_DEFAULT, DINO_HEIGHT_DEFAULT);
        soldadoArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SOLDADO_SPRITE), 6, 5, SOLDADO_WIDTH_DEFAULT, SOLDADO_HEIGHT_DEFAULT);
        dienteArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.DIENTE_SPRITE), 6, 5, DIENTE_WIDTH_DEFAULT, DIENTE_HEIGHT_DEFAULT);
        reyArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.REY_SPRITE), 9, 5, REY_WIDTH_DEFAULT, REY_HEIGHT_DEFAULT);
        guerreroArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.GUERRERO_SPRITE), 6, 5, GUERRERO_WIDTH_DEFAULT, GUERRERO_HEIGHT_DEFAULT);
    }
    
    private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
        BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
        for (int j = 0; j < tempArr.length; j++)
            for (int i = 0; i < tempArr[j].length; i++)
                    tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
        return tempArr;
    }

    public void resetAllEnemies() {
        for (Dino c : dinos) {
            c.resetEnemy();
        }
        
        for (DienteDeSable d : diente_sable) {
            d.resetEnemy();
        }
        
        for (Soldier s : soldados) {
            s.resetEnemy();
        }
        
        for (Rey r : reyes) {
            r.resetEnemy();
        }
        
        for (FinalEnemy f : guerreros) {
            f.resetEnemy();
        }
    }

}
