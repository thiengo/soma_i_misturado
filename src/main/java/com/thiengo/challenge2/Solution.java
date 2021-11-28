/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thiengo.challenge2;

import com.thiengo.challenge2.strategies.fileloader.FileLoaderConcreteStrategyFromResourceImpl;
import com.thiengo.challenge2.strategies.fileloader.FileLoaderStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author tiago
 */
public class Solution {

    //Matrixes parameters
    private final int i_order = 3;
    private final int s_order = 6;
    //File Load Strategy
    private final FileLoaderStrategy fileLoader
            = new FileLoaderConcreteStrategyFromResourceImpl();
    //Buffers
    private final List<List<Long>> bufferLongs = new LinkedList<>();
    private Long total = 0L;
    //Biggest I found
    private List<List<Long>> biggestI = new LinkedList<>();
    //Sum of elements from biggest I
    private Long lastTotal = 0L;

    public void solution() throws IOException {
        //My concrete strategy for fiole loader reads file from resource folder
        //The file name is input
        try (InputStreamReader isr = new InputStreamReader(fileLoader.getFileAsInputStream("input")
                .orElseThrow(()
                        -> new IllegalArgumentException("File is not found")));
                BufferedReader br = new BufferedReader(isr);) {

            String line;
            while ((line = br.readLine()) != null) {
                //We need to keep a number of lines according to matriz order
                bufferLongs(line);

                //When buffering is complete, analyze the Is and store the largest I matrix found
                if (bufferLongs.size() == i_order) {
                    parseI();
                }
            }

            //Print biggest sum found
            System.out.println(lastTotal);
            
//            biggestI.forEach(l -> {
//                System.out.println(l);
//            });
//            System.out.println("");
        }
    }

    private void bufferLongs(String line) {
        if (bufferLongs.size() < i_order) {
            //Here, we put lines in the buffer until they are almost completely filled.
            //We will need to add one more line to fill in completely
            bufferLongs.add(converter(line));
        } else {
            //When a buffer is completed we ca
            bufferLongs.remove(0);
            bufferLongs.add(converter(line));
        }
    }

    private List<Long> converter(String line) {
        return Stream.of(line.split(" "))
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    private void parseI() {
        int retrieved = 0; //Quantidade de Is encontrados no buffer
        int sumI = 0; //soma do I encontrado
        //Verificar se posso recuperar matrizes quadradas da ordem determinada para I
        while (i_order <= (s_order - retrieved)) {
            List<List<Long>> tempI = new LinkedList<>();
            for (List<Long> l : bufferLongs) {
                List<Long> tempL = new LinkedList<>();
                for (int c = retrieved; c < (i_order + retrieved); c++) {
                    Long e = l.get(c);
                    total = total + e;
                    tempL.add(e);
                }
                tempI.add(tempL);
            }
            retrieved++;

            //verificar e guardar a maior soma
            if (lastTotal < total) {
                lastTotal = total;
                biggestI = tempI;
            }
            total = 0L;

//            tempI.forEach(l -> {
//                System.out.println(l);
//            });
//            System.out.println("");
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Starting Challeng 2...");

        Solution solution = new Solution();
        solution.solution();

    }

}
