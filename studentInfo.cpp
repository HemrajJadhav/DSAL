#include<iostream>
#include<fstream>
#include<cstring>

using namespace std;

class Student {
public:
    int rollNo;
    char name[10];
    char division;
    char address[20];
    
    void accept() {
        cout << "\n\tEnter Roll Number : ";
        cin >> rollNo;
        cout << "\n\tEnter the Name : ";
        cin >> name;
        cout << "\n\tEnter the Division: ";
        cin >> division;
        cout << "\n\tEnter the Address: ";
        cin >> address;
    }
    
    void show() {
        cout << "\n\t" << rollNo << "\t\t" << name << "\t\t" << division << "\t\t" << address;
    }
};

int main() {
    int ch;
    char name[20];
    Student stu;
    fstream file;
    
    do {
        cout << "\n>>>>>>>>>>>>>>>>>>>>>> MENU <<<<<<<<<<<<<<<<<<<<<<";
        cout << "\n1. Insert and Overwrite\n2. Show\n3. Search & Edit (by number)\n4. Search & Edit (by name)\n5. Search & Edit (by only number)\n6. Search & Edit (by only name)\n7. Delete a Student Record\n8. Exit\nEnter your choice: ";
        cin >> ch;
        
        switch(ch) {
            case 1: {
                file.open("StudentRecord.txt", ios::out);
                int choice;
                do {
                    stu.accept();
                    file.write((char*)&stu, sizeof(stu));
                    cout << "\nDo you want to enter more records?\n1. Yes\n2. No\nEnter your choice: ";
                    cin >> choice;
                } while(choice == 1);
                file.close();
                break;
            }
            case 2: {
                file.open("StudentRecord.txt", ios::in);
                file.read((char*)&stu, sizeof(stu));
                cout << "\n\tRoll No.\tName\t  Division\t\tAddress";
                while(file) {
                    stu.show();
                    file.read((char*)&stu, sizeof(stu));
                }
                file.close();
                break;
            }
            case 3: {
                int rollNo;
                cout << "\nEnter the roll number you want to find: ";
                cin >> rollNo;
                file.open("StudentRecord.txt", ios::in | ios::out);
                file.read((char*)&stu, sizeof(stu));
                while(file) {
                    if(rollNo == stu.rollNo) {
                        cout << "\nRecord found";
                        stu.accept();
                        file.seekp(-sizeof(stu), ios::cur);
                        file.write((char*)&stu, sizeof(stu));
                        file.close();
                        break;
                    }
                    file.read((char*)&stu, sizeof(stu));
                }
                if(!file) {
                    cout << "\nRecord not found";
                    file.close();
                }
                break;
            }
            // Implement other cases similarly...
            case 8: {
                cout << "\n\tThank you";
                break;
            }
            default: {
                cout << "\nInvalid choice!";
            }
        }
    } while(ch != 8);

    return 0;
}
