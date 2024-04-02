package Controller;

import java.util.concurrent.Semaphore;
import java.util.Arrays;

public class ThreadCorrida extends Thread {
    private static double[] classificacao = new double[14];
    
    private static int cont = 0;
    private int idCarro;
    private Semaphore semaforo;
    public ThreadCorrida(int idCarro, Semaphore semaforo) {
        this.idCarro = idCarro;
        this.semaforo = semaforo;

    }

    @Override
    public void run() {
        
        
        try {
            semaforo.acquire();
            corrida();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
        }
        
        if(cont == 14) {
            grid();
        }

    }

    private void grid() {
        Arrays.sort(classificacao);

        for(int i = 1; i <= 14; i++) {
            System.out.println("GRID " + i + " LUGAR " + classificacao[i - 1]);
        }

    }

    private void corrida() {
        int tamanhoCorrida = 3000;
        int percorridoCorrida = 0;
        int voltas = 1;
        double menorTempo = 1000000;
        double tempoInicio;
        double tempoFim;
        double tempoResultado;


        while(voltas <= 3){
           tempoInicio = (System.nanoTime() / Math.pow(10, 9));
             while (percorridoCorrida < tamanhoCorrida){



            percorridoCorrida = percorridoCorrida + (int) (Math.random() * 26) + 20;

            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
            tempoFim = (System.nanoTime() / Math.pow(10, 9));
            tempoResultado = tempoFim - tempoInicio;
             if(tempoResultado < menorTempo) {
                 menorTempo = tempoResultado;
             }
        System.out.println("O carro #" + idCarro + " completou " + voltas + " volta no tempo de " + tempoResultado + " segundos");
            voltas = voltas + 1;
        }

        classificacao[idCarro] = menorTempo;
        cont = cont + 1;

    }
}
