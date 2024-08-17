import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router";
import { toast } from "react-toastify";
import PharmacyManagerHeader from "../../../Components/PharmacyManagerHeader";
import { URL } from '../../../config'
import './index.css'

const ManagerAssignDelivery = () => {
    const pharmacyName = sessionStorage['name'];
    const name = sessionStorage['name'];

    const { state } = useLocation();
    const navigate = useNavigate()

    const [deliveryPersons, setDeliveryPersons] = useState([])

    const getAvailableDeliveryPersons = () => {
        const url = `${URL}/pharmacymanager/availabledeliveryperson/true`
        axios.get(url).then(response => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                setDeliveryPersons(result.data);
            } else {
                toast.error("Failed to get delivery persons")
            }
        })
    }

    const assign = (orderId, deliveryPersonId) => {
        // axios call
        const url = `${URL}/orders/assign/${orderId}/${deliveryPersonId}`
        axios.post(url).then(response => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                toast.success("Succesfully assigned delivery")
                navigate("/manager/home");
            } else {
                toast.error("Could not assign delivery")
            }
        })
    }

    useEffect( () => {
        getAvailableDeliveryPersons();
    }, [])

    return (
        (state === null || state === undefined) ?
        <div>State not received. Please try again</div>
        :
        <div>
            <PharmacyManagerHeader 
                pharmacyName ={pharmacyName}
                name={name}
            />
            <h2>Assign To Delivery Person</h2>
            <h5>Order No: {state.orderId}</h5>

            {deliveryPersons.map(dp =>
                <div className="row mt-3 bg-light p-3" key={dp.id}>
                    <div className="col-sm-12 col-md-8 align-middle d-flex align-items-center">{dp.name}</div>
                    <div className="col-sm-12 col-md-4">
                        <button className="btn btn-primary" onClick={
                            () => {
                                assign(state.orderId, dp.id);
                            }
                        }>Assign</button>
                    </div>
                </div>)}

            <div className="mt-5">
                <button className="btn btn-outline-primary" onClick={() => {
                    navigate("/manager/home")
                }}>
                    Back to Home
                </button>
            </div>
        </div>
    )
}

export default ManagerAssignDelivery;