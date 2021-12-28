
/**
 * Generator of the byte file
 * 
 * @author samat
 * @version 08/07/2018
 */
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteFileGenerator {
    public ByteFileGenerator() {
    }


    /**
     * Generates the byte file
     * 
     * @param numRecords
     *            number of records you want your byte file to have
     * @throws IOException
     */
    public void generate(int numRecords) throws IOException {

        File file = new File("generated.dat");
        DataOutputStream outs = new DataOutputStream(new FileOutputStream(file,
            false));
        for (int j = 0; j < numRecords; j++) {
            short key = (short)(Math.random() * Short.MAX_VALUE);
            short val = (short)(Math.random() * Short.MAX_VALUE);
            byte[] testB = new byte[4];
            for (int i = 0; i < 2; i++) {
                testB[i] = (byte)(key >> (8 - 8 * i));
                testB[i + 2] = (byte)(val >> (8 - 8 * i));
            }

            outs.write(testB);
        }
        outs.close();
    }
}
