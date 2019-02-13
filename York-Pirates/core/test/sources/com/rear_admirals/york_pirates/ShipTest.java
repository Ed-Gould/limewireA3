package sources.com.rear_admirals.york_pirates;

import com.rear_admirals.york_pirates.Ship;
import org.junit.Test;
import sources.GameTest;

import static com.rear_admirals.york_pirates.College.Derwent;
import static com.rear_admirals.york_pirates.ShipType.Brig;
import static org.junit.Assert.*;

public class ShipTest extends GameTest {

    @Test
    public void setupShip() {
        Ship ship = new Ship(Brig, Derwent);
        assertTrue(true);
    }

}