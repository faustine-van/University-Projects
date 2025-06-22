import java.io.File;

public class FileOpen{
    public static void main(String[] args){
        try {
            File obFile = new File("file.txt");
            if(obFile.createNewFile()){
                System.out.println("File created: " +  obFile.getName());
            } else{
                System.out.println("File already exist");
            }

            System.out.println(obFile.getClass());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}