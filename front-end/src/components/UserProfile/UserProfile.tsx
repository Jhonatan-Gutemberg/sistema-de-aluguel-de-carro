import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './UserProfile.css'; 

interface UserData {
    id: number;
    name: string;
    address: string;
    type: string;
    status: boolean;
    registration: string;
    cpf: string;
    orders: number[];
    employments: any[];
    balance: number;
}

const UserProfile: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const [userData, setUserData] = useState<UserData | null>(null);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/systemUser/${id}`);
                if (!response.ok) {
                    throw new Error('Erro ao buscar dados do usuário.');
                }
                const data = await response.json();
                setUserData(data.data);
    
                localStorage.setItem("USUARIOID", data.data.id.toString());
            } catch (error: unknown) {
                if (error instanceof Error) {
                    setErrorMessage(error.message);
                } else {
                    setErrorMessage('Erro desconhecido.');
                }
            }
        };
    
        fetchUserData();
    }, [id]);
    

    if (errorMessage) {
        return <div className="error">{errorMessage}</div>;
    }

    if (!userData) {
        return <div>Loading...</div>;
    }

    return (
        <div className="user-profile-card">
            <h2>User Profile</h2>
            <p><strong>ID:</strong> {userData.id}</p>
            <p><strong>Email:</strong> {userData.name}</p>
            <p><strong>Endereço:</strong> {userData.address}</p>
            <p><strong>Tipo de usuário:</strong> {userData.type}</p>
            <p><strong>Saldo:</strong> R$ {userData.balance}</p>
            {/* <p><strong>Status:</strong> {userData.status ? 'Active' : 'Inactive'}</p>
            <p><strong>Registration:</strong> {userData.registration || 'N/A'}</p>
            <p><strong>CPF:</strong> {userData.cpf || 'N/A'}</p>
            <p><strong>Orders:</strong> {userData.orders && userData.orders.length > 0 ? userData.orders.join(', ') : 'None'}</p>
            <p><strong>Employments:</strong> {userData.employments && userData.employments.length > 0 ? userData.employments.join(', ') : 'None'}</p> */}
        </div>
    );
};

export default UserProfile;
