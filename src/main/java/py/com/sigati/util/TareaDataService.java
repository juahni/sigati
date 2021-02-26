package py.com.sigati.util;

import py.com.sigati.entities.Tarea;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TareaDataService {

	@PersistenceContext(unitName = "com.mycompany_Rollout_war_1.0-SNAPSHOTPU")
	private EntityManager em;



}
