package pl.carshare.core.bean;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author leonzio
 */
public final class BeanValidation
{
  private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
    .getValidator();

  public static <T> void validate(T t) throws ConstraintViolationException
  {
    Set<ConstraintViolation<T>> violations = VALIDATOR.validate(t);
    if (violations.isEmpty())
    {
      return;
    }
    throw new ConstraintViolationException(violations);
  }
}
