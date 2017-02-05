package testQA;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class dataProvider {
	
	public String[] provide(String fileName , String sheetName){
		String arr[] = null ;
		String filePath = "C:\\Users\\Richard\\workspace\\testQA\\res\\";
		
	try {

        FileInputStream fis = new FileInputStream(filePath+fileName);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheet(sheetName);
        System.out.println("count is:" +sheet.getLastRowNum());
        arr= new String[sheet.getLastRowNum()];
        for(int count = 1;count<=sheet.getLastRowNum();count++){
            HSSFRow row = sheet.getRow(count);
            arr[count - 1] = row.getCell(0).toString();
            System.out.println("Running test case " + row.getCell(0).toString());
            
        }
        wb.close();
        fis.close();
    } catch (IOException e) {
        System.out.println("Test data file not found");
    } 
	
	return arr;
	
	}
}

