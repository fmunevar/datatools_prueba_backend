package com.proyecto.prueba_datatools;

import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("user")
public class UserResource {
UserRepository userRepository = new UserRepository();
	
	@POST
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(User user) {
		int company_id = 0;
		if( user==null ) {
			System.out.println("NULO");
		}else {
			company_id = (int) user.getCompany_id();
			System.out.println(user.getCompany_id());
		}
		return this.userRepository.getUsers(company_id);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") int id) {
		return this.userRepository.getUser(id);
	}
	
	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public Response newUser(User user) {
		int insertedId = this.userRepository.createUser(user);
		
		if( insertedId!=0 ) {
//			return Response.status(Response.Status.OK).entity(this.userRepository.getUser(insertedId)).build();
			return Response.status(Response.Status.OK).entity("{\"Response\":\"User created succesfully\"}").build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error registering user\"}").build();
		}
	}
	
	@PUT
	@Path("edit")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {
		if( this.userRepository.updateUser(user) ) {
			return Response.status(Response.Status.OK).entity(user).build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error updating user registry\"}").build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id) {
		if( this.userRepository.deleteUser(id) ) {
			return Response.status(Response.Status.OK).entity("{\"Response\":\"User registry deleted succesfully\"}").build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error deleting user registry\"}").build();
		}
	}
	
	@POST
	@Path("vehicle_assignment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignVehicle(Driving driving) {
		if( this.userRepository.assignVehicle(driving) ) {
			return Response.status(Response.Status.OK).entity("{\"Response\":\"Vehicle assigned to driver succesfully\"}").build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error assigning vehicle to driver\"}").build();
		}
	}
	
	@GET
	@Path("check_assignments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vehicle> checkAssignments(@PathParam("id") String id) {
		return this.userRepository.checkAssignments(id);
	}
}
