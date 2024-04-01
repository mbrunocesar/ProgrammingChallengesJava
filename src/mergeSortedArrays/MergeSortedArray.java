package mergeSortedArrays;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = nums1.clone();

        int combinedIndex = 0;
        int index1 = 0;
        int index2 = 0;

        while (combinedIndex < n+m) {
            if (index1 >= m) {
                nums1[combinedIndex] = nums2[index2];
                index2++;
            } else if (index2 >= n) {
                nums1[combinedIndex] = nums[index1];
                index1++;
            } else {
                if (nums[index1] < nums2[index2]) {
                    nums1[combinedIndex] = nums[index1];
                    index1++;
                } else {
                    nums1[combinedIndex] = nums2[index2];
                    index2++;
                }
            }
            combinedIndex++;
        }
    }


    public static void main(String[] args) {
        MergeSortedArray minCost = new MergeSortedArray();

        int[] num1 = {1,2,3,0,0,0};
        int[] num2 = {2,5,6};
        int m = 3;
        int n = 3;
        minCost.merge(num1, m, num2, n);

        for (int num : num1) {
            System.out.print(num +" | ");
        }

    }

}
