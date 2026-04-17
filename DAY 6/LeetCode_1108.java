public class LeetCode_1108 {
    class Solution {
    public String defangIPaddr(String address) {
        String ans=address.replace(".","[.]");
        return ans;
        
    }
}
}