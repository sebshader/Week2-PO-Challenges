import java.util.*;
import java.util.regex.Pattern;
/* when prompted enter characters as nodes. the characters are values and the same character will
 * be mapped to the same node */

public class Cycle {

    private static class Node {
        Node next = null;
        char value;
        public Node(char value) {
            this.value = value;
        }
    }

    // Brent's Algorithm
    private static Node findCycle(Node root) {
        int length = 1;
        int power = 1;
        Node tortoise = root.next;
        Node hare = tortoise.next;
        // get length of cycle
        while(hare != tortoise) {
            if(hare == null) return hare;
            if(power == length) {
                // teleport tortoise
                tortoise = hare;
                // power = power * 2;
                power <<= 1;
                length = 0;
            }
            hare = hare.next;
            length++;
        }
        hare = root.next;
        tortoise = hare;
        // advance hare the cycle length
        while(length-- > 0) {
            hare = hare.next;
        }
        // then we can advance both until they're at the common node
        while(hare != tortoise) {
            tortoise = tortoise.next;
            hare = hare.next;
        }

        return hare;
    }

    public static void main(String[] args) {
        Map<Character, Node> charMap = new HashMap<Character, Node>();
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(Pattern.compile("[ \t]*"));
        System.out.println("Enter list:");
        Node root = new Node(Character.MIN_VALUE);
        Node prev = root;
        while(true) {
            String input = sc.next();
            //System.out.println("Input: " + input);
            if(input.equals("\n") || input.equals("\r")) break;
            char nextChar = input.charAt(0);
            Node newNode = charMap.get(nextChar); 
            if(newNode == null) {
                newNode = new Node(nextChar);
                charMap.put(nextChar, newNode);
            }
            prev.next = newNode;
            prev = newNode;
        }

        Node result = findCycle(root);
        if(result == null) System.out.println("No cycle found");
        else System.out.println("Cycle node value: " + result.value);
        sc.close();
    }
}
