/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.berwyn.test;

/**
 *
 * @author dell
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\test.csv"));
        String line;
        int lineNumber = 0;
        int sum = 0;
        int val3Length = 0;
        ArrayList<String> guidList = new ArrayList<>();
        ArrayList<String> duplicateGuid = new ArrayList<>();
        ArrayList<String> record = new ArrayList<>();
        br.readLine();
        if ((line = br.readLine()) != null) {
            lineNumber++;
            String[] cols = line.split(",");
            guidList.add(cols[0]);
            sum = Integer.parseInt(cols[1].replace(" \"", "").replace("\"", "")) + Integer.parseInt(cols[2].replace(" \"", "").replace("\"", ""));
        }
        while ((line = br.readLine()) != null) {

            lineNumber++;
            String[] cols = line.split(",");
            if (sum < Integer.parseInt(cols[1].replace(" \"", "").replace("\"", "")) + Integer.parseInt(cols[2].replace(" \"", "").replace("\"", ""))) {
                sum = Integer.parseInt(cols[1].replace(" \"", "").replace("\"", "")) + Integer.parseInt(cols[2].replace(" \"", "").replace("\"", ""));
                if (!record.isEmpty()) {
                    record.clear();
                    record.add(cols[0] + "," + cols[1] + "," + cols[2]);
                } else {
                    record.add(cols[0] + "," + cols[1] + "," + cols[2]);
                }
            }
            if (guidList.contains(cols[0].replace(" \"", "").replace("\"", ""))) {

                duplicateGuid.add(cols[0]);
            }
            guidList.add(cols[0].replace(" \"", "").replace("\"", ""));
            val3Length += cols[3].replace(" \"", "").replace("\"", "").length();
        }
        int avgval3 = val3Length / lineNumber;
        System.out.println(lineNumber);
        System.out.println(record);
        System.out.println(duplicateGuid);
        System.out.println(avgval3);
        br.close();

        br = new BufferedReader(new FileReader("C:\\test.csv"));
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("example.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        String columnNamesList = "GUID,VAL1+VAL2,IsDuplicateGuid (Y or N),VAL3 GT AVGVAL3";
        builder.append(columnNamesList + "\n");
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] cols = line.split(",");

            builder.append(cols[0] + ",");
            builder.append(Integer.parseInt(cols[1].replace(" \"", "").replace("\"", "")) + Integer.parseInt(cols[2].replace(" \"", "").replace("\"", "")) + ",");
            builder.append((duplicateGuid.contains(cols[0]) ? "Y" : "N") + ",");
            builder.append(avgval3 > cols[3].replace(" \"", "").replace("\"", "").length() ? "N" : "Y");
            builder.append('\n');

        }
        pw.write(builder.toString());
        pw.close();

    }

}
