
package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class FinalObjects extends GameObject {
    
    public FinalObjects(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        createHitbox();
    }
    
    public void createHitbox(){
        double c = 0.06;
        if(objType == BOMBA){
            initHitbox((int)(640 * c), (int)(390 * c));
            xDrawOffset = (int)(0 * c * Game.SCALE);
            yDrawOffset = (int)(100 * c * Game.SCALE);
            hitbox.y += yDrawOffset + (int)(Game.SCALE * 3);
        }else if(objType == PENICILINA){
            initHitbox((int)(330 * c), (int)(540 * c));
            xDrawOffset = (int)(140 * c * Game.SCALE);
            yDrawOffset = (int)(40 * c * Game.SCALE);
        }else if(objType == INTERNET){
            initHitbox((int)(480 * c), (int)(410 * c));
            xDrawOffset = (int)(80 * c * Game.SCALE);
            yDrawOffset = (int)(100 * c * Game.SCALE);
            hitbox.y += yDrawOffset + (int)(Game.SCALE * 3);
        }
        
    }
    
    public void update(){
        updateAnimationTick();
    }
    
}