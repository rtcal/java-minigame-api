package com.rtcal;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.game.MGArenaManager;
import com.rtcal.game.area.MGAreaSettings;
import com.rtcal.game.area.MGLocation;
import com.rtcal.game.area.MGProtectedArea;
import com.rtcal.game.area.settings.MGSettingsFlagBoolean;
import com.rtcal.game.map.MGMap;
import com.rtcal.implementations.arenas.MGTeamDeathmatchArena;

public class Main {
    public static void main(String[] args) {

        // Create an example set of settings for the MGProtectedArea
        MGAreaSettings exampleMapSettings = new MGAreaSettings();

        // Add a flag which can determine whether pvp is enabled or disabled
        exampleMapSettings.setFlag(new MGSettingsFlagBoolean("pvp", true));

        // An example map using the MGProtectedArea implementation which takes settings
        MGMap exampleMap = new MGMap(
                "example_map",
                new MGProtectedArea(
                        new MGLocation(0, 0, 0),
                        new MGLocation(100, 100, 100),
                        exampleMapSettings
                )
        );

        try {
            // Create a base instance of an arena which will register it
            MGTeamDeathmatchArena teamDeathmatchArena = new MGTeamDeathmatchArena(exampleMap);

            System.out.println("Initial Instance: " + teamDeathmatchArena);

            for (int i = 0; i < 5; i++) {
                //Generate an offset from the initial map
                MGLocation offset = new MGLocation(i * 50, i, i * 100);

                // Create a new instance of the teamDeathmatchArena with an offset
                MGTeamDeathmatchArena instance = (MGTeamDeathmatchArena) MGArenaManager.getInstance().createNewInstanceOfArena(teamDeathmatchArena.getName(), offset);

                assert instance != null;
                System.out.println("Instance[" + i + "]: " + instance);
            }

        } catch (MGDuplicateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}