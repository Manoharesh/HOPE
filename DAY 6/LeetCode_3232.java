public class LeetCode_3232 {
    class Solution {
    public boolean canAliceWin(int[] nums) {
      int sum1=0;
      int sum2=0;
      for(int num:nums){
        if(num<10){
            sum1+=num;
        }else{
            sum2+=num;
        }
      }
      return sum1!=sum2;

    }
}
}
