package com.rtcal.area;

import com.rtcal.area.exceptions.MGDuplicateIDException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MGAreaManager {

    private static final Map<String, MGArea> areas = new HashMap<>();

    public synchronized void registerArea(MGArea area) {
        areas.put(area.getName(), area);
        areas.put(area.getID().toString(), area);
    }

    public synchronized void unregisterArea(MGArea area) {
        areas.remove(area.getName());
        areas.remove(area.getID().toString());

        if (area.hasChildAreas()) {
            for (MGArea childArea : area.getChildAreas()) {
                unregisterArea(childArea);
            }
        }
    }

    public void areaNameCheck(@NotNull String name) throws MGDuplicateIDException {
        if (areas.containsKey(name))
            throw new MGDuplicateIDException("Area with name='" + name + "' already exists");
    }

    public void areaIDCheck(@NotNull UUID uuid) throws MGDuplicateIDException {
        if (areas.containsKey(uuid.toString()))
            throw new MGDuplicateIDException("Area with uuid='" + uuid + "' already exists");
    }

}
