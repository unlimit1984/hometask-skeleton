package ua.epam.spring.hometask.domain.to;

/**
 * Created by Vladimir on 28.10.2017.
 */
public class AuditoriumDetailsDTO {
    private String auditoriumName;
    private Long vipSeat;

    public AuditoriumDetailsDTO(String auditoriumName, Long vipSeat) {
        this.auditoriumName = auditoriumName;
        this.vipSeat = vipSeat;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public Long getVipSeat() {
        return vipSeat;
    }

    public void setVipSeat(Long vipSeat) {
        this.vipSeat = vipSeat;
    }
}
