public class LeetCode_151 {
    class Solution {
    public String reverseWords(String s) {
        String[] str=s.trim().split("\\s+");
        String reversed="";
        for(int i=str.length-1;i>0;i--){
            reversed+=str[i]+" ";
        }
        return reversed+str[0];
    }
}
}
