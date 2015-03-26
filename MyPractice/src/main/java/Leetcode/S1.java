package Leetcode;
// https://oj.leetcode.com/problemset/algorithms/

import java.util.HashMap;
public class S1 {
    static final Solution s = new Solution();
    public static void main(String[] args){

        int[] nums = {10, 20, 30};
        s.twoSum(nums, 50);
    }

    private static class Solution {
        public boolean ifSameNumbers(int[] numbers, int i) {
            int count = 0;
            for (int j : numbers) {
                if (i == j) {
                    count++;
                    if (count > 1)
                        return true;
                }
            }
            return false;
        }

        public int[] twoSum(int[] numbers, int target) {
            HashMap h = new HashMap<>();
            int j = 0;
            for (int i : numbers)
                h.put(i, j++);

            j = 0;
            for (int i : numbers) {
                int k = target - i;
                if (h.containsKey(k)) {
                    if (k != i || ifSameNumbers(numbers, k)) {
                        int[] res = new int[2];
                        res[0] = Math.min(j, (int) h.get(k)) + 1;
                        res[1] = Math.max(j, (int) h.get(k)) + 1;
                        System.out.println(" " + res[0] + " " + res[1]);
                        return res;
                    }
                }
                j++;
            }
            return null;
        }
    }
}
