package com.rtcal.area;

public final class MGLocation {

    public static final MGLocation ZERO = new MGLocation(0, 0, 0);

    private final int x, y, z;

    public MGLocation(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MGLocation(final int x, final int y) {
        this(x, y, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public MGLocation add(MGLocation other) {
        return new MGLocation(
                getX() + other.getX(),
                getY() + other.getY(),
                getZ() + other.getZ()
        );
    }

    @Override
    public String toString() {
        return "MGLocation{x=" + x + ",y=" + y + ",z=" + z + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        final MGLocation other = (MGLocation) o;

        if (other.getX() != getX()) return false;
        if (other.getY() != getY()) return false;
        return other.getZ() == getZ();
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + getX();
        hash = 31 * hash + getY();
        hash = 31 * hash + getZ();
        return hash;
    }

    public static MGLocation getMinimumLocation(MGLocation loc1, MGLocation loc2) {
        return new MGLocation(
                Math.min(loc1.getX(), loc2.getX()),
                Math.min(loc1.getY(), loc2.getY()),
                Math.min(loc1.getZ(), loc2.getZ())
        );
    }

    public static MGLocation getMaximumLocation(MGLocation loc1, MGLocation loc2) {
        return new MGLocation(
                Math.max(loc1.getX(), loc2.getX()),
                Math.max(loc1.getY(), loc2.getY()),
                Math.max(loc1.getZ(), loc2.getZ())
        );
    }

    /**
     * Convert coordinates of location to be positive integers
     *
     * @param location location to make positive
     * @return a MGLocation with positive coordinates
     */
    public static MGLocation abs(MGLocation location) {
        return new MGLocation(
                Math.abs(location.getX()),
                Math.abs(location.getY()),
                Math.abs(location.getZ())
        );
    }

    /**
     * Check whether a location is inside an area
     *
     * @param loc1  the first location
     * @param loc2  the second location
     * @param check the location to check if it is inside the area created by loc1 and loc2
     * @return whether the location is within the area created by loc1 and loc2
     */
    public static boolean isInside(MGLocation loc1, MGLocation loc2, MGLocation check) {
        if (loc1 == null || loc2 == null || check == null) return false;

        MGLocation min = MGLocation.getMinimumLocation(loc1, loc2);
        MGLocation max = MGLocation.getMaximumLocation(loc1, loc2);

        int x = max.getX() - min.getX();
        int y = max.getY() - min.getY();
        int z = max.getZ() - min.getZ();

        return x >= 0 && x <= check.getX() && y >= 0 && y <= check.getY() && z >= 0 && z <= check.getZ();
    }
}
