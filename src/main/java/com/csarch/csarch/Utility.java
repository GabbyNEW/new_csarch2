package com.csarch.csarch;


import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class Utility
{
    public static String hexToBin(String hex){
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    public static void writeToFile(String output) throws IOException {
        FileWriter myWriter = new FileWriter("output.txt");
        myWriter.write(output);
        myWriter.close();
    }

    public static boolean checkIfStringIsBinary(String string) {
        if (string.matches("^[01.]+$"))
            // accept this input
            return true;
        else return false;
    }

    public static int getNumberofDigits(int num) {
        return String.valueOf(num).length();
    }

    public static int getQ0Qminus1(String q, int q_minus1) {
        int n = q.length();
        char qLsb = q.charAt(n - 1);

        String returnValue = "";
        returnValue = returnValue.concat(String.valueOf(qLsb));
        returnValue = returnValue.concat(String.valueOf(q_minus1));

        return Integer.parseInt(returnValue);
    }

    public static String binaryArithmeticAdd(String a, String m) {
        int answer = Integer.parseInt(a, 2) + Integer.parseInt(m, 2);
        String binaryAnswer = Integer.toBinaryString(answer);
        int n = DataClass.numberOfDigits;
        return binaryAnswer.substring(binaryAnswer.length() - n);
    }

    public static String binaryArithmeticSubtract(String a, String m_negative) {
        int answer = Integer.parseInt(a, 2) + Integer.parseInt(m_negative, 2);
        String binaryAnswer = Integer.toBinaryString(answer);

        return binaryAnswer;
    }

    public static void arithmeticShiftRight(SimulationController simulationController) {
        // Get lsb of Q
        int n = String.valueOf(simulationController.q).length();
        char qLsbChar = simulationController.q.charAt(n - 1);
        int qLsb = Integer.parseInt(String.valueOf(qLsbChar));
        // Get lsb of A
        n = String.valueOf(simulationController.a).length();
        char aLsbChar = simulationController.a.charAt(n - 1);
        int aLsb = Integer.parseInt(String.valueOf(aLsbChar));

        // Shift A to the right
        String shiftedA = simulationController.a.substring(0, 1) + simulationController.a.substring(0, simulationController.a.length() - 1);
        simulationController.a = shiftedA;
        // Shift Q to the right
        String shiftedQ = String.valueOf(aLsb) + simulationController.q.substring(0, simulationController.q.length() - 1);
        simulationController.q = shiftedQ;
        // Update Q-1 based on previous lsb of Q
        simulationController.q_negative = qLsb;
    }

    // Function to convert decimal to binary upto
    // k-precision after decimal point
    public static String decimalToBinary(String num)
    {
        int num_int = Integer.parseInt(num);
        if (num_int < 0) // negative
            return Integer.toBinaryString(num_int).substring(0, 17);


        String binaryString = Integer.toBinaryString(num_int);

        // Comment out
        /*if (binaryString.length() < 4)
            binaryString = String.format("%04d", Integer.parseInt(binaryString));*/

        StringBuilder sb = new StringBuilder(binaryString);

        if(num_int >= 0) {
            sb.insert(0, "0");
            binaryString = new String(sb.toString());

        }

        return binaryString;
    }

    private static String reverse(String input)
    {
        char[] temparray = input.toCharArray();
        int left, right = 0;
        right = temparray.length - 1;

        for (left = 0; left < right; left++, right--)
        {
            // Swap values of left and right
            char temp = temparray[left];
            temparray[left] = temparray[right];
            temparray[right] = temp;
        }
        return String.valueOf(temparray);
    }

    // Method to find two's complement
    public static String findTwoscomplement(String str_in)
    {
        StringBuffer str = new StringBuffer();
        str.append(str_in);

        int n = str.length();

        // Traverse the string to get first '1' from
        // the last of string
        int i;
        for (i = n-1 ; i >= 0 ; i--)
            if (str.charAt(i) == '1')
                break;

        // If there exists no '1' concat 1 at the
        // starting of string
        if (i == -1)
            return "1" + str;

        // Continue traversal after the position of
        // first '1'
        for (int k = i-1 ; k >= 0; k--)
        {
            //Just flip the values
            if (str.charAt(k) == '1')
                str.replace(k, k+1, "0");
            else
                str.replace(k, k+1, "1");
        }

        // return the modified string
        return str.toString();
    }

    public static String checkNumBitsQShort(String m, String q) {

        StringBuilder sb;

        if(m.length() > q.length()) {

            sb = new StringBuilder(q);

            // concat MSb number of times
            while(sb.length() < m.length()) {

                // insert based on negative or not
                if(Integer.parseInt(q) >= 0)
                    sb.insert(0, 0);
                else
                    sb.insert(0, 1);
            }

            q = new String(sb.toString());
        }

        return q;
    }

    public static String checkNumBitsMShort(String m, String q) {

        StringBuilder sb;

        if(q.length() > m.length()) {

            sb = new StringBuilder(m);

            // concat MSb number of times
            while(sb.length() < q.length()) {

                // insert based on negative or not
                if(Integer.parseInt(m) >= 0)
                    sb.insert(0, 0);
                else
                    sb.insert(0, 1);
            }

            m = new String(sb.toString());
        }

        return m;
    }
}
