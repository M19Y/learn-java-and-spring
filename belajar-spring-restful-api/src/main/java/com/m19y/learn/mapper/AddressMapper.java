package com.m19y.learn.mapper;

import com.m19y.learn.entity.Address;
import com.m19y.learn.model.address.AddressResponse;

public class AddressMapper {
  public static AddressResponse toDto(Address address){
    return AddressResponse.builder()
            .id(address.getId())
            .city(address.getCity())
            .street(address.getStreet())
            .country(address.getCountry())
            .province(address.getProvince())
            .postalCode(address.getPostalCode())
            .build();
  }
}
