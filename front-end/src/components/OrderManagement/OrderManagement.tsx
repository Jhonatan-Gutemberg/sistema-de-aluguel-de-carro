import React, { useState, useEffect } from "react";
import "./OrderManagement.css";
import Card from "../Card/Card"; 

interface Order {
  id: number;
  creationDate: string;
  deliveryDate: string;
  status: boolean;
  customerId: number;
  automobileId: number;
  value: number | null;
  planType: string;
  agencyId: number;
}

const OrderManagement: React.FC = () => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [message, setMessage] = useState("");
  const [orderId, setOrderId] = useState("");
  const [singleOrder, setSingleOrder] = useState<Order | null>(null);

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const response = await fetch("http://localhost:8080/order/all");
      const data = await response.json();
      if (data.success) {
        setOrders(data.data); 
        setSingleOrder(null); 
      } else {
        setMessage(data.message);
      }
    } catch (error) {
      setMessage("Falha ao buscar pedidos");
    }
  };

  const fetchOrderById = async () => {
    try {
      const response = await fetch(`http://localhost:8080/order/${orderId}`);
      const data = await response.json();
      if (data.success) {
        setSingleOrder(data.data); 
        setMessage(""); 
        setOrders([]); 
      } else {
        setSingleOrder(null);
        setMessage(data.message);
      }
    } catch (error) {
      setSingleOrder(null);
      setMessage("Falha ao buscar pedido");
    }
  };

  const toggleOrderStatus = async (id: number) => {
    try {
      const response = await fetch(
        `http://localhost:8080/order/toggleStatus/${id}`,
        {
          method: "PUT",
        }
      );
      const data = await response.json();
      if (data.success) {
        setOrders((prevOrders) =>
          prevOrders.map((order) =>
            order.id === id ? { ...order, status: !order.status } : order
          )
        );

        if (singleOrder && singleOrder.id === id) {
          setSingleOrder((prevSingleOrder) =>
            prevSingleOrder
              ? { ...prevSingleOrder, status: !prevSingleOrder.status }
              : null
          );
        }
      } else {
        setMessage(data.message);
      }
    } catch (error) {
      setMessage("Falha ao mudar o status do pedido");
    }
  };

  const deleteOrder = async (id: number) => {
    try {
      const response = await fetch(`http://localhost:8080/order/${id}`, {
        method: "DELETE",
      });
      const data = await response.json();
      if (data.success) {

        setOrders((prevOrders) =>
          prevOrders.filter((order) => order.id !== id)
        );


        if (singleOrder && singleOrder.id === id) {
          setSingleOrder(null);
        }
        setMessage("Pedido excluído com sucesso.");
      } else {
        setMessage(data.message);
      }
    } catch (error) {
      setMessage("Falha ao excluir o pedido");
    }
  };

  const renderOrder = () => {
    if (singleOrder) {
      return (
        <Card
          key={singleOrder.id}
          id={singleOrder.id}
          creationDate={singleOrder.creationDate}
          deliveryDate={singleOrder.deliveryDate}
          status={singleOrder.status}
          customerId={singleOrder.customerId}
          automobileId={singleOrder.automobileId}
          value={singleOrder.value}
          planType={singleOrder.planType}
          agencyId={singleOrder.agencyId}
          onToggleStatus={toggleOrderStatus}
          onDelete={deleteOrder}
        />
      );
    } else if (message) {
      return <p className="message">{message}</p>;
    }
    return null;
  };

  return (
    <div className="order-management">
      <h2>Página de Gerenciamento de Pedidos</h2>
      <div className="button-container">
        <button onClick={fetchOrders}>Todos os Pedidos</button>
        <div className="search-container">
          <button onClick={fetchOrderById}>Buscar</button>
          <input
            type="text"
            placeholder="   ID"
            value={orderId}
            onChange={(e) => setOrderId(e.target.value)}
          />
        </div>
      </div>
      <div className="order-list">
        {singleOrder
          ? renderOrder()
          : orders.map((order) => (
              <Card
                key={order.id}
                id={order.id}
                creationDate={order.creationDate}
                deliveryDate={order.deliveryDate}
                status={order.status}
                customerId={order.customerId}
                automobileId={order.automobileId}
                value={order.value}
                planType={order.planType}
                agencyId={order.agencyId}
                onToggleStatus={toggleOrderStatus}
                onDelete={deleteOrder}
              />
            ))}{" "}
      </div>
    </div>
  );
};

export default OrderManagement;
