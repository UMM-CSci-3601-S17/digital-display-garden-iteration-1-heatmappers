package umm3601.Converter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by saliy002 on 3/6/17.
 */
public class Mapping {
    public static void main(String[] arg) throws IOException {

        //using absolute path for iteration 1 can be fixed next iteration
        final String FILE_NAME = "/home/carav008/IdeaProjects/digital-display-garden-iteration-1-heatmappers/server/src/main/java/umm3601/Converter/AccessionList2016.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Converter sample = new Converter();
        dataInsert(sample.convertToLists(excelFile));

    }


        public static Document makeDocument(ArrayList<ArrayList<Object>> data, int row) {


                Map<String, Object> documentMap = new HashMap<String, Object>();
                ArrayList<String> currField = fieldAssigner();
                for(int j = 0; j < data.get(row).size(); j++){
                    documentMap.put(currField.get(j),data.get(row).get(j));
                }

                Document document = new Document();
                document.putAll(documentMap);

                return document;

        }


        public static void dataInsert(ArrayList<ArrayList<Object>> data){
            MongoClient mongoClient = new MongoClient();
            MongoDatabase test = mongoClient.getDatabase("test");
            MongoCollection Flower = test.getCollection("flowers");

            for(int i = 0; i < data.size();i++){

                Flower.insertOne(makeDocument(data,i));
            }
        }

        public static ArrayList<String> fieldAssigner(){
            ArrayList<String> fields = new ArrayList<>();
            fields.add("plantID");
            fields.add("commonName");
            fields.add("cultivar");
            fields.add("source");
            fields.add("plantType");
            fields.add("Wall");
            fields.add("gardenLocation");
            fields.add("Description");
            return fields;
        }
}
