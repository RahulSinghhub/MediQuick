import axios from "axios";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import PharmacyManagerHeader from "../../../Components/PharmacyManagerHeader";
import PharmacyManagerOrder from "../../../Components/PharmacyManagerOrder";
import { URL } from '../../../config'

const ManagerAllOrders = () => {
    const pharmacyName = sessionStorage['name'];
    const name = sessionStorage['name'];
    const pharmacyId = sessionStorage["pharmacyId"]

    const [orders, setOrders] = useState([]);

    const loadAllOrders = () => {
        const url = `${URL}/pharmacymanager/allorders/${pharmacyId}`
        axios.post(url).then(response => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                setOrders(result.data)
            } else {
                toast.error(result.message)
            }
        })
    }

    useEffect( () => {
        loadAllOrders();
    }, [])


    return (
        <div>
            <PharmacyManagerHeader 
                pharmacyName ={pharmacyName}
                name={name}
            />

            {orders.map(order =>
                <PharmacyManagerOrder 
                    key={order.orderId}
                    orderId={order.orderId}
                    medicineItems={order.medicineItems}
                    customer={order.customer}
                    pharmacy={order.pharmacy}
                    status={order.status}
                    deliveryPerson={order.deliveryPerson}
                    displayAssignedTo={true}
                    displayAssignButton={false}
                    displayStatus={true}
                />
            )}
        </div>
    )
}

export default ManagerAllOrders