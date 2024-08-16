import { Link } from "react-router-dom";
import { Container, Row, Col } from "reactstrap";
import Crousel from "./Carousel";
import "./home.css";
import car from "./car";

const Home = () => {
  return (
    <Container>
      <div>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container-fluid">
            <h1 className="mt-2 mb-2">Welcome To MediQuick</h1>

            <div className="collapse navbar-collapse justify-content-end">
              <ul className="navbar-nav">
                <li className="nav-item dropdown">
                  <button
                    className="btn btn-info dropdown-toggle rounded-pill"
                    type="button"
                    id="customerDropdown"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    Customer
                  </button>
                  <ul
                    className="dropdown-menu dropdown-menu-dark"
                    aria-labelledby="customerDropdown"
                  >
                    <li>
                      <Link className="dropdown-item active" to={"/customer/signin"}>
                        Sign In
                      </Link>
                    </li>
                    <li>
                      <Link className="dropdown-item active" to={"/customer/signup"}>
                        Sign Up
                      </Link>
                    </li>
                  </ul>
                </li>

                <li className="nav-item dropdown">
                  <button
                    className="btn btn-info dropdown-toggle rounded-pill"
                    type="button"
                    id="managerDropdown"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    Pharmacy Manager
                  </button>
                  <ul
                    className="dropdown-menu dropdown-menu-dark"
                    aria-labelledby="managerDropdown"
                  >
                    <li>
                      <Link className="dropdown-item active" to={"/manager/signin"}>
                        Sign In
                      </Link>
                    </li>
                    <li>
                      <Link className="dropdown-item active" to={"/manager/signup"}>
                        Sign Up
                      </Link>
                    </li>
                  </ul>
                </li>

                <li className="nav-item dropdown">
                  <button
                    className="btn btn-info dropdown-toggle rounded-pill"
                    type="button"
                    id="deliveryDropdown"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    Delivery Person
                  </button>
                  <ul
                    className="dropdown-menu dropdown-menu-dark"
                    aria-labelledby="deliveryDropdown"
                  >
                    <li>
                      <Link className="dropdown-item active" to={"/dp/signin"}>
                        Sign In
                      </Link>
                    </li>
                    <li>
                      <Link className="dropdown-item active" to={"/deliveryperson/signup"}>
                        Sign Up
                      </Link>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>

      <marquee
        behavior="scroll"
        direction="left"
        scrollamount="10"
        className="Info"
        style={{ marginTop: "10px", color: "darkblue", fontWeight: "bold" }}
      >
        Order your medicine online and get free delivery !!!!!
      </marquee>

      <div>
        <Row>
          <Col md={12} style={{ padding: "0px", marginTop: "20px" }}>
            <Crousel />
          </Col>
        </Row>
      </div>

      <hr />

      <div className="about-us-section" style={{ marginTop: "30px" }}>
        <h2>About Us</h2>
        <p>
          <span style={{ fontWeight: "bold" }}>MediQuick</span> is your trusted
          partner for all your pharmaceutical needs. Our mission is to provide
          fast and reliable access to medicines and healthcare products, ensuring
          that you receive the best care possible. We work with certified
          pharmacies and experienced delivery personnel to ensure that your
          orders are handled with the utmost care and delivered to your doorstep
          swiftly.
        </p>
        <p>
          Our platform offers a seamless user experience, allowing you to easily
          browse through a wide range of medicines, place orders, and track your
          deliveries in real-time. With <span style={{ fontWeight: "bold" }}>MediQuick</span>,
          you can trust that your health is in good hands.
        </p>
        <p>
          Whether you're managing a chronic condition or simply need a quick
          refill, <span style={{ fontWeight: "bold" }}>MediQuick</span> is here to make your life easier. Our
          customer-centric approach and commitment to quality ensure that you
          receive the care and service you deserve.
        </p>
      </div>

      <div>
        <h3>Companys supporting us </h3>
        <car/>
      </div>


    </Container>
  );
};

export default Home;
