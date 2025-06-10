package sample.assets.estate.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "asset_types")
public class AssetType {

    public static final AssetType CONSUMABLE = new AssetType(1L);
    public static final AssetType FURNITURE = new AssetType(2L);
    public static final AssetType VEHICLE = new AssetType(3L);
    public static final AssetType BUILDING = new AssetType(4L);

    @Id
    private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated;

    public AssetType(){}

    public AssetType(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
