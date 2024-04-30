import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinarySearchTree2 {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    static Node root;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice, data;

        while (true) {
            println("1. Insert");
            println("2. Delete");
            println("3. Inorder Traversal");
            println("4. Display Tree Structure");
            println("5. Exit");
            println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    print("Enter the data to insert: ");
                    data = scanner.nextInt();
                    root = insert(root, data);
                    break;
                case 2:
                    print("Enter the data to delete: ");
                    data = scanner.nextInt();
                    root = delete(root, data);
                    break;
                case 3:
                    inorder(root);
                    println();
                    break;
                case 4:
                    displayTree(root);
                    break;
                case 5:
                    System.exit(0);
                default:
                    println("Invalid choice. Please try again.");
            }
        }
    }

    static Node insert(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);

        return root;
    }

    static Node delete(Node root, int data) {
        if (root == null)
            return root;

        if (data < root.data)
            root.left = delete(root.left, data);
        else if (data > root.data)
            root.right = delete(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }

        return root;
    }

    static int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    static void displayTree(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");

            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
    }

    static void print(String s){
        System.out.print(s);
    }

    static void println(String... s){
        if(s.length == 0){
            System.out.println();
        }
        for(String ss : s ){
            System.out.println(ss);
        }
    }
    static void printPy(String... s){
        StringBuilder stringBuilder = new StringBuilder();
        for(String messages : s ){
            stringBuilder.append(messages);
        }
        System.out.print(stringBuilder);
    }
    static void printlnPy(String... s){
        StringBuilder stringBuilder = new StringBuilder();
        for(String messages : s ){
            stringBuilder.append(messages);
        }
        System.out.println(stringBuilder);
    }

}