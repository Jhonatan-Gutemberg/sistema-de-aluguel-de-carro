import React, { useState, useEffect } from 'react';
import './AutomobileManager.css';

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

const AutomobileManager: React.FC = () => {
    const [cars, setCars] = useState<Automobile[]>([]);
    const [message, setMessage] = useState('');
    const [newCar, setNewCar] = useState<Automobile>({
        id: 0,
        name: '',
        valuePerDay: 0,
        year: new Date().getFullYear(),
        registrationNumber: '',
        brand: '',
        model: '',
        plate: '',
    });

    const [editCarId, setEditCarId] = useState<number | null>(null);
    const [updatedCar, setUpdatedCar] = useState<Automobile | null>(null);

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

    const handleCreateCar = async () => {
        try {
            const response = await fetch('http://localhost:8080/automobile/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newCar),
            });
            const data = await response.json();
            if (data.success) {
                setMessage('Carro criado com sucesso');
                setNewCar({ id: 0, name: '', valuePerDay: 0, year: new Date().getFullYear(), registrationNumber: '', brand: '', model: '', plate: '' });
                loadCars();
            } else {
                setMessage(data.message);
            }
        } catch (error) {
            setMessage('Erro ao criar carro');
        }
    };

    const handleUpdateCar = async (car: Automobile) => {
        try {
            const response = await fetch(`http://localhost:8080/automobile/update/${car.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(car),
            });
            const data = await response.json();
            if (data.success) {
                setMessage('Carro atualizado com sucesso');
                setEditCarId(null);
                setUpdatedCar(null);
                loadCars();
            } else {
                setMessage(data.message);
            }
        } catch (error) {
            setMessage('Erro ao atualizar carro');
        }
    };

    const handleDeleteCar = async (id: number) => {
        try {
            const response = await fetch(`http://localhost:8080/automobile/delete/${id}`, {
                method: 'DELETE',
            });
            const data = await response.json();
            if (data.success) {
                setMessage('Carro deletado com sucesso');
                loadCars();
            } else {
                setMessage(data.message);
            }
        } catch (error) {
            setMessage('Erro ao deletar carro');
        }
    };

    return (
        <div className="automobile-manager-container">
            <h2>Gerenciamento de Automóveis</h2>
            {message && <p className="message">{message}</p>}

            <div className="new-car-card">
                <h3>Criar Novo Carro</h3>
                <input
                    type="text"
                    placeholder="Nome"
                    value={newCar.name}
                    onChange={(e) => setNewCar({ ...newCar, name: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Marca"
                    value={newCar.brand}
                    onChange={(e) => setNewCar({ ...newCar, brand: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Modelo"
                    value={newCar.model}
                    onChange={(e) => setNewCar({ ...newCar, model: e.target.value })}
                />
                <input
                    type="number"
                    placeholder="Ano"
                    value={newCar.year}
                    onChange={(e) => setNewCar({ ...newCar, year: Number(e.target.value) })}
                />
                <input
                    type="text"
                    placeholder="Placa"
                    value={newCar.plate}
                    onChange={(e) => setNewCar({ ...newCar, plate: e.target.value })}
                />
                <input
                    type="number"
                    placeholder="Valor por Dia"
                    value={newCar.valuePerDay}
                    onChange={(e) => setNewCar({ ...newCar, valuePerDay: Number(e.target.value) })}
                />
                <button onClick={handleCreateCar}>Criar Carro</button>
            </div>

            <div className="car-table-container">
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
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {cars.map((car) => (
                            <tr
                                key={car.id}
                                onClick={() => {
                                    if (editCarId !== car.id) {
                                        setEditCarId(car.id);
                                        setUpdatedCar({ ...car });
                                    }
                                }}
                            >
                                <td>{car.id}</td>
                                <td>{car.name}</td>
                                <td>
                                    {editCarId === car.id ? (
                                        <input
                                            type="text"
                                            value={updatedCar?.brand}
                                            onChange={(e) => setUpdatedCar({ ...updatedCar!, brand: e.target.value })}
                                            placeholder="Marca"
                                        />
                                    ) : (
                                        car.brand
                                    )}
                                </td>
                                <td>
                                    {editCarId === car.id ? (
                                        <input
                                            type="text"
                                            value={updatedCar?.model}
                                            onChange={(e) => setUpdatedCar({ ...updatedCar!, model: e.target.value })}
                                            placeholder="Modelo"
                                        />
                                    ) : (
                                        car.model
                                    )}
                                </td>
                                <td>
                                    {editCarId === car.id ? (
                                        <input
                                            type="number"
                                            value={updatedCar?.year}
                                            onChange={(e) => setUpdatedCar({ ...updatedCar!, year: Number(e.target.value) })}
                                            placeholder="Ano"
                                        />
                                    ) : (
                                        car.year
                                    )}
                                </td>
                                <td>
                                    {editCarId === car.id ? (
                                        <input
                                            type="text"
                                            value={updatedCar?.plate}
                                            onChange={(e) => setUpdatedCar({ ...updatedCar!, plate: e.target.value })}
                                            placeholder="Placa"
                                        />
                                    ) : (
                                        car.plate
                                    )}
                                </td>
                                <td>
                                    {editCarId === car.id ? (
                                        <input
                                            type="number"
                                            value={updatedCar?.valuePerDay}
                                            onChange={(e) => setUpdatedCar({ ...updatedCar!, valuePerDay: Number(e.target.value) })}
                                            placeholder="Valor"
                                        />
                                    ) : (
                                        car.valuePerDay.toFixed(2)
                                    )}
                                </td>
                                <td>
                                    {editCarId === car.id ? (
                                        <button onClick={() => handleUpdateCar(updatedCar!)}>Salvar</button>
                                    ) : (
                                        <button className="btn-update" onClick={(e) => {
                                            e.stopPropagation(); // Previne que o click no botão atualize a linha
                                            setEditCarId(car.id);
                                            setUpdatedCar({ ...car });
                                        }}>
                                            Atualizar
                                        </button>
                                    )}
                                    <button className="btn-delete" onClick={() => handleDeleteCar(car.id)}>Deletar</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default AutomobileManager;
