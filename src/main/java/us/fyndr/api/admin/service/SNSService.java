package us.fyndr.api.admin.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

/**
 * SNSService class contains configuration for Amazon SNS service to publish the
 * messages to user.
 *
 */
@Service
public class SNSService {

    /**
     * AmazonSNS bean.
     * */
    @Autowired
    private AmazonSNS snsClient;

    /**
     * @param message          stores information that will be sent to the user
     * @param notificationType stores type of notification sent to the user
     * @param topicArn         is the topicArn on which we want to publish the
     *                         messages.
     */
    public void publishMessage(final String message, final String notificationType, final String topicArn) {

        PublishRequest publishRequest = new PublishRequest();
        publishRequest.setMessage(message);

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("notificationType",
                new MessageAttributeValue().withDataType("String").withStringValue(notificationType));

        publishRequest.setTopicArn(topicArn);
        publishRequest.setMessageAttributes(messageAttributes);
        PublishResult result = snsClient.publish(publishRequest);
        result.getSdkResponseMetadata();
    }
}
