
import org.sql2o.*;

import java.util.List;

public class Sighting {

    private String location;
    private String rangername;
    private int id;

    public Sighting(String location, String rangername){
        this.location =location;
        this.rangername =rangername;
    }

    public String getLocation(){
        return location;
    }
    public String getRangerName(){
        return rangername;
    }

    @Override
    public boolean equals(Object otherSighting){
        if (!(otherSighting instanceof Sighting)){
            return false;
        }else{
            Sighting newSighting = (Sighting) otherSighting;
            return this.getLocation().equals(newSighting.getLocation()) &&
                    this.getRangerName().equals(newSighting.getRangerName());
        }
    }

    public  void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (location,rangername) VALUES(:location,:rangername)";
            con.createQuery(sql).addParameter("location",this.location).addParameter("rangername",this.rangername).executeUpdate();
        }
    }
    public static List<Sighting>all(){
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }
}
