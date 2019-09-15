import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sql2o.*;
import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion",1,"healthy","newborn");
    EndangeredAnimal testAnotherEndangeredAnimal = new EndangeredAnimal("Lion",1,"healthy","newborn");
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Lion",1,"healthy","newborn");
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Zebra",3,"okay","adult");

    Sighting testSighting = new Sighting("ZoneOne","Frank");

    @Test
    public void animal_instantiatesCorrectly_true(){
        assertEquals(true,testEndangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void endangeredAnimal_instantiatesWithName_String(){
        assertEquals("Lion",testEndangeredAnimal.getAnimalName());
    }
    @Test
    public void endangeredAnimal_animalHasSightingId_int(){
        assertEquals(1,testEndangeredAnimal.getSightingId());
    }
    @Test
    public void endangeredAnimal_hasAStateOfHealth(){
        assertEquals("healthy",testEndangeredAnimal.getEndangeredHealth());
    }
    @Test
    public void endangeredAnimal_hasACertainAge(){
        assertEquals("newborn",testEndangeredAnimal.getEndangeredAge());
    }
    @Test
    public void equals_returnsTrueIfNameAndPersonAreSame_true1(){
        assertTrue(testEndangeredAnimal.equals(testAnotherEndangeredAnimal));
    }

    //saving monster to database
    @Test
    public void save_returnIfDescriptionAreTheSame(){
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
    }

    //setting the animal id
    @Test
    public void save_assignsIdToEndangeredAnimal(){
        testEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal= (EndangeredAnimal)EndangeredAnimal.all().get(0);
        assertEquals(savedEndangeredAnimal.getId(),testEndangeredAnimal.getId());
    }
    // returning all enteries in the database
    @Test
    public void all_returnsAllInstancesOfMonster_true(){
        firstEndangeredAnimal.save();
        secondEndangeredAnimal.save();
        assertEquals(true,EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
        assertEquals(true,EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
    }
    //finding animals by id
    @Test
    public void find_returnAnimalsWithSameId_secondAnimal(){
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Lion",1,"healthy","newborn");
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Zebra",3,"okay","adult");
        secondEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()),secondEndangeredAnimal);
    }

    //association of animal to sighting
    @Test
    public void save_savesSightingIdIntoDB_true(){
        testSighting.save();
        EndangeredAnimal testEndangeredAnimal =new EndangeredAnimal("Lion",testSighting.getId(),"healthy","newborn");
        testEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = (EndangeredAnimal) EndangeredAnimal.find(testEndangeredAnimal.getId());
        assertEquals(savedEndangeredAnimal.getSightingId(),testSighting.getId());
    }
}