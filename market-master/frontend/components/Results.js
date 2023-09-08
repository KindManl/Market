import React, {Component} from "react";
import Link from "next/link";


export default class Results extends Component{


    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: [],
            lastName: '',
            lastOrder: '',
            lastFilter: ''
        };
    }

    getResults() {
        this.lastName = this.props.name;
        this.lastFilter = this.props.filter;
        this.lastOrder = this.props.order;
        let url = "http://51.250.16.106:8181/search?";
        let search = "toSearch=";
        search += this.lastName;
        search += this.lastFilter;
        search += this.lastOrder;
        let page_num = "page=0&";
        let page_size = "page_size=" + this.props.page_size+ "&";
        let query = url +  search + page_num + page_size;
        console.log(search);
        fetch(query)
            .then(result => result.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        items: result.products
                    });
                    },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
                )
    }

    checkChanges(){
        return ((this.lastName !== this.props.name) ||
                (this.lastFilter !== this.props.filter) ||
                (this.lastOrder!== this.props.order))
    }



    render() {
        if (this.checkChanges())
            this.getResults();
        const {error, isLoaded, items} = this.state;
        if (error) {
            return <p> По вашему запросу ничего нет</p>
        } else if (!isLoaded) {
            return <p>Loading...</p>
        } else
            return (
                <>
                    <ul>
                        {items.map(item => (
                            <div key={item.link} className="phoneBlock">
                                <Link target="_blank" href={item.link}>
                                    <a>
                                        <img className="image" width='100px' src={item.images}/>
                                    </a>
                                </Link>
                                <div className="name">{item.name}</div>
                                <div>{item.rating}</div>
                                <div>{item.marketplace}</div>
                                <div className="price">{item.price}p</div>
                            </div>
                        ))}
                    </ul>
                </>
            )
    }
}
