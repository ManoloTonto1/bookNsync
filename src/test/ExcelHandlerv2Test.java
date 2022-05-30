package test;
import app.ExcelHandlerv2;
import static org.junit.Assert.*;
import org.junit.Test;

public class ExcelHandlerv2Test {

    @Test
    public void WriteToFile_CellLength_AB3_returns_true(){
        //arrange
        ExcelHandlerv2 test = ExcelHandlerv2.getInstance();
        String input = "Dit is een test";
        //act
        Boolean condition = test.writeToFile(true,"AB3", input);
        
        //assert
        assertTrue(condition);
    }
    @Test
    public void WriteToFile_CellLength_AB_returns_false(){
        //arrange
        ExcelHandlerv2 test = ExcelHandlerv2.getInstance();
        String input = "Dit is een test";
        //act
        Boolean condition = test.writeToFile(true,"AB", input);
        
        //assert
        assertFalse(condition);
    }
    
}
