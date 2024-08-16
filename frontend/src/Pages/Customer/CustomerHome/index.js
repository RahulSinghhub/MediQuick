import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router'
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import { URL } from '../../../config'

const CustomerHome = () => {
    const loginStatus = sessionStorage['loginStatus'];
    const [pharmacy, setPharmacy] = useState([]);

    const navigate = useNavigate();

    const loadPharmacies = () => {
        const url = `${URL}/pharmacies`
        axios.get(url).then((response) => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                setPharmacy(result.data);
                console.log(result);
            } else {
                toast.error(result.message);
            }
        })
    }

    useEffect (() => {
        loadPharmacies();
    }, [])

    return (
        loginStatus === '1' ? 
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <th>pharmacy</th>
                        <th>Medicine Items</th>
                    </tr>
                </thead>
                <tbody>
                    {pharmacy.map(pharma =>
                            <tr key={pharma.id}>
                                <td>{pharma.name}</td>
                                <td>
                                    <button
                                        className="btn btn-primary"
                                        onClick={ () => navigate("/customer/medicineitems", { state : {
                                            pharmaId: pharma.id,
                                            pharmaName: pharma.name}  }) }
                                        >
                                            View Medicine Items
                                    </button>
                                </td>
                            </tr>
                    )}
                </tbody>
            </table>
        </div>
        
        :  <div>You have not Signed In. Please <Link to="/customer/signin">Sign In here</Link></div>
        
    )
}

export default CustomerHome