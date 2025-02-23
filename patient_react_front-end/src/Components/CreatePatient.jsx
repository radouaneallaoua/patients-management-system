import { useContext, useState } from "react";
import CustomInput from "./Admin/CustomInput";
import CustomButton from "./Admin/CustomButton";
import { Alert, DatePicker, Input, Select } from "antd";
import axios from "axios";
import { JwtContext } from "../App";

const genders = [
  {
    value: "MALE",
    label: "Homme",
  },
  {
    value: "FEMALE",
    label: "Femme",
  },
];
const CreatePatient = () => {
  const [jwtToken, setJwtToken] = useContext(JwtContext);
  const [formCreatePatient, setFormCreatePatient] = useState({
    firstName: "",
    lastName: "",
    birthDate: null,
    gender: genders[0].value,
    email: "",
    address: "",
    phoneNumber: null,
    password: "",
  });
  const [success, setSuccess] = useState(false);

  const onChange = (event) => {
    const { name, value } = event.target;
    setFormCreatePatient((pre) => ({ ...pre, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log(jwtToken);
      const response = await axios.post(
        "http://localhost:8888/patient-management/patients",
        formCreatePatient,
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      );

      console.log("Response:", response.data);
      //alert(JSON.stringify(response.data));
      setSuccess(true);
      setTimeout(() => {
        setSuccess(false);
        setFormCreatePatient({
            firstName: "",
            lastName: "",
            birthDate: null,
            gender: genders[0].value,
            email: "",
            address: "",
            phoneNumber: null,
            password: "",
          })
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
      <div className="col-md rounded-4 p-3 bg-light-subtle mx-5">
        <div className="my-auto align-content-center ">
          <h2 className="row col-md-8 col-12 mx-auto  ">Créer un patient</h2>
          <form onSubmit={handleSubmit} className="col-md-8 mx-auto p-3">
            <div className="row  my-3">
              <label className=" row mb-2">Prénom</label>
              <CustomInput
                placeholder="firstName"
                onChange={onChange}
                value={formCreatePatient.firstName}
              />
            </div>
            <div className="row  my-3">
              <label className=" row mb-2">Nom</label>
              <CustomInput
                placeholder="lastName"
                onChange={onChange}
                value={formCreatePatient.lastName}
              />
            </div>
            <div className="row  my-3">
              <label className=" row mb-2">Email</label>
              <CustomInput
                placeholder="email"
                onChange={onChange}
                value={formCreatePatient.email}
              />
            </div>
            <div className="row  my-3">
              <label className=" row mb-2">Adresse</label>
              <CustomInput
                placeholder="address"
                onChange={onChange}
                value={formCreatePatient.address}
              />
            </div>
            <div className="row  my-3">
              <label className=" row mb-2">Téléphone</label>
              <CustomInput
                placeholder="phoneNumber"
                onChange={onChange}
                value={formCreatePatient.phoneNumber}
              />
            </div>
            <div className="row  my-3">
              <label className=" row mb-2">Genre</label>
              <Select
                defaultValue="Homme"
                value={formCreatePatient.gender}
                onChange={(newValue) =>
                  setFormCreatePatient((pre) => ({ ...pre, gender: newValue }))
                }
                size="large"
                options={genders}
              />
            </div>
            <div className="row  my-3">
              <label className=" row mb-2">Date de naissance</label>
              <DatePicker
                value={formCreatePatient.birthDate}
                onChange={(newValue) =>
                  setFormCreatePatient((pre) => ({
                    ...pre,
                    birthDate: newValue,
                  }))
                }
                size="large"
                placeholder="date de naissance"
              />
            </div>
            <div className="row mt-4">
              <label className="row mb-2">Mot de passe</label>
              <Input.Password
                name="password"
                size="large"
                placeholder="mot de passe"
                value={formCreatePatient.password}
                onChange={onChange}
                required
              />
            </div>
            <div className="row mt-4">
              <CustomButton actionType="submit" text="Créer patient" />
            </div>
          </form>
          {success && (
            <div className="row col-md-8 mx-auto">
              <Alert message="patient créer avec succès" showIcon type="success" />
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default CreatePatient;
