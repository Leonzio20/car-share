package pl.carshare.core.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode
@Entity
@Table(schema = "public", name = "user",
  uniqueConstraints = @UniqueConstraint(name = "user_name_uk", columnNames = "user_name"),
  indexes = @Index(name = "user_name_i", columnList = "user_name"))
public class User
{
  @Id
  @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "user_id_seq_gen", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "user_name", unique = true, length = 30, updatable = false, nullable = false)
  private String userName;

  @Column(name = "password", nullable = false)
  private String password;
}
