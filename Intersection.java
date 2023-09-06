import java.util.*;
import java.util.regex.Pattern;

/* when prompted enter characters as nodes. the characters are values and the same character will
 * be mapped to the same node */

public class Intersection {

    private static class Node {
        Node next = null;
        char value;
        public Node(char value) {
            this.value = value;
        }
    }

    private static Node getIntersection(Node root1, Node root2) {
        // get real 1st elements
        Node elem1 = root1.next;
        Node elem2 = root2.next;
        /* length of list 1 + length of list 2 = length of list 2 + length of list 1
         * we can iterate through both concurrently, and 'roll over' to the other list upon reaching the end of
         * each. On the 2nd pass, the shared node will match because both pointers will have gone through the
         * length of both portions leading up to the intersection, plus the shared length after the intersection.
         * both will be null if no intersection is present. (and will be reached at the same time as well)
        */
        while(elem1 != elem2) {
            elem1 = elem1 == null ? root2.next : elem1.next;
            elem2 = elem2 == null ? root1.next : elem2.next;
        }
        return elem1;
    }

    public static void main(String[] args) {
        Map<Character, Node> charMap = new HashMap<Character, Node>();
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(Pattern.compile("[ \t]*"));
        System.out.println("Enter 1st list:");
        Node root1 = new Node(Character.MIN_VALUE);
        Node prev = root1;
        while(true) {
            String input = sc.next();
            //System.out.println("Input: " + input);
            if(input.equals("\n") || input.equals("\r")) break;
            char nextChar = input.charAt(0);
            Node newNode = new Node(nextChar);
            prev.next = newNode;
            prev = newNode;
            charMap.put(nextChar, newNode);
        }
        System.out.println("Enter 2nd list:");
        Node root2 = new Node(Character.MIN_VALUE);
        prev = root2;
        while(true) {
            String input = sc.next();
            //System.out.println("Input: " + input);
            if(input.equals("\n") || input.equals("\r")) break;
            char nextChar = input.charAt(0);
            Node newNode = charMap.get(nextChar); 
            if(newNode == null) {
                newNode = new Node(nextChar);
            }
            prev.next = newNode;
            prev = newNode;
        }
        /*
        System.out.print("First list: ");
        Node current = root1.next;
        while(current != null) {
            System.out.print(current.value);
            if(current.next != null) System.out.print(' ');
            current = current.next;
        }
        System.out.print('\n');
        current = root2.next;
        System.out.print("Second list: ");
        while(current != null) {
            System.out.print(current.value);
            if(current.next != null) System.out.print(' ');
            current = current.next;
        }
        System.out.print('\n');
        */
        Node result = getIntersection(root1, root2);
        if(result == null) System.out.println("No intersection found");
        else System.out.println("Intersection node value: " + result.value);
        sc.close();
    }
}
