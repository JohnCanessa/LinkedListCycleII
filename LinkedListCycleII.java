import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;


/**
 * LeetCode 142. Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 */
public class LinkedListCycleII {


    /**
     * Class for nodes in the linked list.
     */
    static class ListNode {
    
        // **** members ****
        int         val;
        ListNode    next;
    
        // **** constructor(s) ****
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
        // **** ****
        @Override
        public String toString() {
            return "" + this.val;
        }
    }


    /**
     * Populate linked list from the contents of the specified array.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static ListNode populate(int[] arr, int pos) {
    
        // **** sanity ckeck(s) ****
        if (arr.length == 0) return null;
    
        // **** initialization ****
        ListNode head   = null;
        ListNode cycle  = null;
        ListNode tail   = null;

        // **** traverse array adding nodes to the linked list ****
        for (int i = arr.length - 1; i >= 0; i--) {

            // **** update head of list ****
            head = new ListNode(arr[i], head);

            // **** update tail of list ****
            if (tail == null) tail = head;

            // **** save node at specified position ****
            if (pos == i) cycle = head;
        }

        // **** generate cycle ****
        if (cycle != null) tail.next = cycle;

        // **** return head of linked list ****
        return head;
    }


    /**
     * Display linked list.
     * Stops after last node or cycle detected.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static String display(ListNode head) {
    
        // **** sanity check(s) ****
        if (head == null) return "";

        // **** initialization ****
        StringBuilder sb        = new StringBuilder();
        HashSet<ListNode> hs    = new HashSet<ListNode>();
    
        // **** traverse link list ****
        for (ListNode p = head; p != null; p = p.next) {

            // **** add this node to the hash set ****
            if (hs.add(p) == false) {
                sb.append(" cycle detected @ p.val: " + p.val);
                break;
            }

            // **** append node value to string builder ****
            sb.append(p.val);
            if (p.next != null)
                sb.append("->");
        }
    
        // **** return string representation of linked list ****
        return sb.toString();
    }


    /**
     * Given the head of a linked list, 
     * return the node where the cycle begins.
     * If there is no cycle, return null.
     * 
     * Runtime: O(n) - Space: O(n)
     * 
     * Runtime: 3 ms, faster than 24.16% of Java online submissions.
     * Memory Usage: 39.9 MB, less than 30.58% of Java online submissions.
     */
    static public ListNode detectCycle0(ListNode head) {
        
        // **** sanity check(s) ****
        if (head == null || head.next == null) return null;
        if (head.next == head) return head;

        // **** initialization ****
        HashSet<ListNode> hs = new HashSet<>();

        // **** traverse the linked list detecting cycle ****
        for (ListNode p = head; p != null; p = p.next) {

            // **** check if cycle found at thsi node ****
            if (hs.add(p) == false) return p;
        }

        // **** no cycle ****
        return null;
    }


    /**
     * Given the head of a linked list, 
     * return the node where the cycle begins.
     * If there is no cycle, return null.
     * 
     * Using Floyd’s Cycle-Finding Algorithm.
     * 
     * Execution: O(n) - Space: O(1)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.8 MB, less than 86.49% of Java online submissions.
     * 
     * 16 / 16 test cases passed.
     * Status: Accepted
     * Runtime: 0 ms
     * Memory Usage: 38.8 MB
     */
    static public ListNode detectCycle(ListNode head) {
        
        // **** sanity check(s) ****
        if (head == null || head.next == null) return null;
        if (head.next == head) return head;

        // **** initialization ****
        ListNode t  = head;             // tortoise at start of linked list
        ListNode h  = head;             // hare at start of linked list

        // **** traverse the linked list ****
        while (h != null && h.next != null) {

            // **** move h & t forward ****
            h = h.next.next;            // use double speed
            t = t.next;                 // use single speed

            // **** we encounter a loop ****
            if (t == h) break;
        }

        // ???? ????
        System.out.println("<<< h: " + h + " h.next: " + h.next);

        // **** check if a loop was NOT found ****
        if (h == null || h.next == null) return null;

        // **** set h to start of linked list ****
        h = head;

        // ???? ????
        System.out.println("<<< h: " + h + " t: " + t);

        // **** move h & t forward at single speed ****
        while (t != h) {
            t = t.next;                 // single speed
            h = h.next;                 // single speed
        }

        // ???? ????
        System.out.println("<<< h: " + h + " t: " + t);

        // **** h and t at the cycle node ****
        return h;
    }


    /**
     * Test scaffold.
     * !!! NOT PART OF SOLUTION !!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reder ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
        // **** read and split node values ****
        String[] strs = br.readLine().trim().split(",");
        
        // **** read `pos` ****
        int pos = Integer.parseInt(br.readLine().trim());
        
        // **** close buffered reader ****
        br.close();
        
        // **** convert string array to integer array ****
        int[] arr = Arrays.stream(strs).mapToInt(Integer::parseInt).toArray();
        
        // ???? ????
        System.out.println("main <<<  arr: " + Arrays.toString(arr));
        System.out.println("main <<<  pos: " + pos);

        // **** populate linked list ****
        ListNode head = populate(arr, pos);
        
        // ???? ????
        System.out.println("main <<< head: " + display(head));
    
        // **** invoke function of interest and display result ****
        System.out.println("main <<< detectCycle0: " + detectCycle0(head));

        // **** invoke function of interest and display result ****
        System.out.println("main <<<  detectCycle: " + detectCycle(head));
    }
}