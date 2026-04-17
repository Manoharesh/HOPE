import java.util.*;
public class Waveform {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        StringBuilder ans1=new StringBuilder();
        StringBuilder ans2=new StringBuilder();
        for(int i=0;i<str.length()-1;i++){
            char ch=str.charAt(i);
            if(i%2==0 ){
                ans1.append(ch);
            }
            else if(i%2!=0 ){
                ans2.append(ch);
            }
        }
        System.out.print(ans1);
        System.out.println(ans2);
    }
}
