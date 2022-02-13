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

@Path("company")
public class CompanyResource {
	CompanyRepository companyRepository = new CompanyRepository();
	
	@GET
	@Path("companies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> getCompanies() {
		return this.companyRepository.getCompanies();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompany(@PathParam("id") int id) {
		return this.companyRepository.getCompany(id);
	}
	
	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public Response newCompany(Company company) {
		int insertedId = this.companyRepository.createCompany(company);
		
		if( insertedId!=0 ) {
//			return Response.status(Response.Status.OK).entity(this.companyRepository.getCompany(insertedId)).build();
			return Response.status(Response.Status.OK).entity("{\"Response\":\"Company registry created succesfully\"}").build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error creating company registry\"}").build();
		}
	}
	
	@PUT
	@Path("edit")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCompany(Company company) {
		if( this.companyRepository.updateCompany(company) ) {
			return Response.status(Response.Status.OK).entity(company).build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error updating company registry\"}").build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCompany(@PathParam("id") int id) {
		if( this.companyRepository.deleteCompany(id) ) {
			return Response.status(Response.Status.OK).entity("{\"Response\":\"Company registry deleted succesfully\"}").build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Response\":\"Error deleting company registry\"}").build();
		}
	}
}
