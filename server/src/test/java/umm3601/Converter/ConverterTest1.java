package umm3601.Converter;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;



import static org.junit.Assert.assertEquals;
import static umm3601.Converter.Converter.convertToLists;


/**
 * Created by carav008 on 3/7/17.
 */
public class ConverterTest1 {


//    @Before
//    public void setup() throws IOException{
//        final String FILE_NAME = "/home/carav008/IdeaProjects/digital-display-garden-iteration-1-heatmappers/server/src/main/java/umm3601/AccessionList2016.xlsx";
//        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
//    }


    //test to see if the the convertTo list does actually make it into an ArrayList of ArrayLists
    @Test
    public void TestConvertToLists() throws IOException{
        ArrayList<ArrayList<Object>> testHolder =  new ArrayList<>();
        ArrayList<Object> test =  new ArrayList<>();

        String FILE_NAME = "/home/carav008/IdeaProjects/digital-display-garden-iteration-1-heatmappers/server/src/main/java/umm3601/Converter/AccessionList2016.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));

        testHolder = convertToLists(excelFile);
        test = testHolder.get(4);


        assertEquals("the first element should be a type of type S","S",test.get(4));

    }


    @Test
    public void TestConvertToListSize() throws IOException{

        ArrayList<ArrayList<Object>> testHolder =  new ArrayList<>();
        String FILE_NAME = "/home/carav008/IdeaProjects/digital-display-garden-iteration-1-heatmappers/server/src/main/java/umm3601/Converter/AccessionList2016.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));


        //testing that its getting all the rows and columns so there must be 362 instead of 358
        testHolder = convertToLists(excelFile);
        assertEquals("size of the ArrayList of ArrayList should have 356 elements",testHolder.size(),362);


        //testing that each ArrayList in the list of ArrayList has 8 elements
        for(ArrayList a: testHolder) {
            assertEquals("Each ArrayList within the test holder should contain ", 8, a.size());
        }
    }
}
