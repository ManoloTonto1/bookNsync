// package test;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import app.Local_Subscriber;

// public class ExcelhandlerPairwiseTests{
//     static Local_Subscriber test = Local_Subscriber.getInstance();

//     @Test
//     public void WriteToFile_Input_empty_returns_false(){
//         //arrange
//         String input = "";
//         //act
//         Boolean condition = test.writeToFile(true,"AB3", input);
        
//         //assert
//         assertFalse(condition);
//     }
//     @Test
//     public void WriteToFile_CellLength_A4_returns_false(){
//         //arrange
//         String input = "test me";
//         //act
//         Boolean condition = test.writeToFile(true,"A4", input);
        
//         //assert
//         assertFalse(condition);
//     }
//     @Test
//     public void WriteToFile_FileisLoaded_false_returns_false(){
//         //arrange
//         String input = "this is a test bro";
//         //act
//         Boolean condition = test.writeToFile(false,"BC88", input);
        
//         //assert
//         assertFalse(condition);
//     }

//     @Test
//     public void WriteToFile_CellLength_AB69_returns_true(){

//         String input = "This test wUrKs";
//         //act
//         Boolean condition = test.writeToFile(true,"AB69", input);
        
//         //assert
//         assertTrue(condition);
//     }

// }