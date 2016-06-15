package com.raj.binarysearch;

/**
 * @author <a href="mailto:rshekhar@walmartlabs.com">Shekhar Raj</a>
 */
public class MedianSortedArray {

    /**
    This method works by first getting medians of the two sorted arrays and then comparing them.

    Let ar1 and ar2 be the input arrays.

    Algorithm:

            1) Calculate the medians m1 and m2 of the input arrays ar1[]
    and ar2[] respectively.
    2) If m1 and m2 both are equal then we are done.
    return m1 (or m2)
    3) If m1 is greater than m2, then median is present in one
    of the below two subarrays.
            a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
    b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
    4) If m2 is greater than m1, then median is present in one
    of the below two subarrays.
            a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
    b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
    5) Repeat the above process until size of both the subarrays
    becomes 2.
            6) If size of the two arrays is 2 then use below formula to get
    the median.
    Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
    Example:

    ar1[] = {1, 12, 15, 26, 38}
    ar2[] = {2, 13, 17, 30, 45}
    For above two arrays m1 = 15 and m2 = 17

    For the above ar1[] and ar2[], m1 is smaller than m2. So median is present in one of the following two subarrays.

    [15, 26, 38] and [2, 13, 17]
    Let us repeat the process for above two subarrays:

    m1 = 26 m2 = 13.
    m1 is greater than m2. So the subarrays become

    [15, 26] and [13, 17]
    Now size is 2, so median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
            = (max(15, 13) + min(26, 17))/2
            = (15 + 17)/2
            = 16
    Implementation:

// A divide and conquer based efficient solution to find median
// of two sorted arrays of same size.
     */
}
