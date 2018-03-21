package io.full.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.full.model.Employee;
import io.full.model.Response;


@Path("/employee")
public class EmployeeServiceImpl {

	private static Map<Integer,Employee> emps = new HashMap<Integer,Employee>();
	
	
	@POST
    @Path("/add")
	@Consumes("application/json")
	public void addEmployee(Employee e) 
	{
		System.out.println("comes here ::" + e);
		Response response = new Response();
		
		if(emps.get(e.getId()) != null)
		{
			response.setStatus(false);
			response.setMessage("Employee Already Exists");
			response.setErrorCode("EC-01");
			//return response;
		}
		
		System.out.println("id ::" + e.getId());
		
		emps.put(e.getId(), e);
		response.setStatus(true);
		response.setMessage("Employee created successfully");
		
		System.out.println("after adding");
		//return response;
	}

	@DELETE
    @Path("/{id}")
	@Produces("application/json")
	public Response deleteEmployee(@PathParam("id") int id) 
	{
		Response response = new Response();
		if(emps.get(id) == null){
			response.setStatus(false);
			response.setMessage("Employee Doesn't Exists");
			response.setErrorCode("EC-02");
			return response;
		}
		emps.remove(id);
		response.setStatus(true);
		response.setMessage("Employee deleted successfully");
		return response;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Employee getEmployee(@PathParam("id") int id) {
		
		System.out.println( "id ::" + id);
		
		System.out.println("comes here ad" + emps.get(id));
		
		System.out.println(emps.get(id));
		
		if(emps.get(id) != null)
			return emps.get(id);
			
		else
			return null;
	}
	

//	@GET
//	@Path("/")
//	@Produces("application/json")
//	public Employee[] getAllEmployees() {
//		Set<Integer> ids = emps.keySet();
//		Employee[] e = new Employee[ids.size()];
//		int i=0;
//		for(Integer id : ids){
//			e[i] = emps.get(id);
//			i++;
//		}
//		return e;
//	}

}
