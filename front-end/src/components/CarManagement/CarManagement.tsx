import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom'; 
import './CarManagement.css';

export interface Automobile {
    id: number; 
    name: string; 
    valuePerDay: number; 
    year: number; 
    registrationNumber?: string; 
    brand: string; 
    model: string; 
    plate: string; 
}

const CarManagement: React.FC = () => {
    const [cars, setCars] = useState<Automobile[]>([]);
    const [message, setMessage] = useState('');

    useEffect(() => {
        loadCars();
    }, []);

    const fetchCars = async () => {
        const response = await fetch('http://localhost:8080/automobile/all');
        return await response.json();
    };

    const loadCars = async () => {
        try {
            const data = await fetchCars();
            if (data.success) {
                setCars(data.data);
            } else {
                setMessage(data.message);
            }
        } catch (error) {
            setMessage('Falha ao buscar carros');
        }
    };

    return (
        <div className="car-management">
            <h2>Carros Disponíveis para Aluguel</h2>
            {message && <p className="message">{message}</p>}

            <table className="car-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Marca</th>
                        <th>Modelo</th>
                        <th>Ano</th>
                        <th>Placa</th>
                        <th>Valor por Dia (R$)</th>
                    </tr>
                </thead>
                <tbody>
                    {cars.map((car) => (
                        <tr key={car.id}>
                            <td>{car.id}</td>
                            <td>{car.name}</td>
                            <td>{car.brand}</td>
                            <td>{car.model}</td>
                            <td>{car.year}</td>
                            <td>{car.plate}</td>
                            <td>{car.valuePerDay.toFixed(2)}</td>
                            {/* <td>
                                <Link to="/create-order">
                                    <button className="btn-rent">Alugar</button> 
                                </Link>
                            </td> */}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default CarManagement;
