package application;

import java.io.FileWriter;

import org.h2.expression.function.CSVWriteFunction;

public interface ExportList {
public  void exportList(ItemList l); 
public void exportOrderedList(ItemList l);
}
