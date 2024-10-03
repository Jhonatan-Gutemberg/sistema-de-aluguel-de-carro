import React, { useEffect, useState } from "react";
import axios from "axios";
import "./MyOrders.css";

interface Order {
  id: number;
  creationDate: string;
  deliveryDate: string;
  status: boolean;
  customerId: number;
  automobileId: number;
  value: number | null;
  planType: string;
  creditContract: any;
  agencyId: number;
}

interface MyOrdersProps {
  userId: number;
}

const MyOrders: React.FC<MyOrdersProps> = ({ userId }) => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState(""); 
  const [showInput, setShowInput] = useState<number | null>(null);
  const [creditAmount, setCreditAmount] = useState("");
  const [orderIdInput, setOrderIdInput] = useState("");
  const [bankIdInput, setBankIdInput] = useState("");

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/order/user/${userId}`
        );

        if (!response.ok) {
          throw new Error("Erro ao buscar as ordens.");
        }

        const data = await response.json();
        setOrders(data);
      } catch (error: unknown) {
        if (error instanceof Error) {
          setErrorMessage(error.message);
        } else {
          setErrorMessage("Erro desconhecido.");
        }
      }
    };

    fetchOrders();
  }, [userId]);

  const handlePayment = async (order: Order) => {
    try {
      const response = await axios.post(
        `http://localhost:8080/customer/pay/${order.customerId}/${order.id}/${order.value}`
      );

      if (response.data.success) {
        alert("Pagamento realizado com sucesso!");

        try {
          const statusResponse = await fetch(
            `http://localhost:8080/order/toggleStatus/${order.id}`,
            {
              method: "PUT",
            }
          );

          if (!statusResponse.ok) {
            throw new Error("Erro ao alterar o status da ordem.");
          }

          setOrders((prevOrders) =>
            prevOrders.map((o) =>
              o.id === order.id ? { ...o, status: !o.status } : o
            )
          );
        } catch (error) {
          console.error("Erro ao alterar o status da ordem:", error);
          alert("Ocorreu um erro ao alterar o status da ordem.");
        }
      } else {
        alert("Erro ao processar o pagamento: " + response.data.message);
      }
    } catch (error) {
      alert("Ocorreu um erro ao processar o pagamento.");
    }
  };

  const handleDelete = async (id: number) => {
    
    const orderToDelete = orders.find((order) => order.id === id);
  
    if (orderToDelete && orderToDelete.status) {
      alert("Não é possível cancelar um pedido que já foi pago.");
      return; 
    }

    try {
      await axios.delete(`http://localhost:8080/order/${id}`);

      const updatedOrders = orders.filter((order) => order.id !== id);
      setOrders(updatedOrders);
    } catch (error) {
      alert("Ocorreu um erro ao cancelar a ordem.");
    }
  };

  const handleCreditRequest = (id: number) => {
    setShowInput(id);
  };

  const handleSubmitCredit = async () => {
    if (!creditAmount || isNaN(parseFloat(creditAmount))) {
      alert("Por favor, insira um valor válido para o crédito.");
      return;
    }

    if (!orderIdInput || isNaN(parseInt(orderIdInput))) {
      alert("Por favor, insira um ID válido da ordem.");
      return;
    }

    if (!bankIdInput || isNaN(parseInt(bankIdInput))) {
      alert("Por favor, insira um ID válido do banco.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/creditContract/register",
        {
          userId: userId,
          amount: parseFloat(creditAmount),
          orderId: parseInt(orderIdInput),
          bankId: parseInt(bankIdInput),
        }
      );

      if (response.status === 201) {
        alert("Contrato de crédito registrado com sucesso!");
        setCreditAmount("");
        setOrderIdInput("");
        setBankIdInput("");
        setShowInput(null);
      } else {
        alert("Erro ao registrar o contrato de crédito.");
      }
    } catch (error) {
      console.error("Erro ao solicitar crédito:", error);
      alert("Ocorreu um erro ao solicitar crédito.");
    }
  };

  const handleCreateOrder = async () => {

    setSuccessMessage("Order created successfully!"); 
  };

  return (
    <div>
      <h2>Minhas Ordens</h2>
      {errorMessage && <div className="error">{errorMessage}</div>}
      {successMessage && <div className="success">{successMessage}</div>}{" "}
      {/* Mensagem de sucesso */}
      {orders.length === 0 ? (
        <p>Você não possui ordens.</p>
      ) : (
        <div className="orders-container-my-orders">
          {orders.map((order) => (
            <div key={order.id} className="order-card-my-orders">
              <h3>Ordem ID: {order.id}</h3>
              <p>
                Data de Criação:{" "}
                {new Date(order.creationDate).toLocaleDateString()}
              </p>
              <p>
                Data de Entrega:{" "}
                {new Date(order.deliveryDate).toLocaleDateString()}
              </p>
              <p>Status: {order.status ? "Ativo" : "Inativo"}</p>
              <p>
                Valor: R${" "}
                {order.value !== null ? order.value.toFixed(2) : "N/A"}
              </p>
              <p>Tipo de Plano: {order.planType}</p>
              <p>ID do Cliente: {order.customerId}</p>
              <p>ID do Automóvel: {order.automobileId}</p>
              <p>ID da Agência: {order.agencyId}</p>

              <button
                onClick={() => handleDelete(order.id)}
                disabled={order.status}
                className={order.status ? "button-disabled" : "button-cancel"}
              >
                Cancelar
              </button>

              <button
                onClick={() => handleCreditRequest(order.id)}
                disabled={order.status}
                className={order.status ? "button-disabled" : "button-request-credit"}
              >
                Solicitar Crédito
              </button>
              <button
                onClick={() => handlePayment(order)}
                disabled={order.status}
                className={order.status ? "button-disabled" : ""}
              >
                {order.status ? "Pago" : "Pagar"}{" "}
              </button>

              {showInput === order.id && (
                <div className="inputs">
                  <input
                    type="number"
                    value={creditAmount}
                    onChange={(e) => setCreditAmount(e.target.value)}
                    placeholder="Valor do Crédito (amount)"
                  />
                  <input
                    type="text"
                    value={orderIdInput}
                    onChange={(e) => setOrderIdInput(e.target.value)}
                    placeholder="ID da Ordem (orderId)"
                  />
                  <input
                    type="text"
                    value={bankIdInput}
                    onChange={(e) => setBankIdInput(e.target.value)}
                    placeholder="ID do Banco (bankId)"
                  />
                  <button onClick={handleSubmitCredit}>Confirmar</button>
                </div>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default MyOrders;
