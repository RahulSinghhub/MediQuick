import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom"
import MedicineItemHorizontal from "../../../Components/MedicineItemHorizontal";
import { URL } from '../../../config'
import { useNavigate } from "react-router";

const CustomerCart = () => {

    const navigate = useNavigate();

    let medicineItemArray = [];
    const [medicineItems, setMedicineItems] = useState([])
    
    let medicineItemMap = new Map(JSON.parse(sessionStorage["medicineItemMap"]));
    
    const [total, setTotal] = useState(0.00);

    const loadMedicineItems = () => {
        const url = `${URL}/medicineItems/cart`
        medicineItemMap = new Map(JSON.parse(sessionStorage["medicineItemMap"]));

        medicineItemArray = [];
        medicineItemMap.forEach(((value, key, map) => {
            medicineItemArray.push(key);
        }))

        const body = {
            itemIds: [...medicineItemArray]
        }

        axios.post(url, body).then(response => {
            const result = response.data;
            if(result.status === "SUCCESS") {
                const cart = result.data;
                sessionStorage["sessionCart"] = JSON.stringify(cart);
                setMedicineItems(cart);
            }
        })
    }

    const addQtyBtn = (id) => {

        // get data from session
        const cart = JSON.parse(sessionStorage["sessionCart"]);
        medicineItemMap = new Map(JSON.parse(sessionStorage["medicineItemMap"]));

        // add quantity
        cart.forEach(medicine => {
            if(medicine.id === id) {
                medicineItemMap.set(id, medicineItemMap.get(id) + 1);
            }
        });

        sessionStorage["sessionCart"] = JSON.stringify(cart);
        sessionStorage["medicineItemMap"] = JSON.stringify(Array.from(medicineItemMap.entries()));
        setMedicineItems(cart);

        // triggers
        // update total
        updateTotal();
    }

    const delQtyBtn = (id) => {
        let cart = JSON.parse(sessionStorage["sessionCart"]);
        let medicineIdToDelete = undefined;

        medicineItemMap = new Map(JSON.parse(sessionStorage["medicineItemMap"])); // get map

        cart.forEach(medicine => {
            if(medicine.id === id) {
                // medicine.quantity = medicine.quantity - 1;
                medicineItemMap.set(id, medicineItemMap.get(id) - 1);
                if(medicineItemMap.get(id) <= 0) { // if qty <= 0
                    medicineIdToDelete = medicine.id; // store for later, to remove from cart
                    medicineItemMap.delete(id); // remove from map
                }
            }
        });
        if(medicineIdToDelete){ // remove from cart (if qty < 0, this value is set)
            cart = cart.filter(medicine => !(medicine.id===medicineIdToDelete))
        }
        
        // save in session
        sessionStorage["medicineItemMap"] = JSON.stringify(Array.from(medicineItemMap.entries()));
        sessionStorage["sessionCart"] = JSON.stringify(cart);
        setMedicineItems(cart); // update state of cart

        // triggers
        // update total
        updateTotal();
    }

    const updateTotal = () => {
        // reset count
        setTotal(0.0);

        // get items from session
        let cart = JSON.parse(sessionStorage["sessionCart"]);
        medicineItemMap = new Map(JSON.parse(sessionStorage["medicineItemMap"])); // get map

        // calculate total
        let tempTotal = 0.0;
        cart.forEach(medicineItem => {
            tempTotal += medicineItemMap.get(medicineItem.id) * medicineItem.price; // qty * price
        })

        // set total
        setTotal(tempTotal);
    }

    useEffect ( () => {
        loadMedicineItems();
        setTimeout(updateTotal, 1000);
    }, [])

    return (

        <div>
            <h2>Cart</h2>
            {(sessionStorage["medicineItemMap"] === null) || (sessionStorage["medicineItemMap"] === undefined) || (sessionStorage["medicineItemMap"] === "[]") ? 
                <div className="mt-4">
                    Your cart is empty. Add some medicine items to cart to continue. <br />
                    <Link to="/customer/home"><button className="btn btn-primary mt-3">Browse Pharmacies</button></Link>
                </div> :

                // MedicineItemHorizontal container
                <div>
                    {
                    medicineItems.map(medicineItem => <MedicineItemHorizontal
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
                    )}

                    <div className="row">
                        <div className="col">
                            <h5 className="mt-3">Total: {total}</h5>
                        </div>
                    </div>
                    <button className="btn btn-primary float-right" onClick={() => {navigate("/customer/address")}}>Next</button>
                </div>
            }
        </div>
    )
}
export default CustomerCart