import { useContext, useState } from "react";
import prof from "../prof.png";
import CustomInput from "./Admin/CustomInput";
import CustomButton from "./Admin/CustomButton";
import axios from "axios";
import { JwtContext } from "../App";
import { Link, useNavigate } from "react-router-dom";
import { Alert, Button } from "antd";
import { CheckCircleOutlined } from "@ant-design/icons";
const Login = () => {
  const [jwtToken, setJwtToken] = useContext(JwtContext);
  const navigate = useNavigate();
  const [formLogin, setFormLogin] = useState({
    email: "",
    password: "",
  });

  const onChange = (event) => {
    const { name, value } = event.target;
    setFormLogin((pre) => ({ ...pre, [name]: value }));
  };
  const [success, setSuccess] = useState(false);
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8888/auth-service/auth/login",
        formLogin
      );

      setJwtToken(response.data.access_token)
      setSuccess(true);
      setTimeout(() => {
        setSuccess(false);
        navigate("/create-patient");
      }, 3000);
    } catch (error) {
      console.error(
        "Error:",
        error.response ? error.response.data : error.message
      );
    }
  };

  return (
    <div
      className="container-fluid min-vh-100 p-4"
      style={{ backgroundColor: "#EAF1FB" }}
    >
      <div className="row  my-auto">
        <div className="col-md align-items-center">
          <center>
            <img src={prof} width="80%" height="80%" alt="" />
          </center>
        </div>
        <div className="col-md rounded-4 p-3 bg-light-subtle shadow mx-5">
          <div className="my-auto align-content-center h-100">
            <h2 className="row col-md-8 mx-auto ">Se connecter</h2>
            <form onSubmit={handleSubmit}>
              <div className="row col-md-8 mx-auto my-3">
                <label className=" row mb-2">Email</label>
                <CustomInput
                  label="email"
                  placeholder="email"
                  onChange={onChange}
                  value={formLogin.email}
                />
              </div>
              <div className="row col-md-8 mx-auto  my-3">
                <label className="row mb-2">Mot de passe</label>
                <CustomInput
                  label="Mot de passe"
                  placeholder="password"
                  onChange={onChange}
                  value={formLogin.password}
                />
              </div>
              <div className="row col-md-8  mx-auto mt-4">
                <CustomButton actionType="submit" text="Se connecter" />
              </div>
              <div className="row col-md-8 w-100 mx-auto mt-4"></div>
            </form>
            {success && (
              <div className="row col-md-8 mx-auto">
                <Alert message="connexion réussie" showIcon  type="success" />
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
