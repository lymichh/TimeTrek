package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class PrehistoricObjects extends GameObject{
    
    public PrehistoricObjects(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        createHitbox();
    }
    public void createHitbox(){
        double c = 0.045;
        if(objType == PINTURA){
            initHitbox((int)(595 * c), (int)(150 * c));
            xDrawOffset = (int)(235 * c * Game.SCALE);
            yDrawOffset = (int)(20 * c * Game.SCALE);
        }else if(objType == CUCHILLO){
            initHitbox((int)(527 * c), (int)(500 * c));
            xDrawOffset = (int)(299 * c * Game.SCALE);
            yDrawOffset = (int)(80 * c * Game.SCALE);
        }else if(objType == RUEDA){
            initHitbox((int)(748 * c), (int)(748 * c));
            xDrawOffset = (int)(185 * c * Game.SCALE);
            yDrawOffset = (int)(140 * c * Game.SCALE);
        }
        
    }
    
    public void update(){
        updateAnimationTick();
    }
    
}
