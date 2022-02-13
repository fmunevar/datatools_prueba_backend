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

@Path("vehicle")
public class VehiclesResource {
	VehicleRepository vehicleRepository = new VehicleRepository();
	
	@POST
	@Path("vehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vehicle> getVehicles(Enrollment enrollment) {
		int company_id = 0;
		if( enrollment==null ) {
			System.out.println("NULO");
		}else {
			company_id = (int) enrollment.getCompany_id();
			System.out.println(enrollment.getCompany_id());
		}
		return this.vehicleRepository.getVehicles(company_id);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vehicle getVehicle(@PathParam("id") int id) {
		return this.vehicleRepository.getVehicle(id);
	}
	
	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public Response newVehicle(Vehicle vehicle) {
		int insertedId = this.vehicleRepository.createVehicle(vehicle);
		
		if( insertedId!=0 ) {
//			return Response.status(Response.Status.OK).entity(this.vehicleRepository.getVehicle(insertedId)).build();
			return Response.status(Response.Status.OK).entity("{\"Response\":\"Vehicle registered succesfully\"}").build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error creating vehicle registry\"}").build();
		}
	}
	
	@PUT
	@Path("edit")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateVehicle(Vehicle vehicle) {
		if( this.vehicleRepository.updateVehicle(vehicle) ) {
			return Response.status(Response.Status.OK).entity(vehicle).build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error updating vehicle registry\"}").build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVehicle(@PathParam("id") int id) {
		if( this.vehicleRepository.deleteVehicle(id) ) {
			return Response.status(Response.Status.OK).entity("{\"Response\":\"Vehicle registry deleted succesfully\"}").build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error deleting vehicle registry\"}").build();
		}
	}
	
	@POST
	@Path("enroll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response enrollVehicle(Enrollment enrollment) {
		String status = this.vehicleRepository.enrollVehicle(enrollment);
		System.out.println(status);
		if( status!="false" ) {
			return Response.status(Response.Status.OK).entity("{\"Response\":\""+status+"\"}").build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error enrolling vehicle to company\"}").build();
		}
	}
}
