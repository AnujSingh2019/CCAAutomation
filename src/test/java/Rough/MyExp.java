package Rough;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class MyExp {
protected DocumentBuilderFactory domFactory = null;
protected DocumentBuilder domBuilder = null;

public MyExp(){
    try {
        domFactory = DocumentBuilderFactory.newInstance();
        domBuilder = domFactory.newDocumentBuilder();
    } catch (FactoryConfigurationError exp) {
        System.err.println(exp.toString());
    } catch (ParserConfigurationException exp) {
        System.err.println(exp.toString());
    } catch (Exception exp) {
        System.err.println(exp.toString());
    }
}
public void convertFile(String xlsFileName, String xmlFileName){

    try {
        Document newDoc = domBuilder.newDocument();
       /* Element rootElement = newDoc.createElement("soapenv:Header");
        newDoc.appendChild(rootElement);*/
        Element rootElement = newDoc.createElement("soapenv:Header");
        newDoc.appendChild(rootElement);
        Element chileElement=newDoc.createElement("diam:DiamondContext");
        newDoc.appendChild(chileElement);
        InputStream InputStream=new FileInputStream(new File(xlsFileName));
        HSSFWorkbook      workBook = new HSSFWorkbook (InputStream);
        HSSFSheet         sheet    = workBook.getSheetAt (0); 
        Iterator<?> rows     = sheet.rowIterator ();
        List<String> headers = new ArrayList<String>(5);
        while (rows.hasNext ()) 
        {
            HSSFRow row = (HSSFRow) rows.next(); 

            int rowNumber = row.getRowNum ();
            Iterator<?> cells = row.cellIterator (); 
            ArrayList<String> rowData = new ArrayList<String>();
            while (cells.hasNext ())
            {
                HSSFCell cell = (HSSFCell) cells.next ();
                switch (cell.getCellType ())
                {
                case HSSFCell.CELL_TYPE_NUMERIC :
                {
                    // NUMERIC CELL TYPE
                    rowData.add(cell.getNumericCellValue () + "");
                    break;
                }
                case HSSFCell.CELL_TYPE_STRING :

                {
                    // STRING CELL TYPE
          HSSFRichTextString richTextString = cell.getRichStringCellValue();
                    rowData.add(richTextString.getString ());
                    break;
                }
                default:
                {
                    break;
                }
              }



            } // end while
            if(rowNumber==1){
                headers.addAll(rowData);
            }
            else
            {
                    Element rowElement = newDoc.createElement("row");
                    rootElement.appendChild(rowElement);
                    for (int col = 0; col < headers.size(); col++) {

                        String header = headers.get(col);
                        String value = null;

                        if (col < rowData.size()) {

                            value = rowData.get(col);

                        } else {
                            value = "";
                        }

                        Element curElement = newDoc.createElement(header);
                       curElement.appendChild(newDoc.createTextNode(value));
                        rowElement.appendChild(curElement);

                    }

            }

        } //end while

        ByteArrayOutputStream baos = null;
        OutputStreamWriter osw = null;

        try {

            baos = new ByteArrayOutputStream();
            osw = new OutputStreamWriter(baos);

          TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
           // aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            aTransformer.setOutputProperty("{https://fmssit1.cca-nonprod.nttdataservices.com/diamond-ws/services/AccountsReceivableService}indent-amount", "2");

            Source src = new DOMSource(newDoc);
            Result result = new StreamResult(new File(xmlFileName));
            aTransformer.transform(src, result);

            osw.flush();
            System.out.println(new String(baos.toByteArray()));

        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            try {
                osw.close();
            } catch (Exception e) {
            }
            try {
                baos.close();
            } catch (Exception e) {
            }
        }

    }
    catch(IOException e)
    {
        System.out.println("IOException " + e.getMessage());
    }
}


public static void main (String[] args)
{
    MyExp poiExample = new MyExp ();
    poiExample.convertFile("C:\\Users\\124859\\Desktop\\Excel\\Test1.xls", "C:\\Users\\124859\\Desktop\\Excel\\schema.xml");
}
}