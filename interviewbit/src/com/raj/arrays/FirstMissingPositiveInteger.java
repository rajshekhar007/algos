package com.raj.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rshekh1 on 4/28/16.
 */
public class FirstMissingPositiveInteger {

    public int firstMissingPositive(ArrayList<Integer> a) {
        if (a == null || a.isEmpty()) return 1;
        int maxN = getMaxN(a);  // range of missing num is between 1(all N's less than equal to 0) & size of array+1(if all N's within 1 to N are there)
        if (maxN <= 0) return 1;
        // Bucket array - use index as indicator for presence of N
        for (int i=0; i<a.size(); i++) {
            int n = a.get(i);
            if (n > (maxN + 1)) n = n - (maxN + 1); // if this index was set earlier, retrieve the original N for processing
            if (n <= a.size() && n > 0) a.set(n-1, a.get(n-1) + maxN + 1); // mark by N + maxN+1
        }
        // Now check bucket array to find first missing number
        int i;
        for (i=0; i<a.size(); i++) {
            int n = a.get(i);
            if (n > maxN) continue;  // skip i's that were found (maxN+1 was added, so)
            return i+1; // first missing +ve number
        }
        return i+1;  // if all were skipped, then it's the i+1
    }

    public int getMaxN(ArrayList<Integer> a) {
        int maxN = 0;
        for (int i=0; i<a.size(); i++) {
            int n = a.get(i);
            maxN = (n > maxN) ? n : maxN;
            if (n<=0) a.set(i, 0);  // get rid of -ve numbers, as we are interested in +ve numbers
        }
        return maxN;
    }

    public static void main(String[] args) {
        FirstMissingPositiveInteger f = new FirstMissingPositiveInteger();
        int[] ints = { 699, 2, 690, 936, 319, 784, 562, 35, 151, 698, 126, 730, 587, 157, 201, 761, 956, 359, 198, 986, 915, 7, 703, 324, 814, 382, 294, 204, 120, 731, 615, 330, 486, 52, 223, 376, 649, 458, 564, 971, 72, 605, 177, 20, 461, 790, 872, 363, 916, 435, 991, 184, 410, 320, 16, 480, 768, 801, 117, 338, 650, 786, 17, 369, 979, 304, 445, 688, 862, 229, 311, 351, 985, 697, 135, 299, 310, 3, 643, 221, 831, 196, 887, 679, 484, 209, 824, 292, 588, 721, 140, 675, 827, 913, 271, 170, 812, 552, 334, 860, 981, 550, 308, 584, 442, 328, 251, 456, 976, 31, 507, 954, 982, 742, 45, 727, 794, 309, 527, 623, 56, 843, 436, 681, 143, 130, 689, 870, 362, 580, 560, 474, 385, 525, 881, 51, 890, 917, 820, 826, 139, 443, 978, 144, 512, 205, 682, 188, 344, 429, 497, 181, 749, 864, 664, 145, 621, 629, 886, 572, 89, 725, 945, 29, 553, 977, 783, 590, 236, 728, 125, 90, 492, 261, 543, 259, 662, 622, 285, 392, 561, 670, 200, 504, 246, 513, 910, 583, 460, 179, 207, 709, 127, 926, 816, 426, 520, 174, 464, 883, 780, 5, 268, 606, 1, 109, 704, 391, 661, 924, 516, 241, 477, 952, 405, 522, 247, 335, 356, 839, 423, 779, 4, 43, 720, 238, 965, 951, 914, 10, 496, 775, 651, 788, 373, 491, 746, 799, 518, 93, 86, 774, 652, 955, 494, 252, 781, 946, 412, 202, 741, 719, 612, 673, 896, 1000, 289, 554, 69, 424, 980, 506, 593, 889, 25, 959, 28, 736, 8, 969, 865, 657, 567, 434, 9, 167, 357, 929, 645, 250, 565, 94, 928, 473, 509, 823, 313, 762, -1, 208, 903, 922, 655, 948, 326, 485, 150, 73, 505, 225, 122, 129, 648, 838, 811, 972, 735, 78, 428, 740, 782, 632, 316, 440, 737, 297, 873, 281, 479, 654, 0, 633, 212, 152, 154, 470, 866, 79, 722, 958, 732, 900, 832, 278, 58, 842, 745, 540, 169, 347, 592, 438, 882, 462, 53, 34, 519, 489, 85, 757, 919, 701, 15, 211, 667, 637, 74, 573, 240, 559, -2, 472, 203, 112, 162, 776, -4, 155, 837, 99, 98, 64, 101, 983, 366, 853, 970, 482, 40, 921, 374, 758, 413, 339, 705, 771, 360, 734, 282, 219, 766, 535, 133, 532, 254 };       //int[] ints = {1};
        ArrayList<Integer> a = new ArrayList<>();
        for (int i : ints) {
            a.add(i);
            if (i == 1) System.out.println(a.size()-1);
        }
        System.out.println(f.firstMissingPositive(a));
    }
}
