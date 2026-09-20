/** MIT License.
  Copyright (C) QMX Corporation (all authors, maintainers and collaborators),
  Licensed and Released under MIT License. */
package TerminalPrintOfView.bootstrap;

/** Libs importeds for specific actions */
import java.io.File;
/** For print */
import TerminalPrintOfView.mTermSession;
/** For IOExceptiond */
import java.io.IOException;

/** Class for variables */
class Bootstrapvars {
  /** For success */
  static final int ASKAS_SUCCESS = 0;
  /** For error */
  static final int ASKAS_FAILED = 1;
  /** The path of dependencies */
  static final String path_deps = System.getProperty("user.home") + "/.askas/deps";
  /** Remote CDN URL for dependencies */
  static final String remote_cdn = "https://qmx-corporation.github.io/Askas-Dependencies/";
}

/** MainClass of Bootstrap */
public class BootstrapEngine {
  /** The Main Function for boot */
  public static int boot() {
    /** Print of initialization: System */
    mTermSession.write("Preparing the init.sh/vla.r");
    /** 1. Verify if local path (~/.askas/deps) exists */
    File depsDir = new
    File(Bootstrapvars.path_deps);
    if (!depsDir.exists()) {
      depsDir.mkdirs();
    }
    /** 2. Download this dependencies */
    try {
      DependencyFetcher.fetchFile(
        Bootstrapvars.remote_cdn +
        "deps.manifest"
      );
    } catch (IOException e) {
      mTermSession.write("| === ERROR === |\n");
      mTermSession.write("Failed in download.\n");
      mTermSession.write("Code Error: 0x120000000B.");
      return Bootstrapvars.ASKAS_FAILED;
    }
    /** 3. Validation: Files .jar valids? */
    try {
      String manifestPath = Bootstrapvars.path_deps + "/deps.manifest";
      boolean isValid = IntegrityChecker.checkIntegrity(manifestPath, "expected_hash_here");
    } catch (Exception e) {
      mTermSession.write("Integrity check failed.\n");
      return Bootstrapvars.ASKAS_FAILED;
    }
    /** 4. Pass a info if: Boot -> Success (return ASKAS_SUCCESS) or Error (return ASKAS_FAILED) */
    return Bootstrapvars.ASKAS_SUCCESS;
  }
}