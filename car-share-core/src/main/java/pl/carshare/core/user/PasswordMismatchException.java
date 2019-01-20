package pl.carshare.core.user;

/**
 * @author leonzio
 */
public class PasswordMismatchException extends RuntimeException
{
  PasswordMismatchException()
  {
    super("Password and repeated password does not match!");
  }
}
