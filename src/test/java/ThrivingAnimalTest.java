import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThrivingAnimalTest {
    @Rule DatabaseRule database = new DatabaseRule();

    Animal testAnimal = new Animal("Lion",1);
    Animal testAnotherAnimal = new Animal("Lion",1);
    Animal firstAnimal = new Animal("Lion",1);
    Animal secondAnimal = new Animal("Zebra",2);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void animal_instantiatesCorrectly_true(){
        assertEquals(true,testAnimal instanceof Animal);
    }

    @Test
    public void getAnimalName_getAnimalNamesInSighting(){
        assertEquals("Lion",testAnimal.getAnimalName());
    }
    @Test
    public void getSightingId_animalHasSightingId(){
        assertEquals(1,testAnimal.getSightingId());
    }
    @Test
    public void equals_returnsTrueIfNameAndPersonAreSame_true1(){
        assertTrue(testAnimal.equals(testAnotherAnimal));
    }

    //saving monster to database
    @Test
    public void save_returnIfDescriptionAreTheSame(){
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    //setting the animal id
    @Test
    public void save_assignsIdToMonster(){
        testAnimal.save();
        Animal savedAnimal= Animal.all().get(0);
        assertEquals(savedAnimal.getId(),testAnimal.getId());
    }
    // returning all enteries in the database
    @Test
    public void all_returnsAllInstancesOfMonster_true(){
        firstAnimal.save();
        secondAnimal.save();
        assertEquals(true,Animal.all().get(0).equals(firstAnimal));
        assertEquals(true,Animal.all().get(1).equals(secondAnimal));
    }
    //finding animals by id
    @Test
    public void find_returnAnimalsWithSameId_secondAnimal(){
        Animal firstAnimal = new Animal("Lion",1);
        firstAnimal.save();
        Animal secondAnimal = new Animal("Zebra",2);
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()),secondAnimal);
    }

    //association of anima to sighting
    @Test
    public void save_savesSightingIdIntoDB_true(){
        testSighting.save();
        Animal testAnimal =new Animal("Lion",testSighting.getId());
        testAnimal.save();
        Animal savedAnimal = Animal.find(testAnimal.getId());
        assertEquals(savedAnimal.getSightingId(),testSighting.getId());
    }
}