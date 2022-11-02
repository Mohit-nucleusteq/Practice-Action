package us.fyndr.api.admin.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SNSServiceTest {

    @Mock
    private AmazonSNS amazonSns;

    @Mock
    private ResponseMetadata responseMetadata;

    @InjectMocks
    private SNSService snsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPublishMessage() {

        String message = "message";
        String notificationType = "notificationType";
        String topicArn = "arn:aws:sns:us-west-1:6:test";

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("notificationType",
                new MessageAttributeValue().withDataType("String").withStringValue(notificationType));

        PublishRequest publishRequest = new PublishRequest();

        publishRequest.setMessage(message);
        publishRequest.setTopicArn(topicArn);
        publishRequest.setMessageAttributes(messageAttributes);

        PublishResult publishResult = mock(PublishResult.class);

        when(amazonSns.publish(publishRequest)).thenReturn(publishResult);

        when(publishResult.getSdkResponseMetadata()).thenReturn(responseMetadata);

        snsService.publishMessage(message, notificationType, topicArn);

        verify(amazonSns,times(1)).publish(publishRequest);
        verify(publishResult, times(1)).getSdkResponseMetadata();

    }

}