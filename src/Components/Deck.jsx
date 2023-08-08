import React, { useState } from 'react';
import CardList from "../Components/CardList";
import { ReactComponent as DeleteDeckIcon }  from '../Icons/Property 1=UI, Property 2=Remove.svg'; 
import '../Styles/Deck.css'; 
import { ReactComponent as DeckBgIcon } from '../Icons/Property 1=Factions, Property 2=Galatic Empire Watermark.svg';
import Card from './Card';

const Deck = ({ deckName, totalCards, cards, onClick , selectedDeckType }) => {
    const [deckCards, setDeckCards] = useState(cards);
  
  
    const handleRemoveCardFromDeck = (cardName) => {
      setDeckCards(deckCards.filter((card) => card.name !== cardName));
    };
  
    return (
      <div className={"deckComponent"}>
              <div className={`deckHeader ${selectedDeckType}`}>

          <div className="deleteDeckbutton" onClick={onClick}>
            <DeleteDeckIcon />
          </div>
          <div className="deckBgIcon">
            <DeckBgIcon />
          </div>
          <h3>{deckName}</h3>
        </div>
        <div className="deck-content">
          <div id="actualCardTotal">
            <p>{totalCards}</p>
          </div>
          <p>total cards</p>
          {deckCards.map((card) => (
            <Card
              key={card.name}
              name={card.name}
              homeworld={card.homeworld}
              vehicles={card.vehicles}
              starships={card.starships}
              onClick={() => handleRemoveCardFromDeck(card.name)}
            />
          ))}
        </div>
      </div>
    );
  };
  
  export default Deck;
  