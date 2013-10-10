package com.smc.winetasting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.smc.utils.BloomLogger;
import com.smc.utils.FileUtils;

public class WineMatcher {

  private Map<String, String> personIdToWineIdPrefMap = Maps.newHashMap();
  private String              filePath;

  public WineMatcher(String filePath) {
    this.filePath = filePath;
  }

  public void performMapping() throws IOException {
    BloomLogger.log("Mapping started");

    List<String> lines = FileUtils.readFileIntoLines(filePath);
    StringBuilder sb = new StringBuilder();
    for (String line : lines) {
      sb.append(line);
    }

    BloomLogger.log("File read: \n" + sb.toString());
  }

}
