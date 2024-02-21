package com.Signify.auth;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserAuthenticator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the user details from the Excel sheet
        String excelFilePath = "C:\\Users\\670310873\\IdeaProjects\\TestProject\\UserDetails.xlsx";
        String username = null;
        String password = null;

        try {
            Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
            Sheet sheet = workbook.getSheetAt(0);
            boolean accessGranted = false;
            System.out.print("Enter your username: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Enter your password: ");
            String enteredPassword = scanner.nextLine();
            for (Row row : sheet) {
                username = row.getCell(0).getStringCellValue();
                password = row.getCell(1).getStringCellValue();
                // Compare the entered username and password with the details from the Excel sheet

                if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
                    System.out.println("Access granted!");
                    accessGranted = true;
                    break;
                }
            }
            if (!accessGranted) {
                System.out.println("Access denied!");
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
