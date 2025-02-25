package com.example.entity.dto;

import com.example.entity.Cliente;
import com.example.entity.DetallePedido;
import com.example.entity.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerarPedidoDTO {

    private Pedido pedido;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Iterable<DetallePedido> detallePedidos;

    private Cliente cliente;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Iterable<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(Iterable<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
