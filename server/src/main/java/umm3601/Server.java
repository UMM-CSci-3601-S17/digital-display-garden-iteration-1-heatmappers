package umm3601;

import umm3601.Flowers.Flower;
import umm3601.Flowers.flowerController;
import umm3601.user.UserController;

import java.io.IOException;

import static spark.Spark.*;


public class Server {
    public static void main(String[] args) throws IOException {

        UserController userController = new UserController();
        flowerController FlowerController = new flowerController();

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
 
            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        // Simple example route
        get("/hello", (req, res) -> "Hello World");

        // Redirects for the "home" page
        redirect.get("", "/");
        redirect.get("/", "http://localhost:9000");

        // List users
        get("api/users", (req, res) -> {
            res.type("application/json");
            return userController.listUsers(req.queryMap().toMap());
        });

        get("api/flowers", (req, res) -> {
            res.type("application/json");
            return FlowerController.listFlowers(req.queryMap().toMap());
        });

        // See specific user
        get("api/users/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            return userController.getUser(id);
        });

        // Get average ages by company
        get("api/avgUserAgeByCompany", (req, res) -> {
            res.type("application/json");
            return userController.getAverageAgeByCompany();
        });

        get("api/getBeds", (req, res) -> {
            res.type("application/json");
            return FlowerController.getBeds();
        });

        // Handle "404" file not found requests:
        notFound((req, res) -> {
            res.type("text");
            res.status(404);
            return "Sorry, we couldn't find that!";
        });

//        get("api/flowers/:id", (req, res) -> {
//            res.type("application/json");
//            String id = req.params("id");
//            return FlowerController.getFlower(id);
//        });
        //this might get the flower within the bed by id
        get("api/flowers/getBeds/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            FlowerController.getBeds();
            return FlowerController.getFlower(id);
        });
    }

}
