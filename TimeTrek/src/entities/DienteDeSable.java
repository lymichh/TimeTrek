
package entities;

import static utilz.Constants.EnemyConstants.*;
import java.awt.geom.Rectangle2D;
import main.Game;

public class DienteDeSable extends Enemy{
    private int attackBoxOffsetX;

    public DienteDeSable(float x, float y) {
        super(x, y, DIENTE_WIDTH, DIENTE_HEIGHT, DIENTES_SABLE);
        initHitbox(35, 24);
        initAttackBox();
    }

    private void initAttackBox() { //86,19
        attackBox = new Rectangle2D.Float(x, y, (int) ( 70* Game.SCALE), (int) (20 * Game.SCALE));
        attackBoxOffsetX = (int) (Game.SCALE * 30);
    }

    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }

        if (inAir) {
            updateInAir(lvlData);
        } else {
            switch (state) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);
                        if (isPlayerCloseForAttack(player)) {
                            newState(ATTACK);
                        }
                    }

                    move(lvlData);
                    break;
                case ATTACK:
                    if (aniIndex == 0) {
                        attackChecked = false;
                    }
                    if (aniIndex == 3 && !attackChecked) {
                        checkPlayerHit(attackBox, player);
                    }
                    break;
                case HIT:
                    break;
            }
        }
    }
    
}