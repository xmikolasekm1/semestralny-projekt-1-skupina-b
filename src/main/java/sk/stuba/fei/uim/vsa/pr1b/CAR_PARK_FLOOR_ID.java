package sk.stuba.fei.uim.vsa.pr1b;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class CAR_PARK_FLOOR_ID implements Serializable {

    private Long idPark;

    private String floorIdentifier;

    public CAR_PARK_FLOOR_ID() {
    }

    public CAR_PARK_FLOOR_ID(Long idPoschodia, String floorIdentifier) {
        this.idPark = idPoschodia;
        this.floorIdentifier = floorIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CAR_PARK_FLOOR_ID that = (CAR_PARK_FLOOR_ID) o;
        return Objects.equals(idPark, that.idPark) && Objects.equals(floorIdentifier, that.floorIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPark, floorIdentifier);
    }
}
