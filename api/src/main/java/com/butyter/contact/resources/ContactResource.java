package com.butyter.contact.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.butyter.contact.core.domain.model.ContactDTO;
import com.butyter.contact.core.service.ContactService;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;


@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
@Component
@Timed(name = "contactTimer")
@Metered(name = "contactMeter")
@ExceptionMetered(name = "contactExceptionMeter")
public class ContactResource {

	@Autowired
    private final ContactService contactService;

    public ContactResource(ContactService contactService) {
        this.contactService = contactService;
    }

    @GET
    @Path("/{id}")
    public Response getContactById(@PathParam("id") int id) {
        ContactDTO contact = contactService.getContactById(id);
        return Response.ok(contact).build();
    }

    @GET
    public Response getAllContacts() {
        List<ContactDTO> contacts = contactService.getAllContacts();
        return Response.ok(contacts).build();
    }

    @POST
    public Response createContact(@Valid ContactDTO contact) throws URISyntaxException {
        return Response.created(new URI(String.valueOf(contactService.createContact(contact)))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id) {
        contactService.deleteContact(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, @Valid ContactDTO contact) {
        contact.setId(id);
        contactService.updateContact(contact);
        return Response.ok(contact).build();
    }

}
