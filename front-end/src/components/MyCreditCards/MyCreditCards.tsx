import React, { useEffect, useState } from "react";
import axios from "axios";
import "./MyCreditCards.css";

interface CreditContract {
  id: number;
  amount: number;
  orderId: number;
  bankId: number;
  customerId: number; 
  approved: boolean;
}

const MyCreditCards: React.FC = () => {
  const [creditCards, setCreditCards] = useState<CreditContract[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchCreditCards = async () => {
      const userId = localStorage.getItem("userId");

      if (!userId) {
        setError("User ID not found. Please log in.");
        setLoading(false);
        return;
      }

      try {
        const USUARIOID = localStorage.getItem("USUARIOID");
        const response = await axios.get(
          `http://localhost:8080/creditContract/user/${USUARIOID}`
        );
        console.log("data", response.data);
        if (response.data.success) {
          setCreditCards(response.data.data);
        } else {
          setError("Failed to fetch credit cards.");
        }
      } catch (err) {
        setError("Você não possui nenhuma solicitação de crédito.");
      } finally {
        setLoading(false);
      }
    };

    fetchCreditCards();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="card-container-myCreditCards">
      {creditCards.map((card) => (
        <div key={card.id} className="card-myCreditCards">
          <h3>Id do Contrato de Crédito: {card.id}</h3>
          <p>Id do solicitnte: {card.customerId}</p>
          <p>Id do Pedido: {card.orderId}</p>
          <p>Id do Banco: {card.bankId}</p>
          <p>Valor Solicitado: ${card.amount}</p>
          <p>Aprovado: {card.approved ? "Sim" : "Não"}</p>
        </div>
      ))}
    </div>
  );
};

export default MyCreditCards;
