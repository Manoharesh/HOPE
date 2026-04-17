import java.util.*;
class leetcode349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }
        List<Integer> resultList = new ArrayList<>();
        for (int num : set1) {
            if (set2.contains(num)) {
                resultList.add(num);
            }
        }
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }
}