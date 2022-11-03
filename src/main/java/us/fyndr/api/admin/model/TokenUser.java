/**
 * @author Prerna Goyal
 */
package us.fyndr.api.admin.model;

import java.util.Objects;

/**
 * This class contains information about user fetched from token service.
 * */
public class TokenUser {

    /**
     * objId of TokenUser.
     */
    private long objId;

    /**
     * email of TokenUser.
     */
    private String email;

    /**
     * bizName of TokenUser.
     */
    private String bizName;

    /**
     * userEntity of TokenUser.
     */
    private String userEntity;

    /**
     * userRole of TokenUser.
     */
    private String userRole;

    /**
     * id of admin who is generating the token.
     */
    private long generatedBy;

    /**
     * @return the objId
     */
    public long getObjId() {
        return objId;
    }

    /**
     * @param objId the objId to set
     */
    public void setObjId(final long objId) {
        this.objId = objId;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the bizName
     */
    public String getBizName() {
        return bizName;
    }

    /**
     * @param bizName the bizName to set
     */
    public void setBizName(final String bizName) {
        this.bizName = bizName;
    }

    /**
     * @return the userEntity
     */
    public String getUserEntity() {
        return userEntity;
    }

    /**
     * @param userEntity the userEntity to set
     */
    public void setUserEntity(final String userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * @return the userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(final String userRole) {
        this.userRole = userRole;
    }

    /**
     * @return the generatedBy
     */
    public long getGeneratedBy() {
        return generatedBy;
    }

    /**
     * @param generatedBy the generatedBy to set
     */
    public void setGeneratedBy(final long generatedBy) {
        this.generatedBy = generatedBy;
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "TokenUser [objId=" + objId + ", email=" + email + ", bizName="
                + bizName + ", userEntity=" + userEntity + ", userRole="
                + userRole + ", generatedBy=" + generatedBy + "]";
    }

    /**
     * The hashcode method.
     */
    @Override
    public int hashCode() {

        return Objects.hash(objId, email, bizName, userEntity, userRole,
                generatedBy);
    }

    /**
     * The equals method.
     * @param obj
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TokenUser)) {
            return false;
        }
        TokenUser other = (TokenUser) obj;
        return Objects.equals(objId, other.objId)
                && Objects.equals(email, other.email)
                && Objects.equals(bizName, other.bizName)
                && Objects.equals(userRole, other.userRole)
                && Objects.equals(userEntity, other.userEntity)
                && Objects.equals(generatedBy, other.generatedBy);
    }
}
