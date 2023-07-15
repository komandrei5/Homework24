package app.logger;

public enum Level {
    INFO(true, false),
    DEBUG(true, true);

    private boolean includeInfo;
    private boolean isIncludeDebug;

    Level(boolean includeInfo, boolean isIncludeDebug) {
        this.includeInfo = includeInfo;
        this.isIncludeDebug = isIncludeDebug;
    }

    public boolean includes(Level level) {
        if (this == level) {
            return true;
        }
        if (this == DEBUG) {
            return true;
        }
        if(this == INFO && level != DEBUG) {
            return true;
        }
        return false;
    }
}
