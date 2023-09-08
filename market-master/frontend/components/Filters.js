import React from "react"

function Filters({ChangeFilters}){
    const [lowCost, setLowCost] = React.useState(null);
    const [highCost, setHighCost] = React.useState(null);
    const [stars, setStars] = React.useState(0);


    const checkFilter = () => {
        let filter = "";
        if (lowCost)
            filter += "lp=" + lowCost + "&";
        if (highCost)
            filter += "hp=" + highCost + "&";
        if (stars !== 0)
            filter += "rating=" + stars + "&";
        ChangeFilters(filter);
    }

    const click = (index) => {
        switch (index) {
            case 0: {
                setLowCost(null);
                setHighCost(null);
                setStars(0);
                checkFilter();
                document.getElementById("1").value = "";
                document.getElementById("2").value = "";
                break;
            }
            case 1: {
                setStars(0);
                checkFilter();
                break;
            }
            case 2: {
                setStars(4.5);
                checkFilter();
                break;
            }
            case 3: {
                setStars(4);
                checkFilter();
                break;
            }
            case 4: {
                setStars(3.5);
                checkFilter();
                break
            }
            default: {
                console.log("error ind button")
                return;
            }
        }
    }

    const lc = (event) => {
        let ptr = event.target.value;
        setLowCost(ptr);
    }

    const hc = (event) => {
        let ptr = event.target.value;
        setHighCost(ptr)
    }


    return (
        <ul>
            <div className="fname">Цена, Р</div>
            <input id="1" type="numeric" pattern="[0-9 ]*" onChange={lc}/>
            <a>-</a>
            <input id="2" type="numeric" pattern="[0-9 ]*" onChange={hc}/>
            <div className="fname">Оценка товара по отзывам</div>
            <li onClick={()=>{click(1)}}>Любой</li>
            <li onClick={()=>{click(2)}}>4,5 и выше</li>
            <li onClick={()=>{click(3)}}>4 и выше</li>
            <li onClick={()=>{click(4)}}>3,5 и выше</li>
            <p className="btn" onClick={()=>{checkFilter()}}>Применить</p>
            <p className="btn" onClick={()=>{click(0)}}>Сбросить</p>
        </ul>
    )
}
export default Filters
