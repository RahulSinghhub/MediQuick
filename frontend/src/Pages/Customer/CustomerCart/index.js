import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import MedicineItemHorizontal from "../../../Components/MedicineItemHorizontal";
import { URL } from '../../../config';
import { useNavigate } from "react-router";

const CustomerCart = () => {
    const navigate = useNavigate();
    const [medicineItems, setMedicineItems] = useState([]);
    const [medicineItemMap, setMedicineItemMap] = useState(new Map(JSON.parse(sessionStorage.getItem("medicineItemMap") || "[]")));
    const [total, setTotal] = useState(0.00);

    const loadMedicineItems = async () => {
        const url = `${URL}/medicineitems/cart`;

        const medicineItemArray = Array.from(medicineItemMap.keys());

        if (medicineItemArray.length === 0) {
            setMedicineItems([]);
            setTotal(0);
            return;
        }

        const body = { itemIds: medicineItemArray };

        try {
            const response = await axios.post(url, body);
            const result = response.data;

            if (result.status === "SUCCESS") {
                const cart = result.data;
                sessionStorage.setItem("sessionCart", JSON.stringify(cart));
                setMedicineItems(cart);
                updateTotal(cart);
            }
        } catch (error) {
            console.error("Error loading medicine items", error);
        }
    };

    const addQtyBtn = (id) => {
        const cart = JSON.parse(sessionStorage.getItem("sessionCart"));
        const updatedMap = new Map(medicineItemMap);

        cart.forEach(medicine => {
            if (medicine.id === id) {
                updatedMap.set(id, (updatedMap.get(id) || 0) + 1);
            }
        });

        sessionStorage.setItem("sessionCart", JSON.stringify(cart));
        sessionStorage.setItem("medicineItemMap", JSON.stringify(Array.from(updatedMap.entries())));
        setMedicineItemMap(updatedMap);
        setMedicineItems(cart);
        updateTotal(cart);
    };

    const delQtyBtn = (id) => {
        let cart = JSON.parse(sessionStorage.getItem("sessionCart"));
        const updatedMap = new Map(medicineItemMap);
        let medicineIdToDelete = undefined;

        cart.forEach(medicine => {
            if (medicine.id === id) {
                const newQty = (updatedMap.get(id) || 1) - 1;
                if (newQty <= 0) {
                    updatedMap.delete(id);
                    medicineIdToDelete = id;
                } else {
                    updatedMap.set(id, newQty);
                }
            }
        });

        if (medicineIdToDelete) {
            cart = cart.filter(medicine => medicine.id !== medicineIdToDelete);
        }

        sessionStorage.setItem("medicineItemMap", JSON.stringify(Array.from(updatedMap.entries())));
        sessionStorage.setItem("sessionCart", JSON.stringify(cart));
        setMedicineItemMap(updatedMap);
        setMedicineItems(cart);
        updateTotal(cart);
    };

    const updateTotal = (cart = medicineItems) => {
        const updatedMap = new Map(medicineItemMap);
        let tempTotal = 0.0;

        cart.forEach(medicineItem => {
            tempTotal += (updatedMap.get(medicineItem.id) || 0) * medicineItem.price;
        });

        setTotal(tempTotal);
    };

    useEffect(() => {
        loadMedicineItems();
    }, []);

    return (
        <div>
            <h2>Cart</h2>
            {medicineItemMap.size === 0 ? (
                <div className="mt-4">
                    Your cart is empty. Add some medicine items to cart to continue. <br />
                    <Link to="/customer/home"><button className="btn btn-primary mt-3">Browse Pharmacies</button></Link>
                </div>
            ) : (
                <div>
                    {medicineItems.map(medicineItem => (
                        <MedicineItemHorizontal
                            key={medicineItem.id}
                            id={medicineItem.id}
                            imagePath={medicineItem.imagePath}
                            name={medicineItem.name}
                            quantity={medicineItemMap.get(medicineItem.id)}
                            displayQuantityCounter={true}
                            price={medicineItem.price}
                            displayEdit={false}
                            addQtyBtn={addQtyBtn}
                            delQtyBtn={delQtyBtn}
                        />
                    ))}
                    <div className="row">
                        <div className="col">
                            <h5 className="mt-3">Total: {total}</h5>
                        </div>
                    </div>
                    <button className="btn btn-primary float-right" onClick={() => navigate("/customer/address")}>Next</button>
                </div>
            )}
        </div>
    );
};

export default CustomerCart;
