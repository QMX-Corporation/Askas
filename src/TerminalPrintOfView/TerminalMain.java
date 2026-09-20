package TerminalPrintOfView;
import java.util.Scanner;
import java.io.*;

/** Variables:
  * Of The Principal Session 
  * of Terminal in a dedicated class of
  * Variables. */
class mTermVar {
  static int MainMode = 0; /** 0? Executing in the 
                           * Principal Session. */
  static String Session_Princ = "MainSession: MainMode";

  static boolean isRunning = true;

  static boolean HelpMode = false;
}

/** The Class of the
  main Session */
public class mTermSession {
  /** The mTermSession() for print in Session,
    * NO Modes. */
  public static void write(String str) {
    // Is executing in the Principal Session?
    if (mTermVar.MainMode == 0 && mTermVar.Session_Princ.equals("MainSession: MainMode")) {
      System.out.print(str);
    } 
  }
}

/** The Class:
  * Functions,
  * used by the main Function */
class TermSession {
  /** The ResetTheSession() for the Reset 
    * in cases of errors. */
  public static void ResetTheSession() {
    // Entering the Reset Mode
    mTermVar.MainMode = 0x32;
    mTermVar.Session_Princ = "Reset Vector";
    // Notification: Warning of initialization of Reset
    System.out.println("Initializing Reset...");
    // Clear the Screen and Buffer
    System.out.println("\033[c\033[3J\033[H\033[2J");
    System.out.flush();
    // --- Try/Catch: Clear the chars in the Buffer ---
    // Clear the Buffer
    try {
      while (System.in.available() > 0) {
        System.in.read();
      }
    }
    // Handling the Exception
    catch (IOException e) {
      /** In the Version 0.00.1, 
        * a Sample Print is necessary. */
      System.out.println("Exception: caught!");
    }
    // Warning of exit: Reset Mode
    System.out.println("Exiting the Reset Mode...");
    /** Handling: If other thread
      * modified the Variable MainMode? */
    if (mTermVar.MainMode != 0x32) {
      System.out.println("Critical State Error during Reset");
      return;
    }
    // Exit of Reset Mode
    mTermVar.MainMode = 0;
    mTermVar.Session_Princ = "MainSession: MainMode";
  }
} 

/** The Class:
   Read Commands. */
class TermInput {
  static Scanner input = new Scanner(System.in);
  public static String readCommand() {
    return input.nextLine().trim();
  }
}

/** The Class:
   Commands! */
class Commands {
  // The License Command
  public static void LicenseCommand() {
    mTermSession.write("Copyright (C)\n");
    mTermSession.write("Licensed and Released under MIT License.\n");
  }
  // The Exit Command
  public static void ExitCommand() {
    mTermVar.isRunning = false;
  }
  // The Help Command
  public static void HelpCommand() {
    mTermSession.write("| ----------- COMMANDS ------------ |\n");
    mTermSession.write("| license -- Show the License. |\n");
    mTermSession.write("| exit -- Shutdown the Askas. |\n");
    mTermSession.write("| help -- Displays the commands.\n");
    mTermSession.write("| ----------- COMMANDS ------------ |\n");
    mTermVar.HelpMode = true;
  }
  // The ExitHelp Command
  public static void ExitHelpCommand() {
    // Exit: Help Mode
    mTermVar.HelpMode = false;
  }
}

/** The Class:
  Commands Interpreter */
class TermExec {
  // The Interpreter
  public static void commandExec(String cmd) {
    if (cmd.equals("license")) { // Command
       // Executes the LicenseCommand()
       Commands.LicenseCommand();
     } else if (cmd.equals("exit")) {
      // Executes the ExitCommand()
      Commands.ExitCommand();
     } else if (cmd.equals("help")) {
      Commands.HelpCommand();
     } else if (cmd.equals("exhelp")) {
      Commands.ExitHelpCommand();
     } else {
       // Not commands.
       mTermSession.write("Command not found.\n");
    }
  }
}

/** The MainClass */
public class TerminalMain {
  /** Method:
     print the Prompt! */
  public static void PrintPrompt() {
    mTermSession.write("askas>_ $?  ");
    // Handling the "Not executing in the Main Session?"
    if (mTermVar.MainMode != 0) {
      /** no? Print the Code Error, and 
                * Reset the Session. */
      System.out.println("ERROR: \n");
      System.out.println("Code Error: 0x00000000B");
      TermSession.ResetTheSession();
    }
  }
  /** Method:
    print the Help Prompt */
  public static void HelpPrompt() {
    mTermSession.write("askas_help> $?  ");
    // Handling the "Not executing in the Main Session?"
    if (mTermVar.MainMode != 0) {
      /** no? Print the Code Error, and 
                * Reset the Session. */
      System.out.println("ERROR: \n");
      System.out.println("Code Error: 0x00000000B");
      TermSession.ResetTheSession();
    }
  }
  /** Main Method! */
  public static void mAskas() {
    // Entry in the MainLoop
     while (mTermVar.isRunning) {
       // Print the Prompt
       PrintPrompt();
       // Executing the Commands
       String command = TermInput.readCommand();
       TermExec.commandExec(command);
       // HelpMode is active? 
       while (mTermVar.HelpMode) {
         HelpPrompt();
         String helpCmd = TermInput.readCommand();
         TermExec.commandExec(helpCmd);
       }
     }
   }
   /** The main() for 
     Rules Java */
   public static void main(String[] args) {
     /** Executes the boot() of BootstrapEngine.java */
     int bootStatus = TerminalPrintOfView.bootstrap.BootstrapEngine.boot();
     /** Boot the Terminal if bootStatus == 0 */
     if (bootStatus == 0) {
       mAskas();
     }
   }
}

/** Notes:
   Name of Terminal: Askas. 
   License: MIT.*/