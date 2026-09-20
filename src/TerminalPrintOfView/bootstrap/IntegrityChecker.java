/**MIT License.
  Copyright (C) QMX Corporation (all authors, maintainers and collaborators),
  Licensed and Released under MIT License. */
package TerminalPrintOfView.bootstrap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class IntegrityChecker {
  public static boolean checkIntegrity(String filePath, String expectedHash) 
  throws NoSuchAlgorithmException, IOException {
    // 1. Create a instance of digest
    MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
    // 2. Read the bytes of File 
    byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
    // 3. Generation of Hash 
    byte[] hashBytes = msgDigest.digest(fileBytes);
    // 4. Convert: Bytes for Hexadecimal
    StringBuilder hexString = new StringBuilder();
    for (byte b : hashBytes) {
      hexString.append(String.format("%02x", b));
    }
    // 5. Comparation
    return hexString.toString().equalsIgnoreCase(
      expectedHash
    );
  }
}