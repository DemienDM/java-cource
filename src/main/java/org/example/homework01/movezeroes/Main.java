package org.example.homework01.movezeroes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] arr = {0, 1, 0, 3, 12};
        moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void moveZeroes(int[] nums) {
        int tmp;
        int next;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 || i == (nums.length - 1)) {
                continue;
            }

            next = i + 1;
            while (next < nums.length) {
                if (nums[next] != 0) {
                    break;
                }

                next++;
            }

            if (next == nums.length) {
                break;
            }

            tmp = nums[i];
            nums[i] = nums[next];
            nums[next] = tmp;
        }
    }
}
