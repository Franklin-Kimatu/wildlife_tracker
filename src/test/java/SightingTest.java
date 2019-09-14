import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    Sighting testSighting = new Sighting("ZoneOne","Frank");
    Sighting firstSighting = new Sighting("ZoneOne","Frank");
    Sighting secondSighting = new Sighting("ZoneOne","Frank");
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sighting_instantiatesCorrectly(){
        assertEquals(true,testSighting instanceof Sighting);
    }
    @Test
    public  void getLocation_sightingHasALocation(){
        assertEquals("ZoneOne",testSighting.getLocation());
    }
    @Test
    public void getRangerName_sightingHasARangerName(){
        assertEquals("Frank",testSighting.getRangerName());
    }
    @Test
    public void equals_returnsTrueIfLocationAndRangerNameAreSame(){
        assertTrue(firstSighting.equals(secondSighting));
    }
    //saving location
    @Test
    public void save_insertsObjectIntoDb_Sighting(){
        testSighting.save();
    }
}