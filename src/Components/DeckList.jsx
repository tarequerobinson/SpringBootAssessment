import React, { useState , useContext } from 'react';
import Deck from './Deck';
import Modal from 'react-modal';
import CardList from './CardList.jsx';
import DeckContext from '../Context/DeckContext';
import {ReactComponent as DeckTypeIcon1}  from '../Icons/Property 1=Factions, Property 2=Galatic Empire.svg';
import {ReactComponent as DeckTypeIcon2} from '../Icons/Property 1=Factions, Property 2=Jedi Order.svg';
import {ReactComponent as DeckTypeIcon3}  from '../Icons/Property 1=Factions, Property 2=Rebel Alliance.svg';



const DeckList = () => {


  const { decks, addCardToDeck, createDeck , removeDeck} = useContext(DeckContext);






  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newDeckName, setNewDeckName] = useState('');
  const [selectedDeckType, setSelectedDeckType] = useState('type1'); // Default deck type



  const handleDeckTypeChange = (deckType) => {
    setSelectedDeckType(deckType);
  };


  



  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setNewDeckName('');
  };

  const handleAddDeck = () => {
    if (newDeckName.trim() === '') return; // Don't add empty deck names
    const deckColor =
    selectedDeckType === 'type1'
      ? 'rgb(64, 163, 64)'
      : selectedDeckType === 'type2'
      ? 'rgb(252, 206, 5)'
      : ''; 


  createDeck(newDeckName, deckColor);
  closeModal();
  };

  const handleRemoveDeck = (deckId) => {
    removeDeck(deckId); // Call the removeDeck function from DeckContext and pass the deckId
 
 
  };



  const handleInputChange = (event) => {
    setNewDeckName(event.target.value);
  };


  return (
    <div>
            <button onClick={openModal}>Add Deck</button>

      {decks.map((deck) => (
        <Deck
          key={deck.id}
          deckName={deck.name}
          totalCards={deck.totalCards}
          cards={deck.cards} 
          selectedDeckType={selectedDeckType} 
          onClick={() => handleRemoveDeck(deck.id)}
        />
      ))}



      {/* <CardList onAddCardToDeck={handleAddCardToDeck} /> */}

      {/* Add Deck modal */}
      <Modal
        isOpen={isModalOpen}
        onRequestClose={closeModal}
        contentLabel="Add Deck Modal"
      >
        <div>
          <h2>Add a New Deck</h2>
          <div>
            <input
              type="text"
              value={newDeckName}
              onChange={handleInputChange}
              placeholder="Deck Name"
            />
          </div>

<div className="deckTypeIcons">








            <DeckTypeIcon1
              selected={selectedDeckType === 'type1'}
              onClick={() => handleDeckTypeChange('type1')}
            />
            <DeckTypeIcon2
              selected={selectedDeckType === 'type2'}
              onClick={() => handleDeckTypeChange('type2')}
            />
             <DeckTypeIcon3
              selected={selectedDeckType === 'type3'}
              onClick={() => handleDeckTypeChange('type3')}
            />
          </div>



          
          <button onClick={handleAddDeck}>Add</button>
          <button onClick={closeModal}>Cancel</button>
        </div>
      </Modal>
    </div>
  );
};


export default DeckList;






