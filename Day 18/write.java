import java.io.FileWriter;
import java.io.IOException;

public class write {
    public static void main(String[] args) {
        try{
            FileWriter writer = new FileWriter("test1.txt");
            String data = "computer is the mother of programs";
            writer.write(data);
            writer.close();
            System.out.println("file created");
        }
        catch(IOException e){
            System.out.print("Error occured");
            //
        }
    }
}