package sources.com.rear_admirals.york_pirates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rear_admirals.york_pirates.Department;
import com.rear_admirals.york_pirates.PirateGame;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sources.GameTest;

import static org.junit.Assert.*;

public class DepartmentTest extends GameTest{

    @Test
    public void purchase() {
        System.out.println("OI");
        PirateGame pirateGame = Mockito.mock(PirateGame.class);

        application.getApplicationListener().create();

        Department defence_dep = new Department("Derwent", "Attack", pirateGame);
        Department attack_dep = new Department("Vanbrugh", "Defence", pirateGame);

        System.out.println(pirateGame.getPlayer().getGold());
        //pirateGame.getPlayer().payGold(defence_dep.getPrice());
        //int initialD = pirateGame.getPlayer().getPlayerShip().getDefence();
        //assertTrue();
    }
}