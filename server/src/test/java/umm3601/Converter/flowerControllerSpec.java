package umm3601.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.bson.codecs.*;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonReader;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import umm3601.Flowers.flowerController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by carav008 on 3/7/17.
 */
public class flowerControllerSpec {
    private flowerController FlowerController;
    private String FlowerIdString;

    @Before
    public void clearAndPopulateDB() throws IOException {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> flowerDoc = db.getCollection("flowers" );
        flowerDoc.drop();
        List<Document> testFlowers = new ArrayList<>();
        testFlowers.add(Document.parse("{\n" +
                "                    type: \"S\",\n" +
                "                    number: 16001,\n" +
                "                    CommonName: \"Alternantherac\",\n" +
                "                    Cultivar: \"Experimental\"\n" +
                "                    Source: \"PA\"\n" +
                "                    Wall: \"''\"\n" +
                "                    Location: \"13\"\n" +
                "                    Comments: \"Name change from Purple Prince   14x18 spreader\"\n" +
                "                }"));

        testFlowers.add(Document.parse("{\n" +
                "                    type: \"V\",\n" +
                "                    number: 16248,\n" +
                "                    CommonName: \"Geranium\",\n" +
                "                    Cultivar: \"Fancy leaf Glitterati Ice Queen\"\n" +
                "                    Source: \"HC\"\n" +
                "                    Wall: \"C\"\n" +
                "                    Location: \"2N\"\n" +
                "                    Comments: \"Garden and container\"\n" +
                "                }"));


        testFlowers.add(Document.parse("{\n" +
                "                    type: \"V\",\n" +
                "                    number :16260,\n" +
                "                    CommonName: \"Dahlia\",\n" +
                "                    Cultivar: \"Starsister Crimson Tipped White\"\n" +
                "                    Source: \"DU\"\n" +
                "                    Wall: \"''\"\n" +
                "                    Location: \"2S\"\n" +
                "                    Comments: \" '' \"\n" +
                "                }"));
        ObjectId newFlowerID = new ObjectId();
        BasicDBObject flower = new BasicDBObject("_id", newFlowerID);
        flower = flower.append("type", "S")
                .append("number", 16002)
                .append("CommonName", "Angelonia")
                .append("Cultivar", "Serenita™ Pink F1")
                .append("Source", "AAS")
                .append("Wall", "")
                .append("Location", "7")
                .append("Comments", "");
        FlowerIdString = newFlowerID.toHexString();
        flowerDoc.insertMany(testFlowers);
        flowerDoc.insertOne(Document.parse(flower.toJson()));

        // It might be important to construct this _after_ the DB is set up
        // in case there are bits in the constructor that care about the state
        // of the database.
        FlowerController = new flowerController();
    }

    // http://stackoverflow.com/questions/34436952/json-parse-equivalent-in-mongo-driver-3-x-for-java
    private BsonArray parseJsonArray(String json) {
        final CodecRegistry codecRegistry
                = CodecRegistries.fromProviders(Arrays.asList(
                new ValueCodecProvider(),
                new BsonValueCodecProvider(),
                new DocumentCodecProvider()));

        JsonReader reader = new JsonReader(json);
        BsonArrayCodec arrayReader = new BsonArrayCodec(codecRegistry);

        return arrayReader.decode(reader, DecoderContext.builder().build());
    }

    private static String getCommonName(BsonValue val) {
        BsonDocument doc = val.asDocument();
        return ((BsonString) doc.get("CommonName")).getValue();
    }
    private static String getCultivar(BsonValue val) {
        BsonDocument doc = val.asDocument();
        return ((BsonString) doc.get("Cultivar")).getValue();
    }
    private static String getType(BsonValue val) {
        BsonDocument doc = val.asDocument();
        return ((BsonString) doc.get("type")).getValue();
    }



    @Test
    public void getAllFlowers() {
        Map<String, String[]> emptyMap = new HashMap<>();
        String jsonResult = FlowerController.listFlowers(emptyMap);
        BsonArray docs = parseJsonArray(jsonResult);

        assertEquals("Should be 4 flowers", 4, docs.size());
        List<String> names = docs
                .stream()
                .map(flowerControllerSpec::getCommonName)
                .sorted()
                .collect(Collectors.toList());
        List<String> expectedNames = Arrays.asList("Alternantherac", "Angelonia", "Dahlia", "Geranium");
        assertEquals("Names should match", expectedNames, names);
    }

    @Test
    public void getFlowerCultivar() {
        Map<String, String[]> emptyMap = new HashMap<>();
        String jsonResult = FlowerController.listFlowers(emptyMap);
        BsonArray docs = parseJsonArray(jsonResult);

        assertEquals("Should be 4 cultivars", 4, docs.size());
        List<String> cultivars = docs
                .stream()
                .map(flowerControllerSpec::getCultivar)
                .sorted()
                .collect(Collectors.toList());
        List<String> expectedCultivars = Arrays.asList("Experimental", "Fancy leaf Glitterati Ice Queen","Serenita™ Pink F1", "Starsister Crimson Tipped White");
        assertEquals("Names should match", expectedCultivars, cultivars);
    }

    @Test
    public void getFlowerswithTypeS() {
        Map<String, String[]> argMap = new HashMap<>();
        argMap.put("type", new String[] { "S" });
        String jsonResult = FlowerController.listFlowers(argMap);
        BsonArray docs = parseJsonArray(jsonResult);

        assertEquals("Should be 4 types", 4, docs.size());
        List<String> types = docs
                .stream()
                .map(flowerControllerSpec::getType)
                .sorted()
                .collect(Collectors.toList());
        List<String> expectedTypes = Arrays.asList("S","S","V","V");
        assertEquals("Names should match", expectedTypes, types);
    }

}

