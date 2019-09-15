import org.sql2o.*;

import java.util.List;

public class EndangeredAnimal extends Animal {

    public static final String DATABASE_TYPE ="endangered";

    public EndangeredAnimal(String animalname, int sightingId){
        this.animalname=animalname;
        this.sightingId=sightingId;
        type=DATABASE_TYPE;
    }

    public static EndangeredAnimal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql ="SELECT *FROM animals where id =:id";
            EndangeredAnimal animal =con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(EndangeredAnimal.class);
            return animal;
        }
    }

    public static List<EndangeredAnimal> all(){
        String sql ="SELECT * FROM animals WHERE type ='endangered'";
        try(Connection con =DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }
}
