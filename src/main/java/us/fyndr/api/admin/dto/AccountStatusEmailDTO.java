package us.fyndr.api.admin.dto;

import java.util.Objects;

/**
 * AccountStatusEmailDTO class contains the details of a user required to send
 * an e-mail.
 */
public class AccountStatusEmailDTO {

    /**
     * The data is an object of AccountStatusMailInfoDTO contains the information
     * related to user.
     */
    private AccountStatusMailInfoDTO data;

    /**
     * The to represents the email address of a user whom we need to send an e-mail.
     */
    private String to;

    /**
     * The template indicates template of an email.
     */
    private String template;

    /**
     * @return to email Address of user whom we send mail
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to - Setter function to set email address
     */
    public void setTo(final String to) {
        this.to = to;
    }

    /**
     * @return template of an email
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template - Setter function to set template of an email
     */
    public void setTemplate(final String template) {
        this.template = template;
    }

    /**
     * @return data object of AccountStatusMailInfoDTO
     */
    public AccountStatusMailInfoDTO getData() {
        return data;
    }

    /**
     * @param data - Setter function to set details of AccountStatusMailInfoDTO
     */
    public void setData(final AccountStatusMailInfoDTO data) {
        this.data = data;
    }

    /**
     * The to String Method.
     */
    @Override
    public String toString() {
        return "AccountStatusEmailDTO [data=" + data + ", to=" + to + ", template=" + template + "]";
    }

    /**
     * The hash Code method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(data, template, to);
    }

    /**
     * The equals Method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccountStatusEmailDTO)) {
            return false;
        }
        AccountStatusEmailDTO other = (AccountStatusEmailDTO) obj;
        return Objects.equals(data, other.data) && Objects.equals(template, other.template)
                && Objects.equals(to, other.to);
    }

}
