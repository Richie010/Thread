package Controller.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionDto {
    private String ipAddress;
    private LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;

    public SessionDto(String ipAddress, LocalDateTime sessionStartTime) {
        this.ipAddress = ipAddress;
        this.sessionStartTime = sessionStartTime;
    }

    public SessionDto() {}

//    public SessionDto(String ipAddress, LocalDateTime sessionStartTime, LocalDateTime sessionEndTime) {
//        this.ipAddress = ipAddress;
//        this.sessionStartTime = sessionStartTime;
//        this.sessionEndTime = sessionEndTime;
//    }

    // Getters and Setters
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalDateTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalDateTime getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalDateTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }
}
