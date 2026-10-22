
package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class AntiquityObjects extends GameObject {
  
    public AntiquityObjects(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        createHitbox();
    }
    public void createHitbox(){
        double c = 0.08;
        if(objType == CIVILIZACION){
            initHitbox((int)(620 * c), (int)(480 * c));
            xDrawOffset = (int)(10 * c * Game.SCALE);
            yDrawOffset = (int)(140 * c * Game.SCALE);
        }else if(objType == MONEDA){
            initHitbox((int)(459 * c), (int)(295 * c));
            xDrawOffset = (int)(100 * c * Game.SCALE);
            yDrawOffset = (int)(110 * c * Game.SCALE);
        }else if(objType == ESCULTURA){
            initHitbox((int)(608 * c), (int)(62 * c));
            xDrawOffset = (int)(27 * c * Game.SCALE);
            yDrawOffset = (int)(210 * c * Game.SCALE);
        }
        
    }
    
    public void update(){
        updateAnimationTick();
    }
    
}
