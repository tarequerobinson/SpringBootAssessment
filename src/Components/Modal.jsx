import React from 'react';

const Modal = ({ deckList, onSelectDeck }) => {
  return (
    <div className="modal">
      <div className="modal-content">
        <h2>Deck List</h2>
        {deckList.map(deck => (
          <button key={deck.id} onClick={() => onSelectDeck(deck.id)}>
            {deck.name}
          </button>
        ))}
        <button onClick={() => onSelectDeck(null)}>Cancel</button>
      </div>
    </div>
  );
};

export default Modal;
