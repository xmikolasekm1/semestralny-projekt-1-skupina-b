package sk.stuba.fei.uim.vsa.pr1b;

import org.eclipse.persistence.jpa.config.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PARKING_SPOT")
public class PARKING_SPOT implements Serializable {
    @Id
    //@Column(name = "idMiesta", nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMiesta;

    private String spotIdentifier;

    private String carType;

    @ManyToOne
    @JoinColumns(
            {
                    @JoinColumn(name = "idParkingFloor",referencedColumnName = "floorIdentifier"),
                    @JoinColumn(name = "idCarParku",referencedColumnName = "idPark")
            }
    )
    private CAR_PARK_FLOOR car_park_floor;


    @OneToOne(cascade=CascadeType.MERGE,mappedBy = "parkingSpot")
    private RESERVATION reservation;

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public RESERVATION getReservation() {
        return reservation;
    }

    public void setReservation(RESERVATION reservation) {
        this.reservation = reservation;
    }

    public String getSpotIdentifier() {
        return spotIdentifier;
    }

    public void setSpotIdentifier(String spotIdentifier) {
        this.spotIdentifier = spotIdentifier;
    }

    public CAR_PARK_FLOOR getCar_park_floor() {
        return car_park_floor;
    }

    public void setCar_park_floor(CAR_PARK_FLOOR car_park_floor) {
        this.car_park_floor = car_park_floor;
    }

    public Long getIdMiesta() {
        return idMiesta;
    }

    public void setIdMiesta(Long id) {
        this.idMiesta = id;
    }

    @Override
    public String toString() {
        return "PARKING_SPOT{" +
                "idMiesta=" + idMiesta +
                ", spotIdentifier='" + spotIdentifier + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PARKING_SPOT that = (PARKING_SPOT) o;
        return Objects.equals(idMiesta, that.idMiesta) && Objects.equals(spotIdentifier, that.spotIdentifier) && Objects.equals(car_park_floor, that.car_park_floor) && Objects.equals(reservation, that.reservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMiesta, spotIdentifier, car_park_floor, reservation);
    }
}
