import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import filecompare.FileCompare;

public class Main {
    private final static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        FileCompare fc = new FileCompare();

        if(fc.areFilesIdentical("C:/Temp/db54_f333_(141)_output", "C:/Temp/f141_pc_20170629")) {
            log.info("File are identical.");
        }

    }

}
