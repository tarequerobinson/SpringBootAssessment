import React, { createContext, useState , useEffect } from "react";
// import { saveDecksToLocalStorage, getDecksFromLocalStorage } from '../Components/localStorageUtils';


const DeckContext = createContext();
 const getDecksFromLocalStorage = JSON.parse(localStorage.getItem("decks"));

export function DeckProvider({ children }) {
  const [decks, setDecks] = useState([]);


  useEffect(() => {

    localStorage.setItem("decks", JSON.stringify(decks));
  }, [decks]);




  // Function to add a card to a specific deck
  const addCardToDeck = (deckId, card) => {
    // Find the deck with the given deckId
    const deckIndex = decks.findIndex((deck) => deck.id === deckId);

    if (deckIndex !== -1) {
      // Make a shallow copy of the decks array
      const updatedDecks = [...decks];

      // Add the card to the deck
      updatedDecks[deckIndex].cards.push(card);

      // Update the state with the new decks array
      setDecks(updatedDecks);
      // saveDecksToLocalStorage(decks);

    }
  };

  // Function to create a new deck
  const createDeck = (deckName , deckColor) => {
    const newDeck = {
      id: Date.now(), 
      name: deckName,
      color: deckColor,

      cards: [],
    };


    // Make a shallow copy of the decks array
    const updatedDecks = [...decks, newDeck];

    // Update the state with the new decks array
    setDecks(updatedDecks);
  };



    // Function to remove a  deck

    const removeDeck = (id) => {
        const updatedDecks = decks.filter((deck) => deck.id !== id);
        setDecks(updatedDecks);
      };
      


  // The value that will be provided to the context consumers
  const contextValue = {
    decks,
    addCardToDeck,
    createDeck,
    removeDeck,
  };

  return <DeckContext.Provider value={contextValue}>{children}</DeckContext.Provider>;
}

export default DeckContext;
