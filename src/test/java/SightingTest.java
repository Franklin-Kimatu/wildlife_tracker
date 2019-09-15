import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
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
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }
    // returning all entries inthe database
    @Test
    public void all_returnsAllInstancesOfPerson_true(){
        firstSighting.save();
        secondSighting.save();
        assertEquals(true,Sighting.all().get(0).equals(firstSighting));
        assertEquals(true,Sighting.all().get(1).equals(secondSighting));
    }
    //save to assign id to database objects
    @Test
    public void save_assignsIdToObject(){
        testSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(testSighting.getId(),savedSighting.getId());
    }
    //database returns person with Id
    @Test
    public void find_returnsSightingWithSameId_secondSighting(){
        firstSighting.save();
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()),secondSighting);
    }

    //functionality to return all animals in a sighting
    @Test
    public void getAnimals_retrievesAllAnimalsFromDatabase_animalList(){
        testSighting.save();
        ThrivingAnimal firstAnimal =new ThrivingAnimal("Lion",testSighting.getId());
        firstAnimal.save();
        EndangeredAnimal secondAnimal =new EndangeredAnimal("Zebra",testSighting.getId());
        secondAnimal.save();
        Object[] animals = new Object[]{
                firstAnimal,secondAnimal
        };
        assertTrue(testSighting.getAnimals().containsAll(Arrays.asList(animals)));
    }
}