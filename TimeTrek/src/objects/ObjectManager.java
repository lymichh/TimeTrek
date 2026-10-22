package objects;

import audio.AudioPlayer;
import entities.Player;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import levels.LevelManager;
import utilz.LoadSave;
import static utilz.Constants.ObjectConstants.*;

public class ObjectManager {

    private Playing playing;
    private BufferedImage[][] potionImgs, containerImgs, jurasicImgs, prehistoricImgs, antiquityImgs, middleImgs, finalImgs, maquinaImgs;
    private BufferedImage spikeImg;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Spike> spikes;
    private ArrayList<JurasicObjects> jurasics;
    private ArrayList<PrehistoricObjects> prehistorics;
    private ArrayList<AntiquityObjects> antiques;
    private ArrayList<MiddleObjects> middles;
    private ArrayList<FinalObjects> finals;
    private ArrayList<MaquinaObjects> piezas;
    private int objValue;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
    }

    public void checkSpikesTouched(Player p) {
        for (Spike s : spikes) {
            if (s.getHitbox().intersects(p.getHitbox())) {
                p.kill();
            }
        }
    }

    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for (Potion p : potions) {
            if (p.isActive()) {
                if (hitbox.intersects(p.getHitbox())) {
                    p.setActive(false);
                    applyEffectToPlayer(p);
                }
            }
        }
        
        for (JurasicObjects jo : jurasics) {
            if (jo.isActive()) {
                if (hitbox.intersects(jo.getHitbox())) {
                    playing.setObjectValue(jo.objType);
                    playing.setObjectPicked(true);
                    jo.setActive(false);
                    if (jo.getObjType() == CRANEO) {
                        playing.getGame().getAudioPlayer().setVolume(0.8f);
                        playing.getGame().getAudioPlayer().playEffect(AudioPlayer.FOSSIL);
                    } else {
                        if (jo.getObjType() == HUEVO) {
                            playing.getGame().getAudioPlayer().setVolume(0.8f);
                            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.EGGS);
                        } else {
                            if (jo.objType == HUELLA) {
                                playing.getGame().getAudioPlayer().setVolume(0.8f);
                                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.FOOTPRINTS);
                            }
                        }
                    }
                }
            }
        }

        for (PrehistoricObjects po : prehistorics) {
            if (po.isActive()) {
                if (hitbox.intersects(po.getHitbox())) {
                    playing.setObjectValue(po.objType);
                    playing.setObjectPicked(true);
                    po.setActive(false);
                    if (po.getObjType() == PINTURA) {
                        playing.getGame().getAudioPlayer().setVolume(0.8f);
                        playing.getGame().getAudioPlayer().playEffect(AudioPlayer.PAINT);
                    } else {
                        if (po.getObjType() == CUCHILLO) {
                            playing.getGame().getAudioPlayer().setVolume(0.8f);
                            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.KNIFE);
                        } else {
                            if (po.objType == RUEDA) {
                                playing.getGame().getAudioPlayer().setVolume(0.8f);
                                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.WHEEL);
                            }
                        }
                    }
                }
            }
        }
        
        for (AntiquityObjects ao : antiques) {
            if (ao.isActive()) {
                if (hitbox.intersects(ao.getHitbox())) {
                    playing.setObjectValue(ao.objType);
                    playing.setObjectPicked(true);
                    ao.setActive(false);
                    if (ao.getObjType() == CIVILIZACION) {
                        playing.getGame().getAudioPlayer().setVolume(0.8f);
                        playing.getGame().getAudioPlayer().playEffect(AudioPlayer.CIVILIZATION);
                    } else {
                        if (ao.getObjType() == MONEDA) {
                            playing.getGame().getAudioPlayer().setVolume(0.8f);
                            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.COIN);
                        } else {
                            if (ao.objType == ESCULTURA) {
                                playing.getGame().getAudioPlayer().setVolume(0.8f);
                                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.SCULPTURE);
                            }
                        }
                    }
                }
            }
        }
        
        for (MiddleObjects mo : middles) {
            if (mo.isActive()) {
                if (hitbox.intersects(mo.getHitbox())) {
                    playing.setObjectValue(mo.objType);
                    playing.setObjectPicked(true);
                    mo.setActive(false);
                    if (mo.getObjType() == LIBRO) {
                        playing.getGame().getAudioPlayer().setVolume(0.8f);
                        playing.getGame().getAudioPlayer().playEffect(AudioPlayer.UNIVERSITY);
                    } else {
                        if (mo.getObjType() == MOLINO) {
                            playing.getGame().getAudioPlayer().setVolume(0.8f);
                            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.WINDMILL);
                        } else {
                            if (mo.objType == BARCO) {
                                playing.getGame().getAudioPlayer().setVolume(0.8f);
                                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.CONQUEST);
                            }
                        }
                    }
                }
            }
        }
        
        for (FinalObjects fo : finals) {
            if (fo.isActive()) {
                if (hitbox.intersects(fo.getHitbox())) {
                    playing.setObjectValue(fo.objType);
                    playing.setObjectPicked(true);
                    fo.setActive(false);
                    if (fo.getObjType() == BOMBA) {
                        playing.getGame().getAudioPlayer().setVolume(0.8f);
                        playing.getGame().getAudioPlayer().playEffect(AudioPlayer.BOMB);
                    } else {
                        if (fo.getObjType() == PENICILINA) {
                            playing.getGame().getAudioPlayer().setVolume(0.8f);
                            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.PILLS);
                        } else {
                            if (fo.objType == INTERNET) {
                                playing.getGame().getAudioPlayer().setVolume(0.8f);
                                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.WIFI);
                            }
                        }
                    }
                }
            }
        }
        
        for (MaquinaObjects qo : piezas) {
            if (qo.isActive()) {
                if (hitbox.intersects(qo.getHitbox())) {
                    qo.setActive(false);
                }
            }
        }
    }

    public int getObjValue() {
        return objValue;
    }

    public void setObjValue(int objValue) {
        this.objValue = objValue;
    }

    public void applyEffectToPlayer(Potion p) {
        if (p.getObjType() == RED_POTION) {
            playing.getPlayer().changeHealth(RED_POTION_VALUE);
        } else {
            playing.getPlayer().changePower(BLUE_POTION_VALUE);
        }
    }

    public void checkObjectHit(Rectangle2D.Float attackbox) {
        for (GameContainer gc : containers) {
            if (gc.isActive() && !gc.doAnimation) {
                if (gc.getHitbox().intersects(attackbox)) {
                    gc.setAnimation(true);
                    int type = 0;
                    potions.add(new Potion((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y - gc.getHitbox().height / 2), type));
                    return;
                }
            }
        }
    }

    public void loadObjects(Level newLevel) {
        potions = new ArrayList<>(newLevel.getPotions());
        containers = new ArrayList<>(newLevel.getContainers());
        spikes = newLevel.getSpikes();
        jurasics = new ArrayList<>(newLevel.getJurasics());
        prehistorics = new ArrayList<>(newLevel.getPrehistorics());
        antiques = new ArrayList<>(newLevel.getAntiques());
        middles = new ArrayList<>(newLevel.getMiddles());
        finals = new ArrayList<>(newLevel.getFinals());
        piezas = new ArrayList<>(newLevel.getPiezas());
    }

    private void loadImgs() {
        BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];

        for (int j = 0; j < potionImgs.length; j++) {
            for (int i = 0; i < potionImgs[j].length; i++) {
                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);
            }
        }

        BufferedImage jurasicSprite = LoadSave.GetSpriteAtlas(LoadSave.JURASICO_ATLAS);
        jurasicImgs = new BufferedImage[3][7];

        for (int j = 0; j < jurasicImgs.length; j++) {
            for (int i = 0; i < jurasicImgs[j].length; i++) {
                jurasicImgs[j][i] = jurasicSprite.getSubimage(75 * i, 60 * j, 75, 60);
            }
        }

        BufferedImage prehistoricSprite = LoadSave.GetSpriteAtlas(LoadSave.PREHISTORIA_ATLAS);
        prehistoricImgs = new BufferedImage[3][11];

        for (int j = 0; j < prehistoricImgs.length; j++) {
            for (int i = 0; i < prehistoricImgs[j].length; i++) {
                if (1088 * (i + 1) <= prehistoricSprite.getWidth() && 1088 * (j + 1) <= prehistoricSprite.getHeight()) {
                    prehistoricImgs[j][i] = prehistoricSprite.getSubimage(1088 * i, 1088 * j, 1088, 1088);
                }
            }
        }
        
        BufferedImage antiquitySprite = LoadSave.GetSpriteAtlas(LoadSave.ANTIGUEDAD_ATLAS);
        antiquityImgs = new BufferedImage[3][10];

        for (int j = 0; j < antiquityImgs.length; j++) {
            for (int i = 0; i < antiquityImgs[j].length; i++) {
                antiquityImgs[j][i] = antiquitySprite.getSubimage(640 * i, 640 * j, 640, 640);
            }
        }
        
        BufferedImage middleSprite = LoadSave.GetSpriteAtlas(LoadSave.MEDIA_ATLAS);
        middleImgs = new BufferedImage[3][11];

        for (int j = 0; j < middleImgs.length; j++) {
            for (int i = 0; i < middleImgs[j].length; i++) {
                middleImgs[j][i] = middleSprite.getSubimage(80 * i, 80 * j, 80, 80);
            }
        }
        
        BufferedImage FinalSprite = LoadSave.GetSpriteAtlas(LoadSave.CONTEMPORANEO_ATLAS);
        finalImgs = new BufferedImage[3][11];

        for (int j = 0; j < finalImgs.length; j++) {
            for (int i = 0; i < finalImgs[j].length; i++) {
                finalImgs[j][i] = FinalSprite.getSubimage(640 * i, 640 * j, 640, 640);
            }
        }
        
        BufferedImage maquinaSprite = LoadSave.GetSpriteAtlas(LoadSave.MAQUINA_ATLAS);
        maquinaImgs = new BufferedImage[5][4];

        for (int j = 0; j < maquinaImgs.length; j++) {
            for (int i = 0; i < maquinaImgs[j].length; i++) {
                maquinaImgs[j][i] = maquinaSprite.getSubimage(40 * i, 40 * j, 40, 40);
            }
        }

        BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][8];

        for (int j = 0; j < containerImgs.length; j++) {
            for (int i = 0; i < containerImgs[j].length; i++) {
                containerImgs[j][i] = containerSprite.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }
        spikeImg = LoadSave.GetSpriteAtlas(LoadSave.TRAP_ATLAS);
    }

    public void update() {
        for (Potion p : potions) {
            if (p.isActive()) {
                p.update();
            }
        }

        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                gc.update();
            }
        }

        for (JurasicObjects jo : jurasics) {
            if (jo.isActive()) {
                jo.update();
            }
        }

        for (PrehistoricObjects po : prehistorics) {
            if (po.isActive()) {
                po.update();
            }
        }
        
        for (AntiquityObjects ao : antiques) {
            if (ao.isActive()) {
                ao.update();
            }
        }
        
        for (MiddleObjects mo : middles) {
            if (mo.isActive()) {
                mo.update();
            }
        }
        
        for (FinalObjects fo : finals) {
            if (fo.isActive()) {
                fo.update();
            }
        }
        
        for (MaquinaObjects qo : piezas) {
            if (qo.isActive()) {
                qo.update();
            }
        }
    }

    public void update(int[][] lvlData, Player player) {
        boolean isAnyActive = false;
        boolean isAnyActive2 = false;
        for (JurasicObjects c : jurasics) {
            if (c.isActive()) {
                c.update();
                isAnyActive = true;
            }
        }

        for (PrehistoricObjects p : prehistorics) {
            if (p.isActive()) {
                p.update();
                isAnyActive = true;
            }
        }
        
        for (AntiquityObjects a : antiques) {
            if (a.isActive()) {
                a.update();
                isAnyActive = true;
            }
        }
        
        for (MiddleObjects m : middles) {
            if (m.isActive()) {
                m.update();
                isAnyActive = true;
            }
        }
        
        for (FinalObjects f : finals) {
            if (f.isActive()) {
                f.update();
                isAnyActive = true;
            }
        }

        for (MaquinaObjects qo : piezas) {
            if (qo.isActive()) {
                qo.update();
                if (qo.objType == PIEZA1 || qo.objType == PIEZA2 || qo.objType == PIEZA3 || qo.objType == PIEZA4 || qo.objType == PIEZA5) {
                    isAnyActive2 = true;
                }
            }
        }

        if (!isAnyActive && !isAnyActive2) {
            playing.setLevelCompleted(true);
        }
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawPotions(g, xLvlOffset);
        drawContainers(g, xLvlOffset);
        drawTraps(g, xLvlOffset);
        drawJurasic(g, xLvlOffset);
        drawPrehistoric(g, xLvlOffset);
        drawAntiques(g, xLvlOffset);
        drawMiddle(g, xLvlOffset);
        drawFinal(g, xLvlOffset);
        drawPiezas(g, xLvlOffset);
    }

    public void drawJurasic(Graphics g, int xLvlOffset) {
        for (JurasicObjects jo : jurasics) {
            if (jo.isActive()) {
                int type = 0;
                if (jo.getObjType() == HUEVO) {
                    type = 1;
                }
                if (jo.getObjType() == HUELLA) {
                    type = 2;
                }
                g.drawImage(jurasicImgs[type][jo.getAniIndex()],
                        (int) (jo.getHitbox().x - jo.getxDrawOffset() - xLvlOffset),
                        (int) (jo.getHitbox().y - jo.getyDrawOffset()),
                        JURASIC_WIDTH, JURASIC_HEIGHT, null);
            }
        }
    }

    public void drawPrehistoric(Graphics g, int xLvlOffset) {
        for (PrehistoricObjects po : prehistorics) {
            if (po.isActive()) {
                int type = 0;
                if (po.getObjType() == CUCHILLO) {
                    type = 1;
                }
                if (po.getObjType() == RUEDA) {
                    type = 2;
                }
                g.drawImage(prehistoricImgs[type][po.getAniIndex()],
                        (int) (po.getHitbox().x - po.getxDrawOffset() - xLvlOffset),
                        (int) (po.getHitbox().y - po.getyDrawOffset()),
                        PREHISTORIC_WIDTH, PREHISTORIC_HEIGHT, null);
            }
        }
    }
    
    public void drawAntiques(Graphics g, int xLvlOffset) {
        for (AntiquityObjects ao : antiques) {
            if (ao.isActive()) {
                int type = 0;
                if (ao.getObjType() == MONEDA) {
                    type = 1;
                }
                if (ao.getObjType() == ESCULTURA) {
                    type = 2;
                }
                g.drawImage(antiquityImgs[type][ao.getAniIndex()],
                        (int) (ao.getHitbox().x - ao.getxDrawOffset() - xLvlOffset),
                        (int) (ao.getHitbox().y - ao.getyDrawOffset()),
                        ANTIQUITY_WIDTH, ANTIQUITY_HEIGHT, null);
            }
        }
    }
    
    public void drawMiddle(Graphics g, int xLvlOffset) {
        for (MiddleObjects mo : middles) {
            if (mo.isActive()) {
                int type = 0;
                if (mo.getObjType() == MOLINO) {
                    type = 1;
                }
                if (mo.getObjType() == BARCO) {
                    type = 2;
                }
                g.drawImage(middleImgs[type][mo.getAniIndex()],
                        (int) (mo.getHitbox().x - mo.getxDrawOffset() - xLvlOffset),
                        (int) (mo.getHitbox().y - mo.getyDrawOffset()),
                        MIDDLE_WIDTH, MIDDLE_HEIGHT, null);
            }
        }
    }
    
    public void drawFinal(Graphics g, int xLvlOffset) {
        for (FinalObjects fo : finals) {
            if (fo.isActive()) {
                int type = 0;
                if (fo.getObjType() == PENICILINA) {
                    type = 1;
                }
                if (fo.getObjType() == INTERNET) {
                    type = 2;
                }
                g.drawImage(finalImgs[type][fo.getAniIndex()],
                        (int) (fo.getHitbox().x - fo.getxDrawOffset() - xLvlOffset),
                        (int) (fo.getHitbox().y - fo.getyDrawOffset()),
                        CONTEMPORANEO_WIDTH, CONTEMPORANEO_HEIGHT, null);
            }
        }
    }
     
     public void drawPiezas(Graphics g, int xLvlOffset) {
        for (MaquinaObjects qo : piezas) {
            if (qo.isActive()) {
                int type = 0;
                if (qo.getObjType() == PIEZA2) {
                    type = 1;
                }
                if (qo.getObjType() == PIEZA3) {
                    type = 2;
                }
                if (qo.getObjType() == PIEZA4) {
                    type = 3;
                }
                if (qo.getObjType() == PIEZA5) {
                    type = 4;
                }
                
                g.drawImage(maquinaImgs[type][qo.getAniIndex()],
                        (int) (qo.getHitbox().x - qo.getxDrawOffset() - xLvlOffset),
                        (int) (qo.getHitbox().y - qo.getyDrawOffset()),
                        MAQUINA_WIDTH, MAQUINA_HEIGHT, null);
            }
        }
    }

    public void drawTraps(Graphics g, int xLvlOffset) {
        for (Spike s : spikes) {
            g.drawImage(spikeImg, (int) (s.getHitbox().x - xLvlOffset), (int) (s.getHitbox().y - s.getyDrawOffset()), SPIKE_WIDTH, SPIKE_HEIGHT, null);
        }
    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                int type = 0;
                g.drawImage(containerImgs[type][gc.getAniIndex()], (int) (gc.getHitbox().x - gc.getxDrawOffset() - xLvlOffset), (int) (gc.getHitbox().y - gc.getyDrawOffset()), CONTAINER_WIDTH,
                        CONTAINER_HEIGHT, null);
            }
        }
    }

    private void drawPotions(Graphics g, int xLvlOffset) {
        for (Potion p : potions) {
            if (p.isActive()) {
                int type = 0;
                if (p.getObjType() == RED_POTION) {
                    type = 1;
                }
                g.drawImage(potionImgs[type][p.getAniIndex()], (int) (p.getHitbox().x - p.getxDrawOffset() - xLvlOffset), (int) (p.getHitbox().y - p.getyDrawOffset()), POTION_WIDTH, POTION_HEIGHT,
                        null);
            }
        }
    }

    public void resetAllObjects() {

        loadObjects(playing.getLevelManager().getCurrentLevel());

        for (Potion p : potions) {
            p.reset();
        }

        for (GameContainer gc : containers) {
            gc.reset();
        }

        for (JurasicObjects jo : jurasics) {
            jo.reset();
        }

        for (PrehistoricObjects po : prehistorics) {
            po.reset();
        }
        
        for (AntiquityObjects ao : antiques) {
            ao.reset();
        }
        
        for (MiddleObjects mo : middles) {
            mo.reset();
        }
        
        for (FinalObjects fo : finals) {
            fo.reset();
        }
        
        for (MaquinaObjects qo : piezas) {
            qo.reset();
        }
    }

}
