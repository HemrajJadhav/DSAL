import java.util.Scanner;
import java.util.Stack;

class Node {
    char data;
    Node left;
    Node right;

    Node(char data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class ExpressionTree3 {
    static Node constructTree(Stack<Node> mystack, String expression) {
        for (int i = expression.length() - 1; i >= 0; i--) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                Node op1 = mystack.pop();
                Node op2 = mystack.pop();
                Node newnode = new Node(expression.charAt(i));
                newnode.left = op1;
                newnode.right = op2;
                mystack.push(newnode);
            } else {
                Node newnode1 = new Node(expression.charAt(i));
                mystack.push(newnode1);
            }
        }
        return mystack.peek();
    }

    static void nonRecursivePostorderTraversal(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Stack<Node> output = new Stack<>();

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            output.push(current);

            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }

        while (!output.isEmpty()) {
            System.out.print(output.pop().data);
        }
    }

    static void deleteRoot(Node root) {
        if (root == null) {
            return;
        }
        deleteRoot(root.left);
        deleteRoot(root.right);
        root = null;
    }

    public static void main(String[] args) {
        Stack<Node> mystack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the prefix expression here-");
        String s = scanner.next();

        Node root = constructTree(mystack, s);

        System.out.print("Non-recursive postorder traversal of the expression tree is- ");
        nonRecursivePostorderTraversal(root);
        System.out.println();

        deleteRoot(root);
        scanner.close();
    }
}
