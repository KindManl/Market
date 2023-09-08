import React from "react"

function Order({ChangeOrder}){
    const [order, setOrder] = React.useState("");
    const [index, activeIndex] = React.useState(1);

    const click = async (index) => {
        if (index === 1) {
            if (order === "cost")
                setOrder("costFalse")
            else
                setOrder("cost");
        }
        if (index === 2) {
            if (order === "name")
                setOrder("nameFalse")
            else
                setOrder("name");
        }
        if (index === 3) {
            if (order === "star")
                setOrder("starFalse")
            else
                setOrder("star");
        }
        activeIndex(index);
        checkOrder()
    }

    const checkOrder = () => {
        switch (order){
            case "name":{
                ChangeOrder("name_order=true&");
                break
            }
            case "cost":{
                ChangeOrder("price_order=true&");
                break
            }
            case "nameFalse":{
                ChangeOrder("name_order=false&");
                break
            }
            case "costFalse":{
                ChangeOrder("price_order=false&");
                break
            }
            case "star":{
                ChangeOrder("rating=true&");
                break
            }
            case "starFalse":{
                ChangeOrder("rating=false&");
                break
            }
            default: {
                console.log("error in order " + order)
                ChangeOrder("");
                break
            }
        }
    }

    return (
        <div className="order_inner">
            <p className={index === 1 ? "active" : ""} onClick={()=>{click(1)}}>По цене </p>
            <p className={index === 2 ? "active" : ""} onClick={()=>{click(2)}}>По имени</p>
            <p className={index === 3 ? "active" : ""} onClick={()=>{click(3)}}>По оценкам</p>
        </div>
    )
}
export default Order
