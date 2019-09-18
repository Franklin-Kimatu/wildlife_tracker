import org.sql2o.*;

import java.util.List;

public class EndangeredAnimal extends Animal {

    public static final String DATABASE_TYPE ="endangered";
    private String health;
    private String age;

    public EndangeredAnimal(String animalname, int sightingId,String health,String age){
        this.animalname=animalname;
        this.sightingId=sightingId;
        type=DATABASE_TYPE;
        this.health =health;
        this.age=age;
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
            return con.createQuery(sql)
            .executeAndFetch(EndangeredAnimal.class);
        }
    }
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql ="INSERT INTO animals(animalname,sightingId,type,health,age) VALUES (:animalname,:sightingId,:type,:health,:age)";
            this.id =(int)con.createQuery(sql,true)
                    .addParameter("animalname",this.animalname)
                    .addParameter("sightingId",this.sightingId)
                    .addParameter("type",this.type)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .executeUpdate()
                    .getKey();
        }
    }

    public String getEndangeredHealth(){
        return health;
    }
    public String getEndangeredAge(){
        return age;
    }
}
