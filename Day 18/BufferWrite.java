import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class BufferWrite{
    public static void main(String[] args){
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter("test.txt"));
            writer.write("Write using bufferedwriter");
            writer.newLine();
            writer.write("Write using bufferedwriter");
            writer.newLine();
            writer.write("Write using bufferedwriter");
            writer.close();
        }
        catch(IOException e){
            System.out.println(e);
            
        }
    }
}