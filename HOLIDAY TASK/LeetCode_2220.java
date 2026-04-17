public class LeetCode_2220 {
    class Solution {
    public int minBitFlips(int start, int goal) {
        int temp=start^goal;
        int count=0;
        while(temp>0){a
            count=count+temp&1;
            temp=temp>>1;
        }
        return count;
    
    }
}
}
