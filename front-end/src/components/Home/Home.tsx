import React from "react";
import { Link } from "react-router-dom";
import "./Home.css";

const Home: React.FC = () => {
  return (
    <div className="home-container">
      <header className="home-header">
        <h1>Bem-vindo ao Sistema de Aluguel de Carros!</h1>
        <p>Alugue o carro dos seus sonhos de forma fácil e rápida!</p>
        <Link to="/car-management" className="view-cars-button">
          Ver Carros
        </Link>
      </header>

      <section className="services-section">
        <h2>Nossos Serviços</h2>
        <ul className="services-list">
          <li className="service-item">
            <h3>Aluguel de Carros</h3>
            <p>
              Escolha entre uma vasta gama de veículos de diversas marcas e
              modelos para atender às suas necessidades.
            </p>
          </li>
          <li className="service-item">
            <h3>Facilidade de Reserva</h3>
            <p>
              Reserve seu carro online de forma rápida e fácil, sem
              complicações.
            </p>
          </li>
          <li className="service-item">
            <h3>Suporte ao Cliente</h3>
            <p>
              Nossa equipe está disponível para ajudar você em qualquer etapa do
              processo de aluguel.
            </p>
          </li>
        </ul>
      </section>

      <section className="credit-request-section">
        <h2>Solicitação de Crédito</h2>
        <p>
          Caso não tenha dinheiro suficiente, você pode solicitar um pedido de
          crédito a um dos nossos parceiros bancários. O processo é simples e
          rápido, garantindo que você possa alugar o carro que deseja sem
          preocupações.
        </p>
      </section>

      <section className="refund-policy-section">
        <h2>Política de Reembolso</h2>
        <p>
          Se você já efetuou o pagamento de um pedido e deseja solicitar um
          reembolso, será necessário comparecer à agência pessoalmente. Nossa
          equipe estará à disposição para orientá-lo em cada etapa do processo.
        </p>
      </section>

      <section className="registration-section">
        <h2>Cadastro</h2>
        <p>
          Para realizar pedidos, é imprescindível que você se cadastre em nosso
          sistema. O cadastro é rápido e fácil, e garante que você possa acessar
          todos os nossos serviços com comodidade.
        </p>
      </section>
    </div>
  );
};

export default Home;
