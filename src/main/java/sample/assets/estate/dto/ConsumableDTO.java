package sample.assets.estate.dto;

import jakarta.validation.constraints.NotBlank;
import sample.assets.estate.models.*;

import java.time.LocalDateTime;

public class ConsumableDTO {

    private Long id;
    private Long assetId;
    @NotBlank
    private String name;
    private String description;
    private Long departmentId;
    private Double unitValue;
    private Long amount;
    private String comment;
    private Long statusId;
    private Long reasonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }

    public Asset fill(Asset asset) {
        asset.setId(assetId);
        asset.setName(name);
        asset.setDescription(description);
        asset.setType(AssetType.CONSUMABLE);
        if (asset.getCreated() == null)
            asset.setCreated(LocalDateTime.now());
        asset.setUpdated(LocalDateTime.now());
        return asset;
    }

    public ConsumablePosition fill(ConsumablePosition consumablePosition) {
        consumablePosition.setComment(comment);
        consumablePosition.setAmount(amount);
        consumablePosition.setUnitValue(unitValue);
        consumablePosition.setStarted(LocalDateTime.now());
        consumablePosition.setDepartment(new Department(departmentId));
        if (reasonId != null && reasonId > 0)
            consumablePosition.setChangeReason(new ChangeReason(reasonId));
        if (statusId != null && statusId > 0)
            consumablePosition.setAssetStatus(new AssetStatus(statusId));
        if (consumablePosition.getCreated() == null)
            consumablePosition.setCreated(LocalDateTime.now());
        consumablePosition.setUpdated(LocalDateTime.now());
        return consumablePosition;
    }
}
