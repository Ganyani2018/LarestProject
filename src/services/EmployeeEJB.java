package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.EmployeeTbl;
import model.Employee;

/**
 * Session Bean implementation class EmployeeEJB
 */
@Stateless
@LocalBean
public class EmployeeEJB {


	@PersistenceContext
	private EntityManager em;
	
    public EmployeeEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public void addNew(EmployeeTbl employeeEntity)
    {
    	System.out.println("============================");
    	System.out.println(employeeEntity.getName());
    	em.persist(employeeEntity);
    	System.out.println("============================");
    }

	public List<Employee> findEmployees() {
		
		List<EmployeeTbl> employeesTbl = new ArrayList<EmployeeTbl>();
		List<Employee> employees = new ArrayList<Employee>();
		
		employeesTbl = em.createQuery("SELECT e FROM EmployeeTbl e", EmployeeTbl.class).getResultList();
		
		if(employeesTbl.size() > 0) {
			for(EmployeeTbl tbl : employeesTbl) {
				Employee empl = new Employee();
				empl.setName(tbl.getName());
				empl.setSurName(tbl.getSurName()); 
				empl.setDateOfBirth(tbl.getDateOfBirth());
				
				employees.add(empl);
			}
		}	
		return employees;
	}

}

