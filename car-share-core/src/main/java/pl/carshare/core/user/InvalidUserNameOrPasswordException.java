package pl.carshare.core.user;

/**
 * @author leonzio
 */
public class InvalidUserNameOrPasswordException extends RuntimeException
{
  InvalidUserNameOrPasswordException()
  {
    super("Invalid user name or password");
  }
}
