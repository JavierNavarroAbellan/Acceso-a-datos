/**
 * @author Javier Navarro Abellán
 * @date 01-10-2025
 */

import java.util.Random;

public class MonitorExample {

    // Clase Buffer que actúa como monitor
    static class Buffer {
        private int[] buffer;
        private int count = 0; //Número de elementos que hay en el buffer
        private int size;      //Tamaño del buffer

        public Buffer(int size) {
            this.size = size;
            buffer = new int[size];
        }

        //Método sincronizado para añadir un elemento por el suministrador
        public synchronized void add(int valor) throws InterruptedException {
            while (count == size) {
                wait(); //Esperar por buffer lleno
            }
            buffer[count] = valor;
            count++;
            System.out.println(Thread.currentThread().getName() + " añadió: " + valor);
            notifyAll();
        }

        //Método sincronizado para extraer un elemento por el cliente
        public synchronized int remove() throws InterruptedException {
            while (count == 0) {
                wait(); //Esperar por buffer vacío
            }
            int valor = buffer[--count];
            System.out.println(Thread.currentThread().getName() + " extrajo: " + valor);
            notifyAll();
            return valor;
        }
    }

    // Clase Suministrador
    static class Suministrador extends Thread {
        private Buffer buffer;
        private Random random = new Random();

        public Suministrador(Buffer buffer, String name) {
            super(name);
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int valor = random.nextInt(100);
                    buffer.add(valor);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrumpido.");
            }
        }
    }

    // Clase Cliente
    static class Cliente extends Thread {
        private Buffer buffer;

        public Cliente(Buffer buffer, String name) {
            super(name);
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    buffer.remove();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrumpido.");
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);

        System.out.println("Ejecución con 1 cliente y 1 suministrador");
        Suministrador s1 = new Suministrador(buffer, "Suministrador-1");
        Cliente c1 = new Cliente(buffer, "Cliente-1");

        s1.start();
        c1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}

        s1.interrupt();
        c1.interrupt();

        try {
            s1.join();
            c1.join();
        } catch (InterruptedException e) {}

        System.out.println("Ejecución con 3 clientes y 3 suministradores");
        Buffer buffer2 = new Buffer(5);

        Suministrador s2 = new Suministrador(buffer2, "Suministrador-2");
        Suministrador s3 = new Suministrador(buffer2, "Suministrador-3");
        Suministrador s4 = new Suministrador(buffer2, "Suministrador-4");

        Cliente c2 = new Cliente(buffer2, "Cliente-2");
        Cliente c3 = new Cliente(buffer2, "Cliente-3");
        Cliente c4 = new Cliente(buffer2, "Cliente-4");

        s2.start();
        s3.start();
        s4.start();
        c2.start();
        c3.start();
        c4.start();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {}

        s2.interrupt();
        s3.interrupt();
        s4.interrupt();
        c2.interrupt();
        c3.interrupt();
        c4.interrupt();

        try {
            s2.join();
            s3.join();
            s4.join();
            c2.join();
            c3.join();
            c4.join();
        } catch (InterruptedException e) {}
    }
}