package linkedlist;

//solving leet code linked list here
public class LinkedListLeet {

    //for prob 92
    ListNode leftNode = null;
    int subLength=0;
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    //92. Reverse Linked List II
    //Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode temp = head;
        int l =1;
        while (l < left && temp != null) {
            temp = temp.next;
            l++;
        }
        leftNode = temp;

        subLength = right - left +1;
        //use recursion
        recursive(temp, right -left);

        return head;
    }

    private void recursive(ListNode node, int move) {

        if(move > 0) {
            //recursive move for right
            recursive(node.next, move -1);
        }


        //swap left and right
        if(move < subLength/2) {
            int temp = leftNode.val;
            leftNode.val = node.val;
            node.val = temp;
            leftNode = leftNode.next;
        }


    }
}
