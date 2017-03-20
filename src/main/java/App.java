import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/coin-combo", (request, response) -> {
      // Create new HashMap object named model
      Map<String, Object> model = new HashMap<String, Object>();
      // Parse the input from the form and create new Float named moneyInput
      Float moneyInput = Float.parseFloat(request.queryParams("valueInput"));
      //create new ChangeMachine so we can call its method
      ChangeMachine master = new ChangeMachine();
      // call its method
      String moneyOutput = master.makeChange(moneyInput);
      // use the HashMap to attach moneyOutput to the key/name of form valueInput
      model.put("valueInput", moneyOutput);
      model.put("template", "templates/coin-combo.vtl");
      // Is this where the key value gets placed into the $ ?
      return new ModelAndView(model, layout);
      // WTF? new VelocityTemplateEngine?
    }, new VelocityTemplateEngine());
  }
}
