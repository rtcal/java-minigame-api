package com.rtcal.area;

public final class MGLocation {

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
}
