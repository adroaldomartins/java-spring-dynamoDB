package com.medium;

import java.util.Optional;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.medium.model.DataModel;
import com.medium.repositories.DataModelRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private DataModelRepositories dataModelRepositories;

	@Override
    public void run(String... strings) throws Exception {
		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(DataModel.class);

		tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);

        DataModel awsService = new DataModel();
        String awsServiceId = awsService.getID();

        Optional<DataModel> awsServiceQueried = dataModelRepositories.findById(awsServiceId);
        if (awsServiceQueried.get() != null) {
            System.out.println("Queried object: " + awsServiceQueried.get());
        }
        
        Iterable<DataModel> awsServices = dataModelRepositories.findAll();
        
        for (DataModel awsServiceObject : awsServices) {
            System.out.println("List object: " + awsServiceObject);
        }

	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
