package com.smc.winetasting;

public class Preference {

  private String personId;
  private String wineId;

  public Preference(String preferenceLine) throws WineException {
    String[] tokens = preferenceLine.split("\\s+");
    if (tokens.length < 1) {
      throw new WineException(ErrorCodes.COULD_NOT_PARSE_GIVEN_PREFERENCE);
    }

    personId = tokens[0].trim();
    wineId = tokens[1].trim();
  }

  public String getPersonId() {
    return personId;
  }

  public String getWineId() {
    return wineId;
  }

}
