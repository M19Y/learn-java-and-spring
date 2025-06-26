package com.m19y.learn.constraint;

import com.m19y.learn.Register;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, Register> {

  private String messageTemplate;

  @Override
  public void initialize(CheckPassword constraintAnnotation) {
    messageTemplate = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Register register, ConstraintValidatorContext context) {
    if(register.getPassword() == null || register.getRetypePassword() == null){
      return true; // skip validation (nanti di validasi sama notblanknya saja)
    }

    // kegunaan context adalah kita bisa memodifikasi keluaranya
    // contoh: saaat constraint ini diggunakan pada level class maka
    // get property pathnya tidak ada (kosong) maka dari itu
    // kita bisa menambahkan informasi ini.

    boolean isValid = register.getPassword().equals(register.getRetypePassword());

    if(!isValid){
      context.disableDefaultConstraintViolation(); // tidak menggunakan message default dari constraint-nya

      // untuk password (kita menggunakan message pada constraintnya, diambil melalui method initialized)
      context.buildConstraintViolationWithTemplate(messageTemplate)
              .addPropertyNode("password")
              .addConstraintViolation();

      // untuk retype password
      context.buildConstraintViolationWithTemplate("retype password is different with password")
              .addPropertyNode("retype password")
              .addConstraintViolation();
    }
    return isValid;
  }

}
