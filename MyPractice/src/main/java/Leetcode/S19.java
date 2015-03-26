package Leetcode;

public class S19 {
    public class ListNode {
             int val;
             ListNode next;
              }

    public class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if(head == null)
                return head;

            ListNode a = head;
            ListNode b = head;
            int i = 0;
            while (i<n){
                b = b.next;
                i++;
            }
            // for just one node case.
            if (b == null)
                return a.next;

            while (b.next != null){
                a = a.next;
                b = b.next;
            }
            a.next = a.next.next;
            return head;
        }
    }
}
