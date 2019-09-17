import org.sql2o.*;

import java.util.List;

public abstract class Animal {

     String animalname;
     int sightingId;
     int id;
     String type;
    String health;
    String age;
    public String getAnimalName(){
        return animalname;
    }
    public int getSightingId(){
        return sightingId;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Animal)){
            return false;
        }else{
            Animal newAnimal =(Animal) otherAnimal;
            return this.getAnimalName().equals(newAnimal.getAnimalName())
                    && this.getSightingId() ==newAnimal.getSightingId();
        }
    }
    //saving animals to database
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
    public int getId(){
        return id;
    }

}
