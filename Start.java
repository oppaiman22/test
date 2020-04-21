
import queue.*;
import java.util.Scanner;


public class Start {
    public static void main(String args[]){
        queue q = new queue();  //queue
        
        int c = 0;              //pilihan user / user choice
        
        while(c != 4){
            
            System.out.print("\nIsi Queue : ");
            q.printer();
            menu(c, q);
        }
        
    }
    
    public static int input_data(){
        
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
    
    public static void menu(int choice, queue q) {
        System.out.println("1. Tambah Data");
        System.out.println("2. Hapus Data");
        System.out.println("3. Hapus Semua Data");
        System.out.println("4. Keluar");

        System.out.print("pilih menu : ");

        choice = input_data();

        switch (choice) {
            case 1:
                System.out.print("intput data baru : ");
                int baru = input_data();
                q.Enqueue(baru);
                break;
            case 2:
                q.Dequeue();
                break;
            case 3:
                q.cleaner();
                break;
            case 4:
                break;
            default:
                System.out.println("pilih menu 1, 2, 3, atau 4");

        }
    }
}
