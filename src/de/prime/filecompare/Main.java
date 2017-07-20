package de.prime.filecompare;

public class Main {

    public static void main(String[] args) {

        FileCompare fc = new FileCompare();

        if(fc.areFilesIdentical("/Users/Prime/Documents/compareText/file1.txt", "/Users/Prime/Documents/compareText/file2.txt")) {
            System.out.println("Files are identical");
        } else {
            System.out.println("Files are different. See log for further information.");
        }

        if(fc.areFilesIdentical("/Users/Prime/Documents/compareText/file1.txt", "/Users/Prime/Documents/compareText/file3.txt")) {
            System.out.println("Files are identical");
        } else {
            System.out.println("Files are different. See log for further information.");
        }

        if(fc.areFilesIdentical("/Users/Prime/Documents/compareText/file2.txt", "/Users/Prime/Documents/compareText/file3.txt")) {
            System.out.println("Files are identical");
        } else {
            System.out.println("Files are different. See log for further information.");
        }

    }
}
