package leetcode;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private int heapSize = 0;

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(0);
        ListNode iterator = result;
        buildHeap(lists);

        Integer min;
        do {
            min = min(lists);
            if (min != null) {
                iterator.next = new ListNode(min);
                iterator = iterator.next;
            }

        } while (min != null);

        return result.next;
    }

    private void heapify(ListNode[] a, int i) {
        int left = 2 * (i + 1) - 1;
        int right = 2 * (i + 1);
        int min = i;
        int leftVal = (left < heapSize && a[left] != null) ? a[left].val : Integer.MAX_VALUE;
        int rightVal = (right < heapSize && a[right] != null) ? a[right].val : Integer.MAX_VALUE;
        int val = a[i] != null ? a[i].val : Integer.MAX_VALUE;
        if (left < heapSize && leftVal < val && leftVal <= rightVal) {
            min = left;
        } else if (right < heapSize && rightVal < val && rightVal <= leftVal) {
            min = right;
        }
        if (min != i) {
            ListNode tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;

            heapify(a, min);
        }
    }

    private void buildHeap(ListNode[] lists) {
        heapSize = lists.length;
        if (heapSize < 1) {
            return;
        }
        for (int i = lists.length / 2; i >= 0; i--) {
            heapify(lists, i);
        }
    }

    private Integer min(ListNode[] heap) {
        if (heapSize < 1 || heap[0] == null) {
            return null;
        }
        int min = heap[0].val;
        if (heap[0].next != null) {
            heap[0] = heap[0].next;
        } else {
            heap[0] = heap[--heapSize];
        }

        heapify(heap, 0);

        return min;
    }

    private ListNode createList(int[] array) {
        ListNode list = new ListNode(0);
        ListNode pointer = list;
        for (int i : array) {
            ListNode tmp = new ListNode(i);
            pointer.next = tmp;
            pointer = tmp;
        }

        return list.next;
    }

    private ListNode createList(int number) {
        ListNode list = new ListNode(0);
        ListNode pointer = list;
        int[] array = new int[number];

        for (int i = 0; i < number; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(-1000, 1000);
        }

        Arrays.sort(array);

        for (int i = 0; i < array.length; i++) {
            ListNode tmp = new ListNode(array[i]);
            pointer.next = tmp;
            pointer = tmp;
        }

        return list.next;
    }

    public static void main(String[] args) {
        MergeKSortedLists alg = new MergeKSortedLists();

        int count = 100;
        ListNode[] list = new ListNode[count];
        for (int i = 0; i < count; i++) {
            list[i] = alg.createList(ThreadLocalRandom.current().nextInt(5000));
        }

        long t1 = System.nanoTime();
        ListNode result = alg.mergeKLists(list);
        long t2 = System.nanoTime();
        System.out.println((t2-t1)/1000);
    }
}
