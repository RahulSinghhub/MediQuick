import axios from "axios";
import { useEffect, useState, useCallback } from "react";
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import MedicineItemVertical from "../../../Components/MedicineItemVertical";
import { URL } from '../../../config';
import './index.css';

const CustomerMedicineItems = () => {
    const { state } = useLocation();
    const { pharmaId, pharmaName } = state || {};

    const [medicineItems, setMedicineItems] = useState([]);
    const [loading, setLoading] = useState(true); // Add loading state

    const loadPharmacyItems = useCallback(() => {
        if (!pharmaId) {
            toast.error("Pharmacy ID is missing.");
            return;
        }

        const url = `${URL}/medicineitem/pharmacy/${pharmaId}`;
        axios.get(url)
            .then(response => {
                const result = response.data;
                if (result.status === "SUCCESS") {
                    setMedicineItems(result.data);
                } else {
                    toast.error(result.message);
                }
            })
            .catch((error) => {
                console.error("Error loading pharmacy items:", error);
                toast.error("Failed to load pharmacy items.");
            })
            .finally(() => setLoading(false)); // Set loading to false after the request is complete
    }, [pharmaId]);

    useEffect(() => {
        loadPharmacyItems();
    }, [loadPharmacyItems]);

    const addToCart = (medicineId, pharmacyId) => {
        let medicineItemMap = new Map(JSON.parse(sessionStorage.getItem("medicineItemMap") || "[]"));

        if (!sessionStorage.getItem("pharmacyId")) {
            sessionStorage.setItem("pharmacyId", pharmacyId.toString());
        }

        if (pharmacyId === parseInt(sessionStorage.getItem("pharmacyId"))) {
            if (!medicineItemMap.has(medicineId)) {
                medicineItemMap.set(medicineId, 1);
            }

            toast.success("Added to cart");
            sessionStorage.setItem("medicineItemMap", JSON.stringify(Array.from(medicineItemMap.entries())));
        } else {
            toast.error("Can't add to cart. Please order from the same pharmacy.");
        }
    };

    if (loading) {
        return <div>Loading...</div>; // Display loading state
    }

    return (
        <div>
            {pharmaId && pharmaName ? (
                <div>
                    <h2 className="mt-3">Medicine Items from {pharmaName}</h2>
                    <div className="medicine-items-container">
                        {medicineItems.length > 0 ? (
                            medicineItems.map(mediItem => (
                                <MedicineItemVertical
                                    key={mediItem.id}
                                    id={mediItem.id}
                                    name={mediItem.name}
                                    price={mediItem.price}
                                    imagePath={mediItem.imagePath}
                                    pharmacyId={mediItem.pharmacyId}
                                    addToCart={addToCart}
                                />
                            ))
                        ) : (
                            <p>No medicine items available.</p>
                        )}
                    </div>
                </div>
            ) : (
                <div>Something went wrong, please <Link to="/customer/signin">try Signing In again</Link></div>
            )}
        </div>
    );
};

export default CustomerMedicineItems;
