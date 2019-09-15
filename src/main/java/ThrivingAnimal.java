import org.sql2o.*;

import java.util.List;

public class ThrivingAnimal extends Animal{

    public static final String DATABASE_TYPE ="thriving";

    public ThrivingAnimal(String animalname, int sightingId) {

        this.animalname =animalname;
        this.sightingId=sightingId;
        type = DATABASE_TYPE;
    }

    public static List<ThrivingAnimal> all(){
        String sql ="SELECT * FROM animals WHERE type ='thriving'";
        try(Connection con =DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(ThrivingAnimal.class);
        }
    }
}
