import React from "react";
import '../Styles/Main.css';
import CardList from "../Components/CardList";
import { BrowserRouter as Router, Route, Routes, Link, BrowserRouter } from 'react-router-dom';


const Main=()=>{

return (

        <navbar>
          <ul id="sidemenu">
            <li>
              <Link to="/allcards">All Cards</Link>
            </li>
            <li>
              <Link to="/alldecks">All Decks</Link>
            </li>
          </ul>
          </navbar>
)

}
export default Main ;