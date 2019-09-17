import static spark.Spark.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/",(request, response) -> {
            Map<String,Object> model =new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new/animal",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model,"form.hbs");
        },new HandlebarsTemplateEngine());



        post("/display",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            String rangername =request.queryParams("rangername");
            String location = request.queryParams("location");
            String animalname = request.queryParams("animalname");
            String health = request.queryParams("health");
            String age =request.queryParams("age");
            String type = request.queryParams("type");
            if (type.equals("thriving")){
                ThrivingAnimal thrivingAnimal = new ThrivingAnimal(animalname,1);
                thrivingAnimal.save();
                Sighting sightingOne=new Sighting(location,rangername);
                sightingOne.save();
            }else if(type.equals("endangered")){
                EndangeredAnimal endangeredAnimals=new EndangeredAnimal(animalname,1,health,age);
                endangeredAnimals.save();
                Sighting sightingTwo= new Sighting(location,rangername);
                sightingTwo.save();
            }
            List<Sighting>allSightings=Sighting.all();
            List<ThrivingAnimal>allThrivingAnimals =ThrivingAnimal.all();
            List<EndangeredAnimal> allEndangeredAnimals =EndangeredAnimal.all();
            model.put("sightings",allSightings);
//            model.put("rangername",rangername);
//            model.put("animalname",animalname);
//            model.put("health",health);
//            model.put("age",age);
//            model.put("type",type);
            model.put("thrivingAnimal",allThrivingAnimals);
            model.put("endangeredAnimal",allEndangeredAnimals);
            return new ModelAndView(model,"display.hbs");
        },new HandlebarsTemplateEngine());

        get("/display",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            model.put("sightings",Sighting.all());
            model.put("thrivingAnimal",ThrivingAnimal.all());
            model.put("endangeredAnimal",EndangeredAnimal.all());
            return new ModelAndView(model,"display.hbs");
        },new HandlebarsTemplateEngine());




    }
}