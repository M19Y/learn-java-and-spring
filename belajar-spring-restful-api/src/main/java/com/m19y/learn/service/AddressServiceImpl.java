package com.m19y.learn.service;

import com.m19y.learn.entity.Address;
import com.m19y.learn.entity.Contact;
import com.m19y.learn.entity.User;
import com.m19y.learn.mapper.AddressMapper;
import com.m19y.learn.model.address.AddressRequest;
import com.m19y.learn.model.address.AddressResponse;
import com.m19y.learn.repository.AddressRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;
  private final MessageSource messageSource;
  private final ContactService contactService;

  @Autowired
  public AddressServiceImpl(AddressRepository addressRepository, MessageSource messageSource, ContactService contactService) {
    this.addressRepository = addressRepository;
    this.messageSource = messageSource;
    this.contactService = contactService;
  }

  @Override
  @Transactional
  public AddressResponse create(User user, AddressRequest request) {
    Contact contact = contactService.findContactForUserOrThrow(user, request.getContactId());

    Address address = new Address();
    address.setId(UUID.randomUUID().toString());
    address.setCity(request.getCity());
    address.setStreet(request.getStreet());
    address.setCountry(request.getCountry());
    address.setProvince(request.getProvince());
    address.setPostalCode(request.getPostalCode());
    address.setContact(contact);

    addressRepository.save(address);
    return AddressMapper.toDto(address);
  }

  @Override
  @Transactional(readOnly = true)
  public AddressResponse get(User user, String contactId, String addressId) {
    Contact contact = contactService.findContactForUserOrThrow(user, contactId);
    Address address = findAddressForContactAndIdOrThrow(contact, addressId);
    return AddressMapper.toDto(address);
  }

  @Override
  @Transactional
  public void remove(User user, String contactId, String addressId) {
    log.info("contact -> {}, addresss -> {}", contactId, addressId );
    Contact contact = contactService.findContactForUserOrThrow(user, contactId);
    Address address = findAddressForContactAndIdOrThrow(contact, addressId);
    addressRepository.delete(address);
  }

  @Override
  @Transactional
  public AddressResponse update(User user, AddressRequest request) {
    Contact contact = contactService.findContactForUserOrThrow(user, request.getContactId());
    Address address = findAddressForContactAndIdOrThrow(contact, request.getAddressId());

    address.setCity(request.getCity());
    address.setStreet(request.getStreet());
    address.setCountry(request.getCountry());
    address.setProvince(request.getProvince());
    address.setPostalCode(request.getPostalCode());

    addressRepository.save(address);
    return AddressMapper.toDto(address);
  }

  @Override
  @Transactional(readOnly = true)
  public List<AddressResponse> list(User user, String contactId) {
    Contact contact = contactService.findContactForUserOrThrow(user, contactId);
    List<Address> contacts = addressRepository.findAllByContact(contact);
    return contacts.stream().map(AddressMapper::toDto).toList();
  }

  @Override
  public Address findAddressForContactAndIdOrThrow(Contact contact, String id) {
    return addressRepository.findFirstByContactAndId(contact, id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    messageSource.getMessage("error.address.not-found", null, LocaleContextHolder.getLocale())));
  }
}
