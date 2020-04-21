public class Queue {
    private final int MAX_QUEUE_ARRAY = 10;                 //konstanta maksimal array elemen
    private int [] queueData = new int[MAX_QUEUE_ARRAY];    //queue data
    private int currentQueuCount = 0;                       //total data saat ini
    private int firstQueue = 0;                             //indeks data paling depan dalam antrian
    private int lastQueue = 0;                              //indeks paling belakang antrian

    public boolean isEmpty() {
        return currentQueuCount==0;
    }

    public boolean isFull() {
        return currentQueuCount==MAX_QUEUE_ARRAY;
    }

    public void cleaner() {
        queueData = new int[MAX_QUEUE_ARRAY];
        firstQueue = 0;
        lastQueue = 0;
        currentQueuCount = 0;
    }

    public void enqueue(int value) {
        if( !isFull() ){
            queueData[lastQueue] = value;
            lastQueue = (lastQueue + 1) % MAX_QUEUE_ARRAY;
            currentQueuCount++;
        }else{
            System.out.println("Gagal input data, Queue penuh");
        }
    }

    public void dequeue() {
        if(!isEmpty()){
            queueData[firstQueue] = 0;
            firstQueue = (firstQueue+1) % MAX_QUEUE_ARRAY;
            currentQueuCount--;
        }else{
            System.out.println("Gagal hapus data, Queue kosong");
        }
    }

    public void print() {
        for(int i = 0; i<currentQueuCount; i++){
            System.out.print(" "+queueData[(firstQueue+i)%MAX_QUEUE_ARRAY]+",");
        }
        System.out.println("");
    }
}
