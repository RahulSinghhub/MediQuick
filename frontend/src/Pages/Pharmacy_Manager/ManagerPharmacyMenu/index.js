import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import MedicineItemHorizontal from "../../../Components/MedicineItemHorizontal";
import PharmacyManagerHeader from "../../../Components/PharmacyManagerHeader";
import { URL } from '../../../config'


const ManagerPharmacyMenu = () => {
    const pharmacyName = sessionStorage['name'];
    const name = sessionStorage['name'];
    let pharmacyId = sessionStorage["pharmacyId"]
    const [medicineItems, setMedicineItems] = useState([])

    const navigate = useNavigate();

    const loadMedicineItems = () => {
        const url = `${URL}/medicineitem/pharmacy/${pharmacyId}`
        axios.get(url).then(response => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                setMedicineItems(result.data)
            } else {
                toast.error(result.message);
            }
        })
    }

    const editMedicineItem = (medicineItemId) => {
        // console.log(medicineItemId);
        navigate("/manager/editmedicineitem", {
            state: {medicineItemId}
        })
    }

    const addMedicineItem = () => {
        navigate("/manager/addmedicineitem");
    }

    useEffect( () => {
        loadMedicineItems();
    }, [])

    return (
        <div>
            <PharmacyManagerHeader 
                pharmacyName ={pharmacyName}
                name={name}
            />
            <div className="row mt-3">
                <h4 className="col">pharmacy Menu</h4>
                <h4 className="col text-right">
                    <button className="btn btn-primary" onClick={() => {addMedicineItem()}}>
                        Add Item
                    </button>
                </h4>
            </div>
            

            {medicineItems.map(item => 
                <MedicineItemHorizontal
                    key={item.id}
                    id={item.id}
                    imagePath={item.imagePath}
                    name={item.name}
                    price={item.price}
                    editMedicineItem={editMedicineItem}
                    displayEdit={true}
                />
            )}
        </div>
    )
}

export default ManagerPharmacyMenu