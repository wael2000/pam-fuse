package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		//String payload = exchange.getIn().getBody(String.class);
		//Workbook workbook = new XSSFWorkbook();

		exchange.getIn().setBody("Changed body");
	}

}
