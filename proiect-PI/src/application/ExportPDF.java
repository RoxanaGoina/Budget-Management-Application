package application;

import java.io.File;
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
	
	public static void exportList(List<Item> l,String name) {
		
		String filePath = "C:\\Users\\user\\Desktop\\"+ name+ ".pdf";
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            Paragraph p= new Paragraph();
            
            var bold1=new Font(Font.FontFamily.COURIER,27,Font.BOLDITALIC);
            p.setFont(bold1);
            p.setAlignment(1);
            p.add(name);
            var bold=new Font(Font.FontFamily.HELVETICA,18,Font.ITALIC);
            document.add(p);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            int counter=1;
            for(Item i:l) {
            
            	document.add(Chunk.NEWLINE);
            	Paragraph p1= new Paragraph();
            	p1.setFont(bold);
            	p1.setAlignment(0);
            	p1.add(Integer.toString(counter)+".  "+i.getName()+" | "+i.getItemType());
            	
            	document.add(p1);
            	counter++;
            }
            
            document.close();
            File file = new File(filePath);
            System.out.println(file.getAbsolutePath());
	}catch (Exception e) {
        
		
	}
	}
	
}
