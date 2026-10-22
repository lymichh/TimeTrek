
package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class MiddleObjects extends GameObject {
    
    public MiddleObjects(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        createHitbox();
    }
    
    public void createHitbox(){
        double c = 0.7;
        if(objType == LIBRO){
            initHitbox((int)(56 * c), (int)(61 * c));
            xDrawOffset = (int)(20 * c * Game.SCALE);
            yDrawOffset = (int)(17 * c * Game.SCALE);
        }else if(objType == MOLINO){
            initHitbox((int)(53 * c), (int)(66 * c));
            xDrawOffset = (int)(16 * c * Game.SCALE);
            yDrawOffset = (int)(28 * c * Game.SCALE);
        }else if(objType == BARCO){
            initHitbox((int)(60 * c), (int)(68 * c));
            xDrawOffset = (int)(20 * c * Game.SCALE);
            yDrawOffset = (int)(29 * c * Game.SCALE);
        }
        
    }
    
    public void update(){
        updateAnimationTick();
    }
    
}
