package de.xxschrandxx.wsc.wscauthenticator.core.api;

import java.util.Date;

public class SessionData {
    private final Date start;
    private final Date end;
    private final String address;
    public SessionData(String address, Long length) {
        this.start = new Date();
        this.address = address;
        this.end = new Date(this.start.getTime() + length);
    }
    public SessionData(String address, Date start, Long length) {
        this.start = start;
        this.address = address;
        this.end = new Date(this.start.getTime() + length);
    }
    public Date getStart() {
        return this.start;
    }
    public Date getEnd() {
        return this.end;
    }
    public String getAddress() {
        return this.address;
    }
}
