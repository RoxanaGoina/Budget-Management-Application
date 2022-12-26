package application;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportPDF implements ExportList {
	// Rectangle  pageSize = PageSize.A4;
	//String ABSOLUTE_ROOT = "C:\\Users\\user\\Desktop";
	public static void exportList(List<Item> l,String name) {
		
		String filePath = "C:\\Users\\user\\Desktop"+ name+ ".pdf";
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            Paragraph p= new Paragraph();
            p.add(name);
            document.add(p);
            for(Item i:l) {
            	document.add(Chunk.NEWLINE);
            	Paragraph p1= new Paragraph();
            	p1.setAlignment(1);
            	p1.add(i.getName()+"|"+i.getItemType());
            	document.add(p1);
            }
            document.close();
	}catch (Exception e) {
        e.printStackTrace();
		
	}
	}
	public void exportOrderedList(List<Item> l,String name) {
		
	}
}
