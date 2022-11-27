//package src.guess_Number_Higher_Or_Lower_374;
//
//public class Solution {
//    /**
//     * Forward declaration of guess API.
//     * @return 	     -1 if num is higher than the picked number
//     *			      1 if num is lower than the picked number
//     *               otherwise return 0
//     * int guess(int num);
//     */
//
//    public int guessNumber(int n) {
////        int left = 1, right = n;
////        while (left < right) { // 循环直至区间左右端点相同
////            int mid = left + (right - left) / 2; // 防止计算时溢出
////            if (guess(mid) <= 0) {
////                right = mid; // 答案在区间 [left, mid] 中
////            } else {
////                left = mid + 1; // 答案在区间 [mid+1, right] 中
////            }
////        }
////        // 此时有 left == right，区间缩为一个点，即为答案
////        return left;
//
//
//        int l = 1, r = n;
//        while (l < r) {
//            long tmp = (long)l + r >> 1;
//            int mid = (int)tmp;
//            if (guess(mid) <= 0) {
//                r = mid;
//            } else {
//                l = mid + 1;
//            }
//        }
//        return r;
//
//
//
//
//        int l = 1, r = n;
//        while (l < r) {
//            long tmp = (long)l + r + 1 >> 1;
//            int mid = (int)tmp;
//            if (guess(mid) >= 0) {
//                l = mid;
//            } else {
//                r = mid - 1;
//            }
//        }
//        return r;
//
//
//    }
//
//
//}
