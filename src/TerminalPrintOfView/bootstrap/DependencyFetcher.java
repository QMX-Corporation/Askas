/** MIT License.
  Copyright (C) QMX Corporation (all authors, maintainers and collaborators),
  Licensed and Released under MIT License. */
package TerminalPrintOfView.bootstrap;

import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Path;

public class DependencyFetcher {
    public static void fetchFile(String url) 
    throws IOException {
      // 1. Open this requisition HTTP and save in a Variable
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      // 2. Define: Requisition Type is download
      connection.setRequestMethod("GET");
      // 3. Open the entry flow for read the remote file
      InputStream stream = connection.getInputStream();
      // 4. Download ALL DEPENDENCIES
      // Extraction of the Name File
      String fileName = url.substring(url.lastIndexOf('/') + 1);
      // Mouin the local Path
      Path targetPath = Paths.get(TerminalPrintOfView.bootstrap.
               Bootstrapvars.path_deps, fileName);
      // Save-In (Protocol SI of Terminal) disk
      Files.copy(stream, 
                targetPath,
                StandardCopyOption.REPLACE_EXISTING);
      // 5. Close the flow
      stream.close();
      connection.disconnect();
    }
}