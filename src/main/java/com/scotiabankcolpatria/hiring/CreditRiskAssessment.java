package com.scotiabankcolpatria.hiring;

import jdk.internal.misc.FileSystemOption;

import java.util.Arrays;

public class CreditRiskAssessment {

    /**
     * Calcula el valor de la desviación estandar.
     * @param paymentDelays
     * @return Valor de la desviación estandar
     */
    public double standardDeviation(int[] paymentDelays) {

        //Copia de la entrada en un arreglo de double
        double[] doubles = Arrays.stream(paymentDelays).asDoubleStream().toArray();
        double [] differences = new double[paymentDelays.length];
        //Llama al método que obtiene la media
        double media = getMedia(doubles);

        //Obtiene las diferencias con la media
        for (int i = 0; i < paymentDelays.length ; i++) {
            differences[i] = Math.pow(paymentDelays[i] - media, 2);
        }

        //Obtiene la media de las diferencias
        double mediaDifferences = getMedia(differences);

        //Retorna la raíz cuadrada de las diferencias
        return Math.sqrt(mediaDifferences);
    }

    /**
     * Calcula el valor de la media.
     * @param numbers
     * @return Valor de la media
     */
    private double getMedia(double[] numbers ){
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum/ numbers.length;
    }


    /**
     * Caclcula cual es el indice del pico máximo.
     * @param paymentDelays
     * @return Devuelve el indice del pico máximo.
     */
    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {

        int maxPeak = 0;
        int maxPeakIndice = -1;
        int difference = 0;
        int differenceTemp = 0;

        for (int i = 0; i < paymentDelays.length; i++) {
            //Validación primera posición del arreglo
            if(i == 0){
                if(paymentDelays[i] > paymentDelays[i+1]){
                    maxPeak = paymentDelays[i];
                    maxPeakIndice = i;
                    difference = paymentDelays[i] - paymentDelays[i+1];
                }
            //Validación ultima posición del arreglo
            } else if (i == paymentDelays.length-1) {
                if(paymentDelays[i] > paymentDelays[i-1]){
                    differenceTemp = paymentDelays[i] -paymentDelays[i-1];
                    // Valida si el pico es mayor o si la diferencia del pico es mayor
                    if(paymentDelays[i] > maxPeak || differenceTemp > difference) {
                        maxPeak = paymentDelays[i];
                        maxPeakIndice = i;
                        difference = differenceTemp;
                    }
                }
            //Valida las demas posiciones del arrglo
            }else{
                if(paymentDelays[i] > paymentDelays[i+1] && paymentDelays[i] > paymentDelays[i-1]){
                    differenceTemp = (paymentDelays[i] - paymentDelays[i+1]) + (paymentDelays[i] -paymentDelays[i-1]);
                    // Valida si el pico es mayor o si la diferencia del pico es mayor
                    if(paymentDelays[i] > maxPeak || differenceTemp > difference){
                        maxPeak = paymentDelays[i];
                        maxPeakIndice = i;
                        difference = differenceTemp;
                    }
                }
            }
        }
        return maxPeakIndice;
    }

    /**
     * Calcula las probabilidades de pago tardido.
     * @param paymentDelays
     * @return Devuelve un arreglo de las probabilidades de pagos tardidos.
     */
    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
 
        double count = 0;
        double [] res = new double[paymentDelays[0].length];
        for (int i = 0; i < paymentDelays[0].length; i++) {
            count = 0;
            for (int j = 0; j < paymentDelays.length; j++) {
                if(paymentDelays[j][i] > 0){
                    count++;
                }
            }
            res[i] = count/paymentDelays.length;
        }
        
        return res;
    }
}
