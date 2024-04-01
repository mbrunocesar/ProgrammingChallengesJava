package medianOfTwoSortedArrays;

class MedianOfTwoSortedArrays {

    boolean debugMode;

    public MedianOfTwoSortedArrays(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /*
        Time Complexity: O(log(n + m))
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        boolean isPair = totalLength % 2 == 0;
        int desiredPosition = (int) (totalLength / 2);
        double value = 0;

        int combinedIndex = 0;
        int maxNums1 = nums1.length - 1;
        int maxNums2 = nums2.length - 1;
        int index1 = 0;
        int index2 = 0;

        double previous = -1;
        double current = -1;

        // Time Complexity: O(log (n + m))
        while (combinedIndex <= desiredPosition) {
            previous = current;

            if (index1 > maxNums1) {
                current = nums2[index2];
                index2++;
            } else if (index2 > maxNums2) {
                current = nums1[index1];
                index1++;
            } else if (nums1[index1] < nums2[index2]) {
                current = nums1[index1];
                index1++;
            } else {
                current = nums2[index2];
                index2++;
            }

            combinedIndex++;

            if (this.debugMode) {
                System.out.println(combinedIndex + ": " + index1 + " " + index2 + " || " + previous + " -> " + current);
            }
        }

        if (isPair) {
            value = (current + previous) / 2;
        } else {
            value = current;
        }


        if (this.debugMode) {
            System.out.println("RESULT: " + value);
        }

        return value;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfArrays = new MedianOfTwoSortedArrays(false);

        int[] array1 = {1, 3};
        int[] array2 = {2};
        System.out.println(medianOfArrays.findMedianSortedArrays(array1, array2) == 2.00000);

        int[] array3 = {1, 2};
        int[] array4 = {3, 4};
        System.out.println(medianOfArrays.findMedianSortedArrays(array3, array4) == 2.50000);

        int[] array5 = {1, 2, 3, 5, 8};
        int[] array6 = {4, 7, 9, 10};
        System.out.println(medianOfArrays.findMedianSortedArrays(array5, array6) == 5.00000);

        int[] array7 = {1, 2, 3, 5, 8};
        int[] array8 = {4, 7, 9, 10, 11};
        System.out.println(medianOfArrays.findMedianSortedArrays(array7, array8) == 6.00000);

        int[] array11 = {3};
        int[] array12 = {1, 2, 4, 7, 8, 9, 10, 12};
        System.out.println(medianOfArrays.findMedianSortedArrays(array11, array12) == 7.00000);

        int[] array21 = {1, 2, 3, 7, 8, 9, 10, 12};
        int[] array22 = {4};
        System.out.println(medianOfArrays.findMedianSortedArrays(array21, array22) == 7.00000);

        int[] array31 = {1, 2, 3, 6, 8, 9, 10, 12};
        int[] array32 = {4, 7};
        System.out.println(medianOfArrays.findMedianSortedArrays(array31, array32) == 6.50000);
    }

}