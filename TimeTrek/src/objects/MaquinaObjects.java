
package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class MaquinaObjects extends GameObject {
    
    public MaquinaObjects(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        createHitbox();
    }
    public void createHitbox(){
        if(objType == PIEZA1){
            initHitbox((int)(27), (int)(31));
            xDrawOffset = (int)(6 * Game.SCALE);
            yDrawOffset = (int)(3 * Game.SCALE);
        }else if(objType == PIEZA2){
            initHitbox((int)(28), (int)(22));
            xDrawOffset = (int)(6 * Game.SCALE);
            yDrawOffset = (int)(0 * Game.SCALE);
        }else if(objType == PIEZA3){
            initHitbox((int)(18), (int)(24));
            xDrawOffset = (int)(10 * Game.SCALE);
            yDrawOffset = (int)(2 * Game.SCALE);
        }else if(objType == PIEZA4){
            initHitbox((int)(7), (int)(29));
            xDrawOffset = (int)(16 * Game.SCALE);
            yDrawOffset = (int)(2 * Game.SCALE);
        }else if(objType == PIEZA5){
            initHitbox((int)(26), (int)(16));
            xDrawOffset = (int)(8 * Game.SCALE);
            yDrawOffset = (int)(-3 * Game.SCALE);
        }
        
    }
    
    public void update(){
        updateAnimationTick();
    }
    
}
