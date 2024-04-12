package de.joshuaw.mmatechniques;

import static com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableDynamoDBRepositories(basePackages = "de.joshuaw.mmatechniques")
public class DynamoDBConfig {

  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDBEndpoint;

  @Value("${amazon.aws.accesskey}")
  private String amazonAWSAccessKey;

  @Value("${amazon.aws.secretkey}")
  private String amazonAWSSecretKey;

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
    builder.setCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()));
    if (StringUtils.hasText(amazonDynamoDBEndpoint)) {
      builder.setEndpointConfiguration(
          new EndpointConfiguration(
              amazonDynamoDBEndpoint,
              Regions.EU_WEST_1.getName()
          )
      );
    }
    return builder.build();
  }

  @Bean
  public AWSCredentials amazonAWSCredentials() {
    return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
  }

  @Bean(name = "mvcHandlerMappingIntrospectorCustom")
  public HandlerMappingIntrospector mvcHandlerMappingIntrospectorCustom() {
    return new HandlerMappingIntrospector();
  }
}
