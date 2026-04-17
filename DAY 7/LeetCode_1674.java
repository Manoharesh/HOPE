public class LeetCode_1674 {
    class Solution {
    public String interpret(String command) {
        return (command.replace("()","o").replace("(al)","al"));
    }
}
}
