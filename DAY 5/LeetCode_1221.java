public class LeetCode_1221 {
    class Solution {
    public int balancedStringSplit(String s) {
       int count=0;
       for(int i=0;i<s.length()-1;i++){
        int right=0;
        int left=0;
        
        for(int j=i;j<s.length();j++){
            if(s.charAt(j)=='R'){
                right++;
            }else{
                left++;
            }
        }
        if(right==left){
            count++;
        }

       } 
       return count;
    }
}
}
