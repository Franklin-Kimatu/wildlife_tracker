import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThrivingAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    ThrivingAnimal testThrivingAnimal = new ThrivingAnimal("Lion",1);
    ThrivingAnimal testAnotherThrivingAnimal = new ThrivingAnimal("Lion",1);
    ThrivingAnimal firstThrivingAnimal = new ThrivingAnimal("Lion",1);
    ThrivingAnimal secondThrivingAnimal = new ThrivingAnimal("Zebra",3);

    Sighting testSighting = new Sighting("ZoneOne","Frank");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void thrivingAnimal_instantiatesCorrectly_true(){
        assertEquals(true,testThrivingAnimal instanceof ThrivingAnimal);
    }

    @Test
    public void getAnimalName_getAnimalNamesInSighting(){
        assertEquals("Lion",testThrivingAnimal.getAnimalName());
    }
    @Test
    public void getSightingId_animalHasSightingId(){
        assertEquals(1,testThrivingAnimal.getSightingId());
    }
    @Test
    public void equals_returnsTrueIfNameAndPersonAreSame_true1(){
        assertTrue(testThrivingAnimal.equals(testAnotherThrivingAnimal));
    }

    //saving monster to database
    @Test
    public void save_returnIfDescriptionAreTheSame(){
        testThrivingAnimal.save();
        assertTrue(ThrivingAnimal.all().get(0).equals(testThrivingAnimal));
    }

    //setting the animal id
    @Test
    public void save_assignsIdToMonster(){
        testThrivingAnimal.save();
        ThrivingAnimal savedThrivingAnimal= (ThrivingAnimal)ThrivingAnimal.all().get(0);
        assertEquals(savedThrivingAnimal.getId(),testThrivingAnimal.getId());
    }
    // returning all enteries in the database
    @Test
    public void all_returnsAllInstancesOfMonster_true(){
        firstThrivingAnimal.save();
        secondThrivingAnimal.save();
        assertEquals(true,ThrivingAnimal.all().get(0).equals(firstThrivingAnimal));
        assertEquals(true,ThrivingAnimal.all().get(1).equals(secondThrivingAnimal));
    }
    //finding animals by id
    @Test
    public void find_returnAnimalsWithSameId_secondAnimal(){
        ThrivingAnimal firstThrivingAnimal = new ThrivingAnimal("Lion",1);
        firstThrivingAnimal.save();
        ThrivingAnimal secondThrivingAnimal = new ThrivingAnimal("Zebra",3);
        secondThrivingAnimal.save();
        assertEquals(ThrivingAnimal.find(secondThrivingAnimal.getId()),secondThrivingAnimal);
    }

    //association of anima to sighting
    @Test
    public void save_savesSightingIdIntoDB_true(){
        Sighting testSighting = new Sighting("ZoneOne","Frank");
        testSighting.save();
        ThrivingAnimal testThrivingAnimal =new ThrivingAnimal("Lion",testSighting.getId());
        testThrivingAnimal.save();
        ThrivingAnimal savedThrivingAnimal= (ThrivingAnimal) ThrivingAnimal.find(testThrivingAnimal.getId());
        assertEquals(savedThrivingAnimal.getSightingId(),testSighting.getId());
    }
}