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
        final String FILE_NAME = "/home/saliy002/Downloads/shel.xlsx";
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

            for(int i = 4; i < data.size();i++){

                Flower.insertOne(makeDocument(data,i));
            }


        }

        private static ArrayList<String> fieldAssigner(){
            ArrayList<String> fields = new ArrayList<>();
            fields.add("#");
            fields.add("Common Name");
            fields.add("Cultivar");
            fields.add("Source");
            fields.add("Type");
            fields.add("Wall");
            fields.add("Location");
            fields.add("Description");
            return fields;
        }
}