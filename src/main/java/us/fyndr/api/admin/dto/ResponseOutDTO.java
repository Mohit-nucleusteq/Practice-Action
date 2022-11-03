package us.fyndr.api.admin.dto;

import java.util.Objects;

public class ResponseOutDTO {

    /**
     * success.
     */
    private boolean success;

    /**
     * generic object.
     * */
    private Object data;

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(final boolean success) {
        this.success = success;
    }

    /**
     * @return the object
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the object to set
     */
    public void setData(final Object data) {
        this.data = data;
    }

    /***
     * Parameterized Constructor.
     * @param success - true or false
     * @param data - contains data of response service
     * */
    public ResponseOutDTO(final boolean success, final Object data) {
        this.success = success;
        this.data = data;
    }

    /**
     * hashcode method.
     * @return hashCode of object
     * */
    @Override
    public int hashCode() {
        return Objects.hash(data, success);
    }

    /**
     * equals method.
     * @return boolean
     * */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResponseOutDTO)) {
            return false;
        }
        ResponseOutDTO other = (ResponseOutDTO) obj;
        return Objects.equals(data, other.data) && success == other.success;
    }

    /**
     * toString method.
     * */
    @Override
    public String toString() {
        return "ResponseOutDTO [success=" + success + ", data=" + data
                + "]";
    }

}
