package com.ru8anraj.kanban.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ru8anraj.kanban.model.Ticket;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "ticket", description = "the ticket API")
public interface TicketApi {

	@ApiOperation(value = "Add a new ticket to the kanban", nickname = "addTicket", notes = "", tags = { "ticket", })
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/ticket", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Void> addTicket(
			@ApiParam(value = "Ticket object that needs to be added to the kanban", required = true) @Valid @RequestBody Ticket body);

	@ApiOperation(value = "Deletes a ticket", nickname = "deleteTicket", notes = "", tags = { "ticket", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Ticket not found") })
	@RequestMapping(value = "/ticket/{ticketId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteTicket(
			@ApiParam(value = "Ticket id to delete", required = true) @PathVariable("ticketId") Long ticketId,
			@ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey);

	@ApiOperation(value = "Finds all tickets", nickname = "findAllTickets", notes = "Fetches all the tickets from the DB", response = Ticket.class, responseContainer = "List", tags = {
			"ticket", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = Ticket.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "No tickets available") })
	@RequestMapping(value = "/ticket", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<Ticket>> findAllTickets();

	@ApiOperation(value = "Finds ticket by status", nickname = "findTicketsByStatus", notes = "Multiple status values can be provided with comma separated strings", response = Ticket.class, responseContainer = "List", tags = {
			"ticket", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = Ticket.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Invalid status value") })
	@RequestMapping(value = "/ticket/findByStatus", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<Ticket>> findTicketsByStatus(
			@NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "available, pending, sold") @Valid @RequestParam(value = "status", required = true) List<String> status);

	@ApiOperation(value = "Find ticket by ID", nickname = "getTicketById", notes = "Returns a single ticket", response = Ticket.class, tags = {
			"ticket", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Ticket.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Ticket not found") })
	@RequestMapping(value = "/ticket/{ticketId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Ticket> getTicketById(
			@ApiParam(value = "ID of ticket to return", required = true) @PathVariable("ticketId") Long ticketId);

	@ApiOperation(value = "Update an existing ticket", nickname = "updateTicket", notes = "", tags = { "ticket", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Ticket not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/ticket/{ticketId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Void> updateTicket(
			@ApiParam(value = "ID of ticket to return", required = true) @PathVariable("ticketId") Long ticketId,
			@ApiParam(value = "Ticket object that needs to be added to the kanban", required = true) @Valid @RequestBody Ticket body);

}
