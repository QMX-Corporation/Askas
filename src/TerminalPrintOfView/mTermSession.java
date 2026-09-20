/** 
  MIT License.
  Copyright (C) QMX Corporation (all authors, maintainers and collaborators),
  Licensed and Released under MIT License. */
package TerminalPrintOfView;

/** The Class of the main Session */
public class mTermSession {
  /** The mTermSession() for print in Session, NO Modes. */
  public static void write(String str) {
    if (mTermVar.MainMode == 0 && mTermVar.Session_Princ.equals("MainSession: MainMode")) {
      System.out.print(str);
    } 
  }
}