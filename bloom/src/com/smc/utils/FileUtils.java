package com.smc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

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

  public static void writeMapToFile(Map<String, Set<String>> map,
      String filePath) throws IOException {

    File file = new File(filePath);
    JSONObject jsonObject = new JSONObject(map);
    FileOutputStream fos = new FileOutputStream(file);

    OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
    writer.append(jsonObject.toString());

    writer.flush();
    writer.close();

    fos.flush();
    fos.close();
  }
}
