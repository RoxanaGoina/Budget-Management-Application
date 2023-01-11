package application;
 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
	@Test
	void test() {
		Assertions.assertEquals(true,DataBaseOperations.check("Mere"));
	}
	@Test
	void test1() {
		Assertions.assertEquals(false,DataBaseOperations.checkDelete("Fursecuri"));
	}
	@Test
	void test2() {
		Assertions.assertEquals(true,DataBaseOperations.checkIfListExists("L1"));
	}
}
