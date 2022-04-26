package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Interupation;

@Path("/Interupation")
public class InterupationService {
	Interupation InterupationObj = new Interupation();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInterupation() {
		return InterupationObj.readInterupation();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInterupation(@FormParam("province") String province,		
	 @FormParam("time") String time,
	 @FormParam("no_of_crew") String no_of_crew,
	 @FormParam("vehicle_no") String vehicle_no,
	 @FormParam("phone_no") String phone_no)
	{
	 String output = InterupationObj.insertInterupation(province, time, no_of_crew, vehicle_no, phone_no);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInterupation(String interupationData)
	{
	//Convert the input string to a JSON object
	 JsonObject intObject = new JsonParser().parse(interupationData).getAsJsonObject();
	//Read the values from the JSON object
	 String intID = intObject.get("intID").getAsString();
	 String province = intObject.get("province").getAsString();
	 String time = intObject.get("time").getAsString();
	 String no_of_crew = intObject.get("no_of_crew").getAsString();
	 String vehicle_no = intObject.get("vehicle_no").getAsString();
	 String phone_no = intObject.get("phone_no").getAsString();
	 String output = InterupationObj.updateInterupation(intID, province, time, no_of_crew, vehicle_no, phone_no);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInterupation(String interupationData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(interupationData, "", Parser.xmlParser());

	//Read the value from the element <>
	 String intID = doc.select("intID").text();
	 String output = InterupationObj.deleteInterupation(intID);
	return output;
	}
	
}
