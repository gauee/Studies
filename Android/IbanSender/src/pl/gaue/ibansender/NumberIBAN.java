package pl.gaue.ibansender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NumberIBAN {
	 private String pathToFileWithData;
	    private static final String CORRECT_ANSWER = "POPRAWNY";
	    private static final String WRONG_ANSWER = "BLEDNY";
	    private static final String POLAND_PREFIX = "PL";
	    private static final String POLAND_DECODE_PREFIX_VALUE = "2521";
	    private static final String OUTPUT_FILE_NAME = "out.txt";
	    private static final BigInteger MOD_BASE_VALUE = new BigInteger("97");
	    private static final int PL_IBAN_LEN = 28;
	    private OutputStream fileOut;

	    NumberIBAN(String pathToFile) {
	        this.pathToFileWithData = pathToFile;
	    }

	    public void verify() throws IOException {
	        initialiazeOutput();
	        Scanner scanner = new Scanner(new File(pathToFileWithData));
	        while (scanner.hasNext()) {
	            String line = scanner.nextLine().replaceAll("\\s+", "");
	            if (!line.isEmpty()) {
	                boolean correct = checkCountryAndCheckSumOfIBAN(line);
	                if (correct) {
	                    saveCorrectResult(line);
	                } else {
	                    saveWrongResult(line);
	                }
	            }
	        }
	        closeOutput();
	    }

	    private void closeOutput() {
			// TODO Auto-generated method stub
			
		}

		public static boolean verifyIban(String iban) {
	    	return checkCountryAndCheckSumOfIBAN(iban);
		}
	    
	    private static boolean checkCountryAndCheckSumOfIBAN(String ibanNumber) {
	        if (POLAND_PREFIX.equals(ibanNumber.substring(0, 2))) {
	            if (ibanNumber.length() == PL_IBAN_LEN) {
	                BigInteger number = new BigInteger(ibanNumber.substring(4) + POLAND_DECODE_PREFIX_VALUE + ibanNumber.substring(2, 4));
	                if (number.remainder(MOD_BASE_VALUE).intValue() == 1) {
	                    return true;
	                }
	                return false;
	            }
	            return false;
	        }
	        return false;
	    }

	    private void initialiazeOutput() throws FileNotFoundException {
	        String outputDir = new File(pathToFileWithData).getParentFile().getAbsolutePath();
	        fileOut = new FileOutputStream(outputDir + File.separator + OUTPUT_FILE_NAME);
	    }

	    private void saveCorrectResult(String line) throws IOException {
	        StringBuilder sb = new StringBuilder();
	        sb.append(line).
	                append(" ").
	                append(CORRECT_ANSWER).
	                append("\n");
	        System.out.println(sb.toString());
	        fileOut.write(sb.toString().getBytes());
	    }

	    private void saveWrongResult(String line) throws IOException {
	        StringBuilder sb = new StringBuilder();
	        sb.append(line).
	                append(" ").
	                append(WRONG_ANSWER).
	                append("\n");
	        System.out.println(sb.toString());
	        fileOut.write(sb.toString().getBytes());
	    }
	    
	    
}
