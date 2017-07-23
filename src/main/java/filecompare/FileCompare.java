package filecompare;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileCompare {

    private final static Logger log = LogManager.getLogger(FileCompare.class);

    public boolean areFilesIdentical(String pathToFile1, String pathToFile2) {

        log.info("Comparing files '" + pathToFile1 + "' and '" + pathToFile2 + "'");

        ArrayList<String> file1 = new ArrayList<>();
        ArrayList<String> file2 = new ArrayList<>();

        BufferedReader reader1 = null;
        BufferedReader reader2 = null;

        String line1, line2;

        try {
            reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile1), "ISO-8859-15"));
            reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile2), "ISO-8859-15"));

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
                if (reader1 != null) {
                    reader1.close();
                }
                if( reader2 != null) {
                    reader2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return compare(file1, file2);
    }

    private boolean compare(ArrayList<String> file1, ArrayList<String> file2) {
        boolean identical = true;

        if (file1.size() != file2.size()) {
            log.error("Die Dateien sind unterschiedlich lang!");
            return false;
        }

        for (int i = 0; i < file1.size(); i++) {

            String file1String = file1.get(i);
            String file2String = file2.get(i);

            byte[] s1 = file1String.getBytes(StandardCharsets.ISO_8859_1);
            byte[] s2 = file2String.getBytes(StandardCharsets.ISO_8859_1);

            if(s1.length != s2.length) {

                String differentChar = "Die Zeilen sind unterschiedlich lang!";
                String text = "Unterschiede in der Zeile " + (i + 1) + ": " + differentChar;
                log.error(text);

                return false;

            }

            for (int j = 0; j < s1.length; j++) {
                if (s1[j] != s2[j]) {
                    identical = false;

                    String differentChar = "Das " + (j + 1) + ". Zeichen ist unterschiedlich: '" + String.format("0x%02X", s1[j]) + "' <-> '" + String.format("0x%02X", s2[j]) + "'";
                    String text = "Unterschiede in der Zeile " + (i + 1) + ": " + differentChar;
                    log.error(text);

                    break;
                }
            }
        }

        return identical;
    }

}

