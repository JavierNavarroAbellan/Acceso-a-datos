import java.util.LinkedList;
import java.util.Queue;

// Clase Buffer que actúa como monitor
public class Buffer {
    private int[] buffer;
    private int count = 0; //Número de elementos que hay en el buffer
    private int size; //Tamaño del buffer

    public Buffer(int size) {
        this.size = size;
        buffer = new int[size];
    }

    //Método sincronizado para añadir un elemento por el suministrador
    public synchronized void add(int valor) throws InterruptedException {
        while (count == size) {
            wait(); //Esperar por buffer lleno
        }
        buffer[count] = valor; //Añade un nuevo valor al buffer
        count++;
        System.out.println("Suministrador añadió: " + valor);
        notifyAll();
    }

    //Método sincronizado para extraer un elemento por el cliente
    public synchronized int remove() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int valor = buffer[count--];
        System.out.print("Cliente extrajo: " + valor);
        notifyAll();
        return valor;
    }
}

// Clase Suministrador

// Clase Cliente

// Clase principal MonitorExample
