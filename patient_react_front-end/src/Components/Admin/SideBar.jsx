import React, { useState } from "react";
import SideBarItem from "./SideBarItem";
import {
  DashboardOutlined,
  ProductOutlined,
  ShoppingCartOutlined,
} from "@ant-design/icons";
import { Drawer } from "antd";

const SideBar = () => {

  let sideBarItems = [
    { name: "Dashboard ", icon: <DashboardOutlined style={{fontSize:20}}/>, link: "/dashboard" },
    { name: "Orders    ", icon: <ShoppingCartOutlined style={{fontSize:20}} />, link: "/orders" },
    { name: "Categories", icon: <ProductOutlined  style={{fontSize:20}}/>, link: "/categories" },
    { name: "Products  ", icon: <DashboardOutlined style={{fontSize:20}} />, link: "/products" },
    { name: "Brands", icon: <ProductOutlined  style={{fontSize:20}}/>, link: "/brands" },
    { name: "Sizes  ", icon: <DashboardOutlined style={{fontSize:20}} />, link: "/sizes" },
  ];
  return (
    <div className="">
        {sideBarItems.map((item) => (
          <SideBarItem sideBarItem={item} key={item.name} />
        ))}
      
    </div>
  );
};

export default React.memo(SideBar, (prevProps, newProps) =>
  Object.is(prevProps, newProps)
);
