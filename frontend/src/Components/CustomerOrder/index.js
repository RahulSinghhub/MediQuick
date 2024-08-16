import MedicineItemHorizontal from "../MedicineItemHorizontal";
import './index.css'

const CustomerOrder = (props) => {
    let total = 0;
    props.medicineItems.forEach(item => {
        total += item.medicinePrice;
    })


    return (
        <div className="customer-order-container">
            <h3>Order No: {props.orderId}</h3>
            {
                props.medicineItems.map(medicineItem => 
                    <MedicineItemHorizontal 
                        key={medicineItem.medicineItemId}
                        imagePath={medicineItem.medicineItemUrl}
                        name={medicineItem.medicineName}
                        quantity={medicineItem.medicineQuantity}
                        displayQuantityCounter={false}
                        price={medicineItem.medicinePrice}
                        displayEdit={false}
                    />
                )
            }
            <div className="row">
                <div className="col mt-3">
                    <h4>pharmacy Details</h4>
                    <span className="small-bold-title">{props.pharmacy.name}</span> <br />
                    <div>{props.pharmacy.adressText}</div>
                    <div>{props.pharmacy.pinCode}</div>
                </div>
                <div className="col mt-3">
                    <h4>Order Status</h4>
                    <span className="small-bold-title text-capitalize">{props.status}</span>
                    <div>Order total: Rs {total}</div>
                </div>
            </div>
        </div>
    )
}

export default CustomerOrder