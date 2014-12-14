package cherry.sqlapp.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class AsyncProcessCommandResult implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.ASYNC_ID
     *
     * @mbggenerated
     */
    private Long asyncId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.EXIT_VALUE
     *
     * @mbggenerated
     */
    private Integer exitValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.STDOUT
     *
     * @mbggenerated
     */
    private String stdout;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.STDERR
     *
     * @mbggenerated
     */
    private String stderr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ASYNC_PROCESS_COMMAND_RESULT.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.ID
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.ID
     *
     * @param id the value for ASYNC_PROCESS_COMMAND_RESULT.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.ASYNC_ID
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.ASYNC_ID
     *
     * @mbggenerated
     */
    public Long getAsyncId() {
        return asyncId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.ASYNC_ID
     *
     * @param asyncId the value for ASYNC_PROCESS_COMMAND_RESULT.ASYNC_ID
     *
     * @mbggenerated
     */
    public void setAsyncId(Long asyncId) {
        this.asyncId = asyncId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.EXIT_VALUE
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.EXIT_VALUE
     *
     * @mbggenerated
     */
    public Integer getExitValue() {
        return exitValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.EXIT_VALUE
     *
     * @param exitValue the value for ASYNC_PROCESS_COMMAND_RESULT.EXIT_VALUE
     *
     * @mbggenerated
     */
    public void setExitValue(Integer exitValue) {
        this.exitValue = exitValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.STDOUT
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.STDOUT
     *
     * @mbggenerated
     */
    public String getStdout() {
        return stdout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.STDOUT
     *
     * @param stdout the value for ASYNC_PROCESS_COMMAND_RESULT.STDOUT
     *
     * @mbggenerated
     */
    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.STDERR
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.STDERR
     *
     * @mbggenerated
     */
    public String getStderr() {
        return stderr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.STDERR
     *
     * @param stderr the value for ASYNC_PROCESS_COMMAND_RESULT.STDERR
     *
     * @mbggenerated
     */
    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.UPDATED_AT
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.UPDATED_AT
     *
     * @param updatedAt the value for ASYNC_PROCESS_COMMAND_RESULT.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.CREATED_AT
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.CREATED_AT
     *
     * @param createdAt the value for ASYNC_PROCESS_COMMAND_RESULT.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.LOCK_VERSION
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.LOCK_VERSION
     *
     * @param lockVersion the value for ASYNC_PROCESS_COMMAND_RESULT.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ASYNC_PROCESS_COMMAND_RESULT.DELETED_FLG
     *
     * @return the value of ASYNC_PROCESS_COMMAND_RESULT.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ASYNC_PROCESS_COMMAND_RESULT.DELETED_FLG
     *
     * @param deletedFlg the value for ASYNC_PROCESS_COMMAND_RESULT.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AsyncProcessCommandResult other = (AsyncProcessCommandResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAsyncId() == null ? other.getAsyncId() == null : this.getAsyncId().equals(other.getAsyncId()))
            && (this.getExitValue() == null ? other.getExitValue() == null : this.getExitValue().equals(other.getExitValue()))
            && (this.getStdout() == null ? other.getStdout() == null : this.getStdout().equals(other.getStdout()))
            && (this.getStderr() == null ? other.getStderr() == null : this.getStderr().equals(other.getStderr()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAsyncId() == null) ? 0 : getAsyncId().hashCode());
        result = prime * result + ((getExitValue() == null) ? 0 : getExitValue().hashCode());
        result = prime * result + ((getStdout() == null) ? 0 : getStdout().hashCode());
        result = prime * result + ((getStderr() == null) ? 0 : getStderr().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", asyncId=").append(asyncId);
        sb.append(", exitValue=").append(exitValue);
        sb.append(", stdout=").append(stdout);
        sb.append(", stderr=").append(stderr);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}