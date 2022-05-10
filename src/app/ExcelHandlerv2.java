package app;

public class ExcelHandlerv2 {
        // access the settings
        static ExcelHandlerv2 instance;
        public String type = "excel";
    
        ExcelHandlerv2() {
        }
    
        public static ExcelHandlerv2 getInstance() {
            if(instance == null){
                instance = new ExcelHandlerv2();
            }
            return instance;
        }

        public Boolean writeToFile(Boolean fileIsloaded, String cel, String input){
            int CellLength = String.valueOf(cel).length();
            if (CellLength>=3 && !input.equals("") && fileIsloaded){
                return true;
            }
            return false;
        }
        public Boolean loadFile(String filename){
            if(filename == "test.txt") return true;
            return false;
        }
        
        public String saveFile(String filename, String dir){
            if(filename == "test.txt" && dir == "files"){
                String toReturn = "/"+dir+"/"+filename;
                return toReturn;
            }
            return "error";
        }
}
