package com.perfulandia.usuarioservice.service;

import com.perfulandia.usuarioservice.service.dto.PedidoDTO;
import com.perfulandia.usuarioservice.service.dto.NotificacionDTO;
import com.perfulandia.usuarioservice.service.dto.UserOverviewDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioAggregationService {

    private final RestTemplate restTemplate;
    private static final String PEDIDOS_URL       = "http://localhost:8082/api/pedidos/usuario/";
    private static final String NOTIF_URL         = "http://localhost:8083/api/notificaciones/usuario/";

    public UsuarioAggregationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserOverviewDTO getUserOverview(Long userId) {
        // 1️⃣ Llamadas a servicios externos
        PedidoDTO[] pedidos = restTemplate.getForObject(PEDIDOS_URL + userId, PedidoDTO[].class);
        NotificacionDTO[] notifs = restTemplate.getForObject(NOTIF_URL + userId, NotificacionDTO[].class);

        // 2️⃣ Convertir a listas (evitando nulls)
        List<PedidoDTO> pedidosList = pedidos != null
                ? Arrays.asList(pedidos)
                : Collections.emptyList();
        List<NotificacionDTO> notifsList = notifs != null
                ? Arrays.asList(notifs)
                : Collections.emptyList();

        // 3️⃣ Construir DTO con conteos usando el builder
        return UserOverviewDTO.builder()
                .pedidos(pedidosList)
                .notificaciones(notifsList)
                .pedidosCount(pedidosList.size())
                .notificacionesCount(notifsList.size())
                .build();
    }
}
