import './index.css'

const MedicineItemVertical = (props) => {
    return (
        <div className='medicine-item'>
            
            <img src={props.imagePath} className='medicine-item-image' alt={props.name}/>
            <div className='medicine-item-text medicine-item-name'>{props.name}</div>
            <div className='medicine-item-text'>Rs {props.price}</div>
            <button className='btn btn-primary mt-2 mb-4 medicine-item-button' onClick={()=> {props.addToCart(props.id, props.pharmacyId)}}>Add To Cart</button>
        </div>
    )
}

export default MedicineItemVertical;