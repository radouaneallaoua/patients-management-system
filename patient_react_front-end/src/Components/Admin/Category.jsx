import { DeleteFilled, EditOutlined, UserOutlined } from "@ant-design/icons";
import { Avatar, Button, Tooltip } from "antd";
import React from "react";
import { Link } from "react-router-dom";

const Category = ({ categoryInfo }) => {
  return (
    <div
      className="row rounded-4  mt-2 p-2 mx-4"
      style={{ backgroundColor: "#FFFFFF" }}
    >
      <div className="col-2 ">
        <center>
          <Avatar
            shape="square"
            size={80}
            src="https://franklinpetfood.com/cdn/shop/files/600X600_BENGAL_HEADER.jpg?v=1702485452&width=642"
            icon={<UserOutlined />}
          />
        </center>
      </div>
      <div className="col flex-column">
        <h5 >{categoryInfo.name}</h5>
        <p className="text-secondary"> {categoryInfo.description}</p>
      </div>
      <div className="col-2 justify-content-end">
        <Tooltip title="Edit">
          <Link to={`/categories/${categoryInfo.id}/edit`}>
          <Button
            type="primary"
            shape="circle"
            icon={<EditOutlined />}
            size="large"
          />
          </Link>
        
        </Tooltip>
        <Tooltip title="Delete" className="ms-3">
          <Button
            type="primary"
            style={{ backgroundColor: "red" }}
            shape="circle"
            icon={<DeleteFilled />}
            size="large"
          />
        </Tooltip>
      </div>
    </div>
  );
};

export default React.memo(Category, (prevProps, newProps) =>
  Object.is(prevProps, newProps)
);
