import React from "react";
import LoginForm from "../components/LoginForm/LoginForm";

const Login: React.FC = () => {
  const handleLoginSuccess = () => {};

  return (
    <div>
      <LoginForm onLoginSuccess={handleLoginSuccess} />{" "}
    </div>
  );
};

export default Login;
