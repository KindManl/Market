import '../styles/globals.css';
import '../styles/searchButton.css';
import '../styles/filters.css';
import '../styles/resultsOfSearch.css'
import '../styles/lcab.css'
import '../styles/orders.css'
import React, {useState} from "react";
import Results from "../components/Results";
import Button from "../components/SearchButton";
import Filters from "../components/Filters";
import Lcab from "../components/Lcab"
import Order from "../components/Order"


import Link from "next/link";
import Head from "next/head";




const  App = () => {
    const  [search, setSearch] = useState('телефоны&')
    const  [filter, setFilter] = useState('')
    const  [order, setOrder] = useState('')
    const searchQuery = (search) => {
        setSearch(search)
    }
    const searchWithFilters = (filter) => {
        setFilter(filter)
    }
    const searchWithOrder = (order) => {
        setOrder(order)
    }

    return (
        <>
            <Head>
                <title>Market</title>
            </Head>
            <div className="header">
                <div className="header_inner">
                    <Link href="http://51.250.108.33:3000/">
                        <a className="logo">Market</a>
                    </Link>
                    <Button onChange={searchQuery}/>
                    <nav>
                        <Lcab/>
                    </nav>
                </div>
            </div>
            <div className="filters">
                <div className="filters_inner">
                    <Filters ChangeFilters={searchWithFilters}/>
                </div>
            </div>
            <div className="results">
                <div className="order">
                        <Order ChangeOrder={searchWithOrder}/>
                </div>
                <Results name={search} order={order} filter={filter} page_size={40}/>
            </div>
        </>
    )
};
export default App;
