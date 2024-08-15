import './App.css';
import React,{ useState , useEffect  } from 'react';


function App() {
  const [checkLoggedIn, setCheckLoggedIn] = useState();
  useEffect(() => {
    let cust = JSON.parse(sessionStorage.getItem("customer"));
    let ven = JSON.parse(sessionStorage.getItem("supplier"));
    let adm = JSON.parse(sessionStorage.getItem("admin"));
    if (cust !== null || ven !== null || adm !== null) setCheckLoggedIn(true);
    else setCheckLoggedIn(false);
  }, []);

  const updateLogin = (val) => {
    setCheckLoggedIn(val);
  };
  
  return "s"
}

export default App;
