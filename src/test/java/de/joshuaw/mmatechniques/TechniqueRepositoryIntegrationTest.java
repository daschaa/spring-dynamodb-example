package de.joshuaw.mmatechniques;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MmaTechniquesApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
    "amazon.dynamodb.endpoint=http://localhost:8000/",
    "amazon.aws.accesskey=test1",
    "amazon.aws.secretkey=test231" })
public class TechniqueRepositoryIntegrationTest {
  private DynamoDBMapper dynamoDBMapper;

  @Autowired
  private AmazonDynamoDB amazonDynamoDB;

  @Autowired
  TechniqueRepository repository;

  @Before
  public void setup() throws Exception {
    dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

    try {
      CreateTableRequest tableRequest = dynamoDBMapper
          .generateCreateTableRequest(Technique.class);
      tableRequest.setProvisionedThroughput(
          new ProvisionedThroughput(1L, 1L));
      amazonDynamoDB.createTable(tableRequest);
    } catch(final ResourceInUseException e) {
      // Do nothing because table exists
    }

    dynamoDBMapper.batchDelete(
        repository.findAll());
  }

  @Test
  public void givenItemWithTitle_whenRunFindAll_thenItemIsFound() {
    Technique technique = new Technique("Kimura");
    repository.save(technique);
    List<Technique> result = (List<Technique>) repository.findAll();

    assertThat(result.size(), is(greaterThan(0)));
  }
}
