package sk.stuba.fei.uim.vsa.pr1b;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "RESERVATION")
public class RESERVATION implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long idUser;

    @Column(nullable = false)
    private Timestamp datum;


    private Timestamp koniec;
    private Double celkovaCena;

    @OneToOne
    private PARKING_SPOT parkingSpot;

    @OneToOne
    private CAR car;

    public CAR getCar() {
        return car;
    }

    public void setCar(CAR car) {
        this.car = car;
    }

    public PARKING_SPOT getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(PARKING_SPOT parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }


    public Double getCelkovaCena() {
        return celkovaCena;
    }

    public void setCelkovaCena(Double celkovaCena) {
        this.celkovaCena = celkovaCena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RESERVATION{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", datum=" + datum +
                ", koniec=" + koniec +
                ", celkovaCena=" + celkovaCena +
                ", parkingSpot=" + parkingSpot +
                ", car=" + car +
                '}';
    }

    public Timestamp getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    public Timestamp getKoniec() {
        return koniec;
    }

    public void setKoniec(Timestamp koniec) {
        this.koniec = koniec;
    }
}
