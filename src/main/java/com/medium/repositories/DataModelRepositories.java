package com.medium.repositories;

import java.util.Optional;

import com.medium.model.DataModel;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface DataModelRepositories extends CrudRepository<DataModel, String> {
    Optional<DataModel> findById(String id);

}
