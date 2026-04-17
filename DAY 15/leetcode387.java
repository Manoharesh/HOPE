import java.util.*;
class leetcode387 {
    public int firstUniqChar(String s) {
        HashMap<Character,Integer>  out = new HashMap<>();
        char[] arr = s.toCharArray();
        for(int i = 0;i<arr.length;i++){
            char ch = arr[i];
            out.put(ch,out.getOrDefault(ch,0) +1);   
        }
        for(int i = 0;i<arr.length;i++){
            int count = out.get(arr[i]);
            if(count==1){
                return i;
            }
        }
        return -1;
    }
}