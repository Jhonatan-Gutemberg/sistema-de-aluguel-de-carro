import React from 'react';
import './Card.css';

interface CardProps {
    id: number;
    creationDate: string;
    deliveryDate: string;
    status: boolean;
    customerId: number;
    automobileId: number;
    value: number | null;
    planType: string;
    agencyId: number;
    onToggleStatus: (id: number) => void; 
    onDelete: (id: number) => void; 
}

const Card: React.FC<CardProps> = ({
    id,
    creationDate,
    deliveryDate,
    status,
    customerId,
    automobileId,
    value,
    planType,
    agencyId,
    onToggleStatus,
    onDelete,
}) => {
    return (
        <div className="card">
            <h4>{`Pedido ID: ${id}`}</h4>
            <p>{`Data de Criação: ${creationDate}`}</p>
            <p>{`Data de Entrega: ${deliveryDate}`}</p>
            <p>{`Status: ${status ? 'Ativo' : 'Inativo'}`}</p>
            <p>{`ID do Cliente: ${customerId}`}</p>
            <p>{`ID do Veículo: ${automobileId}`}</p>
            <p>{`Valor: ${value ? `R$ ${value.toFixed(2)}` : 'N/A'}`}</p>
            <p>{`Plano: ${planType}`}</p>
            <button className="toggle-status-button" onClick={() => onToggleStatus(id)}>
                {status ? "Desativar" : "Ativar"}
            </button>
            <button className="delete-button" onClick={() => onDelete(id)}>
                Excluir
            </button>
        </div>
    );
};

export default Card;
