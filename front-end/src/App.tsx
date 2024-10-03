// src/App.tsx
import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/Header/Header";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Orders from "./pages/Orders";
import CreateOrder from "./components/CreateOrder/CreateOrder";
import CarManagement from "./components/CarManagement/CarManagement";
import LoginForm from "./components/LoginForm/LoginForm";
import MyOrdersPage from "./pages/MyOrdersPage";
import AutomobileManager from "./components/AutomobileManager/AutomobileManager";
import UserProfile from "./components/UserProfile/UserProfile";
import Bank from "./components/Bank/Bank";
import MyCreditCards from "./components/MyCreditCards/MyCreditCards";
import OrdersRequest from "./components/OrdersRequest/OrdersRequest";

const App: React.FC = () => {
  const [estaLogado, setEstaLogado] = useState(false);
  const [userType, setUserType] = useState<string>("");
  const [userId, setUserId] = useState<number | null>(null);
  const handleLoginSuccess = (tipo: string, id: number) => {
    setEstaLogado(true);
    setUserType(tipo);
    setUserId(id);
  };

  const handleLogout = () => {
    setEstaLogado(false);
    setUserType("");
    setUserId(null);
  };

  return (
    <Router>
      <Header
        estaLogado={estaLogado}
        userType={userType}
        onLogout={handleLogout}
        userId={userId}
      />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Register />} />
        <Route path="/orders" element={<Orders />} />
        <Route path="/create-order" element={<CreateOrder />} />
        <Route path="/car-management" element={<CarManagement />} />
        <Route
          path="/login"
          element={<LoginForm onLoginSuccess={handleLoginSuccess} />}
        />
        <Route path="/my-orders" element={<MyOrdersPage />} />
        <Route path="/user-profile/:id" element={<UserProfile />} />
        <Route path="/car-crud" element={<AutomobileManager />} />
        <Route path="/bank" element={<Bank />} />
        <Route path="/creditCard" element={<MyCreditCards />} />
        <Route path="/requestContracts/:id" element={<OrdersRequest />} />
      </Routes>
    </Router>
  );
};

export default App;
