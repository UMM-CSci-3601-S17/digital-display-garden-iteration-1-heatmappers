package umm3601.Converter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Converter extends ArrayList{

    public Converter(){

    }

//took our base code off of http://www.mkyong.com/java/apache-poi-reading-and-writing-excel-file-in-java/
    // then modified to fit our needs
    public static ArrayList<ArrayList<Object>> convertToLists(FileInputStream file1)throws IOException{
        ArrayList<ArrayList<Object>> document = new ArrayList<ArrayList<Object>>();
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
            ArrayList<ArrayList<Object>> filtered = new ArrayList<>();

            //getting rid of the flowers with no location
            for(int i = 4; i < document.size(); i++){
                if(document.get(i).get(0)!=""&& document.get(i).get(6)!="") {
                    filtered.add(document.get(i));
                }
            }
            return filtered;
    }



}