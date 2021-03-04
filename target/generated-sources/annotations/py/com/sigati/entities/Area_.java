package py.com.sigati.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.sigati.entities.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-26T22:38:51")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile SingularAttribute<Area, String> descripcion;
    public static volatile ListAttribute<Area, Usuario> usuarioList;
    public static volatile SingularAttribute<Area, Integer> id;

}