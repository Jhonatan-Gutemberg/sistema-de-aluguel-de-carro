
import React, { useEffect, useState } from 'react';
import MyOrders from '../components/MyOrders/MyOrders';

const MyOrdersPage: React.FC = () => {
    const [userId, setUserId] = useState<number | null>(null);

    useEffect(() => {
        
        const storedUserId = localStorage.getItem('userId');

        if (storedUserId) {
            setUserId(parseInt(storedUserId, 10));
        }
    }, []);

    return (
        <div>
            {userId ? <MyOrders userId={userId} /> : <p>Carregando...</p>}
        </div>
    );
};

export default MyOrdersPage;
