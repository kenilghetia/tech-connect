import java.util.concurrent.Semaphore;

class ReadersWriters {
    private Semaphore rwMutex; // Semaphore for controlling access to the resource for writers
    private Semaphore readersMutex; // Semaphore for controlling access to the readers count
    private int readersCount; // Number of active readers

    public ReadersWriters() {
        rwMutex = new Semaphore(1);
        readersMutex = new Semaphore(1);
        readersCount = 0;
    }

    public void startReading(int id) {
        try {
            readersMutex.acquire(); // Acquire access to readers count
            readersCount++;
            if (readersCount == 1) {
                rwMutex.acquire(); // If the first reader, acquire the resource for readers
            }
            readersMutex.release(); // Release access to readers count

            // Reading operation
            System.out.println("Reader " + id + " is reading. Total readers: " + readersCount);
            // Simulating reading process
            Thread.sleep(1000);

            readersMutex.acquire(); // Acquire access to readers count
            
            readersCount--;
            System.out.println("Reader " + id + " completed reading. Total readers: " + readersCount);
            if (readersCount == 0) {
                rwMutex.release(); // If the last reader, release the resource for readers
            }
            readersMutex.release(); // Release access to readers count
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startWriting(int id) {
        try {
            rwMutex.acquire(); // Acquire exclusive access to the resource for writers

            // Writing operation
            System.out.println("Writer " + id + " is writing.");
            // Simulating writing process
            Thread.sleep(2000);

            rwMutex.release(); // Release exclusive access to the resource for writers
            System.out.println("Writer " + id + " completed writing.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ReadersWritersDemo {
    public static void main(String[] args) {
        ReadersWriters rw = new ReadersWriters();
        int numReaders = 5;
        int numWriters = 10;

        // Creating reader threads
        for (int i = 0; i < numReaders; i++) {
            final int readerId = i;
            new Thread(() -> {
                System.out.println("new thread initialized for reader" + readerId);
                rw.startReading(readerId);
            }).start();
        }

        // Creating writer threads
        for (int i = 0; i < numWriters; i++) {
            final int writerId = i;
            new Thread(() -> {
                System.out.println("new thread initialized for writer" + writerId);
                rw.startWriting(writerId);
            }).start();
        }
    }
}
