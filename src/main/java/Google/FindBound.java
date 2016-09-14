package Google;

public class FindBound {
    public static int lower_bound(int[] nums, int key) {
        int left = 0;
        int right = nums.length - 1;
        int pos = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < key) {
                left = mid + 1;
                pos = left;
            } else {
                right = mid;
                pos = right;
            }
        }
        return pos;
    }


    public int binaryFirst(int[] nums, int target) {
        if (nums.length < 1) return -1;
        int i=0;
        int j=nums.length-1;
        while(i<j) {
            if (j-1 == i) return justTwoFirst(nums, i, target);
            int mid = i + (j-i)/2;
            if (nums[mid] < target) i=mid+1;
            else if (nums[mid] > target) j=mid-1;
            else j=mid;
        }
        if (nums[i] == target) return i;
        else return -1;
    }

    public int binaryLast(int[] nums, int target) {
        if (nums.length < 1) return -1;
        int i=0;
        int j=nums.length-1;
        while(i<j) {
            if (i+1 == j) return justTwoLast(nums, i, target);
            int mid = i + (j-i)/2;
            if (nums[mid] < target) i=mid+1;
            else if (nums[mid] > target) j=mid-1;
            else i=mid;
        }
        if (nums[i] == target) return i;
        else return -1;
    }

    public int justTwoFirst(int[] nums, int i, int target) {
        if (nums[i] == target) {
            return i;
        } else if (nums[i+1] == target) {
            return i+1;
        } else return -1;
    }

    public int justTwoLast(int[] nums, int i, int target) {
        if (nums[i+1] == target) {
            return i+1;
        } else if (nums[i] == target) {
            return i;
        } else return -1;
    }

    public static void main(String[] args) {
        FindBound f = new FindBound();
        int[] a = {1, 2, 3};
        System.out.println(f.binaryFirst(a, 1));
        System.out.println(f.binaryLast(a, 1));
    }

}
