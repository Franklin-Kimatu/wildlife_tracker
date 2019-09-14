import org.sql2o.*;

import java.util.List;

public abstract class Animal {

    public String animalname;
    public int sightingId;
    public int id;
//    public Animal(String animalname,int sightingId){
//        this.animalname =animalname;
//        this.sightingId =sightingId;
//    }
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
            String sql ="INSERT INTO animals(animalname,sightingId) VALUES (:animalname,:sightingId)";
            this.id =(int)con.createQuery(sql,true).addParameter("animalname",this.animalname).addParameter("sightingId",this.sightingId).executeUpdate().getKey();
        }
    }
    public int getId(){
        return id;
    }

    public static List<Animal> all(){
        String sql ="SELECT * FROM animals";
        try(Connection con =DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }
    public static Animal find(int id){
        try(Connection con=DB.sql2o.open()){
            String sql = "SELECT * FROM animals where id =:id";
            Animal animal =con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Animal.class);
            return animal;
        }
    }

}
