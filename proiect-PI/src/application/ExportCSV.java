package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public  class ExportCSV implements ExportList {

	public static void exportList(List<Item> l,String name) {
		File resFile = new File("C:\\Users\\user\\Desktop\\" + name+".csv");
		try (FileWriter fileWriter = new FileWriter(resFile)) {
			CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
			String[] headers= {new String("Index"),new String("Nume"),new String("ItemType")};
			csvPrinter.printRecord(headers);
			int rowNumber = 1;
			for(Item i:l) {
				csvPrinter.printRecord(
						rowNumber,
						i.getName(),
						i.getItemType());
				rowNumber++;
			}
			
			csvPrinter.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		} 
	
	}
	

}
