package com.m19y.learn.constraint;

import com.m19y.learn.enums.CaseMode;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

//constraint composition
@CheckCase(message = "{car.id.upper}", mode = CaseMode.UPPER)
@Size(message = "{car.id.size}", min = 4, max = 10)
@NotBlank(message = "car id must not be blank")
@Documented
@Constraint(
        validatedBy = {}
)
@Target(value = {ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
//@ReportAsSingleViolation
public @interface CheckCarId {

  // message ini tidak akan dipanggil selama kita tidak mendefinisikan
  // @ReportAsSingleViolation
  String message() default "invalid car id";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
