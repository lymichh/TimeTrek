package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.*;
import main.Game;
import objects.*;
import utilz.HelpMethods;
import static utilz.HelpMethods.*;

public class Level {

    private BufferedImage img;
    private String levelAtlas;
    private String background;
    private boolean cloudsGame;
    private int[][] lvlData;
    private ArrayList<Dino> dinos;
    private ArrayList<DienteDeSable> dientes;
    private ArrayList<Soldier> soldados;
    private ArrayList<Rey> reyes;
    private ArrayList<FinalEnemy> guerreros;
    
    private ArrayList<Potion> potions;
    private ArrayList<Spike> spikes;
    private ArrayList<GameContainer> containers;
    private ArrayList<JurasicObjects> jurasics;
    private ArrayList<PrehistoricObjects> prehistorics;
    private ArrayList<AntiquityObjects> antiques;
    private ArrayList<MiddleObjects> middles;
    private ArrayList<FinalObjects> finals;
    private ArrayList<MaquinaObjects> piezas;
    
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    private Point playerSpawn;

    public Level(BufferedImage img, String levelAtlas, String background, boolean cloudsGame) {
        this.img = img;
        this.levelAtlas = levelAtlas;
        this.background = background;
        this.cloudsGame = cloudsGame;
        createLevelData();
        createDinos();
        createSoldados();
        createDienteDeSable();
        createReyes();
        createGuerreros();
        createPotions();
        createContainers();
        createSpikes();
        createJurasics();
        createPrehistorics();
        createAntiques();
        createMiddles();
        createFinals();
        createPiezas();
        calcLvlOffsets();
        calcPlayerSpawn();
    }
    
    public void createSpikes(){
        spikes = HelpMethods.GetSpikes(img);
    }

    private void createContainers() {
        containers = HelpMethods.GetContainers(img);
    }
    
    private void createJurasics() {
        jurasics = HelpMethods.GetJurasics(img);
    }
    
    private void createPrehistorics() {
        prehistorics = HelpMethods.GetPrehistorics(img);
    }
    
    private void createAntiques() {
        antiques = HelpMethods.GetAntiques(img);
    }
    
    private void createMiddles() {
        middles = HelpMethods.GetMiddles(img);
    }
    
    private void createFinals() {
        finals = HelpMethods.GetFinals(img);
    }
    
    private void createPiezas() {
        piezas = HelpMethods.GetPiezas(img);
    }

    private void createPotions() {
        potions = HelpMethods.GetPotions(img);
    }

    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);
    }

    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
    }

    private void createDinos() {
        dinos = GetDinos(img);
    }
    
    private void createDienteDeSable() {
        dientes = GetDientes(img);
    }
    
    private void createSoldados() {
        soldados = GetSoldados(img);
    }
    
    private void createReyes() {
        reyes = GetReyes(img);
    }
    
    private void createGuerreros() {
        guerreros = GetGuerreros(img);
    }
    
    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return lvlData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    public ArrayList<Dino> getDinos() {
        return dinos;
    }
    
    public ArrayList<DienteDeSable> getDientes() {
        return dientes;
    }
    
    public ArrayList<Soldier> getSoldados() {
        return soldados;
    }
    
    public ArrayList<Rey> getReyes(){
        return reyes;
    }
    
    public ArrayList<FinalEnemy> getGuerreros(){
        return guerreros;
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<GameContainer> getContainers() {
        return containers;
    }
    
    public ArrayList<Spike> getSpikes(){
        return spikes;
    }
    
    public ArrayList<JurasicObjects> getJurasics() {
        return jurasics;
    }
    
    public ArrayList<PrehistoricObjects> getPrehistorics() {
        return prehistorics;
    }
    
    public ArrayList<AntiquityObjects> getAntiques() {
        return antiques;
    }
    
    public ArrayList<MiddleObjects> getMiddles() {
        return middles;
    }
    
    public ArrayList<FinalObjects> getFinals() {
        return finals;
    }
    
    public ArrayList<MaquinaObjects> getPiezas() {
        return piezas;
    }
    
    public String getLevelAtlas() {
        return levelAtlas;
    }
    
    public void setLevelAtlas(String levelAtlas) {
        this.levelAtlas = levelAtlas;
    }
    
    public String getBackground() {
        return background;
    }
    
    public void setBackground(String background) {
        this.background = background;
    }
    
    public boolean getCloudsGame() {
        return cloudsGame;
    }

    public void setCloudsGame(boolean cloudsGame) {
        this.cloudsGame = cloudsGame;
    }

}
