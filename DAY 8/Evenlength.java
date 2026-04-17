import java.util.*;
public class Evenlength {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        int a;
        String[] arr=str.split("\\s+");
        for(int i=0;i<=arr.length;i++){
            a=arr[i].length();
            if(a%2==0){
            
                System.out.print(arr[i]+" ");
                
            }
        }
        
    }
}
