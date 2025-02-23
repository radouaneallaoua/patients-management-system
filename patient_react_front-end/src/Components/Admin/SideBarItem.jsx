import { Button } from "antd";
import React from "react";
import { Link, useLocation } from "react-router-dom";

const SideBarItem = ({ sideBarItem,onClick }) => {
  const location = useLocation();
  return (
    <Link to={sideBarItem.link} style={{ listStyle: "none" }} onClick={onClick}>
      <Button
        color="primary"
        className={`${
          location.pathname.includes(sideBarItem.link)
            ? "justify-content-start"
            : "justify-content-start"
        }`}
        variant={
          location.pathname.includes(sideBarItem.link) ? "solid" : "text"
        }
        style={{
          width: "100%",
          height: 50,
          color: location.pathname.includes(sideBarItem.link)
            ? "white"
            : "black",
        }}
      >
        {sideBarItem.icon}
        <h6
          className={`mt-2 ${
            location.pathname.includes(sideBarItem.link)
              ? "text-light"
              : "text-dark"
          }`}
        >
          {"   "}
          {sideBarItem.name}
        </h6>
      </Button>
    </Link>
  );
};

export default React.memo(SideBarItem, (prevProps, newProps) =>
  Object.is(prevProps, newProps)
);
