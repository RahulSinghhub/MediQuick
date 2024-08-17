import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import { toast } from "react-toastify";
import PharmacyManagerHeader from "../../../Components/PharmacyManagerHeader";
import { URL } from '../../../config'

const ManagerEditMedicineItem = () => {
    const pharmacyName = sessionStorage['name'];
    const name = sessionStorage['name'];

    const { state } = useLocation()
    const medicineItemId = state.medicineItemId;
    const pharmacyId = sessionStorage["pharmacyId"];
    const [medicineName, setMedicineName] = useState("");
    const [medicineTypes, setMedicineTypes] = useState([]);
    const [medicineTypeId, setMedicineTypeId] = useState(0);
    const [price, setPrice] = useState(0.00);
    const [imagePath, setImagePath] = useState("");
    const [isRequired, setIsRequired] = useState(false); 

    const navigate = useNavigate();

    const getMedicineItem = () => {
        const url = `${URL}/medicineTypes/edit/${medicineItemId}`
        axios.get(url).then(response => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                setMedicineTypes(result.data[1]);

                setMedicineName(result.data[0].name);
                setMedicineTypeId(result.data[0].medicineTypeId);
                setPrice(result.data[0].price);
                setImagePath(result.data[0].imagePath);
                setIsRequired(result.data[0].required);
            }
        })
    }

    const updateMedicineItem = () => {
        const url = `${URL}/medicineTypes/edit/${medicineItemId}`
        const body = {
            id: medicineItemId,
            medicineTypeId: medicineTypeId,
            pharmacyId: pharmacyId,
            name: medicineName,
            price: price,
            imagePath: imagePath,
            required: isRequired
        }

        // console.log(body);
        axios.post(url, body).then(response => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                toast.success("medicine item edited successfully");
                navigate("/manager/pharmacymenu");
            } else {
                toast.error(result.message);
            }
        })
    }

    useEffect(()=> {
        getMedicineItem();

    }, [])


    return (
        <div>
            <PharmacyManagerHeader 
                pharmacyName ={pharmacyName}
                name={name}
            />

            <h3>Edit medicine Item</h3>

            <div className="row">
                <div className="col"></div>
                <div className="col">
                    <div className="form">

                        {/* name */}
                        <div className="mb-3">
                            <label htmlFor="medicineName" className="label-control">
                                Item Name
                            </label>
                            <input type="text" onChange={ (e) => setMedicineName(e.target.value) } value={medicineName} className="form-control" />
                        </div>

                        {/* medicine type */}
                        <div className="mb-3">
                            <label htmlFor="medicineTypeId" className="label-control">
                                Medicine Type
                            </label>

                            <select className="form-select form-control"
                                onChange={ (e) => setMedicineTypeId(e.target.value) }
                                value={medicineTypeId}
                                >
                                {
                                    medicineTypes.map(medicineType => 
                                        <option value={medicineType.id} key={medicineType.id}>
                                            {medicineType.name}
                                        </option>
                                    )
                                }
                            </select>

                        </div>

                        {/* price */}
                        <div className="mb-3">
                            <label htmlFor="price" className="label-control">
                                Price
                            </label>
                            <input type="text" onChange={ (e) => setPrice(e.target.value) } value={price} className="form-control" />
                        </div>

                        {/* image url */}
                        <div className="mb-3">
                            <label htmlFor="imagePath" className="label-control">
                                Image URL
                            </label>
                            <input type="text" onChange={ (e) => setImagePath(e.target.value) } value={imagePath}  className="form-control"/>
                        </div>

                        {/* is required */}
                        <div className="mb-3">
                            <label htmlFor="isRequired" className="label-control">
                                Is Required
                            </label>
                            <input
                                type="checkbox"
                                onChange={ (e) => setIsRequired(e.target.checked) }
                                value={isRequired}
                                className="form-control"
                                checked={isRequired}
                            />
                        </div>

                        {/* button */}
                        <div className="d-flex justify-content-between">
                            <div className="mb-3">
                               <button className="btn btn-outline-primary" onClick={() => {navigate("/manager/pharmacymenu")}}>Cancel</button>
                            </div>
                            <div className="mb-3 float-right">
                               <button className="btn btn-primary" onClick={() => {updateMedicineItem()}}>Update</button>
                            </div>
                        </div>

                        
                    </div>
                </div>
                <div className="col"></div>
            </div>
        </div>
    )
}

export default ManagerEditMedicineItem