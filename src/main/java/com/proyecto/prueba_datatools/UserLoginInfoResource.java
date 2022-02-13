package com.proyecto.prueba_datatools;

import java.util.List;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class UserLoginInfoResource {
	UserLoginInfoRepository userLoginInfoRepository = new UserLoginInfoRepository();
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserLoginInfo userLoginInfo) {
		Object loginInfo = this.userLoginInfoRepository.login(userLoginInfo);
		if( loginInfo!=null ) {
//			return Response.status(Response.Status.OK).entity("{\"Response\":\"true\"}").build();
			return Response.status(Response.Status.OK).entity(loginInfo).build();
		}else {
			return Response.status(Response.Status.OK).entity("{\"Response\":\"false\"}").build();
		}
	}

}
