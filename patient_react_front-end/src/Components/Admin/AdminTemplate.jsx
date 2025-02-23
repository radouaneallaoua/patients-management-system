import React, { createContext, useEffect, useState } from "react";
import SideBar from "./SideBar";
import { Outlet } from "react-router-dom";
import AppBar from "./AppBar";
import CustomDrawer from "./CustomDrawer";
import { Button, Menu } from "antd";
import { MenuOutlined } from "@ant-design/icons";

export const OpenDrawerContext = createContext(null);

const AdminTemplate = () => {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [openDrawer, setOpenDrawer] = useState(false);
  window.addEventListener("resize", function () {
    setWindowWidth(this.window.innerWidth);
  });

  const handleOpenDrawer = () => {
    setOpenDrawer(true);
  };
  return (
    <OpenDrawerContext.Provider value={[openDrawer, setOpenDrawer]}>
      <div className="container-fluid">
        <div
          className={`${
            windowWidth < 600
              ? " row col  py-3  rounded-2 flex-row justify-content-between  "
              : " row col  justify-content-end rounded-2  py-3 "
          } `}
          style={{ backgroundColor: "#F6F8FC" }}
        >
          {windowWidth < 600 && (
            <Button className="mx-2" onClick={handleOpenDrawer} icon={<MenuOutlined />} />
          )}
          <AppBar />
        </div>
        <div className="row">
          <div className="col-md-2 rounded-end-3  p-3">
            {windowWidth > 600 ? <SideBar /> : <CustomDrawer />}
          </div>
          <div
            className="col-md rounded-5 p-3  h-auto"
            style={{ backgroundColor: "#EAF1FB" }}
          >
            <Outlet />
          </div>
        </div>
      </div>
    </OpenDrawerContext.Provider>
  );
};

export default React.memo(AdminTemplate, (prevProps, newProps) =>
  Object.is(prevProps, newProps)
);
