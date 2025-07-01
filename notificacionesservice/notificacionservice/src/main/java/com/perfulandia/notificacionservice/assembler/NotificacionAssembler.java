package com.perfulandia.notificacionservice.assembler;

import com.perfulandia.notificacionservice.controller.NotificacionController;
import com.perfulandia.notificacionservice.model.Notificacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class NotificacionAssembler implements RepresentationModelAssembler<Notificacion, EntityModel<Notificacion>> {

    @Override
    public EntityModel<Notificacion> toModel(Notificacion notificacion) {
        return EntityModel.of(
                notificacion,
                linkTo(methodOn(NotificacionController.class).buscar(notificacion.getId())).withSelfRel(),
                linkTo(methodOn(NotificacionController.class).listar()).withRel("notificaciones")
        );
    }
}
