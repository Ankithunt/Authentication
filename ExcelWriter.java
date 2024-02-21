package com.Signify.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Get user details
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Open the existing workbook or create a new one if it doesn't exist
        File file = new File("C:\\Users\\670310873\\IdeaProjects\\TestProject\\UserDetails.xlsx");
        XSSFWorkbook workbook;
        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } else {
            workbook = new XSSFWorkbook();
        }

        // Get the first sheet or create a new one if it doesn't exist
        XSSFSheet sheet = workbook.getSheet("User Details");
        if (sheet == null) {
            sheet = workbook.createSheet("User Details");
            // Create header row if the sheet is new
            Row headerRow = sheet.createRow(0);
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("Username");
            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Password");
        }

        // Get the last row number or start from 1 if the sheet is new
        int lastRowNum = sheet.getLastRowNum();
        Row dataRow = sheet.createRow(lastRowNum + 1);
        Cell dataCell1 = dataRow.createCell(0);
        dataCell1.setCellValue(username);
        Cell dataCell2 = dataRow.createCell(1);
        dataCell2.setCellValue(password);

        // Write the workbook back to the file system
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

        System.out.println("User details written to UserDetails.xlsx successfully");
    }
}