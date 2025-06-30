package sample.assets.estate.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sample.assets.estate.models.*;

import java.time.LocalDateTime;

public class CreateConsumableDTO {

    @NotBlank
    private String name;
    private String description;
    @NotNull
    @Min(1)
    private Long departmentId;
    @NotNull
    @Min(0)
    private Double unitValue;
    @NotNull
    @Min(0)
    private Long amount;
    private String comment;
    private Long statusId;
    private Long reasonId;

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
        consumablePosition.setChangeReason(ChangeReason.INCLUSION);
        consumablePosition.setAssetStatus(AssetStatus.AVAILABLE);
        consumablePosition.setCreated(LocalDateTime.now());
        consumablePosition.setUpdated(LocalDateTime.now());
        return consumablePosition;
    }
}
