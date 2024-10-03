import React, { useEffect, useState } from 'react';
import './Bank.css'; 

interface BankData {
    id: number;
    name: string;
    address: string;
    type: string;
    status: boolean;
    limitValue: number;
}

const Bank: React.FC = () => {
    const [banks, setBanks] = useState<BankData[]>([]);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        const fetchBanks = async () => {
            try {
                const response = await fetch('http://localhost:8080/bank/all');
                
                if (!response.ok) {
                    throw new Error('Erro ao buscar os bancos.');
                }

                const data = await response.json();
                setBanks(data.data); 
            } catch (error: unknown) {
                if (error instanceof Error) {
                    setErrorMessage(error.message);
                } else {
                    setErrorMessage('Erro desconhecido.');
                }
            }
        };

        fetchBanks();
    }, []);

    return (
        <div>
            <h2>Lista de Bancos para Solicitação de Crédito</h2>
            {errorMessage && <div className="error">{errorMessage}</div>}
            {banks.length === 0 ? (
                <p>Nenhum banco encontrado.</p>
            ) : (
                <div className="banks-container">
                    {banks.map((bank) => (
                        <div key={bank.id} className="bank-card">
                            <h3>{bank.name}</h3>
                            <p><strong>Id:</strong> {bank.id}</p>
                            <p><strong>Endereço:</strong> {bank.address}</p>
                            <p><strong>Tipo:</strong> {bank.type}</p>
                            <p><strong>Status:</strong> {bank.status ? 'Ativo' : 'Inativo'}</p>
                            <p><strong>Limite de Crédito:</strong> R$ {bank.limitValue.toFixed(2)}</p>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Bank;
