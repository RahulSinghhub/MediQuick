import './index.css'

const MedicineItemHorizontal = (props) => {

    return (
        <div className='container mt-4 medicine-item-container'>
            <div className="row">
                <div className="col-2 data-holder image-holder">
                    <img src={props.imagePath} alt={props.name} className='image-size'/>
                </div>
                <div className="col-4 data-holder medicine-name">{props.name}</div>
                {
                    props.quantity && 
                    <div className="col-2 data-holder quantity-holder">

                            <div>
                                { props.displayQuantityCounter && 
                                    <button type="button" className="btn btn-primary" onClick={ () => {
                                        if(props.addQtyBtn)
                                            props.addQtyBtn(props.id)
                                    }}>+</button>
                                }
                            </div>
                            <div>{props.quantity}</div>
                            <div>
                                { props.displayQuantityCounter && 
                                    <button type="button" className="btn btn-primary" onClick={ () => {
                                        if(props.delQtyBtn)
                                            props.delQtyBtn(props.id)
                                    }}>-</button>
                                }
                            </div>

                    </div>
                }
                
                <div className="col data-holder">
                    Rs {props.price}
                </div>
                {props.displayEdit && 
                <div className="col data-holder">
                    <button className="btn btn-primary" onClick={() => {props.editMedicineItem(props.id)}}>Edit</button>
                </div>
                }
            </div>
            
        </div>
    )
}

export default MedicineItemHorizontal