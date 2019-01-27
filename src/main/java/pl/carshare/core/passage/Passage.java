package pl.carshare.core.passage;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.carshare.core.user.User;

@Getter
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode
@Entity
@Table(schema = "public", name = "passage",
  indexes = { @Index(name = "passage_time_of_departure_i", columnList = "time_of_departure"),
    @Index(name = "passage_origin_destination_i", columnList = "origin, destination") })
public class Passage
{
  @Id
  @SequenceGenerator(name = "passage_id_seq_gen", sequenceName = "passage_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "passage_id_seq_gen", strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "passage_author_id_fk"))
  private User author;

  @Column(name = "origin", nullable = false, updatable = false, length = 100)
  private String origin;

  @Column(name = "destination", nullable = false, updatable = false, length = 100)
  private String destination;

  @Column(name = "available_seats_count", nullable = false, updatable = false)
  private int availableSeatsCount;

  @Column(name = "time_of_departure", nullable = false)
  private LocalDateTime timeOfDeparture;
}
