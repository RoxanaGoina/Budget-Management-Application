package application;

import java.io.FileWriter;
import java.util.List;

import org.h2.expression.function.CSVWriteFunction;

public interface ExportList {
public static void   exportList(List<Item> l,String name) {
} 
public void exportOrderedList(List<Item> l,String name);
}
