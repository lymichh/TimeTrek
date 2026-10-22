package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private ArrayList<Level> levels;
    private int lvlIndex = 0;

    public LevelManager(Game game) {
        this.game = game;
        levels = new ArrayList<>();
        buildAllLevels();
        importOutsideSprites(levels.get(lvlIndex).getLevelAtlas());
    }

    public void loadNextLevel() {
        lvlIndex++;
        if (lvlIndex >= 5) {
            lvlIndex = 0;
            Gamestate.state = Gamestate.MENU;
        }

        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
        importOutsideSprites(newLevel.getLevelAtlas()); //Cambia los suelos
        game.getPlaying().setBackgroundImg(newLevel.getBackground(), newLevel.getCloudsGame()); // Cambia la img de fondo
    }

    private void buildAllLevels() {
        BufferedImage[] allLevels = LoadSave.GetAllLevels();
        String[] levelAtlases = {
            LoadSave.LEVEL_JURASICO_ATLAS, //Primer nivel
            LoadSave.LEVEL_PREHISTORIA_ATLAS, //Segundo nivel
            LoadSave.LEVEL_ANTIGUEDAD_ATLAS, //Tercer nivel
            LoadSave.LEVEL_EDADMEDIA_ATLAS, //Cuarto nivel
            LoadSave.LEVEL_CONTEMPORANEO_ATLAS, //Quinto nivel
            LoadSave.LEVEL_JURASICO_ATLAS
        };
        
        String[] backgrounds = {
            LoadSave.PLAYING_BG1, // Fondo para el primer nivel
            LoadSave.PLAYING_BG2, // Fondo para el segundo nivel
            LoadSave.PLAYING_BG3, // Fondo para el tercer nivel
            LoadSave.PLAYING_BG4, // Fondo para el cuarto nivel
            LoadSave.PLAYING_BG5, // Fondo para el quinto nivel
            LoadSave.PLAYING_BG2
        };
        
        boolean[] cloudInGame = { false, true, false, false, false, true};
        
        for (int i = 0; i < allLevels.length; i++) {
            BufferedImage img = allLevels[i];
            String levelAtlas = levelAtlases[i];
            String background = backgrounds[i];
            boolean cloudsGame = cloudInGame[i];
            levels.add(new Level(img, levelAtlas, background, cloudsGame));
        }
    }

    private void importOutsideSprites(String atlasName) {
        BufferedImage img = LoadSave.GetSpriteAtlas(atlasName);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < levels.get(lvlIndex).getLevelData()[0].length; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }
    
    public void drawTutorial(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < levels.get(5).getLevelData()[0].length; i++) {
                int index = levels.get(5).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levels.get(lvlIndex);
    }
    
    public Level getLevelTutorial(){
        return levels.get(5);
    }

    public int getAmountOfLevels() {
        return 5;
    }
    
    public int getLevelIndex(){
            return lvlIndex;
    }

}
