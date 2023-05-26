package sk.stuba.fei.uim.vsa.pr1b;


import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@IdClass(CAR_PARK_FLOOR_ID.class)
@Table(name = "CAR_PARK_FLOOR")
public class CAR_PARK_FLOOR implements Serializable {
    @Id
    @Column(name = "idPark", nullable = false)
    private Long idPark;

    @Id
    @Column(name = "floorIdentifier", nullable = false, unique = true)
    private String floorIdentifier;


    @ManyToOne
    private CAR_PARK carPark;

    @OneToMany(mappedBy = "car_park_floor",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch=FetchType.EAGER)
    @JoinColumn(name = "idMiesta",referencedColumnName = "idMiesta")
    private List<PARKING_SPOT> parking_spots=new ArrayList<>();

    public List<PARKING_SPOT> getParking_spots() {
        return parking_spots;
    }

    public void setParking_spots(List<PARKING_SPOT> parking_spots) {
        this.parking_spots = parking_spots;
    }

    public Long getidPark() {
        return idPark;
    }

    public void setidPark(Long idPark) {
        this.idPark = idPark;
    }

    public CAR_PARK getCarPark() {
        return carPark;
    }

    public void setCarPark(CAR_PARK carPark) {
        this.carPark = carPark;
    }

    public CAR_PARK_FLOOR() {
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CAR_PARK_FLOOR)) {
            return false;
        }
        CAR_PARK_FLOOR other = (CAR_PARK_FLOOR) object;
        return (this.idPark != null || other.idPark == null) && (this.idPark == null || this.idPark.equals(other.idPark));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPark != null ? idPark.hashCode() : 0);
        return hash;
    }

    public String getFloorIdentifier() {
        return floorIdentifier;
    }

    public void setFloorIdentifier(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public Long getId() {
        return idPark;
    }

    public void setId(Long id) {
        this.idPark = id;
    }

    @Override
    public String toString() {
        return "CAR_PARK_FLOOR{" +
                "idPark=" + idPark +
                ", floorIdentifier='" + floorIdentifier + '\'' +
                ", carPark=" + carPark +
                ", parking_spots=" + parking_spots +
                '}';
    }
}
