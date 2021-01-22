package modelo;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Panella Lautaro
 */
@MappedSuperclass// clase padre de las entidades
@Inheritance(strategy = InheritanceType.JOINED)// joined: crea 2 tablas unidas por id
                                                // singletable: 1 tabla con todos los atributos de las demas
                                                // txclass: copia los atributos del padre=redundancia
public abstract class EntityBean implements Serializable{
    
    protected Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// gener.value = autonumerico; identity=sin repetir
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getIdentityPropery() {
        return "id";
    }   
}
