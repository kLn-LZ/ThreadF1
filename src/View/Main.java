package View;

import Controller.ThreadCorrida;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Semaphore semaforo = new Semaphore(5);

        for(int i = 0; i < 14; i++) {

            Thread tF1 = new ThreadCorrida(i, semaforo);
            tF1.start();

        }

    }
}
