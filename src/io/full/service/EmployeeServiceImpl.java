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
    @Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addEmployee(Employee e) 
	{
		Response response = new Response();
		
		if(emps.get(e.getId()) != null)
		{
			response.setStatus(false);
			response.setMessage("Employee Already Exists");
			return response;
		}
		
		emps.put(e.getId(), e);
		response.setStatus(true);
		response.setMessage("Employee created successfully");
		
		return response;
	}

	@DELETE
    @Path("/{id}")
	@Produces("application/json")
	public Response deleteEmployee(@PathParam("id") int id) 
	{
		Response response = new Response();
		
		if(emps.get(id) == null)
		{
			response.setStatus(false);
			response.setMessage("Employee Doesn't Exists");
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
	public Response getEmployee(@PathParam("id") int id) {
		
		Response response = new Response();
		
		if(emps.get(id) != null)
		{
			response.setStatus(true);
			response.setMessage("Employee record found");
			response.setData(emps.get(id));
			
			return response;
		}
		else
		{
			response.setStatus(true);
			response.setMessage("Employee record not found");
			return response;
		}
			
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
