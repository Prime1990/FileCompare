package de.prime.filecompare;

import java.io.*;
import java.util.ArrayList;

class FileCompare {

    boolean areFilesIdentical(String pathToFile1, String pathToFile2) {

        ArrayList<String> file1 = new ArrayList<>();
        ArrayList<String> file2 = new ArrayList<>();

        BufferedReader reader1, reader2;

        String line1, line2;

        try {
            reader1 = new BufferedReader(new FileReader(pathToFile1));
            reader2 = new BufferedReader(new FileReader(pathToFile2));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            while ((line1=reader1.readLine()) != null){
                file1.add(line1);
            }

            while ((line2=reader2.readLine()) != null){
                file2.add(line2);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                reader1.close();
                reader2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return compare(file1, file2);
    }

    private boolean compare(ArrayList<String> file1, ArrayList<String> file2) {
        boolean identical = true;

        if (file1.size() != file2.size()) {
            System.out.println("Die Dateien sind unterschiedlich lang!");
            return false;
        }

        for (int i = 0; i < file1.size(); i++) {
            if(! file1.get(i).equals(file2.get(i))) {
                identical = false;
                System.out.println("Unterschiede in der Zeile " + (i + 1) + ": ");
                System.out.println("    " + file1.get(i));
                System.out.println("    " + file2.get(i));
            }
        }

        return identical;
    }

}
