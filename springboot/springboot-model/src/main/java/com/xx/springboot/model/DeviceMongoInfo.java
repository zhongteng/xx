package com.xx.springboot.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceMongoInfo extends MongoRepository<DeviceInfo, String> {

}
