package us.fyndr.api.admin.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The type Error response.
 */
@JsonInclude(value = Include.NON_NULL)
public class ErrorResponse {

    /**
     *  The time stamp of error.
     */
    private Instant timestamp;

    /**
     *  The status of error.
     */
    private String status;

    /**
     *  The message of error.
     */
    private String message;

    /**
     *  The details of error.
     */
    private String details;

    /**
     *  The List of errorMessages.
     */
    private List<String> errorMessages;

    /**
     *  The constructor.
     *  @param timestamp Timestamp of the error
     *  @param status status of error
     *  @param message message due to which error occured
     *  @param details of the error message
     */
    public ErrorResponse(final Instant timestamp, final String status, final String message, final String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    /**
     *  The constructor.
     *  @param timestamp Timestamp of the error
     *  @param status status of error
     *  @param errorMessages list of messages due to which error occured
     *  @param details of the error message
     */
    public ErrorResponse(final Instant timestamp, final String status, final List<String> errorMessages, final String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.errorMessages = errorMessages;
        this.details = details;
    }

    /**
     * @return the timestamp
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(final Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return the errorMessages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * @param errorMessages the errorMessages to set
     */
    public void setErrorMessages(final List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(final String details) {
        this.details = details;
    }

    /**
     * override toString.
     * */
    @Override
    public String toString() {
        return "ErrorResponse [timestamp=" + timestamp + ", status=" + status
                + ", message=" + message + ", details=" + details
                + ", errorMessages=" + errorMessages + "]";
    }

    /**
     * The hashcode method.
     */
    @Override
    public int hashCode() {

        return Objects.hash(timestamp, status, message, details, errorMessages);
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
        if (!(obj instanceof ErrorResponse)) {
            return false;
        }
        ErrorResponse other = (ErrorResponse) obj;
        return Objects.equals(timestamp, other.timestamp)
                && Objects.equals(status, other.status)
                && Objects.equals(message, other.message)
                && Objects.equals(details, other.details)
                && Objects.equals(errorMessages, other.errorMessages);
    }

}
