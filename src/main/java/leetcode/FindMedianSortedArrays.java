package leetcode;

import java.util.Arrays;

public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 < n2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int lo = 0;
        int hi = n2 * 2;
        while (lo <= hi) {
            int mid2 = (lo + hi) / 2;
            int mid1 = n1 + n2 - mid2;

            double l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1)/2];
            double l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1)/2];
            double r1 = (mid1 == n1 * 2) ? Integer.MAX_VALUE : nums1[mid1/2];
            double r2 = (mid2 == n2 * 2) ? Integer.MAX_VALUE : nums2[mid2/2];

            if (l1 > r2) {
                lo = mid2 + 1;
            } else if (l2 > r1) {
                hi = mid2 - 1;
            } else {
                return (Math.max(l1, l2) + Math.min(r1, r2))/2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 5, 10, 15, 20 };
        int[] nums2 = new int[] { 2, 4, 6, 8, 11, 12 };

        int[] result = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            result[i + nums1.length] = nums2[i];
        }

        Arrays.sort(result);
        double median;
        if (result.length % 2 == 0)
            median = ((double)result[result.length/2] + (double)result[result.length/2 - 1])/2;
        else
            median = (double)result[result.length/2];

        FindMedianSortedArrays algorithm = new FindMedianSortedArrays();
//        algorithm.find(nums1, 13, 0);

        double median2 = algorithm.findMedianSortedArrays(nums1, nums2);
        int k = 0;

        // 1 4 6 9 10 12 15 18
        // 11 13 14 16 17 19 20












    }
}
