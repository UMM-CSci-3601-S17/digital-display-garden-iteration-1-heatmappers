package umm3601.Converter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;

/**
 * Created by saliy002 on 3/6/17.
 */
public class Mapping {
    public static void main(String[] arg) throws IOException {
        final String FILE_NAME = "/home/carav008/IdeaProjects/digital-display-garden-iteration-1-heatmappers/server/src/main/java/umm3601/Converter/AccessionList2016.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Converter v = new Converter();
        dataInsert(v.convertToLists(excelFile));


    }


    public static Document makeDocument(ArrayList<ArrayList<Object>> data, int row) {


//            Mongo mongo = new Mongo("localhost", 27017);
//            DB db = mongo.getDB("yourdb");
//
//            DBCollection collection = db.getCollection("dummyColl");




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
