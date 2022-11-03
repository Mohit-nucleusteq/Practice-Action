package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import us.fyndr.api.admin.model.CountCampaignOffers;

@DataJpaTest
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql", "classpath:/dml/repository/campaign_offers_details_insertion.sql" })
@ComponentScan(basePackages = { "us.fyndr.api.admin.repository" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Import(CampaignOfferDetailsRepositoryImpl.class)
public class CampaignOfferDetailsRepositoryImplTest {

    @Autowired
    private CampaignOfferDetailsRepositoryImpl campaignOfferDetailsRepositoryImpl;

    @Test
    public void testCountCampaignOffers() {

        int campaignId = 2;

        CountCampaignOffers expectedCountCampaignOffers = new CountCampaignOffers(1, 1, 2);

        CountCampaignOffers actualCountCampaignOffers = campaignOfferDetailsRepositoryImpl
                .getCampaignOfferDetails(campaignId);

        assertEquals(expectedCountCampaignOffers, actualCountCampaignOffers);
    }

}
