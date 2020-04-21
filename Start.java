import java.util.Scanner;
public class Start {
    public static void main(String args[]){
        Queue queueObject = new Queue();  //create queue object
        int userChoice = 0;               //pilihan user / user choices
        while(userChoice != 4){
            System.out.print("\nIsi Queue : ");
            queueObject.print();
            showMenu(userChoice, queueObject);
        }
    }
    
    public static int inputData(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
    
    public static void showMenu(int choice, Queue queueObject) {
        System.out.println("1. Tambah Data");
        System.out.println("2. Hapus Data");
        System.out.println("3. Hapus Semua Data");
        System.out.println("4. Keluar");
        System.out.print("pilih menu : ");
        choice = inputData();
        int tempInput
        switch (choice) {
            case 1:
                System.out.print("intput data baru : ");
                tempInput = inputData();
                queueObject.enqueue(tempInput);
                break;
            case 2:
                queueObject.dequeue();
                break;
            case 3:
                queueObject.cleaner();
                break;
            case 4:
                break;
            default:
                System.out.println("pilih menu 1, 2, 3, atau 4");
        }
    }
}
