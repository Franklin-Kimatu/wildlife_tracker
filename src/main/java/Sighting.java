import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class Sighting {

    private String location;
    private String rangername;

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
}
