
import queue.*;

public class queue {
    public int M = 10;              //konstanta maksimal array element
    
    public int [] qd = new int[M];  //queue data
    
    public int n = 0, f = 0, b = 0;
    //total data saat ini
    //indeks data paling depan dalam antrian
    //indeks paling belakang antrian
    
    public boolean isEmpty(){
        return n==0;
    }
    public boolean isFull(){
        return n==M;
    }
    public void cleaner(){
        qd = new int[M];
        f = 0;
        b = 0;
        n = 0;
    }
    public void Enqueue(int value){
        
        if( !isFull() ){
            qd[b] = value;
            b = (b + 1) % M;
            n++;
            
        }else{System.out.println("Gagal input data, Queue penuh");}
    }
    public void Dequeue(){
        if(!isEmpty()){
            qd[f] = 0;
            f = (f+1) % M;
            n--;
            
        }else{System.out.println("Gagal hapus data, Queue kosong");}
    }
    public void printer(){
        for(int i = 0; i<n; i++){System.out.print(" "+qd[(f+i)%M]+",");}
        System.out.println("");
    }
}
