import React, { useState } from "react";
import Category from "./Category";
import { Button, Form, Input } from "antd";
import CustomInput from "./CustomInput";
import CustomButton from "./CustomButton";

const Categories = () => {
  const [categories, setCategories] = useState([
    { id: 1, name: "category1", description: "description1" },
    { id: 2, name: "category2", description: "description1" },
    { id: 3, name: "category3", description: "description1" },
    { id: 4, name: "category4", description: "description1" },
    { id: 5, name: "category5", description: "description1" },
    { id: 6, name: "category6", description: "description1" },
  ]);
  const [categoryInfo, setCategoryInfo] = useState({
    name: "",
    description: "",
  });
  const onChange = (event) => {
    const { name, value } = event.target;
    setCategoryInfo((pre) => ({ ...pre, [name]: value }));
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    alert("submited");
  };
  return (
    <div>
      <h3>Categories</h3>

      <form onSubmit={handleSubmit}>
        <div className="row ">
          <div className="col-md-5 my-3">
            <CustomInput
              label="name"
              placeholder="name"
              onChange={onChange}
              value={categoryInfo.name}
            />
          </div>
          <div className="col-md-5  my-3">
            <CustomInput
              label="description"
              placeholder="description"
              onChange={onChange}
              value={categoryInfo.description}
            />
          </div>
          <div className="col-md-2 my-3">
            <CustomButton
              actionType="submit"
              onClick={null}
              text="Add category"
            />
          </div>
        </div>
      </form>

      {categories.map((c) => (
        <Category categoryInfo={c} key={c.id} />
      ))}
    </div>
  );
};

export default Categories;
