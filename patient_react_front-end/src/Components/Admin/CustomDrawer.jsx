import React, { useContext, useState } from "react";
import SideBarItem from "./SideBarItem";
import {
  DashboardOutlined,
  ProductOutlined,
  ShoppingCartOutlined,
} from "@ant-design/icons";
import { Drawer } from "antd";
import { OpenDrawerContext } from "./AdminTemplate";

const CustomDrawer = () => {
  const [openDrawer, setOpenDrawer] = useContext(OpenDrawerContext);
  const onClose = () => setOpenDrawer((prev) => !prev);
  let sideBarItems = [
    { name: "Dashboard ", icon: <DashboardOutlined style={{fontSize:20}}/>, link: "/dashboard" },
       { name: "Orders    ", icon: <ShoppingCartOutlined style={{fontSize:20}} />, link: "/orders" },
       { name: "Categories", icon: <ProductOutlined  style={{fontSize:20}}/>, link: "/categories" },
       { name: "Products  ", icon: <DashboardOutlined style={{fontSize:20}} />, link: "/products" },
       { name: "Brands", icon: <ProductOutlined  style={{fontSize:20}}/>, link: "/brands" },
       { name: "Sizes  ", icon: <DashboardOutlined style={{fontSize:20}} />, link: "/sizes" },
  ];
  return (
    <Drawer
      width={250}
      title="Admin"
      placement="left"
      onClose={onClose}
      open={openDrawer}
    >
      {sideBarItems.map((item) => (
        <SideBarItem sideBarItem={item} key={item.name} onClick={onClose} />
      ))}
    </Drawer>
  );
};

export default React.memo(CustomDrawer, (prevProps, newProps) =>
  Object.is(prevProps, newProps)
);
