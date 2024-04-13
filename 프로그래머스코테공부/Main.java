


class Solution {

    /* 
    // 분할 과정
    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] >= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[right];
        arr[right] = temp;
        return i + 1;
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex -1);
            quickSort(arr, pivotIndex +1, right);
        }
    }
    */

    // 병합 과정
    private void merge(int[] arr, int left, int mid, int right, int m) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] >= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 병합된 배열을 원래의 배열에 복사
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }

    private void mergeSort(int[] arr, int left, int right, int m) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, m);
            mergeSort(arr, mid + 1, right, m);
            merge(arr, left, mid, right, m);
        }
    }

    public int solution(int k, int m, int[] score) {
        
        int answer = 0;

        // 배열을 돌려서, 우선 정렬하고 m개씩 끊어서 만든 다음
        // 내림차순 정렬 한 후, 
        // 버블 정렬
        /*
        for (int i=0; i<score.length; i++) {
            for (int j=i+1; j<score.length; j++) {
                if (score[i] < score[j]) {
                    int temp = score[i];
                    score[i] = score[j];
                    score[j] = temp;
                }
            }
        }
        */

        // 퀵 정렬
        /*
        quickSort(score, 0, score.length-1);
        */

        // 병합정렬
        mergeSort(score, 0, score.length - 1, m);

        for (int i=m-1; i<score.length; i+=m) {
            answer += score[i];
        }

        answer *= m;

        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행
        System.out.println(solution.solution(3, 4, new int[]{1,2,3,1,2,3,1}));
    }
}