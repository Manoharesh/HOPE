import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class BufferRead{
    public static void main(String[] args){
        try{
            BufferedReader reader=new BufferedReader(new FileReader("test.txt"));
            String line;
            while((line=reader.readLine())!=null){
                //ch=reader.read();
                System.out.print(line);
            }
        }
        catch(IOException e){
            e.printStackTrace();
            
        }
    }
}