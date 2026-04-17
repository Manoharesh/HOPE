import java.io.File;
import java.io.IOException;

public class file {
    public static void main(String[] args) {
        File file = new File("text.txt");
        try{
        if(file.createNewFile()){
            System.out.println("file created"+file.getName());
        }
        else{
            System.out.print("cannot create a file");
        }
    }
        catch(IOException e){
            System.out.print("error occured");
            //
        }
    }
}