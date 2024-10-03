
import React from "react";
import { Link, useNavigate } from "react-router-dom"; 
import "./Header.css";

interface HeaderProps {
  userId: number | null;
  estaLogado: boolean;
  userType: string;
  onLogout: () => void;
}

const Header: React.FC<HeaderProps> = ({
  estaLogado,
  userType,
  onLogout,
  userId,
}) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    onLogout();
    navigate("/");
  };

  return (
    <header className="header">
      <nav className="navbar">
        <ul className="nav-list">
          <li className="nav-item">
            <Link to="/" className="nav-link">
              Página Inicial
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/bank" className="nav-link">
              Bancos
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/register" className="nav-link">
              Registre-se
            </Link>
          </li>
          {estaLogado && userType === "AGENCY" && (
            <li className="nav-item">
              <Link to="/orders" className="nav-link">
                Pedidos
              </Link>
            </li>
          )}
          {estaLogado && userType === "AGENCY" && (
            <li className="nav-item">
              <Link to="/car-crud" className="nav-link">
                Grenciamento de Carros
              </Link>
            </li>
          )}
          {estaLogado && userType === "CUSTOMER" && (
            <li className="nav-item">
              <Link to="/creditCard" className="nav-link">
                Solicitações de Credito
              </Link>
            </li>
          )}
          {estaLogado && userType === "CUSTOMER" && (
            <li className="nav-item">
              <Link to="/create-order" className="nav-link">
                Criar um Pedido
              </Link>
            </li>
          )}
          {
            <li className="nav-item">
              <Link to="/car-management" className="nav-link">
                Carros
              </Link>
            </li>
          }
          {estaLogado && userType === "CUSTOMER" && (
            <li className="nav-item">
              <Link to="/my-orders" className="nav-link">
                Meus Pedidos
              </Link>
            </li>
          )}
          {estaLogado && (
            <li className="nav-item">
              <Link to={`/user-profile/${userId}`} className="nav-link">
                Meus Dados
              </Link>
            </li>
          )}
          {estaLogado && userType === "BANK" && (
            <li className="nav-item">
              <Link to={`/requestContracts/${userId}`} className="nav-link">
                Pedidos de Crédito
              </Link>
            </li>
          )}
        </ul>
        <ul className="nav-list-right">
          {!estaLogado ? (
            <li className="nav-item">
              <Link to="/login" className="nav-link">
                Login
              </Link>
            </li>
          ) : (
            <li className="nav-item">
              <button onClick={handleLogout} className="nav-link">
                Logout
              </button>
            </li>
          )}
        </ul>
      </nav>
    </header>
  );
};

export default Header;
