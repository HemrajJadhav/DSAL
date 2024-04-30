import java.util.Scanner;

class ExpressionsTree2 {
    static class Node {
        char data;
        Node left, right;

        Node(char item) {
            data = item;
            left = right = null;
        }
    }

    Node root;

    ExpressionsTree2() {
        root = null;
    }

    void insert(String expression) {
        char[] charArray = expression.toCharArray();
        root = insertNode(charArray, 0, charArray.length - 1);
    }

    Node insertNode(char[] expression, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = findOperatorWithLowestPrecedence(expression, start, end);
        Node node = new Node(expression[mid]);

        if (start == end) {
            return node;
        }

        node.left = insertNode(expression, start, mid - 1);
        node.right = insertNode(expression, mid + 1, end);

        return node;
    }

    int findOperatorWithLowestPrecedence(char[] expression, int start, int end) {
        int parenthesisCount = 0;
        int lowestPrecedenceIndex = -1;
        int lowestPrecedence = Integer.MAX_VALUE;

        for (int i = end; i >= start; i--) {
            char c = expression[i];

            if (c == '(') {
                parenthesisCount--;
            } else if (c == ')') {
                parenthesisCount++;
            } else if (parenthesisCount == 0 && isOperator(c) && getPrecedence(c) <= lowestPrecedence) {
                lowestPrecedence = getPrecedence(c);
                lowestPrecedenceIndex = i;
            }
        }

        if (lowestPrecedenceIndex == -1) {
            lowestPrecedenceIndex = end;
        }

        return lowestPrecedenceIndex;
    }

    int getPrecedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return Integer.MAX_VALUE;
        }
    }

    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    Node delete(Node root, char key) {
        if (root == null)
            return root;

        if (root.left == null && root.right == null && root.data == key)
            return null;

        Node parent = null;
        Node current = root;
        while (current != null && current.data != key) {
            parent = current;
            if (current.data < key)
                current = current.right;
            else
                current = current.left;
        }

        if (current == null) // Key not found
            return root;

        if (current.left == null && current.right == null) {
            if (current != root) {
                if (parent.left == current)
                    parent.left = null;
                else
                    parent.right = null;
            } else
                root = null;
        } else if (current.left == null) {
            if (current != root) {
                if (parent.left == current)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            } else
                root = current.right;
        } else if (current.right == null) {
            if (current != root) {
                if (parent.left == current)
                    parent.left = current.left;
                else
                    parent.right = current.left;
            } else
                root = current.left;
        } else {
            Node successor = getSuccessor(current);
            if (current != root) {
                if (parent.left == current)
                    parent.left = successor;
                else
                    parent.right = successor;
            } else
                root = successor;

            successor.left = current.left;
        }
        return root;
    }

    Node getSuccessor(Node node) {
        Node current = node.right;
        while (current != null && current.left != null)
            current = current.left;
        return current;
    }

    void showPostOrder(Node root) {
        if (root == null)
            return;

        showPostOrder(root.left);
        showPostOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ExpressionsTree tree = new ExpressionsTree();

        int choice;
        char key;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Insert Expression");
            System.out.println("2. Delete a Node");
            System.out.println("3. Show Postorder Traversal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the expression: ");
                    String expression = scan.next();
                    tree.insert(expression);
                    break;
                case 2:
                    if (tree.root != null) {
                        System.out.print("Enter the node to delete: ");
                        key = scan.next().charAt(0);
                        tree.root = tree.delete(tree.root, key);
                        System.out.println("Expression tree after deletion:");
                        tree.showPostOrder(tree.root);
                    } else {
                        System.out.println("Expression tree is empty!");
                    }
                    break;
                case 3:
                    System.out.println("Postorder Traversal:");
                    tree.showPostOrder(tree.root);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 4);
        scan.close();
    }
}
