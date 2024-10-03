// src/components/LoginForm/LoginForm.tsx
import React, { useState } from 'react';
import './LoginForm.css';
import { useNavigate } from 'react-router-dom';

interface UserInfo {
    userType: string;
    id: number;
    estaLogado: boolean; 
}

interface LoginFormProps {
    onLoginSuccess: (tipo: string, id: number) => void; 
}

const LoginForm: React.FC<LoginFormProps> = ({ onLoginSuccess }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const navigate = useNavigate(); 

const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const userLoginDTO = {
        name: email,
        password: password,
    };

    try {
        const response = await fetch('http://localhost:8080/systemUser/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userLoginDTO),
        });

        if (!response.ok) {
            throw new Error('Email ou senha incorretos.');
        }

        const data: UserInfo = await response.json();
        localStorage.setItem('userId', data.id.toString());
        onLoginSuccess(data.userType, data.id);
        
        navigate(`/user-profile/${data.id}`); 
    } catch (error: unknown) {
        if (error instanceof Error) {
            setErrorMessage(error.message);
        } else {
            setErrorMessage('Erro desconhecido.');
        }
        setSuccessMessage('');
    }
};


    return (
        <div className="login-form-container">
            <h2>Login</h2>
            {successMessage && <div className="success">{successMessage}</div>}
            {errorMessage && <div className="error">{errorMessage}</div>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label className="label">Email:</label>
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="label">Senha:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button className='login-button' type="submit">Entrar</button>
            </form>
        </div>
    );
};

export default LoginForm;