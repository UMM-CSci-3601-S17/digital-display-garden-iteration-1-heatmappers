package umm3601.Converter;



import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.*;



public class Converter extends ArrayList{


//    private final MongoCollection<Document> flowers;

//    public Converter(){
//        // Try connecting to a database
//        MongoClient mongoClient = new MongoClient(); // Defaults!
//
//        MongoDatabase db = mongoClient.getDatabase("test");
//
//        flowers = db.getCollection("flowers");
//
//
//
//    }


//    public void callDatabase(){
//
//    }
//////////////    ADRESS SAMPLE    ////////////////////////////////////////
///////final String FILE_NAME = "/home/saliy002/Downloads/shel.xlsx";//////
///////////////////////////////////////////////////////////////////////////

    public static void main(String[] args)throws IOException {

        final String FILE_NAME = "/home/carav008/IdeaProjects/digital-display-garden-iteration-1-heatmappers/server/src/main/java/umm3601/AccessionList2016.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        System.out.println(convertToLists(excelFile));

    }

    public Converter(){

    }


    public static ArrayList<ArrayList<Object>> convertToLists(FileInputStream file1)throws IOException{
        ArrayList<ArrayList<Object>> document = new ArrayList<ArrayList<Object>>();
            //FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(file1);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                ArrayList<Object> RoW = new ArrayList<Object>();
                int counter = 0;
                while (cellIterator.hasNext() && counter != 8) {
                    //blank
                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        RoW.add(currentCell.getStringCellValue());

                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        RoW.add(currentCell.getNumericCellValue());
                    }else if (currentCell.getCellTypeEnum() == CellType.BLANK) {
                        RoW.add("");
                    }
                    counter++;

                }

                document.add(RoW);
            }
            int actuallSize = 0;
            ArrayList<ArrayList<Object>> filtered = new ArrayList<>();
            for(int i = 4 ; i< document.size(); i++){
                if (document.get(i).get(0)==""){
                    actuallSize = i;
                    break;
                }
            }
            for(int i = 0; i < actuallSize; i++){
                filtered.add(document.get(i));
            }





            return filtered;
    }



}