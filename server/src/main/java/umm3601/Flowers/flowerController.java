package umm3601.Flowers;


import com.google.gson.Gson;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.util.JSON;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


import java.io.IOException;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class flowerController {

    private final MongoCollection<Document> flowerCollection;

    public flowerController() throws IOException {
        // Set up our server address
        // (Default host: 'localhost', default port: 27017)
        // ServerAddress testAddress = new ServerAddress();

        // Try connecting to the server
        //MongoClient mongoClient = new MongoClient(testAddress, credentials);
        MongoClient mongoClient = new MongoClient(); // Defaults!

        // Try connecting to a database
        MongoDatabase db = mongoClient.getDatabase("test");

        flowerCollection = db.getCollection("flowers");
    }

    // List flowers
    public String listFlowers(Map<String, String[]> queryParams) {

        List<Bson> aggregateParams = new ArrayList<Bson>();

        AggregateIterable<Document> matchingFlowers = flowerCollection.aggregate(aggregateParams);
        return JSON.serialize(matchingFlowers);
    }

    // Get a single flower
    public String getFlower(String id) {
        FindIterable<Document> jsonFlowers
                = flowerCollection
                .find(eq("_id", new ObjectId(id)));
        Iterator<Document> iterator = jsonFlowers.iterator();
        if(iterator.hasNext()) {
            Document flower = iterator.next();

            return flower.toJson();
        }
        else
            return null;
    }


    //Get beds with flowers of common name
    public String getBeds() {
        AggregateIterable<Document> documents
                = flowerCollection.aggregate(
                Arrays.asList(
                        Aggregates.group("$Location",
                                Accumulators.push("flowers", "$Common Name")),
                        Aggregates.sort(Sorts.ascending("Common Name"))
                ));
        System.err.println(JSON.serialize(documents));
        return JSON.serialize(documents);
    }





}
