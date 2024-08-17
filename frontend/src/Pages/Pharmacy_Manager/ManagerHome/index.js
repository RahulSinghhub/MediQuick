import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router'
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import { URL } from '../../../config'
import PharmacyManagerOrder from "../../../Components/PharmacyManagerOrder";
import PharmacyManagerHeader from "../../../Components/PharmacyManagerHeader";

const ManagerHome = () => {
    const loginStatus = sessionStorage['loginStatus'];
    const pharmacyId = sessionStorage['pharmacyId'];
    const pharmacyName = sessionStorage['name'];
    const name = sessionStorage['name'];
    const [orders, setorders] = useState([]);

    const navigate = useNavigate();

    const assignDeliveryPerson = (orderId) => {
        navigate("/manager/assigndelivery", {
            state: { orderId: orderId }
        })
    }

    const loadOrders = () => {
        const url = `${URL}/pharmacymanager/arrivedorders/${pharmacyId}`
        axios.post(url).then((response) => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                setorders(result.data);
            } else {
                toast.error(result.message);
            }
        })
    }

    useEffect (() => {
        loadOrders();
    }, [])

    return (
        loginStatus === '1' ? 
        <div>
            <PharmacyManagerHeader 
                pharmacyName ={pharmacyName}
                name={name}
            />

            <div>
                {orders && orders.map(order =>
                    <PharmacyManagerOrder 
                        key={order.orderId}
                        orderId={order.orderId}
                        medicineItems={order.medicineItems}
                        customer={order.customer}
                        pharmacy={order.pharmacy}
                        status={order.status}
                        deliveryPerson={order.deliveryPerson}
                        assignDeliveryPerson={assignDeliveryPerson}
                        displayAssignedTo={false}
                        displayAssignButton={true}
                    />
                )}
            </div>
        </div>
        
        :  <div>You have not Signed In. Please <Link to="/manager/signin">Sign In here</Link></div>
        
    )
}

export default ManagerHome