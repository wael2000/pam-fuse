package org.mycompany;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ExcelDataFormat implements DataFormat{
	static Logger LOG = LoggerFactory.getLogger(ExcelDataFormat.class);
	
	@Override
	public void marshal(Exchange exchange, Object obj, OutputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object unmarshal(Exchange exchange, InputStream inputStream) throws Exception {
		String processVars = "{";
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	    //int totalRowsCount = firstSheet.getPhysicalNumberOfRows();
	    while (iterator.hasNext()) {
	    	Row nextRow = iterator.next();
	    	if (nextRow.getRowNum() == 0 || nextRow.getRowNum()==1) {
                continue;// skip first row, as it contains column names
            }
	        /*if(nextRow.getRowNum() ==  totalRowsCount -1 || nextRow.getRowNum()== totalRowsCount -2)
	        {
	        	continue ;
	        }*/
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        processVars+="{";
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	            switch (columnIndex) {
	            
		            case 0:	//item name
			            LOG.info("item name " + getCellValue(nextCell));
			            processVars+="'item' : '" + getCellValue(nextCell) + "',";
			            break;
		            case 1:	//item type 
			            LOG.info("item type " + getCellValue(nextCell));
			            processVars+="'type' : '" + getCellValue(nextCell) + "',";
			            break;
	            }
	        
	        }
	        processVars+=" 'status' : 'Not Srartes'";
	        processVars+="},";
	    }
	    processVars=processVars.substring(0, processVars.length() - 1);
		processVars+= "}";
		return processVars;
	}
	
	
	private Object getCellValue(Cell cell) {
		switch(cell.getCellTypeEnum())
        {
        case NUMERIC:
        	return cell.getNumericCellValue();	
        case STRING:
        	return cell.getStringCellValue();
        case BOOLEAN:
        	return cell.getBooleanCellValue();
        default:
        	return null;
        }
	    
	}
	
}
	
