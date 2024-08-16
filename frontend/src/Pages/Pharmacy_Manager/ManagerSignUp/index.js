import axios from "axios";
import { useState } from "react"
import { useNavigate } from "react-router";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import { URL } from '../../../config'

const ManagerSignUp = () => {

    const [managerName, setManagerName] = useState("");
    const [managerEmail, setManagerEmail] = useState("");
    const [managerPassword, setManagerPassword] = useState("");
    const [confirmManagerPassword, setConfirmManagerPassword] = useState("");
    const [pharmacyName,setPharmacyName]=useState("");
    const [pharmacyAdressText,setPharmacyAdressText]=useState("");
    const [pharmacyPinCode,setPharmacyPinCode]=useState(0);
    

    const navigate = useNavigate();
    
    const validateInput = () => {
        if(managerName === "") {
            toast.error("Please enter your name");
            return false;
        }
        if(managerEmail === "") {
            toast.error("Please enter your email");
            return false;
        }
        if(managerPassword === "") {
            toast.error("Please enter your password");
            return false;
        }
        if(confirmManagerPassword === "") {
            toast.error("Please enter confirm password");
            return false;
        }
        if(pharmacyName === "") {
            toast.error("Please enter pharmacy name");
            return false;
        }
        if(pharmacyAdressText === "") {
            toast.error("Please enter pharmacy address");
            return false;
        }
        if(pharmacyPinCode === "") {
            toast.error("Please enter pharmacy pin code");
            return false;
        }
        if (managerPassword !== confirmManagerPassword) {
            toast.error("Password does not match with confirm password");
            return false;
        }

        return true;
    }

    const signUpManager = () => {
        if(validateInput) {
            const url = `${URL}/pharmacymanager/signup`;
            const body = {
                managerName,
                managerEmail,
                managerPassword,
                pharmacyName,
                pharmacyAdressText,
                pharmacyPinCode
            }
            axios.post(url, body).then(response => {
                const result = response.data;
                if(result.status === "SUCCESS") {
                    toast.success("Manager successfully signed up")
                    navigate("/manager/signin")
                } else {
                    toast.error(result.message)
                }
            })
        }
    }


    return (
        <div>
            <h2 className="title">Manager Sign Up</h2>

            <div className="row">
                <div className="col"></div>
                <div className="col">
                    <div className="form">

                        {/* name */}
                        <div className="mb-3">
                            <label htmlFor="managerName" className="label-control">
                                Manager Name
                            </label>
                            <input type="text" onChange={ (e) => setManagerName(e.target.value) } value={managerName} className="form-control" id="managerName"/>
                        </div>

                        {/* email */}
                        <div className="mb-3">
                            <label htmlFor="managerEmail" className="label-control">
                                Manager Email Address
                            </label>
                            <input type="text" onChange={ (e) => setManagerEmail(e.target.value) } value={managerEmail} className="form-control" id="managerEmail"/>
                        </div>

                        {/* password */}
                        <div className="mb-3">
                            <label htmlFor="managerPassword" className="label-control">
                                Manager Password
                            </label>
                            <input type="password" onChange={ (e) => setManagerPassword(e.target.value) } value={managerPassword}  className="form-control" id="managerPassword"/>
                        </div>

                        {/* confirm password */}
                        <div className="mb-3">
                            <label htmlFor="confirmManagerPassword" className="label-control">
                                Confirm Password
                            </label>
                            <input type="password" onChange={ (e) => setConfirmManagerPassword(e.target.value) } value={confirmManagerPassword}  className="form-control" id="confirmManagerPassword"/>
                        </div>

                        {/* pharmacyName */}
                        <div className="mb-3">
                            <label htmlFor="pharmacyName" className="label-control">
                                pharmacy Name
                            </label>
                            <input type="text" onChange={ (e) => setPharmacyName(e.target.value) } value={pharmacyName} className="form-control" id="pharmacyName"/>
                        </div>

                        {/* pharmacyAdressText */}
                        <div className="mb-3">
                            <label htmlFor="pharmacyAdressText" className="label-control">
                                pharmacy Address
                            </label>
                            <input type="text" onChange={ (e) => setPharmacyAdressText(e.target.value) } value={pharmacyAdressText} className="form-control" id="pharmacyAdressText"/>
                        </div>

                        {/* pharmacyPinCode */}
                        <div className="mb-3">
                            <label htmlFor="managerEmail" className="label-control">
                                pharmacy Pin Code
                            </label>
                            <input type="number" onChange={ (e) => setPharmacyPinCode(e.target.value) } value={pharmacyPinCode} className="form-control" id="pharmacyPinCode"/>
                        </div>
                     


                        <div className="mb-3">
                            <div>
                                Already have an account? <Link to="/manager/signin">Sign in here!</Link>
                            </div>
                            <button className="btn btn-primary" onClick={signUpManager}>Sign Up</button>
                        </div>
                    </div>
                </div>
                <div className="col"></div>
            </div>
        </div>
    )    
}

export default ManagerSignUp