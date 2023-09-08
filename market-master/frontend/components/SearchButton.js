import React from "react";


function Button ({onChange})  {
    const[activeIndex, setActiveIndex] = React.useState(0);
    const[search, setSearch] = React.useState('');



    const click = (index) => {
        if (index === 1)
            setActiveIndex(index)
        else if (index === 0)
            setActiveIndex(0)
        else if (index === 2) {
            onChange(search);
            setActiveIndex(0);
        }
    }

    const gg = (event) => {
        setSearch(event.target.value + "&")
    }


    return(
        <>
        <div className={activeIndex === 0 ? "search-box" : "search-box active"}>
            <input type="text" placeholder="Введите для поиска..." className={activeIndex === 0 ? "" : "active"} onChange={gg}/>
                <div className="search-btn" onClick={() => click(activeIndex + 1)}/>
                <div className={activeIndex === 0 ? "cancel-btn" : "cancel-btn active"} onClick={()=> click(0)}>
                    X
                </div>
        </div>
        </>
    )

}
export default Button
