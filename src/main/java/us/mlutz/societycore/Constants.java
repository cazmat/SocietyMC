package us.mlutz.societycore;

import us.mlutz.czlib.CZLib;

public class Constants {
    public static final String ModID = "societycore";
    public static final String AoxasDim = CZLib.getResourceLocation(ModID, "aoxas");
    public static final String LobbyDim = CZLib.getResourceLocation(ModID, "lobby");

    // Dimensional Information Strings.
    public static final String DimFound = "Found %s dimension.";
    public static final String skipDimDataFound = "Skipping datapack for %s dimension, as dimension already loaded.";
}
