package api.utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcelFile {

    public static FileInputStream fis;
    public static Workbook workbook;
    public static Sheet sheet;
    public static Row row;
    public static Cell cell;


    public static String getCellValue(String filePath, String sheetName, int rowNum, int cellNum) {
        try {
            fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(cellNum);
            workbook.close();
            return cell.getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getRowCount(String filePath, String sheetName) {
        try {
            fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            int totalRows = sheet.getLastRowNum() - 1;
            workbook.close();
            return totalRows;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getColCount(String filePath, String sheetName) {
        try {
            fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            int totalCells = sheet.getRow(0).getLastCellNum();
            workbook.close();
            return totalCells;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
