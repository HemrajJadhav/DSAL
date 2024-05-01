import java.util.Scanner;

public class Hashing {

    static int size = 10;
    static Long[] array1 = new Long[size];
    static Long[] array2 = new Long[size];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Insert \n2. Search \n3. Display \n4. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter Telephone Number : ");
                long data1 = scanner.nextLong();
                System.out.println();
                insertLinearProbing(data1);
                System.out.println();
                insertQuadraticProbing(data1);
                System.out.println();
            }

            if (choice == 2) {
                System.out.print("Enter Telephone Number : ");
                long data1 = scanner.nextLong();
                System.out.println();
                search1(data1);
                search2(data1);
                System.out.println();
            }
            if (choice == 3){
                displayLinearProbing();
                displayQuadraticProbing();
            }
            if (choice == 4){
                System.out.println("Exiting... The program!");
                System.exit(0);
                scanner.close();
            }
        }
    }

    static void insertLinearProbing(long data) {
        int i = 0;
        int count = 1;
        int value = (int)((data + i) % size);
        while (array1[value] != null) {
            value = (int)((data + i) % size);
            i = i + 1;
            count = count + 1;
        }
        array1[value] = data;
        displayLinearProbing();
        System.out.println("Number of comparisons : " + count);
    }

    static void insertQuadraticProbing(long data) {
        int i = 0;
        int count = 1;
        int value = (int)((data + (i * i)) % size);
        while (array2[value] != null) {
            if (count > (2 * size)) {
                System.out.println("Opps! Index Not Found....");
                break;
            }
            value = (int)((data + (i * i)) % size);
            i = i + 1;
            count = count + 1;
        }
        array2[value] = data;
        displayQuadraticProbing();
        System.out.println("Number of Comparisons : " + count);
    }

    static void displayLinearProbing() {
        System.out.println("Linear Probing: ");
        for (int i = 0; i < size; i++) {
            if (array1[i] == null)
                System.out.print("null ");
            else
                System.out.print(array1[i] + " ");
        }
        System.out.println();
    }

    static void displayQuadraticProbing() {
        System.out.println("Quadratic Probing : ");
        for (int i = 0; i < size; i++) {
            if (array2[i] == null)
                System.out.print("null ");
            else
                System.out.print(array2[i] + " ");
        }
        System.out.println();
    }

    static void search1(long data) {
        int i = 0;
        int count = 1;
        int value = (int)((data + (i)) % size);
        while (array1[value] != null) {
            if (array1[value] == data) {
                System.out.println("Telephone Number Found (LP)");
                System.out.println("Number of Comparisons in Linear Probing are : " + count);
                break;
            } else if (count > (size * 2)) {
                System.out.println("NO ELEMENT FOUND");
                break;
            }
            value = (int)((data + (i)) % size);
            i += 1;
            count += 1;
        }
        if (array1[value] == null) {
            System.out.println("Opps, Element Not Present");
            System.out.println("Number of Comparisons : " + count);
        }
    }

    static void search2(long data) {
        int i = 0;
        int count = 1;
        int value = (int)((data + (i * i)) % size);
        while (array2[value] != null) {
            if (array2[value] == data) {
                System.out.println("Telephone number found (QP)");
                System.out.println("Number of comparisons in Quadratic Probing are : " + count);
                break;
            }
            else if (count > (2 * size)) {
                System.out.println("Opps! Cannot Fetch Index");
                break;
            }
            value = (int)((data + (i * i)) % size);
            i = i + 1;
            count = count + 1;
        }
        if (array2[value] == null) {
            System.out.println("No such Element Found");
            System.out.println("Number of comparisons : " + count);
        }
    }
}
