package pt.ipbeja.po2.chartracer.gui;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MyAppTest {

    @Test
    public void teste1() {
        int nElements = 49;
        System.out.println("Ler um ficheiro de dados e verificar que está bem lido " +
                "(os dados lidos são os esperados)");

        MyApp obj = new MyApp();
        String[] atual = obj.readFileToStringArray("datafiles/cities.txt");

        String[] atualNLines = new String[nElements];
        for(int i = 0; i < nElements; i++) {
            atualNLines[i] = atual[i];
        }

        String[] expectedNLines = new String[nElements];
        try {
            File myFile = new File("datafiles/cities.txt");
            Scanner myReader = new Scanner(myFile);
            for (int k = 0; k < nElements; k++) {
                String line = myReader.nextLine();
                expectedNLines[k] = line;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        assertArrayEquals(expectedNLines, atualNLines);
    }

    @Test
    public void teste2() {
    }

    @Test
    public void teste3() {
    }

    @Test
    public void teste4() {
    }
}