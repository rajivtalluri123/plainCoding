package linkedlist;

//solving leet code linked list here
public class LinkedListLeet {

    //for prob 92
    ListNode leftNode = null;
    int subLength=0;

    //for prob 143
    int length =0;
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }


    /////////////Level 2 problems--------

    // 234. Palindrome Linked List
    // Given the head of a singly linked list, return true if it is a
    //palindrome or false otherwise.
    // alg - cam impl gracefully with recursion
    public boolean isPalindrome(ListNode head) {
        leftNode = head;
        return recursivelyCheckPalin(head);
    }
    private boolean recursivelyCheckPalin(ListNode node) {
        if(node == null)
            return true;
        if(!recursivelyCheckPalin(node.next))
            return false;
        if(leftNode.val == node.val) {
            leftNode = leftNode.next;
            return true;
        }
        return false;
    }

    //fav-- used recurion nicely to do 2 pointer
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

    //25. Reverse Nodes in k-Group
    public ListNode reverseKGroup(ListNode head, int k) {
        //using approch from pb 92
        int length =0;
        ListNode tempNode = head;
        while(tempNode != null) {
            tempNode = tempNode.next;
            length++;
        }
        for(int left =1, right =k; right <= length; left +=k, right +=k) {
            //use cool pb- 95
            reverseBetween(head, left, right);
        }

        return head;
    }

    //143-Reorder List
    //given head of sll convert from L0 → L1 → … → Ln - 1 → Ln to L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    public void reorderList(ListNode head) {
        leftNode = head;
        ListNode temp = head;

        //find the length of the sll
        while(temp != null) {
            temp =temp.next;
            length++;
        }
        //recurse till the end and chanhe pointer recursively
        recurse(head, 0);
    }
    private ListNode recurse(ListNode curr, int move) {
        if(curr == null)
            return null;
        ListNode prev = recurse(curr.next, move +1);
        if(move >= length/2) {
            if(prev != null)
                prev.next = leftNode;
            //edge case (for mid element or elements)
            if(move == length/2) {
                leftNode.next = curr;  //when the length of sll is odd-- node == left
                curr.next = null;
                return curr;
            }
            ListNode temp = leftNode.next;
            leftNode.next = curr;
            leftNode = temp;
        }
        return curr;
    }

}
