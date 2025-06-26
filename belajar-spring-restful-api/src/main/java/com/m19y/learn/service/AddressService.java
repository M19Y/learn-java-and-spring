package com.m19y.learn.service;

import com.m19y.learn.entity.Address;
import com.m19y.learn.entity.Contact;
import com.m19y.learn.entity.User;
import com.m19y.learn.model.address.AddressRequest;
import com.m19y.learn.model.address.AddressResponse;

import java.util.List;

public interface AddressService {

  AddressResponse create(User user, AddressRequest request);
  AddressResponse update(User user, AddressRequest request);
  AddressResponse get(User user, String contactId, String addressId);
  Address findAddressForContactAndIdOrThrow(Contact contact, String id);
  void remove(User user, String contactId, String addressId);
  List<AddressResponse> list(User user, String contactId);

}
