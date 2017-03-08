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


import javax.print.Doc;
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

        if (queryParams.containsKey("owner")) {
            String targetAge = (queryParams.get("owner")[0]);
            aggregateParams.add(Aggregates.match(eq("owner", targetAge)));
        }


        if (queryParams.containsKey("category")) {
            String targetAge = (queryParams.get("category")[0]);
            aggregateParams.add(Aggregates.match(eq("category", targetAge)));
        }
        if (queryParams.containsKey("body")) {
            String targetAge = (queryParams.get("body")[0]);
            aggregateParams.add(Aggregates.match(Filters.regex("body",targetAge)));
        }

        if (queryParams.containsKey("status")) {
            try {
                boolean status = Boolean.parseBoolean(queryParams.get("status")[0]);
                aggregateParams.add(Aggregates.match(eq("status", status)));
            }
            catch(NumberFormatException nfe)
            {
                nfe.printStackTrace();
            }
        }

        if (queryParams.containsKey("orderBy")) {
            String sortType = (queryParams.get("orderBy")[0]);
            aggregateParams.add(Aggregates.sort(Filters.eq(sortType,1))); //1 is Ascending, -1 is Descending
        }

        if (queryParams.containsKey("limit")) {
            try {
                int limit = Integer.parseInt(queryParams.get("limit")[0]);
                if(limit > 0) {
                    aggregateParams.add(Aggregates.limit(limit)); //1 is Ascending, -1 is Descending
                }
            }catch(NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }

        AggregateIterable<Document> matchingFlowers = flowerCollection.aggregate(aggregateParams);
        return JSON.serialize(matchingFlowers);
    }

    // Get a single flower
    public String getFlower(String id) {
        FindIterable<Document> jsonFlowers
                = flowerCollection
                .find(eq("_id", new ObjectId(id)));

//        Document jsonFlower
//                = flowerCollection
//                .find(eq("_id", new ObjectId(id))).first();
//        if(jsonFlower != null)
//            return jsonFlower.toJson();
//        return null;
        Iterator<Document> iterator = jsonFlowers.iterator();
        if(iterator.hasNext()) {
            Document flower = iterator.next();

            return flower.toJson();
        }
        else
            return null;
    }

    public Document getSummaryCategories()
    {
        Document output = new Document();
        Document filter = new Document();
        DistinctIterable<String> categories = flowerCollection.distinct("category", filter, String.class);
        for(String s : categories)
        {
            filter = new Document();
            filter.append("category",s);
            float entries = flowerCollection.count(filter);
            filter.append("status", true);
            long finished = flowerCollection.count(filter);
            output.append(s, finished/entries);
        }
        return output;
    }

    public Document getSummaryOwners()
    {
        Document output = new Document();
        Document filter = new Document();
        DistinctIterable<String> categories = flowerCollection.distinct("owner", filter, String.class);
        for(String s : categories)
        {
            filter = new Document();
            filter.append("owner",s);
            float entries = flowerCollection.count(filter);
            filter.append("status", true);
            long finished = flowerCollection.count(filter);
            output.append(s, finished/entries);
        }
        return output;
    }


    // List flowers
    public String getSummary() {
        Document output = new Document();

        Document filter;
        float cntEntries;
        long cntFinished;

        //Get count of all entries
        cntEntries = flowerCollection.count();

        //Get Count of all Completed Tasks
        filter = new Document();
        filter.append("status", true); // TODO needs testing, confirmation
        cntFinished = flowerCollection.count(filter);
        output.append("percentToDosComplete: ", (cntFinished/(float)cntEntries));
        output.append("categoriesPercentComplete: ", getSummaryCategories());
        output.append("ownersPercentComplete: ", getSummaryOwners());

        long poj = flowerCollection.count(Filters.regex("body","Lorem"));
        output.append("percentBodiesContain\"Lorem\"", poj /cntEntries);

        long extreme = flowerCollection.count(Filters.and(Filters.regex("body","Lorem"),(Filters.eq("status",true))));

        output.append("percentBodiesContain\"Lorem\"Finished", extreme /(float)poj);
        return JSON.serialize(output);
    }


    //Get the average age of all flowers by company
    public String getBeds() {
        AggregateIterable<Document> documents
                = flowerCollection.aggregate(
                Arrays.asList(
                        Aggregates.group("$Location",
                                    Accumulators.push("flowers", "$#")),
                        Aggregates.sort(Sorts.ascending("#"))
                        )
                )


                ;
        System.err.println(JSON.serialize(documents));
        return JSON.serialize(documents);
    }

    public String getFlowers() {
        AggregateIterable<Document> documents
                = flowerCollection.aggregate(
                Arrays.asList(
                        Aggregates.group("$Common Name",
                                Accumulators.push("flowers", "$_id")),
                        Aggregates.sort(Sorts.ascending("_id"))
                ));
        System.err.println(JSON.serialize(documents));
        return JSON.serialize(documents);
    }

}
