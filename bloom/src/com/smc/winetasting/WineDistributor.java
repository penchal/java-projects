package com.smc.winetasting;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.smc.utils.BloomLogger;
import com.smc.utils.FileUtils;

public class WineDistributor {

  private Map<String, Set<String>> personToWinesPrefMap = Maps.newHashMap();
  private Map<String, Set<String>> wineToPersonsPrefMap = Maps.newHashMap();
  private String                   filePath;
  private Set<String>              users                = Sets.newHashSet();
  private Set<String>              wines                = Sets.newHashSet();
  private Map<String, Set<String>> personToWinesDistMap = Maps.newHashMap();
  private Map<String, Set<String>> wineToPersonsDistMap = Maps.newHashMap();

  public WineDistributor(String filePath) {
    this.filePath = filePath;
  }

  public void distribute() throws IOException {
    BloomLogger.log("Reading data from file...");
    readFileSetupData();

    BloomLogger.log("\n\nDeciding distribution...");
    decideDistribution();

    BloomLogger.log("\n\n\nSaving results to file...");
    persistResults();
  }

  private void persistResults() throws IOException {
    String personDistFile = "person-to-wines-distribution.txt";
    FileUtils.writeMapToFile(personToWinesDistMap, personDistFile);

    String wineDistFile = "wine-to-persons-distribution.txt";
    FileUtils.writeMapToFile(wineToPersonsDistMap, wineDistFile);

    BloomLogger.log("See '" + personDistFile + "' & '" + wineDistFile + "'");
  }

  private void decideDistribution() {
    for (String wineId : wines) {
      Set<String> interestedPersons = wineToPersonsPrefMap.get(wineId);

      // If none is interested
      if (interestedPersons.isEmpty()) {
        continue;
      }

      // If all interested people are already full
      for (String person : interestedPersons) {
        if (isQuotaExceeded(person)) {
          continue;
        }

        wineToPersonsDistMap.get(wineId).add(person);
        personToWinesDistMap.get(person).add(wineId);
      }
    }

    int numUnsoldWines = 0;
    for (Entry<String, Set<String>> entry : wineToPersonsDistMap.entrySet()) {
      if (entry.getValue().isEmpty()) {
        numUnsoldWines++;
      }
    }

    int numPersonsWithNoWines = 0;
    for (Entry<String, Set<String>> entry : personToWinesDistMap.entrySet()) {
      if (entry.getValue().isEmpty()) {
        numPersonsWithNoWines++;
      }
    }

    BloomLogger.log("Num unsold wines: " + numUnsoldWines);
    BloomLogger.log("Num people with no wines: " + numPersonsWithNoWines);
  }

  private boolean isQuotaExceeded(String personId) {
    return personToWinesDistMap.get(personId).size() >= 3;
  }

  public void readFileSetupData() throws IOException {
    List<String> lines = FileUtils.readFileIntoLines(filePath);
    for (String line : lines) {
      updateMaps(personToWinesPrefMap, wineToPersonsPrefMap,
          personToWinesDistMap, wineToPersonsDistMap, line);
    }

    updateUsersAndWines();
    decideDistribution();

    BloomLogger.log("File read. Num lines: " + lines.size());
    BloomLogger.log("Num unique users: " + users.size());
    BloomLogger.log("Num unique wines: " + wines.size());
  }

  public void updateUsersAndWines() {
    users = personToWinesPrefMap.keySet();
    wines = wineToPersonsPrefMap.keySet();
  }

  @VisibleForTesting
  protected void updateMaps(Map<String, Set<String>> personToWinesPrefsMap,
      Map<String, Set<String>> wineToPersonsPrefsMap,
      Map<String, Set<String>> personToWinesDistMap,
      Map<String, Set<String>> wineToPersonsDistMap, String prefLine) {
    Preference preference = getPreference(prefLine);

    if (preference == null) {
      return;
    }

    String personId = preference.getPersonId();
    String wineId = preference.getWineId();

    insertIfNotPresent(personToWinesPrefsMap, personId);
    insertIfNotPresent(wineToPersonsPrefsMap, wineId);

    insertIfNotPresent(personToWinesDistMap, personId);
    insertIfNotPresent(wineToPersonsDistMap, wineId);

    personToWinesPrefsMap.get(personId).add(wineId);
    wineToPersonsPrefsMap.get(wineId).add(personId);
  }

  public void insertIfNotPresent(Map<String, Set<String>> map, String key) {
    if (!map.containsKey(key)) {
      Set<String> value = Sets.newHashSet();
      map.put(key, value);
    }
  }

  @VisibleForTesting
  protected Preference getPreference(String preferenceLine) {
    Preference preference = null;
    try {
      preference = new Preference(preferenceLine);
    } catch (WineException e) {
      BloomLogger.log(Level.SEVERE, "Could not parse line: '" + preferenceLine
          + "'. Skipping it");
    }

    return preference;
  }

}
