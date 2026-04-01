import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

public class BracketChecker {
    public static boolean isBalanced(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : input.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } 
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == ']' && top != '[') || 
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string of parentheses: ");
        String str = scanner.nextLine();
        
        if (isBalanced(str)) {
            System.out.println("The parentheses are BALANCED.");
        } else {
            System.out.println("The parentheses are NOT balanced.");
        }
        scanner.close();
    }
}