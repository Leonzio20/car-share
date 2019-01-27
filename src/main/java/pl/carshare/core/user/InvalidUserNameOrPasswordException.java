package pl.carshare.core.user;

public class InvalidUserNameOrPasswordException extends RuntimeException
{
  InvalidUserNameOrPasswordException()
  {
    super("Invalid user name or password");
  }
}
