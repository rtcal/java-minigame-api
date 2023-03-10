package com.rtcal.arena;

public class MGArena {

    private final String type;

    public MGArena(String type) {
        this.type = type;

        MGArenaType.getInstance().register(this.type);
    }

    public String getType() {
        return type;
    }

}
