package src.first_Bad_Version_278;

import org.junit.Test;

public class Solution {
    /**
     * 说实话，不会写
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        return binanryBadVersion(1, n);
    }
    public int binanryBadVersion(int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        while(lo <= hi) {
            if (isBadVersion(mid) ^ isBadVersion(mid + 1)) return mid+1;
            else {
                if(isBadVersion(mid)) binanryBadVersion(mid+1, hi);
                else binanryBadVersion(lo, mid - 1);
            }
        }
        return mid;
    }
    public boolean isBadVersion(int n) {
        int bad = 4;
        if(n < bad) return true;
        else return false;
    }



    public int firstBadVersion2(int n) {
        int left = 1, right = n;
        while(left < right) {//循环区间至左右端点相同
            int mid = left + (right - left) / 2;//防止计算时溢出
            if(isBadVersion(mid)) {
                right = mid;//答案在区间【left, mid】中
            } else {
                left = mid + 1;//答案在区间[mid + 1,right]中
            }
        }
        //此时有left == right 区间缩小为一个点，即为答案
        return left;
    }


    /**
     * 考虑存在「没有错误版本」和「全是错误版本」的情况，但如果往头部插入一个正确版本，往尾部插入一个错误版本作为哨兵，仍然具有「二段性」。
     * 实际上，只需要进行这样的思考即可，不需要真正插入这样的哨兵，把这个哨兵逻辑放到最后返回的时候判断一下即可。
     * 那么只需要将 isBadVersion 当做 check 函数进行二分即可。
     * 二分通常有以下两种写法，分别代表「找到最靠近中心的 True」 和「找到最靠近中心的 False」。
     * @param n
     * @return
     */
    public int firstBadVersion3(int n) {
        int l = 1, r = n;
        while(l < r) {
            long tmp = (long) l + r >> 1;
            int mid  = (int)tmp;
            if(isBadVersion(mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    public int firstBadVersion4(int n) {
        int l = 1, r = n;
        while(l < r) {
            int mid = l + (r - l) >> 1;
            if(!isBadVersion(mid)) l = mid;
            else r = mid - 1;
        }
        return !isBadVersion(r) ? r + 1 : r;
    }


    //先说一个规律
    //若循环判断无等号， 则只有一边需要±1
    //若循环判断有等号，则两边都需要±1

//    class Solution {
//        public:
//        int firstBadVersion(int n) {
//            int left = 1;
//            int right = n;
//
//            while (left < right) {
//                int mid = (right-left)/2 + left;
//                if (isBadVersion(mid)) {
//                    right = mid;
//                } else {
//                    left = mid + 1;
//                }
//            }
//            // 退出循环时必然是 left == right
//            return left; // return right;一样
//        }
//    };


//
//    class Solution {
//        public:
//        int firstBadVersion(int n) {
//            int left = 1;
//            int right = n;
//
//            while (left <= right) {
//                int mid = (right-left)/2 + left;
//                if (!isBadVersion(mid)) {
//                    left = mid + 1;
//                } else {
//                    right = mid - 1;
//                }
//            }
//
//            return left;
//        }
//    };






    @Test
    public void test() {
        System.out.println(firstBadVersion(5));
    }
}
