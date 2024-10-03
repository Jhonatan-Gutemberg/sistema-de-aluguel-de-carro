import React, { useState } from 'react';
import axios from 'axios';
import './UserRegisterForm.css'; 

interface SystemUserRegisterDTO {
  name: string;
  address: string;
  type: SystemUserType;
  password: string;
}

enum SystemUserType {
  SYSTEMUSER = 'SYSTEMUSER',
  AGENCY = 'AGENCY',
  BANK = 'BANK',
  CUSTOMER = 'CUSTOMER',
}

const UserRegisterForm: React.FC = () => {
  const [formData, setFormData] = useState<SystemUserRegisterDTO>({
    name: '',
    address: '',
    type: SystemUserType.CUSTOMER,
    password: '',
  });

  const [error, setError] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();


    const endpoint = formData.type === SystemUserType.SYSTEMUSER ? 'systemUser' : formData.type.toLowerCase();

    try {
      const response = await axios.post(`http://localhost:8080/${endpoint}/register`, {
        ...formData,
        status: true, 
      });
      setSuccessMessage(response.data.message);
      setError('');
      setFormData({
        name: '',
        address: '',
        type: SystemUserType.CUSTOMER, 
        password: '',
      });
    } catch (error: any) {
      setError(error.response?.data?.message || 'Erro ao cadastrar usuário');
      setSuccessMessage('');
    }
  };

  return (
    <div className="container">
      <h2>Cadastro de Usuário</h2>
      {successMessage && <p className="success">{successMessage}</p>}
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name" className="label">Email:</label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="address" className="label">Endereço:</label>
          <input
            type="text"
            id="address"
            name="address"
            value={formData.address}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="type" className="label">Tipo:</label>
          <select
            id="type"
            name="type"
            value={formData.type}
            onChange={handleChange}
          >
            <option value={SystemUserType.SYSTEMUSER}>System User</option>
            <option value={SystemUserType.AGENCY}>Agency</option>
            <option value={SystemUserType.BANK}>Bank</option>
            <option value={SystemUserType.CUSTOMER}>Customer</option>
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="password" className="label">Senha:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <button className='register' type="submit">Cadastrar</button>
      </form>
    </div>
  );
};

export default UserRegisterForm;
