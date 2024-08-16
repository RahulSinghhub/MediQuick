import MedicineItemHorizontal from "../MedicineItemHorizontal";
import './index.css'

const PharmacyManagerOrder = (props) => {
    const medicineItems = props.medicineItems;
    let total = 0;
    props.medicineItems.forEach(item => {
        total += item.medicinePrice;
    })


    return (
        <div className="pharmacy-manager-order-container">
            <h4>Order No: {props.orderId}</h4>
            {medicineItems.map(medicineItem => 
                <MedicineItemHorizontal 
                    key={medicineItem.medicineItemId}
                    imagePath={medicineItem.medicineItemUrl}
                    name={medicineItem.medicineName}
                    quantity={medicineItem.medicineQuantity}
                    displayQuantityCounter={false}
                    price={medicineItem.medicinePrice}
                    displayEdit={false}
                />
            )}

            <div className="mt-3"><span className="pharmacy-manager-order-text-header">Order Total:</span> Rs {total}</div>

            <div className="mt-3 row">
                <div className="col-md-6">
                    <span className="pharmacy-manager-order-text-header">Deliver To</span> <br />
                    {props.customer.name} <br />
                    {props.customer.addressText}
                </div>
                { (props.displayAssignedTo && props.deliveryPerson) &&
                    <div className="col-md-6">
                        <span className="pharmacy-manager-order-text-header">Assigned To</span> <br />
                        {props.deliveryPerson.name} <br /> 
                        {(props.displayStatus && props.status) &&
                            <div className="text-capitalize">Order Status: {props.status}</div>
                        }
                    </div>
                }
                
            </div>
            { props.displayAssignButton && 
                <button className="btn btn-primary mt-3"
                    onClick={() => {
                                if(props.assignDeliveryPerson)
                                props.assignDeliveryPerson(props.orderId)
                            }
                        }
                    >Assign Delivery
                </button>
            }
            
        </div>
    )
}

export default PharmacyManagerOrder