/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.util;

import java.util.*;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import py.com.sigati.entities.Tarea;

/**
 *
 * @author Juanhi
 */
public class LazyTaskDataModel extends LazyDataModel<Tarea> {

    private List<Tarea> datasource;
	private int rowCount = 0;
	TareaDataService tareaDataService = new TareaDataService();


	// TareaFacade tareaFacade;

    /*public LazyTaskDataModel(List<Tarea> datasource, TareaFacade tareaFacade) {
    	this.tareaFacade = tareaFacade;

        this.datasource = datasource;
    }*/

    @Override
    public Tarea getRowData(String rowKey) {
        for (Tarea tarea : datasource) {
            if (tarea.getId().equals(rowKey)) {
                return tarea;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Tarea tarea) {
        return tarea.getId();
    }

    @Override
    public List<Tarea> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
    	List<Tarea> data = new ArrayList<>();


			StringBuilder queryCount = new StringBuilder("SELECT count(t.idTarea) FROM Tarea t");
			StringBuilder query = new StringBuilder("SELECT t FROM Tarea t");
			StringBuilder where = null;
			StringBuilder orderBy = null;

			boolean and_clause = false;
			boolean addWhere = false;
			where = new StringBuilder(" WHERE ");
			if (filterMeta != null && !filterMeta.isEmpty() ) {
				for (FilterMeta meta : filterMeta.values()) {

					String filterField = meta.getFilterField();
					String filterValue = meta.getFilterValue() + "";

					if (filterValue != null && !filterValue.equals("null")){
						if( and_clause ) {
							where.append(" AND ");
						}
						where.append("t." + filterField + " LIKE '%");
						where.append(filterValue + "%'");
						addWhere = true;
						and_clause = true;
					}
				}

			}



		if(sortMeta != null && !sortMeta.isEmpty()) {
			for (SortMeta meta : sortMeta.values()) {
				orderBy = new StringBuilder(" ORDER BY t." + meta.getSortField());
				orderBy.append( (meta.getSortOrder().toString() == "ASCENDING"? " ASC " : " DESC ") );
			}


		}

		// complete the two queries
		if (addWhere){
			queryCount.append( (where == null ? "": where) ) ;
		}

		if (addWhere){
			query.append(where);
		}
		query.append( (orderBy == null ? "": orderBy) );



		//rowCount = 0;

		/*try {
			rowCount = (int) tareaFacade.countQuery(queryCount.toString());

			data = tareaFacade.filtrarLazyList(query.toString(), first, pageSize);
		} catch(Exception ex) {
			System.out.println("ComuniController:load: " + ex.getLocalizedMessage());
		}*/

        //rowCount
        int dataSize = data.size();
        this.setRowCount(rowCount);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
