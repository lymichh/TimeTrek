package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class JurasicObjects extends GameObject{
    
    public JurasicObjects(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        createHitbox();
    }
    
    public void createHitbox(){
        if(objType == CRANEO){
            initHitbox((int)(61 * 0.5), (int)(44 * 0.5));
            xDrawOffset = (int)(7 * 0.5 * Game.SCALE);
            yDrawOffset = (int)(7 * 0.5 * Game.SCALE);
            hitbox.y += yDrawOffset + (int)(Game.SCALE * 7);
        }else if(objType == HUEVO){
            initHitbox((int)(59 * 0.5), (int)(42 * 0.5));
            xDrawOffset = (int)(8 * 0.5 * Game.SCALE);
            yDrawOffset = (int)(9 * 0.5 * Game.SCALE);
            hitbox.y += yDrawOffset + (int)(Game.SCALE * 7);
        }else if(objType == HUELLA){
            initHitbox((int)(49 * 0.5), (int)(42 * 0.5));
            xDrawOffset = (int)(13 * 0.5 * Game.SCALE);
            yDrawOffset = (int)(9 * 0.5 * Game.SCALE);
            hitbox.y += yDrawOffset + (int)(Game.SCALE * 7);
        }
        
        hitbox.x += xDrawOffset/2;
        
    }
    
    public void update(){
        updateAnimationTick();
    }
    
}
