package com.raj.binarysearch;

/**
 * Created by rshekh1 on 6/9/16.
 */
public class Sqrt {

    /*public int sqrt(int a) {
        return bSearch(a, 0, (a/2)+1);
    }

    public int bSearch(int a, int start, int end) {
        int mid = start + (end-start)/2;
        if (mid*mid == a || (a > mid*mid && a < (mid+1)*(mid+1))) return mid;
        if (a < mid*mid) return bSearch(a, start, mid-1);
        if (a > mid*mid) return bSearch(a, mid+1, end);
        return 0;
    }*/

    public int sqrt(int x) {
	    /*if (a == 0) return 0;
	    if (a < 3) return 1;
	    return bSearch(a, 0, a);
	}

	public int bSearch(int a, int start, int end) {
	    int mid = start + (end-start)/2;
	    if (a/mid == mid) return mid;
	    if (a/mid < mid) return bSearch(a, start, mid);
	    if (a/mid > mid) return bSearch(a, mid, end);
	    return 0;
	}*/
        if(x<=1) return x;
        int left=0, right=x, mid;
        while( (right-left)>1 )
        {
            mid=left+(right-left)/2;
            if(mid==x/mid)
                return mid;
            else if(x/mid < mid)
                right=mid;
            else
                left=mid;
        }
        return left;  // answer is the last left pointer
    }

    public static void main(String[] args) {
        Sqrt s = new Sqrt();
        System.out.println(s.sqrt(3));
    }
}
