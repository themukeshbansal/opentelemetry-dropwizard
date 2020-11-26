package com.example.helloworld.resources;

import com.example.helloworld.core.Person;
import com.example.helloworld.db.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.opentelemetry.api.*;
import io.opentelemetry.api.trace.*;
import io.opentelemetry.context.propagation.*;

import javax.servlet.http.*;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private final PersonDAO peopleDAO;

    @Context
    private HttpServletRequest httpRequest;

//    TracerProvider tracerProvider = OpenTelemetry.getGlobalTracerProvider();
//    Tracer tracer = tracerProvider.get("instrumentation-library-name","1.0.0");
//    TextMapPropagator.Getter<HttpServletRequest> getter =
//            new TextMapPropagator.Getter<HttpServletRequest>() {
//                @Override
//                public Iterable<String> keys(HttpServletRequest httpServletRequest) {
//                    return null;
//                }
//
//                @Override
//                public String get(HttpServletRequest carrier, String key) {
//                    return carrier.getHeader(key);
//                }
//            };

    public PeopleResource(PersonDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @POST
    @UnitOfWork
    public Person createPerson(@Valid Person person) {
//        io.opentelemetry.context.Context extractedContext = OpenTelemetry.getGlobalPropagators().getTextMapPropagator().extract(
//                io.opentelemetry.context.Context.current(),
//                httpRequest,
//                getter);
//        // Push To Kafka
//        // A Separate Microservice Consumes from Kafka <-- How to connect the trace for that microservice ??
        return peopleDAO.create(person);
    }

    @GET
    @UnitOfWork
    public List<Person> listPeople() {
        return peopleDAO.findAll();
    }



}
