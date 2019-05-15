package ru.otus.hw03;

public class FileRW {
    private String fName;
    FileRW(String fName){
        this.fName=fName;
        System.out.println("FileRW object created: " + fName);
    }

    void dispose() {
        System.out.println("Disposed");
    }
}
