import './index.css'
//import MedicineItemVertical from "../../Components/MedicineItemVertical"
//import MedicineItemHorizontal from "../../Components/MedicineItemHorizontal"
import PharmacyManagerOrder from '../../Components/PharmacyManagerOrder'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { toast } from 'react-toastify'

const TestPage = () => {

    const url = "http://localhost:8084/api/v1/pharmacymanager/arrivedorders/2";
    const [arrivedOrders, setArrivedOrders] = useState([])

    const assignDeliveryPerson = (orderId) => {
        console.log("Order No: " + orderId + " is to be assigned")
    }

    const loadArrivedOrders = () => {
        axios.post(url).then(response => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                setArrivedOrders(result.data)
                // console.log(arrivedOrders);
            } else {
                toast.error(result.message);
            }
        })
    }

    useEffect(() => {
        loadArrivedOrders();
    }, [])

    return (

        // <div className="pharmacy-items-container">
        //     <MedicineItemVertical 
        //         name=""
        //         price="150"
        //         imagePath=""
        //     />

        //     />
            

 
        //         <PharmacyItemHorizontal
        //             imagePath="https://images.unsplash.com/photo-1546069901-ba9599a7e63c"
        //             name=""
        //             quantity="2"
        //             displayQuantityCounter={true}
        //             price="120"
        //             displayEdit={true}
        //  

        <div>
            {arrivedOrders.map(order => <PharmacyManagerOrder 
                key={order.orderId}
                orderId={order.orderId}
                medicineItems={order.medicineItems}
                customer={order.customer}
                pharmacy={order.pharmacy}
                status={order.status}
                assignDeliveryPerson={assignDeliveryPerson}
            />)}
        </div>
    )

}

export default TestPage