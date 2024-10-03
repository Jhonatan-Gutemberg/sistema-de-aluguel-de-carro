import React, { useEffect, useState } from "react";
import axios from "axios";
import "./OrdersRequest.css";

type CreditContract = {
  customerId: string;
  id: number;
  amount: number;
  orderId: number;
  bankId: number;
  approved: boolean;
};

type Order = {
  id: number;
  creationDate: string;
  deliveryDate: string;
  status: boolean;
  customerId: number;
  automobileId: number;
  value: number;
  planType: string;
  agencyId: number;
};

const OrdersRequest: React.FC = () => {
  const [contracts, setContracts] = useState<CreditContract[]>([]);
  const [orders, setOrders] = useState<Order[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchContracts = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/creditContract/all"
        );
        const userId = localStorage.getItem("userId");
        console.log(response.data);

        if (response.data.success) {
          const filteredContracts = response.data.data.filter(
            (contract: CreditContract) => contract.bankId === Number(userId)
          );
          setContracts(filteredContracts);
        }
      } catch (err) {
        setError("Erro ao buscar contratos");
      }
    };

    const fetchOrders = async () => {
      try {
        const response = await axios.get("http://localhost:8080/order/all");
        const userId = localStorage.getItem("userId");
        if (response.data.success) {
          const filteredOrders = response.data.data.filter(
            (order: Order) => order.customerId === Number(userId)
          );
          setOrders(filteredOrders);
        }
      } catch (err) {
        setError("Erro ao buscar pedidos");
      }
    };

    fetchContracts();
    fetchOrders();
  }, []);

  const handleApprove = async (id: number) => {
    try {
      const statusResponse = await fetch(
        `http://localhost:8080/creditContract/approve/${id}`,
        {
          method: "PUT",
        }
      );

      if (!statusResponse.ok) {
        throw new Error("Erro ao atualizar o status do contrato");
      }

      setContracts((prevContracts) =>
        prevContracts.map((contract) =>
          contract.id === id
            ? { ...contract, approved: !contract.approved }
            : contract
        )
      );
    } catch (err) {
      setError("Erro ao aprovar o contrato");
      console.error(err);
    }
  };

  const addBalance = async (customerId: number, amount: number) => {
    try {
      const response = await axios.put(
        `http://localhost:8080/customer/addBalance/${customerId}/${amount.toFixed(
          2
        )}`
      );

      if (response.data.success) {
        alert("Saldo atualizado com sucesso!");
      } else {
        alert("Erro ao atualizar saldo");
      }
    } catch (err) {
      console.error("Erro ao adicionar saldo:", err);
    }
  };

  return (
    <div className="orders-request">
      <h2>Contratos de Crédito</h2>
      {error && <p className="error">{error}</p>}
      {contracts.length > 0 ? (
        <div className="cards-container">
          {contracts.map((contract) => {
            return (
              <div className="card" key={contract.id}>
                <h3>Contrato ID: {contract.id}</h3>
                <p>
                  <strong>Valor:</strong> R$ {contract.amount.toFixed(2)}
                </p>
                <p>
                  <strong>ID do Pedido:</strong> {contract.orderId}
                </p>
                <p>
                  <strong>ID do Cliente:</strong> {contract.customerId || "N/A"}
                </p>{" "}
                <p>
                  <strong>ID do Banco:</strong> {contract.bankId}
                </p>
                <p>
                  <strong>Aprovado:</strong> {contract.approved ? "Sim" : "Não"}
                </p>
                <button
                  className="approve-button"
                  onClick={() => handleApprove(contract.id)}
                >
                  {contract.approved ? "Desaprovar" : "Aprovar"}
                </button>
                <button
                  className="approve-button"
                  onClick={() =>
                    addBalance(parseInt(contract.customerId), contract.amount)
                  }
                >
                  Adicionar Saldo
                </button>
              </div>
            );
          })}
        </div>
      ) : (
        <p>Nenhum contrato encontrado.</p>
      )}

      <h2>Pedidos Relacionados</h2>
      {orders.length > 0 ? (
        <div className="orders-container">
          {orders.map((order) => (
            <div className="order-card" key={order.id}>
              <h3>Pedido ID: {order.id}</h3>
              <p>
                <strong>Data de Criação:</strong> {order.creationDate}
              </p>
              <p>
                <strong>Data de Entrega:</strong> {order.deliveryDate}
              </p>
              <p>
                <strong>Status:</strong>{" "}
                {order.status ? "Aprovado" : "Pendente"}
              </p>
              <p>
                <strong>Valor:</strong> R$ {order.value.toFixed(2)}
              </p>
              <p>
                <strong>Tipo de Plano:</strong> {order.planType}
              </p>
              <button
                className="approve-button"
                onClick={() => handleApprove(order.id)}
              >
                Aprovar
              </button>
              <button
                className="approve-button"
                onClick={() => handleApprove(order.id)}
              >
                Aprovar
              </button>
            </div>
          ))}
        </div>
      ) : (
        <p>Nenhum pedido encontrado para este cliente.</p>
      )}
    </div>
  );
};

export default OrdersRequest;
