package com.smc.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;

public class FileUtils {

  public static List<String> readFileIntoLines(String fileName)
      throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    List<String> lines = Lists.newArrayList();

    String eachLine;
    while ((eachLine = br.readLine()) != null) {
      lines.add(eachLine);
    }

    br.close();
    return lines;
  }

}
