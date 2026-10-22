package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String PLAYER_BIG = "player_big.png";
    
    public static final String LEVEL_JURASICO_ATLAS = "jurasico_sprites.png";
    public static final String LEVEL_PREHISTORIA_ATLAS = "prehistoria_sprites.png";
    public static final String LEVEL_ANTIGUEDAD_ATLAS = "antiguedad_sprites.png";
    public static final String LEVEL_EDADMEDIA_ATLAS = "edadMedia_sprites.png";
    public static final String LEVEL_CONTEMPORANEO_ATLAS = "contemporaneo_sprites.png";
    
    public static final String PLAYING_BG1 = "playing_bg1.png";
    public static final String PLAYING_BG2 = "playing_bg2.png";
    public static final String PLAYING_BG3 = "playing_bg3.jpeg";
    public static final String PLAYING_BG4 = "playing_bg4.jpeg";
    public static final String PLAYING_BG5 = "playing_bg5.png";
    public static final String PLAYING_BG_IMG = "playing_bg2.png";
    
    public static final String BIG_CLOUDS = "big_clouds.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";
    
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static final String MENU_BACKGROUND_IMG = "background_futuristic.jpg";
    public static final String STATUS_BAR = "health_power_bar.png";
    public static final String COMPLETED_IMG = "completed_sprite.png";
    public static final String NAME_GAME = "nombreJuego.png";
    public static final String DEATH_SCREEN = "death_screen.png";
    public static final String OPTIONS_MENU = "options_background.png";
    
    public static final String DINOSAURIO_SPRITE = "enemiesDinosaurio.png";
    public static final String SOLDADO_SPRITE = "enemiesSoldado.png";
    public static final String DIENTE_SPRITE = "enemiesDienteDeSable.png";
    public static final String REY_SPRITE = "enemiesRey.png";
    public static final String GUERRERO_SPRITE = "enemiesGuerrero.png";

    public static final String POTION_ATLAS = "potions_sprites.png";
    public static final String CONTAINER_ATLAS = "objects_sprites.png";
    public static final String TRAP_ATLAS = "trap_atlas.png";
    public static final String CANNON_ATLAS = "cannon_atlas.png";
    public static final String JURASICO_ATLAS = "objectsNvl1.png";
    public static final String PREHISTORIA_ATLAS = "objectsNvl2.png";
    public static final String ANTIGUEDAD_ATLAS = "objectsNvl3.png";
    public static final String MEDIA_ATLAS = "objectsNvl4.png";
    public static final String CONTEMPORANEO_ATLAS = "objectsNvl5.png";
    public static final String MAQUINA_ATLAS = "piezas_maquina.png";
    
    public static final String KEY_A = "key_a.png";
    public static final String KEY_W = "key_w.png";
    public static final String KEY_D = "key_d.png";
    public static final String KEY_J = "key_j.png";
    public static final String KEY_K = "key_k.png";
    public static final String KEY_SPACE = "key_space.png";
    public static final String FLECHA_ARRIBA = "FlechaArriba.png";
    public static final String FLECHA_Derecha = "FlechaDerecha.png";
    public static final String FLECHA_Izquierda = "FlechaIzquierda.png";
    public static final String ATAQUE_1 = "Ataque1.png";
    public static final String ATAQUE_2 = "Ataque2.png";
    
    public static final String CRANEO_INFO = "1.png";
    public static final String HUEVO_INFO = "2.png";
    public static final String HUELLA_INFO = "3.png";
    public static final String PINTURA_INFO = "4.png";
    public static final String CUCHILLO_INFO = "5.png";
    public static final String RUEDA_INFO = "6.png";
    public static final String CIVILIZACION_INFO = "7.png";
    public static final String MONEDA_INFO = "8.png";
    public static final String ESCULTURA_INFO = "9.png";
    public static final String LIBRO_INFO = "10.png";
    public static final String MOLINO_INFO = "11.png";
    public static final String BARCO_INFO = "12.png";
    public static final String BOMBA_INFO = "13.png";
    public static final String PENICILINA_INFO = "14.png";
    public static final String INTERNET_INFO = "15.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static BufferedImage[] GetAllLevels() {
        URL url = LoadSave.class.getResource("/lvls");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < filesSorted.length; i++) {
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".png")) {
                    filesSorted[i] = files[j];
                }

            }
        }

        BufferedImage[] imgs = new BufferedImage[filesSorted.length];

        for (int i = 0; i < imgs.length; i++)
            try {
                imgs[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return imgs;
    }

}
