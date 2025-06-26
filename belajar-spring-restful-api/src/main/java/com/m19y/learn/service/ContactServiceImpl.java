package com.m19y.learn.service;

import com.m19y.learn.entity.Contact;
import com.m19y.learn.entity.User;
import com.m19y.learn.mapper.ContactMapper;
import com.m19y.learn.model.contact.ContactResponse;
import com.m19y.learn.model.contact.CreateContactRequest;
import com.m19y.learn.model.contact.SearchContactRequest;
import com.m19y.learn.model.contact.UpdateContactRequest;
import com.m19y.learn.repository.ContactRepository;
import com.m19y.learn.util.UpdateHelper;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;
  private final ValidatorService validator;
  private final MessageSource messageSource;

  @Autowired
  public ContactServiceImpl(ContactRepository contactRepository, ValidatorService validator, MessageSource messageSource) {
    this.contactRepository = contactRepository;
    this.validator = validator;
    this.messageSource = messageSource;
  }

  @Override
  @Transactional
  public ContactResponse create(User user, CreateContactRequest request) {
    validator.validate(request);

    Contact contact = new Contact();
    contact.setId(UUID.randomUUID().toString());
    contact.setEmail(request.getEmail());
    contact.setPhone(request.getPhone());
    contact.setFirstName(request.getFirstName());
    contact.setLastName(request.getLastName());
    contact.setUser(user);

    contactRepository.save(contact);

    return ContactMapper.toDto(contact);
  }

  @Override
  @Transactional(readOnly = true)
  public ContactResponse get(User user, String id) {
    Contact contact = findContactForUserOrThrow(user, id);
    return ContactMapper.toDto(contact);
  }

  @Override
  @Transactional
  public ContactResponse update(User user, UpdateContactRequest request) {
    validator.validate(request);

    Contact contact = findContactForUserOrThrow(user, request.getId());

    contact.setFirstName(request.getFirstName());
    contact.setLastName(request.getLastName());
    contact.setEmail(request.getEmail());
    contact.setPhone(request.getPhone());

    contactRepository.save(contact);

    return ContactMapper.toDto(contact);
  }

  @Override
  @Transactional
  public void delete(User user, String id) {
    Contact contact = findContactForUserOrThrow(user, id);
    contactRepository.delete(contact);
  }

  @Override
  public Page<ContactResponse> search(User user, SearchContactRequest request) {
    Specification<Contact> specification = (root, query, builder) -> {
      List<Predicate> predicates = new ArrayList<>();
      predicates.add(builder.equal(root.get("user"), user));

      UpdateHelper.updateIfNotNull(request.name(),
              name -> predicates.add(
                builder.or(
                  builder.like(root.get("firstName"), "%" + name + "%"),
                  builder.like(root.get("lastName"), "%" + name + "%")
                )
              ));
      UpdateHelper.updateIfNotNull(request.email(),
              email -> predicates.add(builder.like(root.get("email"), "%" + email + "%")));

      UpdateHelper.updateIfNotNull(request.phone(),
              phone -> predicates.add(builder.like(root.get("phone"), "%" + phone + "%")));

      return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
    };

    Pageable pageable = PageRequest.of(request.page(), request.size());
    Page<Contact> contacts = contactRepository.findAll(specification, pageable);
    List<ContactResponse> contactResponses = contacts.getContent().stream()
            .map(ContactMapper::toDto)
            .toList();

    return new PageImpl<>(contactResponses, pageable, contacts.getTotalElements());
  }

  @Override
  public Contact findContactForUserOrThrow(User user, String id){
    return contactRepository.findFirstByUserAndId(user, id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    messageSource.getMessage("error.contact.not-found", null, LocaleContextHolder.getLocale())));
  }
}
