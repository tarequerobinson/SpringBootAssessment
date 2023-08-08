import logo from './logo.svg';
import './App.css';
import Main  from './Components/Main';
import Card from './Components/Card';
import CardList from './Components/CardList';
import DeckList from './Components/DeckList';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Routes } from 'react-router-dom';
import { GlobalProvider } from './Context/GlobalState';
import { DeckProvider } from './Context/DeckContext';

    // <GlobalProvider>


function App() {
  return (
    <DeckProvider >

    <div className='App'>

       <Router>
        <Main/>
        <Routes>
            <Route path = "/allcards" element = {<CardList/>}/>
            {/* <Route path= "/alldecks" element = {<Deck />} /> */}
            {/* <Route path= "/alldecks" element = {<DeckList2 />} /> */}
            <Route path= "/alldecks" element = {<DeckList />} />


        </Routes>

      </Router> 

    {/*<CardList />*/}
    {/* <Deck /> */}


    </div>

      </DeckProvider >
  );
}

    // </GlobalProvider>

export default App;
