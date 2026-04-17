public class LeetCode_53 {

    public int maxSubArray(int[] nums) {
        int curr=0;
        int max=nums[0];
        for(int i:nums){
            curr+=i;
            if(curr>max){
                max=curr;
            }if(curr<0){
                curr=0;
            }
        }
        return max;
    }
}

