package ru.otus.hw03;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class FileRW {

    private final String fName;
    FileRW(String fName){
        this.fName=fName;
        System.out.println("FileRW object created: " + fName);
    }

    public void read () throws IOException {
        FileReader reader = new FileReader(fName);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        reader.close();
    }

    public void write(List<Integer> numbers) throws IOException {
        FileWriter writer = new FileWriter(fName);
        for(int number:numbers)writer.write(number+"\n");
        writer.close();
    }


    public void dispose() {
        System.out.println("Disposed");
    }
}
