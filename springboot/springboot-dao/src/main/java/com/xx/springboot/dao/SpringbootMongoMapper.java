package com.xx.springboot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringbootMongoMapper<T> extends MongoRepository<T, String>{

}
