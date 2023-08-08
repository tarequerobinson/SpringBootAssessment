import '../Styles/Cards.css'; 
import { GlobalContext } from '../Context/GlobalState';
import {ADD_CARD_TO_DECK} from '../Context/Actiontypes'

import { ReactComponent as HomeworldIcon }  from '../Icons/Planet.svg'; 
import { ReactComponent as Vehicles }  from '../Icons/Vehicles.svg';
import { ReactComponent as Starships }  from '../Icons/Starships.svg';
import { ReactComponent as AddToDeckButton }  from '../Icons/Property 1=UI, Property 2=Add.svg';
 import React, { useContext, useState } from 'react';
import Modal from '../Components/Modal';
import DeckList from './DeckList'; 
import DeckContext from '../Context/DeckContext';







const Card = ({ name, homeworld, vehicles, starships , onClick }) => {
  const { deckList, addCardToDeck } = useContext(DeckContext);
const [showDeckList, setShowDeckList] = useState(false);
const { decks, setDecks } = useContext(DeckContext);



const handleDeckSelection = (deckId) => {
  //  logic to save the card to the selected deck by id
  const selectedDeck = decks.find((deck) => deck.id === deckId);

  //if the deck id exists in the array of decks create a cardData object
  if (selectedDeck) {
    
    const cardData = { name, homeworld, vehicles, starships };

    //add the created card object to the cards array in the selectedDeck
    selectedDeck.cards.push(cardData);

    // Save the updated deck back to local storage
    // this will be used to update the state of the array of decks
    const updatedDecks = decks.map((deck) =>
      deck.id === selectedDeck.id ? selectedDeck : deck
    );
    localStorage.setItem('decks', JSON.stringify(updatedDecks));

    //update the state of the decks
    setDecks(updatedDecks);
    //setDecks([...deckList]);


  } else {
    console.log('Selected deck not found!');
  }

  // Hide the deck list after selection
  setShowDeckList(false);
};



  
  
  
  return (


    <div className="cardComponent" >

    <div className="cardHeader">

{/** a boolean which shows the decklist when showDecklist is true */}
    {showDeckList ? (
          <div className="deckList">
            {deckList.map(deck => (
              <button key={deck.id} onClick={() => handleDeckSelection(deck.id)}>
                {deck.name}
              </button>
            ))}
          </div>
        ) : (

      <div className='addToDeckButton'  onClick={()=> addCardToDeck (name, homeworld, vehicles, starships)} >
    <AddToDeckButton/>
     </div>
    )
  }



    <h3>{name}</h3>
    </div>


      <div className="card-content">
        <p>      
          <div className='icon'>
                <HomeworldIcon/> 
          </div>
                homeworld: {homeworld}
        </p>



        <p>
        <div className='icon'>
                <Vehicles/>
        </div>
              vehicles: {vehicles}
        </p>


        <p>
        <div className='icon'>
            <Starships/>
            </div>
            starships:{starships}</p>
        </div>
    </div>

  );
};

export default Card;
