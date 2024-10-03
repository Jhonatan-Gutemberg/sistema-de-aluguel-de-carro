import React, { useState } from "react";
import "./CreateOrder.css";
import axios from "axios";

const CreateOrder: React.FC = () => {
  const userId = localStorage.getItem("userId") || "";
  console.log(userId);
  const [customerId, setCustomerId] = useState(userId);
  const [automobileId, setAutomobileId] = useState("");
  const [creationDate, setCreationDate] = useState("");
  const [deliveryDate, setDeliveryDate] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const orderData = {
      customerId: customerId, 
      automobileId: parseInt(automobileId),
      creationDate,
      deliveryDate,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/order/register",
        orderData
      );
      if (response.status === 201) {
        setMessage("Order created successfully!");
        // setCustomerId('');
        // setAutomobileId('');
        // setCreationDate('');
        // setDeliveryDate('');
      }
    } catch (error) {
      setMessage("Error creating order. Please try again.");
      console.error(error);
    }
  };

  return (
    <div className="create-order-container">
      <div>
        <h2>Solicitar Aluguel</h2>
        {message && <p>{message}</p>}
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="customerId">ID Solicitante:</label>
            <input
              type="number"
              id="customerId"
              value={customerId}
              onChange={(e) => setCustomerId(e.target.value)}
              required
            />
          </div>
          <div>
            <label htmlFor="automobileId">ID Automóvel:</label>
            <input
              type="number"
              id="automobileId"
              value={automobileId}
              onChange={(e) => setAutomobileId(e.target.value)}
              required
            />
          </div>
          <div>
            <label htmlFor="creationDate">Data Início:</label>
            <input
              type="date"
              id="creationDate"
              value={creationDate}
              onChange={(e) => setCreationDate(e.target.value)}
              required
            />
          </div>
          <div>
            <label htmlFor="deliveryDate">Data de Entrega:</label>
            <input
              type="date"
              id="deliveryDate"
              value={deliveryDate}
              onChange={(e) => setDeliveryDate(e.target.value)}
              required
            />
          </div>
          <button className="send-order" type="submit">Enviar pedido de Aluguel</button>
        </form>
      </div>
    </div>
  );
};

export default CreateOrder;
