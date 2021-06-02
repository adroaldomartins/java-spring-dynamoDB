package com.medium.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "stocks")
public class DataModel {
    private String id;
    private String value;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getID() {
        return id;
    }

    @DynamoDBAttribute
    public String getValue() {
        return value;
    }

}
