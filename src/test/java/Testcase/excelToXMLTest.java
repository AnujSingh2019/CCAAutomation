package Testcase;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

//import baseclass.BaseTest1;

import java.text.*;

@Test()
public class excelToXMLTest {
	public static DecimalFormat df = new DecimalFormat("#####0");
   //@Test(priority=1)
    public  void Convertor() {
        FileWriter fostream;
        PrintWriter out = null;
        String strOutputPath = "C:\\Users\\124859\\Desktop\\XMLs";
        String strFilePrefix = "Delinquency";

        try {
            InputStream inputStream = new FileInputStream(new File("C:\\Users\\124859\\Desktop\\Excel\\Testoutput.xlsx"));
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheet("check");

            fostream = new FileWriter(strOutputPath + "\\" + strFilePrefix+ ".xml");
            out = new PrintWriter(new BufferedWriter(fostream));

            out.println("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:diam=\"http://diamond.perotsystems.com\" xmlns:ws=\"http://ws.accountsReceivable.diamond.perotsystems.com/\">");
            out.println("   <soapenv:Header>");
            out.println("     <diam:DiamondContext>");

            boolean firstRow = true;
            for (Row row : sheet) {
                if (firstRow == true) {
                    firstRow = false;
                    continue;
                }
                
                out.println(formatElement("\t\t", "SecurityUsername", formatCell(row.getCell(0))));
                out.println(formatElement("\t\t", "SecurityPassword", formatCell(row.getCell(1))));
                
            }
            out.println("     </diam:DiamondContext>");
            out.println("   </soapenv:Header>");
            
            
            out.println("   <soapenv:Body>");
            out.println("     <ws:processDelinquencyForARAccount>");

            boolean secondRow = true;
            for (Row row : sheet) {
                if (secondRow == true) {
                	secondRow = false;
                    continue;
                }
                
                out.println(formatElement("\t\t", "arAccountId", formatCell(row.getCell(2))));
                out.println(formatElement("\t\t", "systemDate", formatCell(row.getCell(3))));
                
            }
            
            out.println("      </ws:processDelinquencyForARAccount>");
            out.println("    </soapenv:Body>");
            out.println("</soapenv:Envelope>");
            
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public  String formatCell(Cell cell)
    {
        if (cell == null) {
            return "";
        }
        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_ERROR:
                return "*error*";
            case Cell.CELL_TYPE_NUMERIC:
                return excelToXMLTest.df.format(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "<unknown value>";
        }
    }

    public static String formatElement(String prefix, String tag, String value) {
        StringBuilder sb = new StringBuilder(prefix);
        sb.append("<");
        sb.append(tag);
        if (value != null && value.length() > 0) {
            sb.append(">");
            sb.append(value);
            sb.append("</");
            sb.append(tag);
            sb.append(">");
        } else {
            sb.append("/>");
        }
        return sb.toString();
    }
}