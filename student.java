import java.io.*;
import java.util.Scanner;

class Student1 {
    public int rollNo;
    public String name;
    public char division;
    public String address;
    
    public void accept() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\tEnter Roll Number : ");
        rollNo = scanner.nextInt();
        System.out.println("\n\tEnter the Name : ");
        name = scanner.next();
        System.out.println("\n\tEnter the Division: ");
        division = scanner.next().charAt(0);
        System.out.println("\n\tEnter the Address: ");
        address = scanner.next();
    }
    
    public void show() {
        System.out.println("\n\t" + rollNo + "\t\t" + name + "\t\t" + division + "\t\t" + address);
    }
}

public class student {
    public static void main(String[] args) {
        int ch;
        Student1 stu = new Student1();
        Scanner scanner = new Scanner(System.in);
        File file = new File("StudentRecord.txt");
        FileWriter fw = null;
        FileReader fr = null;
        RandomAccessFile raf = null;
        
        do {
            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>> MENU <<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("1. Insert and Overwrite\n2. Show\n3. Search & Edit (by number)\n4. Search & Edit (by name)\n5. Search & Edit (by only number)\n6. Search & Edit (by only name)\n7. Delete a Student Record\n8. Exit\nEnter your choice: ");
            ch = scanner.nextInt();
            
            switch(ch) {
                case 1: {
                    try {
                        fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        
                        int choice;
                        do {
                            stu.accept();
                            pw.println(stu.rollNo + " " + stu.name + " " + stu.division + " " + stu.address);
                            System.out.println("\nDo you want to enter more records?\n1. Yes\n2. No\nEnter your choice: ");
                            choice = scanner.nextInt();
                        } while(choice == 1);
                        
                        pw.close();
                        bw.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    try {
                        fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        
                        System.out.println("\n\tRoll No.\tName\t  Division\t\tAddress");
                        while((line = br.readLine()) != null) {
                            String[] parts = line.split(" ");
                            stu.rollNo = Integer.parseInt(parts[0]);
                            stu.name = parts[1];
                            stu.division = parts[2].charAt(0);
                            stu.address = parts[3];
                            stu.show();
                        }
                        
                        br.close();
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    System.out.println("\nEnter the roll number you want to find: ");
                    int rollNo = scanner.nextInt();
                    
                    try {
                        raf = new RandomAccessFile(file, "rw");
                        String line;
                        while((line = raf.readLine()) != null) {
                            String[] parts = line.split(" ");
                            if(Integer.parseInt(parts[0]) == rollNo) {
                                stu.rollNo = Integer.parseInt(parts[0]);
                                stu.name = parts[1];
                                stu.division = parts[2].charAt(0);
                                stu.address = parts[3];
                                
                                System.out.println("\nRecord found");
                                stu.accept();
                                
                                raf.seek(raf.getFilePointer() - line.length() - 2);
                                raf.writeBytes(stu.rollNo + " " + stu.name + " " + stu.division + " " + stu.address + "\n");
                                
                                break;
                            }
                        }
                        
                        if(line == null) {
                            System.out.println("\nRecord not found");
                        }
                        
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 4: {
                    System.out.println("\nEnter the name you want to find: ");
                    scanner.nextLine(); // consume newline character left-over from previous input
                    String name = scanner.nextLine();
                    
                    try {
                        raf = new RandomAccessFile(file, "rw");
                        String line;
                        long position = 0;
                        while((line = raf.readLine()) != null) {
                            String[] parts = line.split(" ");
                            if(parts[1].equals(name)) {
                                stu.rollNo = Integer.parseInt(parts[0]);
                                stu.name = parts[1];
                                stu.division = parts[2].charAt(0);
                                stu.address = parts[3];
                                
                                System.out.println("\nRecord found");
                                stu.show();
                                
                                raf.setLength(position); // delete current record by setting length of file to current position
                                stu.accept();
                                
                                raf.seek(raf.length());
                                raf.writeBytes(stu.rollNo + " " + stu.name + " " + stu.division + " " + stu.address + "\n");
                                
                                break;
                            }
                            position += line.length() + 2; // move to next record
                        }
                        
                        if(line == null) {
                            System.out.println("\nRecord not found");
                        }
                        
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 5: {
                    System.out.println("\nEnter the roll number you want to search for: ");
                    int rollNo = scanner.nextInt();
                    
                    try {
                        raf = new RandomAccessFile(file, "r");
                        String line;
                        boolean found = false;
                        while((line = raf.readLine()) != null && !found) {
                            String[] parts = line.split(" ");
                            if(Integer.parseInt(parts[0]) == rollNo) {
                                found = true;
                                stu.rollNo = Integer.parseInt(parts[0]);
                                stu.name = parts[1];
                                stu.division = parts[2].charAt(0);
                                stu.address = parts[3];
                                
                                System.out.println("\nRecord found");
                                stu.show();
                            }
                        }
                        
                        if(!found) {
                            System.out.println("\nRecord not found");
                        }
                        
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 6: {
                    System.out.println("\nEnter the name you want to search for: ");
                    scanner.nextLine(); // consume newline character left-over from previous input
                    String name = scanner.nextLine();
                    
                    try {
                        raf = new RandomAccessFile(file, "r");
                        String line;
                        boolean found = false;
                        while((line = raf.readLine()) != null && !found) {
                            String[] parts = line.split(" ");
                            if(parts[1].equals(name)) {
                                found = true;
                                stu.rollNo = Integer.parseInt(parts[0]);
                                stu.name = parts[1];
                                stu.division = parts[2].charAt(0);
                                stu.address = parts[3];
                                
                                System.out.println("\nRecord found");
                                stu.show();
                            }
                        }
                        
                        if(!found) {
                            System.out.println("\nRecord not found");
                        }
                        
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 7: {
                    System.out.println("\nEnter the roll number of the student you want to delete: ");
                    int rollNo = scanner.nextInt();
                    
                    try {
                        raf = new RandomAccessFile(file, "rw");
                        String line;
                        long position = 0;
                        while((line = raf.readLine()) != null) {
                            String[] parts = line.split(" ");
                            if(Integer.parseInt(parts[0]) == rollNo) {
                                raf.setLength(position); // delete current record by setting length of file to current position
                            } else {
                                raf.seek(raf.getFilePointer() - line.length() - 2);
                                raf.writeBytes(line + "\n"); // write current record back to file
                            }
                            position += line.length() + 2; // move to next record
                        }
                        
                        if(line == null) {
                            System.out.println("\nRecord not found");
                        } else {
                            System.out.println("\nRecord deleted successfully.");
                        }
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                
                case 8: {
                    System.out.println("\n\tThank you");
                    break;
                }
                default: {
                    System.out.println("\nInvalid choice!");
                }
            }
        } while(ch != 8);
        
        scanner.close();
    }
}
