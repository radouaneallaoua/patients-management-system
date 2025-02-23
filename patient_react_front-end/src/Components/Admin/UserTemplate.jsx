import React from "react"
const UserTemplate=()=>{
    return <h1>
        for users
    </h1>
}

export default React.memo(UserTemplate,(prevProps,newProps)=>Object.is(prevProps,newProps))