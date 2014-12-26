package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BSchemaVersion is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BSchemaVersion {

    private Integer checksum;

    private String description;

    private Integer executionTime;

    private String installedBy;

    private org.joda.time.LocalDateTime installedOn;

    private Integer installedRank;

    private String script;

    private Boolean success;

    private String type;

    private String version;

    private Integer versionRank;

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    public String getInstalledBy() {
        return installedBy;
    }

    public void setInstalledBy(String installedBy) {
        this.installedBy = installedBy;
    }

    public org.joda.time.LocalDateTime getInstalledOn() {
        return installedOn;
    }

    public void setInstalledOn(org.joda.time.LocalDateTime installedOn) {
        this.installedOn = installedOn;
    }

    public Integer getInstalledRank() {
        return installedRank;
    }

    public void setInstalledRank(Integer installedRank) {
        this.installedRank = installedRank;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getVersionRank() {
        return versionRank;
    }

    public void setVersionRank(Integer versionRank) {
        this.versionRank = versionRank;
    }

    public String toString() {
         return "checksum = " + checksum + ", description = " + description + ", executionTime = " + executionTime + ", installedBy = " + installedBy + ", installedOn = " + installedOn + ", installedRank = " + installedRank + ", script = " + script + ", success = " + success + ", type = " + type + ", version = " + version + ", versionRank = " + versionRank;
    }

}

