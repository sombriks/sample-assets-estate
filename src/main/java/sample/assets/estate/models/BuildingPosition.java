package sample.assets.estate.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="buildings_position")
public class BuildingPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "assets_id")
    private Asset asset;
    @ManyToOne
    @JoinColumn(name = "departments_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    @ManyToOne
    @JoinColumn(name = "change_reasons_id")
    private ChangeReason changeReason;
    private String comment;
    @Column(name = "start_value")
    private Double startValue;
    @Column(name = "current_value")
    private Double currentValue;
    @Column(name = "terminal_value")
    private Double terminalValue;
    private Double interest;
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime start;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime finish;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated;

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getTerminalValue() {
        return terminalValue;
    }

    public void setTerminalValue(Double terminalValue) {
        this.terminalValue = terminalValue;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getStartValue() {
        return startValue;
    }

    public void setStartValue(Double startValue) {
        this.startValue = startValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ChangeReason getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(ChangeReason changeReason) {
        this.changeReason = changeReason;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
