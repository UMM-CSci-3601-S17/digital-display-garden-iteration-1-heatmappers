package umm3601.Converter;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.junit.Before;
import org.junit.Test;
import umm3601.Converter.Converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static umm3601.Converter.Mapping.dataInsert;
import static umm3601.Converter.Mapping.fieldAssigner;
import static umm3601.Converter.Mapping.makeDocument;

/**
 * Created by Brian on 3/8/2017.
 */
public class MappingTest {



    @Test
    public void TestFieldAssigner() throws IOException{
        ArrayList<String> testString = fieldAssigner();

        //test that field assigner assigns a size of 8 components in list
        assertEquals("should have size of 8 equal to test ArrayList",8,testString.size());

        //test that the names in the field assigner is correct
        assertEquals("should be Common Name","commonName",testString.get(1));
        assertEquals("should be Common Name","Wall",testString.get(5));
    }



    @Test
    public void TestDataInsert() throws IOException{
//        String FILE_NAME = "/home/saliy002/Downloads/shel.xlsx";
//        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
//        Converter v = new Converter();
//        dataInsert(v.convertToLists(excelFile));
        ArrayList<ArrayList<Object>> mockData = new ArrayList<>();
        //test that inserts mock data into an Array

        Object obj = new Object();

        for(ArrayList a: mockData){
            a.add(obj);
            a.add(obj);
            a.add(obj);
            mockData.add(a);
            dataInsert(mockData);

            assertEquals("there should be one Arraylist within an ArrayList",1,mockData.size());

            assertEquals("there should be one 3 objects within an array list ",3,a.size());
        }
    }

}
