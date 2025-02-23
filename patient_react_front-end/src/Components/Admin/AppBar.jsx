import React from "react";

const AppBar=()=>{
    return <div className="d-flex justify-content-end">
        appbar
    </div>
}

export default React.memo(AppBar,(prevProps, newProps) =>
    Object.is(prevProps, newProps));